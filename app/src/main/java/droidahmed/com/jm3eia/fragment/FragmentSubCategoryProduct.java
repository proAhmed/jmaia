package droidahmed.com.jm3eia.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.adapter.EndlessAdapter;
import droidahmed.com.jm3eia.adapter.EndlessListView;
import droidahmed.com.jm3eia.adapter.ProGridAdapter;
import droidahmed.com.jm3eia.api.AddCartItem;
import droidahmed.com.jm3eia.api.AddCartItemAuth;
import droidahmed.com.jm3eia.api.GetProductByCategory;
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
import droidahmed.com.jm3eia.model.CartItem;
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
public class FragmentSubCategoryProduct extends Fragment implements OnItemListener, OnCartListener, OnAddItem, EndlessListView.EndlessListener {
    EndlessListView lstProduct;
    EditText edSearch;

    private OnProcessCompleteListener ProductListener;
    MainApi mainApi, mainSearch;
    ImageView imgSearch;
    ArrayList<CartItem> cartItems;
    ArrayList<AllProducts> arrayList, arrayListSearch;
    ArrayList<ProductCart> productCart;
    static double pricesss;
    HashSet<AllProducts> baseHashSet;
    HashSet<ItemJson> itemHashSet;
    int id;
    SaveAuth saveAuth;
    boolean add;
    ArrayList<ItemAddedAlready> itemAddedAlreadies;
    OnItemListener onItemListener;
    OnCartListener onCartListener;
    OnAddItem onAddItem;
    ArrayList<CartQuantity> cartItemsModify, cartItemsModifySearch;
    ArrayList<CartQuantity> cartItemsDeleted;
    ArrayList<CartQuantity> cartItemsResult;
    boolean searched;
    DatabaseHelper databaseHelper;
    int checkAdds = 0;
    int checkEnter = 0;
    boolean check, checkLoad, checkSearch;
    static int page = 1, pageSearch = 1, loading = 0;
    ArrayList<AllProducts> arrayMain, proSearch;
    ProGridAdapter proGridAdapter;
    View view;
    ArrayList<Pagination> paginationArrayList;
    ArrayList<Integer> integers;
    ArrayList<Integer> integerArrayList;
    private ProgressBar progressBar;
    TextView tvNum;
    RelativeLayout reCart;

    private void declaration() {
        cartItems = new ArrayList<>();
        productCart = new ArrayList<>();
        baseHashSet = new HashSet<>();
        arrayListSearch = new ArrayList<>();
        itemHashSet = new HashSet<>();
        itemAddedAlreadies = new ArrayList<>();
        cartItemsModify = new ArrayList<>();
        cartItemsDeleted = new ArrayList<>();
        cartItemsResult = new ArrayList<>();
        cartItemsModifySearch = new ArrayList<>();
        proSearch = new ArrayList<>();
        arrayMain = new ArrayList<>();
        integers = new ArrayList<>();
        integerArrayList = new ArrayList<>();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product, container, false);
        lstProduct = (EndlessListView) view.findViewById(R.id.lstProduct);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        reCart = (RelativeLayout) view.findViewById(R.id.reCart);
        lstProduct.setListener(this);
        lstProduct.setListener();
        arrayList = new ArrayList<>();
        onItemListener = this;
        onCartListener = this;
        onAddItem = this;
        tvNum = (TextView) view.findViewById(R.id.tvNum);
        databaseHelper = new DatabaseHelper(getActivity());
        addNum();
        StrictMode.enableDefaults();
        declaration();

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH && android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if (Locale.getDefault().getDisplayLanguage().equals("العربية")) {
                lstProduct.setHorizontalSpacing((int) -2);
            } else {
                lstProduct.setHorizontalSpacing((int) 2);

            }
        }
        imgSearch = (ImageView) view.findViewById(R.id.imgSearch);
        edSearch = (EditText) view.findViewById(R.id.edSearch);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!edSearch.getText().toString().equals(""))
                        if (Utility.isNetworkConnected(getActivity())) {
                            ProductListener = new OnProcessCompleteListener() {

                                @Override
                                public void onSuccess(Object result) {
                                    mainSearch = (MainApi) result;
                                    proSearch = mainSearch.getData();
                                    if (proSearch.size() > 0) {
                                        checkSearch = true;
                                    } else {
                                        checkSearch = false;
                                    }
                                    cartItemsModifySearch.clear();
                                    Pagination[] paginations = mainSearch.getPages();
                                    paginationArrayList = new ArrayList<>(Arrays.asList(paginations));

                                    integers.clear();
                                    try {
                                        for (int i = 2; paginationArrayList.size() > 0; i++) {
                                            integers.add(Integer.parseInt(paginationArrayList.get(i).getTitle()));
                                        }

                                    } catch (Exception e) {

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
                                    proGridAdapter.clear();
                                    proGridAdapter = new ProGridAdapter(getActivity(), R.layout.main_items, cartItemsModifySearch, onItemListener, onCartListener, itemAddedAlreadies, onAddItem);
                                    lstProduct.addNewData(cartItemsModifySearch, false);

                                    lstProduct.setAdapter(proGridAdapter);
                                    //    ++page;
                                    ++pageSearch;
                                    setSearched(true);
                                    searched = true;
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
                } catch (Exception e) {

                }
            }
        });
        initSearchView(edSearch);
        saveAuth = (SaveAuth) getActivity().getApplicationContext();
        Bundle bundle = getArguments();
        databaseHelper = new DatabaseHelper(getActivity());
        addNum();
        try {
            id = bundle.getInt("id");
        } catch (Exception e) {

        }

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
                        Pagination[] paginations = mainApi.getPages();
                        integerArrayList.clear();
                        if (paginations != null) {
                            if (paginations.length > 0) {
                                ArrayList<Pagination> paginationArrayList = new ArrayList<>(Arrays.asList(paginations));
                                for (int i = 2; i < paginationArrayList.size(); i++) {
                                    try {
                                        integerArrayList.add(Integer.parseInt(paginationArrayList.get(i).getTitle()));
                                    } catch (Exception e) {

                                    }
                                }
                            }
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
                        try {


                            if (databaseHelper.getCartAdd() != null) {
                                for (int i = 0; i < cartItemsModify.size(); i++) {
                                    for (int ii = 0; ii < databaseHelper.getCartAdd().size(); ii++) {
                                        if (databaseHelper.getCartAdd().get(ii).getID() == cartItemsModify.get(i).getID()) {
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
                                            cartItemsModify.get(i).setcQuantity(databaseHelper.getCart().get(ii).getcQuantity());
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {

                        }
                        proGridAdapter = new ProGridAdapter(getActivity(), R.layout.main_items, cartItemsModify, onItemListener, onCartListener, itemAddedAlreadies, onAddItem);
                        lstProduct.addNewData(cartItemsModify, false);

                        lstProduct.setAdapter(proGridAdapter);
                        ++page;

                    }

                    @Override
                    public void onFailure() {
                        Utility.showFailureDialog(getActivity(), false);
                    }
                };

                GetProductByCategory task = new GetProductByCategory(getActivity(), id, ProductListener, 1);
                task.execute();

            } else {
                Utility.showValidateDialog(
                        getResources().getString(R.string.failure_ws),
                        getActivity());
            }
        } catch (Exception e) {

        }

        return view;
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
    public void onClick(int position, boolean isLongClick) {
        Fragment fragmentProduct = new FragmentProductDetails();

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
    public void onAddCart(CartQuantity cartQuantity, int num, boolean watch, double price) {
        pricesss += price;
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
                    databaseHelper.updateCart(cartQuantity);
                    databaseHelper.updateToDo(cartQuantity, 1, 1);
                    addNum();
                } else {
                    databaseHelper.createAdd(cartQuantity, 1, 1);

                }
                addAuth(itemJson);
                checkAdd(true);
                setAdd(true);
            } else if (check) {
                if (new StoreData(getActivity()).getAuthName().equals("")) {
                    FragmentProductCart fragment = new FragmentProductCart();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("cart", productCart);
                    bundle.putDouble("price", pricesss);
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
                    bundle.putDouble("price", pricesss);
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
                if (saveAuth.getCartQuan() != null) {
                    cartItemsDeleted.add(cartQuantity);
                    saveAuth.setCartQuanDelete(cartItemsDeleted);
                } else {
                    cartItemsDeleted.add(cartQuantity);
                    saveAuth.setCartQuanDelete(cartItemsDeleted);
                }
                ItemJson itemJson = new ItemJson(cartQuantity.getID(), num, Utility.getCurrentTimeStamp());
                if (new StoreData(getActivity()).getAuthName().equals("")) {

                    if (saveAuth.getItemJsons() != null) {
                        itemHashSet = saveAuth.getItemJsons();

                    }

                } else {
                    addAuth(itemJson);
                    checkAdd(true);
                    setAdd(true);
                }
            } else if (databaseHelper.getItem(cartQuantity.getID()).getAdd() == 1) {
                if (new StoreData(getActivity()).getAuthName().equals("")) {
                    FragmentProductCart fragment = new FragmentProductCart();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("cart", productCart);
                    bundle.putDouble("price", pricesss);
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
                    bundle.putDouble("price", pricesss);
                    databaseHelper.updateToDo(cartQuantity, 2, 2);

                    fragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                            .replace(R.id.mainFragment, fragment).commit();
                }
            } else if (databaseHelper.getItem(cartQuantity.getID()).getAdd() == 2) {
                Toast.makeText(getActivity(), getResources().getString(R.string.find_cart), Toast.LENGTH_LONG).show();

            }
        }
        addNum();

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
        cartQuantity.setcQuantity(num);
        cartQuantity.setcQuantity(num);
        cartQuantity.setAdded(1);
        if (databaseHelper.getCartItemAdd(cartQuantity.getID()) != null) {
            databaseHelper.updateCartAdd(cartQuantity);
            databaseHelper.updateCart(cartQuantity);
        } else {
            databaseHelper.createCartAdd(cartQuantity);
            databaseHelper.createCart(cartQuantity);
            addNum();
        }
        saveAuth.setCartQuan(cartItemsModify);
        if (saveAuth.getItemAdded() != null)
            if (saveAuth.getItemAdded().size() > 0) {
                for (int i = 0; i < saveAuth.getItemAdded().size(); i++) {
                    if (cartQuantity.getID() == (saveAuth.getItemAdded().get(i).getId())) {
                        saveAuth.getItemAdded().get(i).setNum(num);
                    } else {
                        itemAddedAlreadies.add(new ItemAddedAlready(cartQuantity.getID(), cartQuantity.getName(), num));

                    }
                }
            } else {
                itemAddedAlreadies.add(new ItemAddedAlready(cartQuantity.getID(), cartQuantity.getName(), num));

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
                                            lstProduct.setAdapter(new ProGridAdapter(getActivity(), R.layout.main_items, cartItemsModify, onItemListener, onCartListener, itemAddedAlreadies, onAddItem));


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

    private void search(final String text) {
        try {
            cartItemsResult.clear();

            for (int i = 0; i < cartItemsModify.size(); i++) {
                if (cartItemsModify.get(i).getName().toLowerCase().contains(text)) {

                    cartItemsResult.add(cartItemsModify.get(i));

                }

            }

            if (cartItemsResult.size() > 0) {
                //  lstProduct.setAdapter(new ProGridAdapter(getActivity(), cartItemsResult, onItemListener, onCartListener, itemAddedAlreadies, onAddItem));


            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        cartItemsResult.clear();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                //       lstProduct.setAdapter(null);

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

    @Override
    public void loadData() {
        if (searched) {
            if (checkSearch) {
                progressBar.setVisibility(View.VISIBLE);

                LoaderSearch loader = new LoaderSearch(getActivity(), edSearch.getText().toString(), "" + pageSearch);
                loader.execute();


                ++pageSearch;
            }
        } else {
            if (checkLoad) {
                progressBar.setVisibility(View.VISIBLE);

                Loader loader = new Loader(getActivity(), "" + page, id);
                loader.execute();

                ++page;

            }
        }
    }


    private class Loader extends AsyncTask<String, Void, MainApi> {

        private final static String URL = "http://jm3eia.com/API/ar/product?page=";
        String finalUrl;
        private Context context;
        String responseJSON;

        public Loader(Context context, String num, double id) {
            this.context = context;
            finalUrl = "http://jm3eia.com/API/ar/product/c/" + id + "?page=" + num;
        }

        @Override
        protected void onPreExecute() {
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

            if (progressBar != null) {
                progressBar.setVisibility(View.GONE);
            }
            mainApi = (MainApi) responseHome;
            arrayMain = mainApi.getData();
            if (arrayMain.size() > 0) {
                checkLoad = true;

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
                proGridAdapter.addAll(cartQuantities);
                proGridAdapter.notifyDataSetChanged();
                lstProduct.addNewData(cartQuantities, true);
            } else {
                checkLoad = false;
                lstProduct.addNewData(cartItemsModify, false);
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

    private class LoaderSearch extends AsyncTask<String, Void, MainApi> {

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


                    // cartItemsModifySearch.add(cartItem);
                }


                for (int i = 0; i < proSearch.size(); i++) {

                    AllProducts allProducts = proSearch.get(i);
                    CartQuantity cartItem = new CartQuantity(allProducts.getID(), allProducts.getCode(), allProducts.getCategoryID(),
                            allProducts.getBrandID(), allProducts.getPrice(), allProducts.getQuantity(), allProducts.getPicture(), allProducts.getSliderPictures(),
                            allProducts.getCreatedDate(), allProducts.getModifiedDate(), allProducts.getViewed(), allProducts.getFeatured(), allProducts.getState(),
                            allProducts.getProductID(), allProducts.getLanguageID(), allProducts.getName(), allProducts.getAlias(),
                            allProducts.getContents(), allProducts.getDescription(), allProducts.getKeywords(), allProducts.getCategoryName(), allProducts.getBrandName(),
                            1);

                    //  cartItemsModifySearch.add(cartItem);
                    //    saveAuth.setCartQuan(cartItemsModify);

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
                proGridAdapter.addAll(cartQuantities);
                proGridAdapter.notifyDataSetChanged();
                lstProduct.addNewData(cartQuantities, true);
            } else {
                checkSearch = false;
                lstProduct.addNewData(cartItemsModifySearch, false);

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

                assert in != null;
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
