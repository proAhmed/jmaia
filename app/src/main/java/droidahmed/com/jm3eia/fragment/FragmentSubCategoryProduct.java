package droidahmed.com.jm3eia.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.adapter.ProGridAdapter;
import droidahmed.com.jm3eia.api.AddCartItem;
import droidahmed.com.jm3eia.api.AddCartItemAuth;
import droidahmed.com.jm3eia.api.GetProductByCategory;
import droidahmed.com.jm3eia.controller.DatabaseHelper;
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
import droidahmed.com.jm3eia.model.ProductCart;
import droidahmed.com.jm3eia.model.ResponseChangeUserData;
import droidahmed.com.jm3eia.start.MainActivity;
import droidahmed.com.jm3eia.start.SaveAuth;

/**
 * Created by ahmed on 3/15/2016.
 */
public class FragmentSubCategoryProduct extends Fragment  implements OnItemListener,OnCartListener,OnAddItem {
    GridView lstProduct;
    EditText edSearch;
     AllProducts[] pro;
    private OnProcessCompleteListener ProductListener;
    MainApi mainApi;
    ArrayList<CartItem> cartItems;
     ArrayList<AllProducts> arrayList;
    ArrayList<ProductCart>productCart;
    static  double pricesss;
    HashSet<AllProducts> baseHashSet;
    HashSet<ItemJson>itemHashSet;
    int id;
    SaveAuth saveAuth;
    boolean add;
    ArrayList<ItemAddedAlready>itemAddedAlreadies;
    OnItemListener onItemListener;
    OnCartListener onCartListener;
    OnAddItem onAddItem;
    ArrayList<CartQuantity>cartItemsModify;
    ArrayList<CartQuantity>cartItemsDeleted;
    ArrayList<CartQuantity>cartItemsResult;
    boolean searched;
    DatabaseHelper databaseHelper;
    int checkAdds = 0;
    int checkEnter = 0;
    boolean check;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product,container,false);
        lstProduct = (GridView) view.findViewById(R.id.lstProduct);
        arrayList = new ArrayList<>();
         onItemListener= this;
          onCartListener = this;
        onAddItem = this;
        cartItems = new ArrayList<>();
        productCart = new ArrayList<>();
        baseHashSet = new HashSet<>();
        itemHashSet = new HashSet<>();
        itemAddedAlreadies = new ArrayList<>();
        cartItemsModify = new ArrayList<>();
        cartItemsDeleted = new ArrayList<>();
        cartItemsResult = new ArrayList<>();
        if(android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            if (getActivity().getWindow().getDecorView().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                lstProduct.setHorizontalSpacing((int) -1);
            } else {
                lstProduct.setHorizontalSpacing((int) 1);
            } }
        edSearch = (EditText) view.findViewById(R.id.edSearch);
        initSearchView(edSearch);
        saveAuth = (SaveAuth) getActivity().getApplicationContext();
        Bundle bundle = getArguments();
        databaseHelper = new DatabaseHelper(getActivity());
     id=   bundle.getInt("id");
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
arrayList = new ArrayList<AllProducts>(Arrays.asList(pro));

                    for(int i =0;i<arrayList.size();i++) {

                        AllProducts allProducts = arrayList.get(i);
                        CartQuantity cartItem = new CartQuantity(allProducts.getID(), allProducts.getCode(), allProducts.getCategoryID(),
                                allProducts.getBrandID(), allProducts.getPrice(), allProducts.getQuantity(), allProducts.getPicture(), allProducts.getSliderPictures(),
                                allProducts.getCreatedDate(), allProducts.getModifiedDate(), allProducts.getViewed(), allProducts.getFeatured(), allProducts.getState(),
                                allProducts.getProductID(), allProducts.getLanguageID(), allProducts.getName(), allProducts.getAlias(),
                                allProducts.getContents(), allProducts.getDescription(), allProducts.getKeywords(), allProducts.getCategoryName(), allProducts.getBrandName(),
                                1);

                        cartItemsModify.add(cartItem);
                }

                    if(saveAuth.getItemAdded()!=null&&saveAuth.getItemAdded().size()>0) {
                        for(int i=0;i<cartItemsModify.size();i++){
                            for(int ii=0;ii<saveAuth.getItemAdded().size();ii++){
                                if(  saveAuth.getItemAdded().get(ii).getId()==cartItemsModify.get(i).getID()){
                                    cartItemsModify.get(i).setcQuantity(saveAuth.getItemAdded().get(ii).getNum());
                                }
                            }
                        }
                    }
       lstProduct.setAdapter(new ProGridAdapter(getActivity(),cartItemsModify,onItemListener,onCartListener,saveAuth.getItemAdded(),onAddItem));


                }

                @Override
                public void onFailure() {
                    Utility.showFailureDialog(getActivity(), false);
                }
            };

            GetProductByCategory task = new GetProductByCategory(getActivity(),id, ProductListener);
            task.execute();

        } else {
            Utility.showValidateDialog(
                    getResources().getString(R.string.failure_ws),
                   getActivity());
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
            bundle.putSerializable("products", cartItemsResult.get(position));
            bundle.putDouble("item_id", cartItemsResult.get(position).getCategoryID());
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
    public void onAddCart(CartQuantity cartQuantity, int num,boolean watch,double price) {
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
                    databaseHelper.updateToDo(cartQuantity, 1, 1);

                } else {
                    databaseHelper.createAdd(cartQuantity, 1, 1);

                }
                Log.d("iii", itemJson.toString());
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
                    databaseHelper.updateToDo(cartQuantity, 1, 1);

                } else {
                    databaseHelper.createAdd(cartQuantity, 1, 1);

                }

                databaseHelper.createCart(cartQuantity);
                Log.d("iiiooo", cartQuantity.getName() + "");

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
                    boolean add = itemHashSet.add(itemJson);
                    if (add) {
                        saveAuth.setItemJsons(itemHashSet);
                        checkAdd(true);
                        setAdd(true);

                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.find_cart), Toast.LENGTH_LONG).show();

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
            }
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
    public void add(int num, CartQuantity cartQuantity) {
        cartQuantity.setcQuantity(num);
        if(databaseHelper.getCartItemAdd(cartQuantity.getID())!=null){
            cartQuantity.setcQuantity(num);
            databaseHelper.updateCartAdd(cartQuantity);
        }else{
            databaseHelper.createCartAdd(cartQuantity);
        }
        saveAuth.setCartQuan(cartItemsModify);
        if(saveAuth.getItemAdded()!=null)
            if(saveAuth.getItemAdded().size()>0) {
                for (int i = 0; i < saveAuth.getItemAdded().size(); i++) {
                    if (cartQuantity.getID()==(saveAuth.getItemAdded().get(i).getId())) {
                        saveAuth.getItemAdded().get(i).setNum(num);
                    } else {
                        itemAddedAlreadies.add(new ItemAddedAlready(cartQuantity.getID(),cartQuantity.getName(), num));

                    }
                }
            }else {
                itemAddedAlreadies.add(new ItemAddedAlready(cartQuantity.getID(), cartQuantity.getName(), num));

            }
        Log.d("oo",itemAddedAlreadies.size()+"");
        saveAuth.setItemAdded(itemAddedAlreadies);
    }
    private void addAuth(ItemJson itemJson ){
        if (Utility.isNetworkConnected(getActivity())) {

            ProductListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                    String success= ((ResponseChangeUserData)result).getData();
                    Toast.makeText(getActivity(),success,Toast.LENGTH_LONG).show();
                    check =  ((ResponseChangeUserData)result).isSuccess();


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
    ///////////  search //////////////////

    private void initSearchView(EditText edSearch ) {
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


                        String    myTextString = editable.toString().toLowerCase();

                        search(myTextString);


                        if (myTextString.equals("")) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    getActivity(). runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //    ent = null;
                                            cartItemsResult.clear();
                                            lstProduct.setAdapter(new ProGridAdapter(getActivity(), cartItemsModify, onItemListener, onCartListener, itemAddedAlreadies, onAddItem));
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
                lstProduct.setAdapter(new ProGridAdapter(getActivity(), cartItemsResult, onItemListener, onCartListener, itemAddedAlreadies, onAddItem));


            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        cartItemsResult.clear();
                        getActivity(). runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                lstProduct.setAdapter(null);

                            }


                        });
                    }
                }).start(); }
        }catch (Exception ignored){
            Log.d("hhhye0",ignored.toString());

        }


    }

    public boolean isSearched() {
        return searched;
    }

    public void setSearched(boolean searched) {
        this.searched = searched;
    }
}
