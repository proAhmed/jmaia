package droidahmed.com.jm3eia.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.adapter.EndlessListView;
import droidahmed.com.jm3eia.adapter.ProGridAdapter;
import droidahmed.com.jm3eia.adapter.RelatedAdapter;
import droidahmed.com.jm3eia.api.AddCartItem;
import droidahmed.com.jm3eia.api.AddCartItemAuth;
import droidahmed.com.jm3eia.api.GetRelated;
import droidahmed.com.jm3eia.api.SearchProducts;
import droidahmed.com.jm3eia.controller.DatabaseHelper;
import droidahmed.com.jm3eia.controller.Keys;
import droidahmed.com.jm3eia.controller.OnAddItem;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.OnItemListener;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.AllProducts;
import droidahmed.com.jm3eia.model.CartCheck;
import droidahmed.com.jm3eia.model.CartItemResponse;
import droidahmed.com.jm3eia.model.CartQuantity;
import droidahmed.com.jm3eia.model.ItemAddedAlready;
import droidahmed.com.jm3eia.model.ItemJson;
import droidahmed.com.jm3eia.model.MainApi;
import droidahmed.com.jm3eia.model.Pagination;
import droidahmed.com.jm3eia.model.ProductCart;
import droidahmed.com.jm3eia.model.ResponseChangeUserData;
import droidahmed.com.jm3eia.start.MainActivity;
import droidahmed.com.jm3eia.start.SaveAuth;

/**
 * Created by ahmed on 3/15/2016.
 */
public class FragmentProductDetails extends Fragment implements OnItemListener, OnCartListener, OnAddItem {
    RecyclerView gridView;
    ImageView imgProduct;
    ImageView imgSearch;
    OnItemListener onItemListener;
    ArrayList<AllProducts> arrayMain, proSearch;
    static int page = 1, pageSearch = 1, loading = 0;
    ArrayList<Pagination> paginationArrayList;

    TextView tvName, tvCode, tvBrand, tvCategory, item_change;
    ArrayList<CartQuantity> cartItems;
    ArrayList<ProductCart> productCart;
    static double pricess;
    HashSet<AllProducts> baseHashSet;
    HashSet<ItemJson> itemHashSet;
    SaveAuth saveAuth;
    boolean add;
    ArrayList<CartQuantity> cartItemsModify, cartItemsModifySearch;
    private OnProcessCompleteListener ProductListener;
    ArrayList<CartQuantity> cartItemsDeleted;
    MainApi mainApi, mainSearch;
    ArrayList<AllProducts> arrayListSearch;
    ArrayList<CartQuantity> cartItemsResult;
    OnCartListener onCartListener;
    RelatedAdapter proGridAdapter;
    RelativeLayout reCart;

    boolean searched;
    EditText edSearch, edNumber;
    ImageView imgDelete, imgAdd;
    LinearLayout imgCart;
    int items = 1;
    double id;
    boolean watched;
    ArrayList<ItemAddedAlready> itemAddedAlreadies;
    DatabaseHelper databaseHelper;
    int checkAdds = 0;
    int checkEnter = 0;
    boolean check, checkLoad, checkSearch;
    OnAddItem onAddItem;
    Utility utility;
    TextView tvNum;

    private void declaration() {
        cartItems = new ArrayList<>();
        arrayListSearch = new ArrayList<>();
        cartItemsModifySearch = new ArrayList<>();
        onItemListener = this;
        productCart = new ArrayList<>();
        baseHashSet = new HashSet<>();
        itemHashSet = new HashSet<>();
        cartItemsModify = new ArrayList<>();
        cartItemsDeleted = new ArrayList<>();
        itemAddedAlreadies = new ArrayList<>();
        proSearch = new ArrayList<>();
        arrayMain = new ArrayList<>();
        saveAuth = (SaveAuth) getActivity().getApplicationContext();
        cartItemsResult = new ArrayList<>();
        paginationArrayList = new ArrayList<>();
    }

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_details, container, false);
        gridView = (RecyclerView) view.findViewById(R.id.grid);
        gridView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        tvNum = (TextView) view.findViewById(R.id.tvNum);
        reCart = (RelativeLayout) view.findViewById(R.id.reCart);


        onCartListener = (OnCartListener) this;
        declaration();
        Bundle bundle = getArguments();
        id = bundle.getDouble("item_id");
        utility = new Utility();
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "roboto-thin.ttf");

        edNumber = (EditText) view.findViewById(R.id.edNumber);
        item_change = (TextView) view.findViewById(R.id.item_change);


        final CartQuantity allProducts = (CartQuantity) bundle.getSerializable("products");
        databaseHelper = new DatabaseHelper(getActivity());
        addNum();

        assert allProducts != null;

        try {
            if (databaseHelper.getCartItemAdd(allProducts.getID()) != null) {
                if (Locale.getDefault().getDisplayLanguage().equals("العربية")) {
                    edNumber.setText("" + utility.arabicNumaricCode(String.valueOf(databaseHelper.getCartItemAdd(allProducts.getID()).getcQuantity())));
                } else {
                    edNumber.setText("" + databaseHelper.getCartItemAdd(allProducts.getID()).getcQuantity());

                }
            } else {
                if (Locale.getDefault().getDisplayLanguage().equals("العربية")) {
                    edNumber.setText("" + utility.arabicNumaricCode(String.valueOf(1)));

                } else {
                    edNumber.setText("" + 1);
                }
            }
        } catch (Exception e) {

        }
        try {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH && android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            if ( Locale.getDefault().getDisplayLanguage().equals("العربية")) {
//                gridView.setHorizontalSpacing((int) -2);
//            }else{
//                gridView.setHorizontalSpacing((int) 2);
//
//            }
            }
            onAddItem = this;
            imgDelete = (ImageView) view.findViewById(R.id.imgDelete);
            imgAdd = (ImageView) view.findViewById(R.id.imgAdd);
            imgCart = (LinearLayout) view.findViewById(R.id.imgCart);
            imgCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (databaseHelper.getItem(allProducts.getID()) != null) {
                            CartCheck cartCheck = databaseHelper.getItem(allProducts.getID());
                            checkAdds = cartCheck.getAdd();
                            checkEnter = cartCheck.getEnter();
                        } else {
                            checkAdds = 0;
                            checkEnter = 0;
                        }
                        item_change.setText(getResources().getString(R.string.see_cart));
                        imgCart.setBackgroundDrawable(getResources().getDrawable(R.drawable.market_bar));
                    } catch (Exception e) {

                    }


                    try {
                        if (!new StoreData(getActivity()).getAuthName().equals("")) {
                            ItemJson itemJson = new ItemJson(allProducts.getID(), allProducts.getcQuantity(), Utility.getCurrentTimeStamp());
                            if (checkAdds == 0) {
                                if (databaseHelper.getItem(allProducts.getID()) != null) {
                                    allProducts.setSeen(1);
                                    databaseHelper.updateCart(allProducts);

                                    databaseHelper.updateToDo(allProducts, 1, 1);
                                } else {
                                    databaseHelper.createAdd(allProducts, 1, 1);
                                    databaseHelper.createCart(allProducts);
                                    addNum();

                                }
                                addAuth(itemJson);
                                checkAdd(true);
                                check = true;
                                setAdd(true);
                            } else if (check) {
                                if (new StoreData(getActivity()).getAuthName().equals("")) {
                                    FragmentProductCart fragment = new FragmentProductCart();
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("cart", productCart);
                                    bundle.putDouble("price", allProducts.getPrice());
                                    databaseHelper.updateToDo(allProducts, 2, 2);

                                    fragment.setArguments(bundle);
                                    getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                                            .replace(R.id.mainFragment, fragment).commit();
                                } else {
                                    AddAuth(itemHashSet);
                                    FragmentProductCart fragment = new FragmentProductCart();
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("cart", productCart);
                                    bundle.putString("login", "login");
                                    bundle.putDouble("price", allProducts.getPrice());
                                    databaseHelper.updateToDo(allProducts, 2, 2);

                                    fragment.setArguments(bundle);
                                    getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                                            .replace(R.id.mainFragment, fragment).commit();
                                }
                            } else {
                                Toast.makeText(getActivity(), getResources().getString(R.string.find_cart), Toast.LENGTH_LONG).show();

                            }
                        } else {


                            if (checkAdds == 0) {
                                if (databaseHelper.getItem(allProducts.getID()) != null) {
                                    allProducts.setSeen(1);
                                    databaseHelper.updateCart(allProducts);
                                    databaseHelper.updateToDo(allProducts, 1, 1);
                                    addNum();
                                } else {
                                    databaseHelper.createAdd(allProducts, 1, 1);

                                }
                                allProducts.setSeen(1);

                                databaseHelper.createCart(allProducts);
                                addNum();
                            } else if (databaseHelper.getItem(allProducts.getID()).getAdd() == 1) {
                                if (new StoreData(getActivity()).getAuthName().equals("")) {
                                    FragmentProductCart fragment = new FragmentProductCart();
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("cart", productCart);
                                    bundle.putDouble("price", pricess);
                                    databaseHelper.updateToDo(allProducts, 2, 2);

                                    fragment.setArguments(bundle);
                                    getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                                            .replace(R.id.mainFragment, fragment).commit();
                                } else {
                                    AddAuth(itemHashSet);
                                    FragmentProductCart fragment = new FragmentProductCart();
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("cart", productCart);
                                    bundle.putString("login", "login");
                                    bundle.putDouble("price", pricess);
                                    databaseHelper.updateToDo(allProducts, 2, 2);

                                    fragment.setArguments(bundle);
                                    getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                                            .replace(R.id.mainFragment, fragment).commit();
                                }

                            } else if (databaseHelper.getItem(allProducts.getID()).getAdd() == 2) {
                                Toast.makeText(getActivity(), getResources().getString(R.string.find_cart), Toast.LENGTH_LONG).show();

                            }

                        }
                    } catch (Exception e) {

                    }
                }
            });
        } catch (Exception e) {

        }
        try {
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (items > 1) {
                        --items;
                        if (!edNumber.getText().toString().equals(""))
                            if (Locale.getDefault().getDisplayLanguage().equals("العربية")) {

                                edNumber.setText("" + utility.arabicNumaricCode(String.valueOf(items)));
                            } else {
                                edNumber.setText("" + items);
                            }
                        if (databaseHelper.getCartItemAdd(allProducts.getID()) != null) {
                            allProducts.setcQuantity(Integer.parseInt(edNumber.getText().toString()));
                            databaseHelper.updateCartAdd(allProducts);
                            databaseHelper.updateCart(allProducts);
                            addNum();
                        } else {
                            databaseHelper.createCartAdd(allProducts);
                            databaseHelper.createCart(allProducts);
                            addNum();
                        }
                        assert allProducts != null;
                        allProducts.setcQuantity(items);
                        saveAuth.setCartQuan(cartItemsModify);
                        if (saveAuth.getItemAdded() != null)
                            if (saveAuth.getItemAdded().size() > 0) {
                                for (int i = 0; i < saveAuth.getItemAdded().size(); i++) {
                                    if (allProducts.getID() == (saveAuth.getItemAdded().get(i).getId())) {
                                        saveAuth.getItemAdded().get(i).setNum(items);
                                    } else {
                                        itemAddedAlreadies.add(new ItemAddedAlready(allProducts.getID(), allProducts.getName(), items));

                                    }
                                }
                            } else {
                                itemAddedAlreadies.add(new ItemAddedAlready(allProducts.getID(), allProducts.getName(), items));

                            }
                        saveAuth.setItemAdded(itemAddedAlreadies);

                    }

                }
            });
        } catch (Exception e) {

        }

        try {
            imgAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!edNumber.getText().toString().equals(""))
                        if (Integer.parseInt(edNumber.getText().toString()) == 1) {
                            ++items;
                        } else {
                            items = Integer.parseInt(edNumber.getText().toString());
                            ++items;
                        }

                    edNumber.setText("" + items);

                    if (databaseHelper.getCartItemAdd(allProducts.getID()) != null) {
                        allProducts.setcQuantity(Integer.parseInt(edNumber.getText().toString()));
                        allProducts.setAdded(1);
                        databaseHelper.updateCartAdd(allProducts);
                        databaseHelper.updateCart(allProducts);
                        addNum();
                    } else {
                        allProducts.setcQuantity(Integer.parseInt(edNumber.getText().toString()));
                        allProducts.setAdded(1);
                        databaseHelper.createCartAdd(allProducts);
                        databaseHelper.createCart(allProducts);
                        addNum();
                    }
                    assert allProducts != null;
                    allProducts.setcQuantity(items);
                    saveAuth.setCartQuan(cartItemsModify);
                    if (saveAuth.getItemAdded() != null)
                        if (saveAuth.getItemAdded().size() > 0) {
                            for (int i = 0; i < saveAuth.getItemAdded().size(); i++) {
                                if (allProducts.getID() == (saveAuth.getItemAdded().get(i).getId())) {
                                    saveAuth.getItemAdded().get(i).setNum(items);
                                } else {
                                    itemAddedAlreadies.add(new ItemAddedAlready(allProducts.getID(), allProducts.getName(), items));

                                }
                            }
                        } else {
                            itemAddedAlreadies.add(new ItemAddedAlready(allProducts.getID(), allProducts.getName(), items));

                        }
                    saveAuth.setItemAdded(itemAddedAlreadies);
                }
            });
        } catch (Exception e) {

        }

        try {
            imgSearch = (ImageView) view.findViewById(R.id.imgSearch);
            edSearch = (EditText) view.findViewById(R.id.edSearch);
            imgSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!edSearch.getText().toString().equals(""))
                        if (Utility.isNetworkConnected(getActivity())) {
                            ProductListener = new OnProcessCompleteListener() {

                                @Override
                                public void onSuccess(Object result) {
                                    mainSearch = (MainApi) result;
                                    proSearch = mainSearch.getData();
                                    cartItemsModifySearch.clear();
                                    if (proSearch.size() > 0) {
                                        checkSearch = true;
                                    } else {
                                        checkSearch = false;
                                    }
                                    for (int i = 0; i < proSearch.size(); i++) {

                                        AllProducts allProducts = proSearch.get(i);
                                        CartQuantity cartItem = new CartQuantity(allProducts.getID(), allProducts.getCode(), allProducts.getCategoryID(),
                                                allProducts.getBrandID(), allProducts.getPrice(), allProducts.getQuantity(), allProducts.getPicture(), allProducts.getSliderPictures(),
                                                allProducts.getCreatedDate(), allProducts.getModifiedDate(), allProducts.getViewed(), allProducts.getFeatured(), allProducts.getState(),
                                                allProducts.getProductID(), allProducts.getLanguageID(), allProducts.getName(), allProducts.getAlias(),
                                                allProducts.getContents(), allProducts.getDescription(), allProducts.getKeywords(), allProducts.getCategoryName(), allProducts.getBrandName(),
                                                1);

                                        cartItemsModifySearch.add(cartItem);
                                        //    saveAuth.setCartQuan(cartItemsModify);

                                    }
                                    try {
                                        if (databaseHelper.getCartAdd() != null) {
                                            for (int i = 0; i < cartItemsModifySearch.size(); i++) {
                                                for (int ii = 0; ii < databaseHelper.getCartAdd().size(); ii++) {
                                                    if (databaseHelper.getCartAdd().get(ii).getID() == cartItemsModifySearch.get(i).getID()) {
                                                        cartItemsModifySearch.get(i).setAdded(databaseHelper.getCartAdd().get(ii).getAdded());
                                                    }
                                                }
                                            }
                                        }
                                    } catch (Exception e) {

                                    }

                                    try {
                                        if (databaseHelper.getCart() != null) {
                                            for (int i = 0; i < cartItemsModifySearch.size(); i++) {
                                                for (int ii = 0; ii < databaseHelper.getCart().size(); ii++) {
                                                    if (databaseHelper.getCart().get(ii).getID() == cartItemsModifySearch.get(i).getID()) {
                                                        cartItemsModifySearch.get(i).setSeen(databaseHelper.getCart().get(ii).getSeen());
                                                        cartItemsModifySearch.get(i).setcQuantity(databaseHelper.getCart().get(ii).getcQuantity());
                                                    }
                                                }
                                            }
                                        }
                                    } catch (Exception e) {

                                    }
                                    //     proGridAdapter.clear();
                                    //   gridView.addNewData(cartItemsModifySearch,false);

                                    proGridAdapter = new RelatedAdapter(getActivity(), R.layout.main_items_details, cartItemsModifySearch, onItemListener, onCartListener, itemAddedAlreadies, onAddItem);
                                    gridView.setAdapter(proGridAdapter);

                                    setSearched(true);
                                    ++pageSearch;
//                    }

                                    try {
                                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                                        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                                    } catch (Exception e) {
                                        // TODO: handle exception
                                    }
                                }

                                @Override
                                public void onFailure() {
                                    Utility.showValidateDialog(
                                            getResources().getString(R.string.failure_search),
                                            getActivity());
                                }
                            };

                            SearchProducts task = new SearchProducts(getActivity(), ProductListener, edSearch.getText().toString(), "" + 1);
                            task.execute();
                        } else {
                            Utility.showValidateDialog(
                                    getResources().getString(R.string.failure_search),
                                    getActivity());
                        }
                }
            });
            initSearchView(edSearch);
        } catch (Exception e) {

        }

        tvName = (TextView) view.findViewById(R.id.proName);
        tvName.setTypeface(type);

        tvCode = (TextView) view.findViewById(R.id.proCode);
        tvCategory = (TextView) view.findViewById(R.id.proCategory);
        tvBrand = (TextView) view.findViewById(R.id.proBrand);
        imgProduct = (ImageView) view.findViewById(R.id.imgPro);
        try {
        if (Utility.isNetworkConnected(getActivity())) {

            ProductListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                    mainApi = (MainApi) result;
                    arrayMain = mainApi.getData();
                    if (arrayMain.size() > 0) {
                        checkLoad = true;
                    } else {
                        checkLoad = false;
                    }

//                               Fragment fragmentProduct = new FragmentProductDetails();
//                    FragmentManager fm = getSupportFragmentManager();
//                    FragmentTransaction ft = fm.beginTransaction();
                    //                    ft.replace(R.id.mainFragment, fragmentProduct);
//                    ft.commit();h

                    for (int i = 0; i < arrayMain.size(); i++) {

                        AllProducts allProducts = arrayMain.get(i);
                        CartQuantity cartItem = new CartQuantity(allProducts.getID(), allProducts.getCode(), allProducts.getCategoryID(),
                                allProducts.getBrandID(), allProducts.getPrice(), allProducts.getQuantity(), allProducts.getPicture(), allProducts.getSliderPictures(),
                                allProducts.getCreatedDate(), allProducts.getModifiedDate(), allProducts.getViewed(), allProducts.getFeatured(), allProducts.getState(),
                                allProducts.getProductID(), allProducts.getLanguageID(), allProducts.getName(), allProducts.getAlias(),
                                allProducts.getContents(), allProducts.getDescription(), allProducts.getKeywords(), allProducts.getCategoryName(), allProducts.getBrandName(),
                                1);

                        cartItemsModify.add(cartItem);

                    }
                    if (saveAuth.getItemAdded() != null && saveAuth.getItemAdded().size() > 0) {
                        for (int i = 0; i < cartItemsModify.size(); i++) {
                            for (int ii = 0; ii < saveAuth.getItemAdded().size(); ii++) {
                                if (saveAuth.getItemAdded().get(ii).getId() == cartItemsModify.get(i).getID()) {
                                    cartItemsModify.get(i).setcQuantity(saveAuth.getItemAdded().get(ii).getNum());
                                }
                            }
                        }
                    }
                    try {
                        if (databaseHelper.getCartAdd() != null) {
                            for (int i = 0; i < cartItemsModify.size(); i++) {
                                for (int ii = 0; ii < databaseHelper.getCartAdd().size(); ii++) {
                                    if (databaseHelper.getCartAdd().get(ii).getID() == cartItemsModify.get(i).getID()) {
                                        cartItemsModify.get(i).setcQuantity(databaseHelper.getCartAdd().get(ii).getcQuantity());
                                        cartItemsModify.get(i).setAdded(databaseHelper.getCartAdd().get(ii).getAdded());

                                    }
                                }
                            }
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (databaseHelper.getCart() != null) {
                            for (int i = 0; i < cartItemsModify.size(); i++) {
                                for (int ii = 0; ii < databaseHelper.getCart().size(); ii++) {

                                    if (databaseHelper.getCart().get(ii).getID() == cartItemsModify.get(i).getID()) {
                                        cartItemsModify.get(i).setSeen(databaseHelper.getCart().get(ii).getSeen());
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {

                    }
                    proGridAdapter = new RelatedAdapter(getActivity(), R.layout.main_items_details, cartItemsModify, onItemListener, onCartListener, itemAddedAlreadies, onAddItem);
                    gridView.setAdapter(proGridAdapter);
                }

                @Override
                public void onFailure() {
                    Utility.showFailureDialog(getActivity(), false);
                }
            };

            GetRelated task = new GetRelated(getActivity(), ProductListener, id);
            task.execute();

        } else {
            Utility.showValidateDialog(
                    getResources().getString(R.string.failure_ws),
                    getActivity());
        }
        } catch (Exception e) {

        }

        assert allProducts != null;
        tvName.setText(allProducts.getName() + "");
        if (Locale.getDefault().getDisplayLanguage().equals("العربية")) {

            tvCode.setText(utility.arabicNumaricCode(String.valueOf(allProducts.getPrice() + "") + " دك"));
        } else {
            tvCode.setText(allProducts.getPrice() + " دك");

        }
        tvCategory.setText(allProducts.getCategoryName() + "");
//        if(!allProducts.getBrandName().equals("null")){
        tvBrand.setText(allProducts.getBrandName());

//        }

        Picasso.with(getActivity()).load("http://jm3eia.com/" + allProducts.getPicture()).into(imgProduct);


//        } else if(Utility.widthScreen(getActivity())>=760){
//            Picasso.with(getActivity()).load("http://jm3eia.com/" + allProducts.getPicture()).resize(250,225).into(imgProduct);
//
//        }else{
//            Picasso.with(getActivity()).load("http://jm3eia.com/" + allProducts.getPicture()).into(imgProduct);
//
//        }


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().findViewById(R.id.imageToggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.showSecondaryMenu();
                } catch (Exception e) {

                }
            }
        });
        TextView tv = (TextView) getActivity().findViewById(R.id.textTitle);
        tv.setVisibility(View.GONE);
        ImageView img = (ImageView) getActivity().findViewById(R.id.logo);
        img.setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.imageToggleCategory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null)
                    mainActivity.toggle();
            }
        });
    }

    @Override
    public void onClick(int position, boolean isLongClick) {


        Fragment fragmentProduct = new FragmentProductDetails();
//                    FragmentManager fm = getSupportFragmentManager();
//                    FragmentTransaction ft = fm.beginTransaction();
        //                    ft.replace(R.id.mainFragment, fragmentProduct);
//                    ft.commit();h
        Bundle bundle = new Bundle();
        if (isSearched()) {
            bundle.putSerializable("products", cartItemsModifySearch.get(position));
            bundle.putDouble("item_id", cartItemsModifySearch.get(position).getCategoryID());
        } else {


            bundle.putSerializable("products", cartItemsModify.get(position));
            bundle.putDouble("item_id", cartItemsModify.get(position).getCategoryID());

        }
        fragmentProduct.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragment, fragmentProduct).addToBackStack("")
                .commitAllowingStateLoss();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        getActivity().findViewById(R.id.imageToggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.showSecondaryMenu();
                } catch (Exception e) {

                }
            }
        });

        getActivity().findViewById(R.id.imageToggleCategory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null)
                    mainActivity.toggle();
            }
        });

    }

    @Override
    public void onAddCart(CartQuantity cartQuantity, int num, boolean watch, double price) {
        try {
            if (databaseHelper.getItem(cartQuantity.getID()) != null) {
                CartCheck cartCheck = databaseHelper.getItem(cartQuantity.getID());
                checkAdds = cartCheck.getAdd();
                checkEnter = cartCheck.getEnter();
            } else {
                checkAdds = 0;
                checkEnter = 0;
            }
            if (!new StoreData(getActivity()).getAuthName().equals("")) {
                ItemJson itemJson = new ItemJson(cartQuantity.getID(), num, Utility.getCurrentTimeStamp());
                if (checkAdds == 0) {
                    if (databaseHelper.getItem(cartQuantity.getID()) != null) {
                        cartQuantity.setSeen(1);
                        databaseHelper.updateToDo(cartQuantity, 1, 1);
                        databaseHelper.updateCart(cartQuantity);
                    } else {
                        cartQuantity.setSeen(1);
                        databaseHelper.createAdd(cartQuantity, 1, 1);
                        databaseHelper.createCart(cartQuantity);
                        addNum();

                    }
                    addAuth(itemJson);
                    checkAdd(true);
                    check = true;
                    setAdd(true);
                    addNum();
                } else if (check) {
                    if (new StoreData(getActivity()).getAuthName().equals("")) {
                        FragmentProductCart fragment = new FragmentProductCart();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("cart", productCart);
                        bundle.putDouble("price", price);
                        databaseHelper.updateToDo(cartQuantity, 2, 2);

                        fragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                                .replace(R.id.mainFragment, fragment).commit();
                    } else {
                        AddAuth(itemHashSet);
                        FragmentProductCart fragment = new FragmentProductCart();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("cart", productCart);
                        bundle.putString("login", "login");
                        bundle.putDouble("price", price);
                        databaseHelper.updateToDo(cartQuantity, 2, 2);

                        fragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                                .replace(R.id.mainFragment, fragment).commit();
                    }
                }
            } else {


                if (checkAdds == 0) {
                    if (databaseHelper.getItem(cartQuantity.getID()) != null) {
                        cartQuantity.setSeen(1);
                        databaseHelper.updateCart(cartQuantity);

                        databaseHelper.updateToDo(cartQuantity, 1, 1);
                        addNum();
                    } else {

                        databaseHelper.createAdd(cartQuantity, 1, 1);

                    }
                    cartQuantity.setSeen(1);

                    databaseHelper.createCart(cartQuantity);
                    addNum();

                    ItemJson itemJson = new ItemJson(cartQuantity.getID(), num, Utility.getCurrentTimeStamp());

                    if (new StoreData(getActivity()).getAuthName().equals("")) {

                        price += price;

                        if (saveAuth.getItemJsons() != null) {
                            itemHashSet = saveAuth.getItemJsons();

                        }

                        add = itemHashSet.add(itemJson);
//        if (add) {
                        saveAuth.setItemJsons(itemHashSet);
                        checkAdd(true);
                        setAdd(true);
//        } else {
//            Toast.makeText(getActivity(), getResources().getString(R.string.find_cart), Toast.LENGTH_LONG).show();
//
//
//            return;
//        }

                        // productCart.add(new ProductCart(arrayList.get(position), num));
                    } else {
                        addAuth(itemJson);
                        checkAdd(true);
                        setAdd(true);
                    }
                    addNum();
                } else if (databaseHelper.getItem(cartQuantity.getID()).getAdd() == 1) {

                    if (new StoreData(getActivity()).getAuthName().equals("")) {
                        FragmentProductCart fragment = new FragmentProductCart();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("cart", productCart);
                        bundle.putDouble("price", price);
                        databaseHelper.updateToDo(cartQuantity, 2, 2);

                        fragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                                .replace(R.id.mainFragment, fragment).commit();
                    } else {
                        AddAuth(itemHashSet);
                        FragmentProductCart fragment = new FragmentProductCart();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("cart", productCart);
                        bundle.putString("login", "login");
                        bundle.putDouble("price", price);
                        databaseHelper.updateToDo(cartQuantity, 2, 2);

                        fragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                                .replace(R.id.mainFragment, fragment).commit();
                    }

                } else if (databaseHelper.getItem(cartQuantity.getID()).getAdd() == 2) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.find_cart), Toast.LENGTH_LONG).show();

                }
            }
        } catch (Exception e) {

        }
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    private boolean checkAdd(boolean check) {
        return check;
    }

    @Override
    public void add(int num, CartQuantity cartQuantity) {
        assert cartQuantity != null;
        cartQuantity.setcQuantity(num);
        cartQuantity.setAdded(1);
        if (databaseHelper.getCartItemAdd(cartQuantity.getID()) != null) {
            databaseHelper.updateCartAdd(cartQuantity);
            databaseHelper.updateCart(cartQuantity);
            addNum();
        } else {
            databaseHelper.createCartAdd(cartQuantity);
            databaseHelper.createCart(cartQuantity);
            addNum();
        }
        cartQuantity.setcQuantity(items);
        saveAuth.setCartQuan(cartItemsModify);
        if (saveAuth.getItemAdded() != null)
            if (saveAuth.getItemAdded().size() > 0) {
                for (int i = 0; i < saveAuth.getItemAdded().size(); i++) {
                    if (cartQuantity.getID() == (saveAuth.getItemAdded().get(i).getId())) {
                        saveAuth.getItemAdded().get(i).setNum(items);
                    } else {
                        itemAddedAlreadies.add(new ItemAddedAlready(cartQuantity.getID(), cartQuantity.getName(), items));

                    }
                }
            } else {
                itemAddedAlreadies.add(new ItemAddedAlready(cartQuantity.getID(), cartQuantity.getName(), items));

            }
        saveAuth.setItemAdded(itemAddedAlreadies);
    }

    private void addAuth(ItemJson itemJson) {
        if (Utility.isNetworkConnected(getActivity())) {

            ProductListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                    String success = ((ResponseChangeUserData) result).getData();
                    try {
                        Toast.makeText(getActivity(), success, Toast.LENGTH_LONG).show();

                    } catch (Exception e) {

                    }
                    check = ((ResponseChangeUserData) result).isSuccess();

                }

                @Override
                public void onFailure() {
                    Utility.showFailureDialog(getActivity(), false);
                }
            };

            AddCartItemAuth task = new AddCartItemAuth(getActivity(), ProductListener);
            task.execute(itemJson);

        } else {
            Utility.showValidateDialog(
                    getResources().getString(R.string.failure_ws),
                    getActivity());
        }
    }

    private void AddAuth(HashSet hashSet) {
        if (Utility.isNetworkConnected(getActivity())) {

            ProductListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                    CartItemResponse cartItemResponse = (CartItemResponse) result;

                    cartItemResponse.getData();

                }

                @Override
                public void onFailure() {
                    Utility.showFailureDialog(getActivity(), false);
                }
            };

            AddCartItem task = new AddCartItem(getActivity(), ProductListener);
            task.execute(hashSet);

        } else {
            Utility.showValidateDialog(
                    getResources().getString(R.string.failure_ws),
                    getActivity());
        }
    }

    ///////////  search //////////////////

    private void initSearchView(EditText edSearch) {
        try {

            edSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    try {


                        String myTextString = editable.toString().toLowerCase();

                        search(myTextString);


                        if (myTextString.equals("")) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //    ent = null;
                                            cartItemsResult.clear();
                                            gridView.setAdapter(new RelatedAdapter(getActivity(), R.layout.main_items, cartItemsModifySearch, onItemListener, onCartListener, itemAddedAlreadies, onAddItem));


                                        }

                                    });
                                }
                            }).start();
                        }


                    } catch (Exception ignored) {


                    }
                }
            });
        } catch (Exception ignored) {


        }
    }

    private void search(final String text) {
        try {
            cartItemsResult.clear();

            for (int i = 0; i < cartItemsModify.size(); i++) {
                if (cartItemsModify.get(i).getName().toLowerCase().contains(text)) {

                    cartItemsResult.add(cartItemsModify.get(i));

                }

            }

            if (cartItemsResult.size() > 0) {


            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        cartItemsResult.clear();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }


                        });
                    }
                }).start();
            }
        } catch (Exception ignored) {

        }


    }

    public boolean isSearched() {
        return searched;
    }

    public void setSearched(boolean searched) {
        this.searched = searched;
    }


    private class LoaderSearch extends AsyncTask<String, Void, MainApi> {
        private ProgressBar progressBar;

        String finalUrl;
        private Context context;
        String responseJSON;

        public LoaderSearch(Context context, String word, String num) {
            this.context = context;

            String query;
            try {
                query = URLEncoder.encode(word, "utf-8");
                finalUrl = "https://jm3eia.com/API/ar/product/search?q=" + query + "&page=" + num;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onPreExecute() {
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected MainApi doInBackground(String... params) {

            MainApi obj = null;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                responseJSON = invokeJSONWS();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Gson gson = new Gson();
            if (responseJSON != null && responseJSON.length() > 1) {

                GsonBuilder gb = new GsonBuilder();
                gb.serializeNulls();
                gson = gb.create();
                try {
                    obj = gson.fromJson(responseJSON, MainApi.class);
                } catch (com.google.gson.JsonSyntaxException ex) {
                    ex.printStackTrace();

                }

            }
            return obj;
        }

        @Override
        protected void onPostExecute(MainApi responseHome) {
            super.onPostExecute(responseHome);
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (progressBar != null) {
                progressBar.setVisibility(View.GONE);
            }
            mainApi = (MainApi) responseHome;
            arrayMain = mainApi.getData();
            if (arrayMain.size() > 0) {
                checkSearch = true;

                ArrayList<CartQuantity> cartQuantities = new ArrayList<>();
                for (int i = 0; i < arrayMain.size(); i++) {

                    AllProducts allProducts = arrayMain.get(i);
                    CartQuantity cartItem = new CartQuantity(allProducts.getID(), allProducts.getCode(), allProducts.getCategoryID(),
                            allProducts.getBrandID(), allProducts.getPrice(), allProducts.getQuantity(), allProducts.getPicture(), allProducts.getSliderPictures(),
                            allProducts.getCreatedDate(), allProducts.getModifiedDate(), allProducts.getViewed(), allProducts.getFeatured(), allProducts.getState(),
                            allProducts.getProductID(), allProducts.getLanguageID(), allProducts.getName(), allProducts.getAlias(),
                            allProducts.getContents(), allProducts.getDescription(), allProducts.getKeywords(), allProducts.getCategoryName(), allProducts.getBrandName(),
                            1);
                    cartQuantities.add(cartItem);

                    cartItemsModifySearch.add(cartItem);
                }
                try {
                    if (databaseHelper.getCartAdd() != null) {
                        for (int i = 0; i < cartQuantities.size(); i++) {
                            for (int ii = 0; ii < databaseHelper.getCartAdd().size(); ii++) {
                                if (databaseHelper.getCartAdd().get(ii).getID() == cartQuantities.get(i).getID()) {
                                    cartQuantities.get(i).setAdded(databaseHelper.getCartAdd().get(ii).getAdded());
                                }
                            }
                        }
                    }
                } catch (Exception e) {

                }
                try {
                    if (databaseHelper.getCart() != null) {
                        for (int i = 0; i < cartQuantities.size(); i++) {
                            for (int ii = 0; ii < databaseHelper.getCart().size(); ii++) {
                                if (databaseHelper.getCart().get(ii).getID() == cartQuantities.get(i).getID()) {
                                    cartQuantities.get(i).setSeen(databaseHelper.getCart().get(ii).getSeen());
                                    cartQuantities.get(i).setcQuantity(databaseHelper.getCart().get(ii).getcQuantity());
                                }
                            }
                        }
                    }
                } catch (Exception e) {

                }

//
//                proGridAdapter.addAll(cartQuantities);
                proGridAdapter = new RelatedAdapter(getActivity(), R.layout.main_items_details, cartQuantities, onItemListener, onCartListener, itemAddedAlreadies, onAddItem);

                proGridAdapter.notifyDataSetChanged();
//            gridView.addNewData(cartQuantities,true);
            } else {
                checkSearch = false;

                //    gridView.addNewData(cartItemsModifySearch,false);
            }
        }

        private String invokeJSONWS() throws IOException {
            HttpURLConnection httpConn = null;

            InputStream in = null;
            int response = -1;
            String responseJSON;
            java.net.URL url = new URL(finalUrl);
            URLConnection conn = url.openConnection();
            if (!(conn instanceof HttpURLConnection))
                throw new IOException("Not an HTTP connection");

            try {
                httpConn = (HttpURLConnection) conn;
                httpConn.setRequestMethod("GET");
                httpConn.setConnectTimeout(Keys.TIMEOUT);
                httpConn.setDoInput(true);
                httpConn.setDoOutput(true);

                httpConn.connect();

                response = httpConn.getResponseCode();

                if (response == HttpURLConnection.HTTP_OK) {
                    in = conn.getInputStream();
                }

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(in));
                StringBuilder out = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    out.append(line);
                }

                responseJSON = out.toString();

            } catch (Exception e) {
                throw new IOException("Error connecting");
            } finally {
                httpConn.disconnect();
            }
            return responseJSON;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setListViewHeightBasedOnChildren(EndlessListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, AbsListView.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getVerticalSpacing() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    int value;

    private void addNum() {
        if (databaseHelper.getCart() != null) {
            value = databaseHelper.getCart().size();

        if (value != 0) {
            tvNum.setText(value + "");
            reCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .add(R.id.mainFragment, new FragmentProductCart(), "").addToBackStack("").commit();
                }
            });
        }
    }
    }
}
