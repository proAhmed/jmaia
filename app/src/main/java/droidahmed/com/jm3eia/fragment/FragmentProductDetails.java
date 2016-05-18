package droidahmed.com.jm3eia.fragment;

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
import droidahmed.com.jm3eia.controller.DatabaseHelper;
import droidahmed.com.jm3eia.controller.OnAddItem;
import droidahmed.com.jm3eia.controller.OnCartListener;
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
public class FragmentProductDetails extends Fragment implements OnCartListener,OnAddItem {
      GridView gridView;
      ImageView imgProduct;
    TextView tvName,tvCode,tvBrand,tvCategory,item_change;
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
    ArrayList<ItemAddedAlready>itemAddedAlreadies;
    DatabaseHelper databaseHelper;
    int checkAdds = 0;
    int checkEnter = 0;
    boolean check;
    OnAddItem onAddItem;
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
        itemAddedAlreadies = new ArrayList<>();
        saveAuth = (SaveAuth) getActivity().getApplicationContext();
        cartItemsResult = new ArrayList<>();
        Bundle bundle = getArguments();
        id = bundle.getDouble("item_id");
        edNumber = (EditText) view.findViewById(R.id.edNumber);
        item_change  = (TextView) view.findViewById(R.id.item_change);

        final CartQuantity allProducts = (CartQuantity) bundle.getSerializable("products");
        databaseHelper = new DatabaseHelper(getActivity());
        assert allProducts != null;
        if(databaseHelper.getCartItemAdd(allProducts.getID())!=null){
            edNumber.setText(""+databaseHelper.getCartItemAdd(allProducts.getID()).getcQuantity());
        }else{
            edNumber.setText(""+1);

        }

        if(android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            if (getActivity().getWindow().getDecorView().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                gridView.setHorizontalSpacing((int) -1);
            } else {
                gridView.setHorizontalSpacing((int) 1 );
            } }
        onAddItem =this;
        imgDelete = (ImageView) view.findViewById(R.id.imgDelete);
        imgAdd = (ImageView) view.findViewById(R.id.imgAdd);
        imgCart = (LinearLayout) view.findViewById(R.id.imgCart);
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(databaseHelper.getItem(allProducts.getID())!=null){
                        CartCheck cartCheck =  databaseHelper.getItem(allProducts.getID());
                        checkAdds =  cartCheck.getAdd();
                        checkEnter =  cartCheck.getEnter();
                    }else{
                        checkAdds=0;
                        checkEnter=0;
                    }
                    item_change.setText(getResources().getString(R.string.see_cart));

                    if(!new StoreData(getActivity()).getAuthName().equals("")) {
                        ItemJson itemJson = new ItemJson(allProducts.getID(), allProducts.getcQuantity(), Utility.getCurrentTimeStamp());
                        if(checkAdds==0) {
                            if (databaseHelper.getItem(allProducts.getID()) != null) {
                                databaseHelper.updateToDo(allProducts, 1, 1);

                            } else {
                                databaseHelper.createAdd(allProducts, 1, 1);

                            }
                            Log.d("iii",itemJson.toString());
                            addAuth(itemJson);
                            checkAdd(true);
                            setAdd(true);
                        }else
                        if(check){
                            if(new StoreData(getActivity()).getAuthName().equals("")) {
                                FragmentProductCart fragment = new FragmentProductCart();
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("cart", productCart);
                                bundle.putDouble("price", allProducts.getPrice());
                                databaseHelper.updateToDo(allProducts, 2, 2);

                                fragment.setArguments(bundle);
                                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                                        .replace(R.id.mainFragment, fragment).commit();
                            }else{
                                AddAuth(itemHashSet);
                                FragmentProductCart fragment = new FragmentProductCart();
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("cart", productCart);
                                bundle.putString("login","login");
                                bundle.putDouble("price", allProducts.getPrice());
                                databaseHelper.updateToDo(allProducts,2,2);

                                fragment.setArguments(bundle);
                                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                                        .replace(R.id.mainFragment, fragment).commit();
                            }
                        }else {
                            Toast.makeText(getActivity(), getResources().getString(R.string.find_cart), Toast.LENGTH_LONG).show();

                        }
                    }else {

//                        if (databaseHelper.getItem(allProducts.getID()) != null) {
//                            CartCheck cartCheck = databaseHelper.getItem(allProducts.getID());
//                            checkAdds = cartCheck.getAdd();
//                            checkEnter = cartCheck.getEnter();
//                            Log.d("iiiooo", cartCheck.toString() + "");
//                        } else {
//                            Log.d("iiiooo", 11 + "");
//                            checkAdds = 0;
//                            checkEnter = 0;
//                        }

                        if (checkAdds == 0) {
                            if (databaseHelper.getItem(allProducts.getID()) != null) {
                                databaseHelper.updateToDo(allProducts, 1, 1);

                            } else {
                                databaseHelper.createAdd(allProducts, 1, 1);

                            }

                            databaseHelper.createCart(allProducts);
                            Log.d("iiiooo", allProducts.getName() + "");

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
                }catch (Exception e){

                }
            }
        });

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(items>1){
                  --items;
                  edNumber.setText("" + items);
                  if(databaseHelper.getCartItemAdd(allProducts.getID())!=null){
                      allProducts.setcQuantity(Integer.parseInt(edNumber.getText().toString()));
                      databaseHelper.updateCartAdd(allProducts);
                  }else{
                      databaseHelper.createCartAdd(allProducts);
                  }
                  assert allProducts != null;
                  allProducts.setcQuantity(items);
                  saveAuth.setCartQuan(cartItemsModify);
                  if(saveAuth.getItemAdded()!=null)
                      if(saveAuth.getItemAdded().size()>0) {
                          for (int i = 0; i < saveAuth.getItemAdded().size(); i++) {
                              if (allProducts.getID() ==(saveAuth.getItemAdded().get(i).getId())) {
                                  saveAuth.getItemAdded().get(i).setNum(items);
                              } else {
                                  itemAddedAlreadies.add(new ItemAddedAlready(allProducts.getID(), allProducts.getName(), items));

                              }
                          }
                      }else {
                          itemAddedAlreadies.add(new ItemAddedAlready(allProducts.getID(), allProducts.getName(), items));

                      }
                  Log.d("oo", itemAddedAlreadies.size() + "");
                  saveAuth.setItemAdded(itemAddedAlreadies);

              }

            }
        });
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++ items;
                edNumber.setText("" + items);
                if(databaseHelper.getCartItemAdd(allProducts.getID())!=null){
                    allProducts.setcQuantity(Integer.parseInt(edNumber.getText().toString()));
                    databaseHelper.updateCartAdd(allProducts);
                }else{
                    databaseHelper.createCartAdd(allProducts);
                }
                assert allProducts != null;
                allProducts.setcQuantity(items);
                saveAuth.setCartQuan(cartItemsModify);
                if(saveAuth.getItemAdded()!=null)
                    if(saveAuth.getItemAdded().size()>0) {
                        for (int i = 0; i < saveAuth.getItemAdded().size(); i++) {
                            if (allProducts.getID() ==(saveAuth.getItemAdded().get(i).getId())) {
                                saveAuth.getItemAdded().get(i).setNum(items);
                            } else {
                                itemAddedAlreadies.add(new ItemAddedAlready(allProducts.getID(), allProducts.getName(), items));

                            }
                        }
                    }else {
                        itemAddedAlreadies.add(new ItemAddedAlready(allProducts.getID(), allProducts.getName(), items));

                    }
                Log.d("oo", itemAddedAlreadies.size() + "");
                saveAuth.setItemAdded(itemAddedAlreadies);
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

                    gridView.setAdapter(new CuListAdapter(getActivity(),cartItemsModify,onCartListener,onAddItem));


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
        if(Utility.widthScreen(getActivity())>=580) {
            Picasso.with(getActivity()).load("http://jm3eia.com/" + allProducts.getPicture()).resize(140,180).placeholder(R.drawable.place_holder_list).into(imgProduct);


        } else if(Utility.widthScreen(getActivity())>=760){
            Picasso.with(getActivity()).load("http://jm3eia.com/" + allProducts.getPicture()).resize(180,230).placeholder(R.drawable.place_holder_list).into(imgProduct);

        }else{
            Picasso.with(getActivity()).load("http://jm3eia.com/" + allProducts.getPicture()).placeholder(R.drawable.place_holder_list).into(imgProduct);

        }

//        gridView.setAdapter(new CuListAdapter(getActivity(),related,onCartListener));

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
    public void onAddCart(CartQuantity cartQuantity, int num,boolean watch,double price) {
        try {
            if(databaseHelper.getItem(cartQuantity.getID())!=null){
                CartCheck cartCheck =  databaseHelper.getItem(cartQuantity.getID());
                checkAdds =  cartCheck.getAdd();
                checkEnter =  cartCheck.getEnter();
            }else{
                checkAdds=0;
                checkEnter=0;
            }
            if(!new StoreData(getActivity()).getAuthName().equals("")) {
                ItemJson itemJson = new ItemJson(cartQuantity.getID(), num, Utility.getCurrentTimeStamp());
                if(checkAdds==0) {
                    if (databaseHelper.getItem(cartQuantity.getID()) != null) {
                        databaseHelper.updateToDo(cartQuantity, 1, 1);

                    } else {
                        databaseHelper.createAdd(cartQuantity, 1, 1);

                    }
                    Log.d("iii",itemJson.toString());
                    addAuth(itemJson);
                    checkAdd(true);
                    setAdd(true);
                }else
                if(check){
                    if(new StoreData(getActivity()).getAuthName().equals("")) {
                        FragmentProductCart fragment = new FragmentProductCart();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("cart", productCart);
                        bundle.putDouble("price", price);
                        databaseHelper.updateToDo(cartQuantity, 2, 2);

                        fragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                                .replace(R.id.mainFragment, fragment).commit();
                    }else{
                        AddAuth(itemHashSet);
                        FragmentProductCart fragment = new FragmentProductCart();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("cart", productCart);
                        bundle.putString("login","login");
                        bundle.putDouble("price", price);
                        databaseHelper.updateToDo(cartQuantity,2,2);

                        fragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                                .replace(R.id.mainFragment, fragment).commit();
                    }
                }
            }else {


                if (checkAdds == 0) {
                    if (databaseHelper.getItem(cartQuantity.getID()) != null) {
                        databaseHelper.updateToDo(cartQuantity, 1, 1);

                    } else {
                        databaseHelper.createAdd(cartQuantity, 1, 1);

                    }

                    databaseHelper.createCart(cartQuantity);
                    Log.d("iiiooo", cartQuantity.getName() + "");


                    ItemJson itemJson = new ItemJson(cartQuantity.getID(), num, Utility.getCurrentTimeStamp());

                    if (new StoreData(getActivity()).getAuthName().equals("")) {

                        price += price;

                        Log.d("uuid", cartQuantity.getID() + "");
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
                        //  Log.d("uuu", productCart.toString());
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
    public void add(int num, CartQuantity cartQuantity) {
        assert cartQuantity != null;
        if(databaseHelper.getCartItemAdd(cartQuantity.getID())!=null){
            cartQuantity.setcQuantity(num);
            databaseHelper.updateCartAdd(cartQuantity);
        }else{
            databaseHelper.createCartAdd(cartQuantity);
        }
        cartQuantity.setcQuantity(items);
        saveAuth.setCartQuan(cartItemsModify);
        if(saveAuth.getItemAdded()!=null)
            if(saveAuth.getItemAdded().size()>0) {
                for (int i = 0; i < saveAuth.getItemAdded().size(); i++) {
                    if (cartQuantity.getID() ==(saveAuth.getItemAdded().get(i).getId())) {
                        saveAuth.getItemAdded().get(i).setNum(items);
                    } else {
                        itemAddedAlreadies.add(new ItemAddedAlready(cartQuantity.getID(), cartQuantity.getName(), items));

                    }
                }
            }else {
                itemAddedAlreadies.add(new ItemAddedAlready(cartQuantity.getID(), cartQuantity.getName(), items));

            }
        Log.d("oo", itemAddedAlreadies.size() + "");
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
                                            gridView.setAdapter(new CuListAdapter(getActivity(), cartItemsModify, onCartListener,onAddItem));
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
                gridView.setAdapter(new CuListAdapter(getActivity(),cartItemsResult,onCartListener,onAddItem));


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
