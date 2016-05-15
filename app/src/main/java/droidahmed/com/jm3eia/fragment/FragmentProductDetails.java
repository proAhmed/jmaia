package droidahmed.com.jm3eia.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.adapter.CuListAdapter;
import droidahmed.com.jm3eia.adapter.ProGridAdapter;
import droidahmed.com.jm3eia.api.AddCartItem;
import droidahmed.com.jm3eia.api.AddCartItemAuth;
import droidahmed.com.jm3eia.api.GetProductByCategory;
import droidahmed.com.jm3eia.api.GetRelated;
import droidahmed.com.jm3eia.controller.OnAddItem;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.AllProducts;
import droidahmed.com.jm3eia.model.CartItem;
import droidahmed.com.jm3eia.model.CartItemResponse;
import droidahmed.com.jm3eia.model.CartQuantity;
import droidahmed.com.jm3eia.model.ItemAddedAlready;
import droidahmed.com.jm3eia.model.ItemJson;
import droidahmed.com.jm3eia.model.MainApi;
import droidahmed.com.jm3eia.model.ProductCart;
import droidahmed.com.jm3eia.model.ResponseChangeUserData;
import droidahmed.com.jm3eia.start.MainActivity;
import droidahmed.com.jm3eia.start.SaveAuth;

/**
 * Created by ahmed on 3/15/2016.
 */
public class FragmentProductDetails extends Fragment implements OnCartListener,OnAddItem {
      GridView gridView;
      ImageView imgProduct;
    TextView tvName,tvCode,tvBrand,tvCategory;
      ArrayList<CartQuantity> cartItems;
     ArrayList<ProductCart>productCart;
    static  double pricess;
    HashSet<AllProducts> baseHashSet;
    HashSet<ItemJson>itemHashSet;
    SaveAuth saveAuth;
    boolean add;
    ArrayList<CartQuantity>cartItemsModify;
    private OnProcessCompleteListener ProductListener;
    ArrayList<CartQuantity>cartItemsDeleted;
    MainApi mainApi;
    AllProducts[] pro;
    ArrayList<AllProducts> arrayList;
    ArrayList<CartQuantity>cartItemsResult;
      OnCartListener onCartListener;
    boolean searched;
    EditText edSearch,edNumber;
ImageView imgDelete,imgAdd;
    LinearLayout imgCart;
    int items=1;
    double id;
    boolean watched;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        gridView = (GridView) view.findViewById(R.id.grid);
        onCartListener = (OnCartListener) this;
        cartItems = new ArrayList<>();
        productCart = new ArrayList<>();
        baseHashSet = new HashSet<>();
        itemHashSet = new HashSet<>();
        cartItemsModify = new ArrayList<>();
        cartItemsDeleted = new ArrayList<>();
        saveAuth = (SaveAuth) getActivity().getApplicationContext();
        cartItemsResult = new ArrayList<>();
        Bundle bundle = getArguments();
        id = bundle.getDouble("item_id");
        edNumber = (EditText) view.findViewById(R.id.edNumber);
        edNumber.setText(""+1);
        final CartQuantity allProducts = (CartQuantity) bundle.getSerializable("products");

        imgDelete = (ImageView) view.findViewById(R.id.imgDelete);
        imgAdd = (ImageView) view.findViewById(R.id.imgAdd);
        imgCart = (LinearLayout) view.findViewById(R.id.imgCart);
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if (!watched) {
                        watched=true;
                        if(saveAuth.getCartQuan()!=null){
                            cartItemsDeleted.add(allProducts);
                            saveAuth.setCartQuanDelete( cartItemsDeleted);
                        }
                        else{
                            cartItemsDeleted.add(allProducts);
                            saveAuth.setCartQuanDelete( cartItemsDeleted);
                        }


                         cartItems.add(allProducts);
                        Log.d("uuu", productCart.toString());
                        assert allProducts != null;
                        ItemJson itemJson = new ItemJson(allProducts.getID(), items, Utility.getCurrentTimeStamp());
                        if(new StoreData(getActivity()).getAuthName().equals("")) {

                            if (saveAuth.getItemJsons() != null) {
                                itemHashSet = saveAuth.getItemJsons();

                            }
                            boolean add = itemHashSet.add(itemJson);
                            if (add) {
                                saveAuth.setItemJsons(itemHashSet);
                                checkAdd(true);
                                setAdd(true);
                            } else {
                                Toast.makeText(getActivity(), getResources().getString(R.string.find_cart), Toast.LENGTH_LONG).show();

                            }
                        }else{
                            addAuth(itemJson);
                            checkAdd(true);
                            setAdd(true);
                        }
                    } else if (isAdd()) {
                        if (new StoreData(getActivity()).getAuthName().equals("")) {

                            FragmentProductCart fragment = new FragmentProductCart();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("cart", productCart);
                            bundle.putDouble("price", pricess);

                            fragment.setArguments(bundle);
                            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                                    .replace(R.id.mainFragment, fragment).commit();
                        }else{
                            AddAuth(itemHashSet);
                            FragmentProductCart fragment = new FragmentProductCart();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("cart", productCart);
                            bundle.putString("login","login");
                            bundle.putDouble("price", pricess);

                            fragment.setArguments(bundle);
                            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                                    .replace(R.id.mainFragment, fragment).commit();
                        }
                    }
                }catch (Exception e){

                }
            }
        });

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(items>1){
                  items--;
                  edNumber.setText(""+items);
                  cartItemsModify.remove(allProducts);
                  assert allProducts != null;
                  allProducts.setcQuantity(items);
                  cartItemsModify.add(allProducts);
                  saveAuth.setCartQuan(cartItemsModify);
              }

            }
        });
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items++;
                edNumber.setText("" + items);

                cartItemsModify.remove(allProducts);
                assert allProducts != null;
                allProducts.setcQuantity(items);
                cartItemsModify.add(allProducts);
                saveAuth.setCartQuan(cartItemsModify);
            }
        });

        Log.d("ooo",id+"");


        edSearch = (EditText) view.findViewById(R.id.edSearch);
        initSearchView(edSearch);

        tvName = (TextView) view.findViewById(R.id.proName);
        tvCode = (TextView) view.findViewById(R.id.proCode);
        tvCategory = (TextView) view.findViewById(R.id.proCategory);
        tvBrand = (TextView) view.findViewById(R.id.proBrand);
        imgProduct = (ImageView) view.findViewById(R.id.imgPro);

        if (Utility.isNetworkConnected(getActivity())) {

            ProductListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                    mainApi = (MainApi) result;
                    pro=   mainApi.getData();


//                               Fragment fragmentProduct = new FragmentProductDetails();
//                    FragmentManager fm = getSupportFragmentManager();
//                    FragmentTransaction ft = fm.beginTransaction();
                    //                    ft.replace(R.id.mainFragment, fragmentProduct);
//                    ft.commit();h
                    arrayList = new ArrayList<>(Arrays.asList(pro));
                    Log.d("iii",arrayList.size()+"");

                    for(int i =0;i<arrayList.size();i++) {

                        AllProducts allProducts = arrayList.get(i);
                        CartQuantity cartItem = new CartQuantity(allProducts.getID(), allProducts.getCode(), allProducts.getCategoryID(),
                                allProducts.getBrandID(), allProducts.getPrice(), allProducts.getQuantity(), allProducts.getPicture(), allProducts.getSliderPictures(),
                                allProducts.getCreatedDate(), allProducts.getModifiedDate(), allProducts.getViewed(), allProducts.getFeatured(), allProducts.getState(),
                                allProducts.getProductID(), allProducts.getLanguageID(), allProducts.getName(), allProducts.getAlias(),
                                allProducts.getContents(), allProducts.getDescription(), allProducts.getKeywords(), allProducts.getCategoryName(), allProducts.getBrandName(),
                                1);

                        cartItemsModify.add(cartItem);
                        if(saveAuth.getCartQuan()!=null) {
                            for (int ii = 0; ii < saveAuth.getCartQuan().size(); ii++) {
                                if (cartItemsModify.get(i).getID() ==saveAuth.getCartQuan().get(ii).getID()){
                                    cartItemsModify.get(i).setcQuantity(saveAuth.getCartQuan().get(ii).getcQuantity());
                                }
                            }
                        }
                    }
                    gridView.setAdapter(new CuListAdapter(getActivity(),cartItemsModify,onCartListener));


                }

                @Override
                public void onFailure() {
                    Utility.showFailureDialog(getActivity(), false);
                }
            };

            GetRelated task = new GetRelated(getActivity(), ProductListener,id);
            task.execute();

        } else {
            Utility.showValidateDialog(
                    getResources().getString(R.string.failure_ws),
                    getActivity());
        }





        assert allProducts != null;
        tvName.setText(allProducts.getName()+"");
        tvCode.setText(allProducts.getCode()+"");
        tvCategory.setText(allProducts.getCategoryName()+"");
        tvBrand.setText(allProducts.getBrandName()+"");
        Picasso.with(getActivity()).load("http://jm3eia.com/" +allProducts.getPicture()).placeholder(R.drawable.place_holder_list).into(imgProduct);

//        gridView.setAdapter(new CuListAdapter(getActivity(),related,onCartListener));

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
    public void onAddCart(int position, int num,boolean watch,double price) {
        try {
            if (!watch) {
                if(saveAuth.getCartQuan()!=null){
                    cartItemsDeleted.add(saveAuth.getCartQuan().get(position));
                    saveAuth.setCartQuanDelete( cartItemsDeleted);
                }
                else{
                    cartItemsDeleted.add(cartItemsModify.get(position));
                    saveAuth.setCartQuanDelete( cartItemsDeleted);
                }

                    pricess += price;

                cartItemsModify.get(position);
                    cartItems.add(cartItemsModify.get(position));
                    Log.d("uuu", productCart.toString());
                    ItemJson itemJson = new ItemJson(cartItems.get(position).getID(), num, Utility.getCurrentTimeStamp());
                    if(new StoreData(getActivity()).getAuthName().equals("")) {

                        if (saveAuth.getItemJsons() != null) {
                        itemHashSet = saveAuth.getItemJsons();

                    }
                    boolean add = itemHashSet.add(itemJson);
                    if (add) {
                        saveAuth.setItemJsons(itemHashSet);
                        checkAdd(true);
                        setAdd(true);
                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.find_cart), Toast.LENGTH_LONG).show();

                    }
                }else{
                    addAuth(itemJson);
                    checkAdd(true);
                    setAdd(true);
                }
            } else if (isAdd()) {
                if (new StoreData(getActivity()).getAuthName().equals("")) {

                    FragmentProductCart fragment = new FragmentProductCart();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("cart", productCart);
                    bundle.putDouble("price", pricess);

                    fragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                            .replace(R.id.mainFragment, fragment).commit();
                }else{
                    AddAuth(itemHashSet);
                    FragmentProductCart fragment = new FragmentProductCart();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("cart", productCart);
                    bundle.putString("login","login");
                    bundle.putDouble("price", pricess);

                    fragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                            .replace(R.id.mainFragment, fragment).commit();
                }
            }
        }catch (Exception e){

        }
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    private boolean checkAdd(boolean check){
        return check;
    }

    @Override
    public void add(int num, int position) {
        cartItemsModify.get(position).setcQuantity(num);
        saveAuth.setCartQuan(cartItemsModify);
    }
    private void addAuth(ItemJson itemJson ){
        if (Utility.isNetworkConnected(getActivity())) {

            ProductListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                    String success= ((ResponseChangeUserData)result).getData();
                    Toast.makeText(getActivity(),success,Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure() {
                    Utility.showFailureDialog(getActivity(), false);
                }
            };

            AddCartItemAuth task = new AddCartItemAuth(getActivity(), ProductListener);
            task.execute(itemJson );

        } else {
            Utility.showValidateDialog(
                    getResources().getString(R.string.failure_ws),
                    getActivity());
        }
    }
    private void AddAuth(HashSet hashSet){
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
                                            gridView.setAdapter(new CuListAdapter(getActivity(), cartItemsModify, onCartListener));
                                            //    Log.d("sss", ent.toString());


                                        }

                                    });
                                }
                            }).start();
                        }


                    } catch (Exception ignored) {
                        Log.d("hhhyess", ignored.toString());


                    }
                }
            });
        }catch (Exception ignored){
            Log.d("hhhyesss",ignored.toString());


        }
    }
    private void search(final String text){
        try {
            cartItemsResult.clear();

            //  Log.d("kkk",_choices.size()+"");
            for (int i = 0; i < cartItemsModify.size(); i++) {
                if (cartItemsModify.get(i).getName().toLowerCase().contains(text)) {

                    cartItemsResult.add(cartItemsModify.get(i));

                }

            }

            if (cartItemsResult.size() > 0) {
                gridView.setAdapter(new CuListAdapter(getActivity(),cartItemsResult,onCartListener));


            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        cartItemsResult.clear();
                        getActivity(). runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                gridView.setAdapter(null);

                            }


                        });
                    }
                }).start(); }
        }catch (Exception ignored){
            Log.d("hhhye0",ignored.toString());

        }


    }



}
