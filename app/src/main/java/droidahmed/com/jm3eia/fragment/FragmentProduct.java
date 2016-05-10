package droidahmed.com.jm3eia.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.adapter.ProGridAdapter;
import droidahmed.com.jm3eia.api.AddCartItem;
import droidahmed.com.jm3eia.api.GetHome;
import droidahmed.com.jm3eia.controller.OnAddItem;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.OnItemListener;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.OnUpdateAdapter;
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
import droidahmed.com.jm3eia.start.MainActivity;
import droidahmed.com.jm3eia.start.SaveAuth;

/**
 * Created by ahmed on 3/15/2016.
 */
public class FragmentProduct extends Fragment implements OnItemListener,OnCartListener,OnAddItem {
     EditText edSearch;
    AllProducts[] pro;
    private OnProcessCompleteListener ProductListener;
     MainApi mainApi;
    private GridView lstProduct;
      ArrayList<AllProducts> arrayList;
    ArrayList<CartItem> cartItems;
     ArrayList<ProductCart>productCart;
 static   ArrayList<ItemJson>itemList;
    HashSet<AllProducts>baseHashSet;
    HashSet<ItemJson>itemHashSet;
    JSONArray jsonArray;
static  double prices;
    SaveAuth saveAuth;
     ArrayList<ItemAddedAlready>itemAddedAlreadies;
      OnItemListener onItemListener;
    OnCartListener onCartListener;
      OnAddItem onAddItem;
    ArrayList<CartQuantity>cartItemsModify;
      @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        lstProduct = (GridView) view.findViewById(R.id.lstProduct);
       StoreData storeData =   new StoreData(getActivity());
            onItemListener = this;
            onCartListener = this;
              onAddItem = this;
          cartItemsModify = new ArrayList<>();
          cartItems = new ArrayList<>();
        productCart = new ArrayList<>();
        itemList = new ArrayList<>();
        baseHashSet = new HashSet<>();
        itemHashSet = new HashSet<>();
        itemAddedAlreadies = new ArrayList<>();
        //        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<AllProducts>>() {}.getType();
     //   ArrayList<AllProducts> arrayList = gson.fromJson(category, type);
        final Bundle bundle = getArguments();
        jsonArray = new JSONArray();
          saveAuth = (SaveAuth) getActivity().getApplicationContext();



//        lstProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (Utility.isNetworkConnected(getActivity())) {

            ProductListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                    mainApi = (MainApi) result;
                    pro=   mainApi.getData();
                    arrayList =  new ArrayList<>(Arrays.asList(pro));

                    for(int i =0;i<arrayList.size();i++){

                       AllProducts allProducts = arrayList.get(i);
                        CartQuantity cartItem = new CartQuantity(allProducts.getID(),allProducts.getCode(),allProducts.getCategoryID(),
                        allProducts.getBrandID(),allProducts.getPrice(), allProducts.getQuantity(), allProducts.getPicture(),allProducts.getSliderPictures(),
                        allProducts.getCreatedDate(),allProducts.getModifiedDate(),allProducts.getViewed(), allProducts.getFeatured(),allProducts.getState(),
                       allProducts.getProductID(),allProducts.getLanguageID(),allProducts.getName(),allProducts.getAlias(),
                                allProducts.getContents(),allProducts.getDescription(), allProducts.getKeywords(),allProducts.getCategoryName(),allProducts.getBrandName(),
                      1);

                        cartItemsModify.add(cartItem);
                    //    saveAuth.setCartQuan(cartItemsModify);

                    }
                    Log.d("oooo1", cartItemsModify.size() + "");
                    Log.d("oooo2",arrayList.size()+"");
                    if(saveAuth.getCartQuan()==null) {
                        lstProduct.setAdapter(new ProGridAdapter(getActivity(), cartItemsModify, onItemListener, onCartListener, itemAddedAlreadies, onAddItem));
                    }else{
                        lstProduct.setAdapter(new ProGridAdapter(getActivity(), saveAuth.getCartQuan(), onItemListener, onCartListener, saveAuth.getItemAdded(), onAddItem));

                    }
                }

                @Override
                public void onFailure() {
                    Utility.showFailureDialog(getActivity(), false);
                }
            };

            GetHome task = new GetHome(getActivity(), ProductListener);
            task.execute();

        } else {
            Utility.showValidateDialog(
                    getResources().getString(R.string.failure_ws),
                    getActivity());
        }

//            }
//        });
//        if (Utility.isNetworkConnected(getActivity())) {
//
//            ProductListener = new OnProcessCompleteListener() {
//
//                @Override
//                public void onSuccess(Object result) {
//                    mainApi = (MainApi) result;
//                    pro=   mainApi.getData();
//
//
//                }
//
//                @Override
//                public void onFailure() {
//                    Utility.showFailureDialog(getActivity(), false);
//                }
//            };
//
//            GetHome task = new GetHome(getActivity(), ProductListener);
//            task.execute();
//
//        } else {
//            Utility.showValidateDialog(
//                    getActivity().getResources().getString(R.string.failure_ws),
//                    getActivity());
//        }
//        pro = new ArrayList<>();
//        pro.add(new Product("","",1));
//        pro.add(new Product("","",1));
//        pro.add(new Product("","",1));
//        pro.add(new Product("","",1));
//        pro.add(new Product("","",1));

//        lstProduct.setAdapter(new CuListAdapter(getActivity(),pro));
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        MainActivity mainActivity = new MainActivity();
//        mainActivity.ui("Products");
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
//                    FragmentManager fm = getSupportFragmentManager();
//                    FragmentTransaction ft = fm.beginTransaction();
                //                    ft.replace(R.id.mainFragment, fragmentProduct);
//                    ft.commit();h
                   Bundle bundle = new Bundle();
                bundle.putSerializable("products", cartItemsModify.get(position));
        ArrayList<CartQuantity>related =new ArrayList<>();
        for (int i =0;i<arrayList.size();i++){
            if(cartItemsModify.get(position).getCategoryID()==cartItemsModify.get(i).getCategoryID())
                related.add(cartItemsModify.get(i));
        }
        if(related.size()>0)
        bundle.putSerializable("related-product", related);

        fragmentProduct.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainFragment, fragmentProduct).addToBackStack("")
                        .commitAllowingStateLoss();
    }
    boolean add;
    @Override
    public void onAddCart(int position, int num, boolean watch,double price)  {

if(!watch) {
    prices += price;
    Log.d("ttt", "" + prices);
    arrayList.get(position);
    ItemJson itemJson = new ItemJson(arrayList.get(position).getID(), num, Utility.getCurrentTimeStamp());
    Log.d("uuid", arrayList.get(position).getID() + "");
    if (saveAuth.getItemJsons() != null) {
        itemHashSet = saveAuth.getItemJsons();

    }

    add = itemHashSet.add(itemJson);
    if (add) {
        saveAuth.setItemJsons(itemHashSet);
        checkAdd(true);
        setAdd(true);
    } else {
        Toast.makeText(getActivity(), getResources().getString(R.string.find_cart), Toast.LENGTH_LONG).show();


        return;
    }

    productCart.add(new ProductCart(arrayList.get(position), num));
    Log.d("uuu", productCart.toString());
}else   if(isAdd()) {
    if(new StoreData(getActivity()).getAuthName().equals("")) {
        FragmentProductCart fragment = new FragmentProductCart();
        Bundle bundle = new Bundle();
        bundle.putSerializable("cart", productCart);
        bundle.putDouble("price", prices);

        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                .replace(R.id.mainFragment, fragment).commit();
    }else{
        AddAuth(itemHashSet);
        FragmentProductCart fragment = new FragmentProductCart();
        Bundle bundle = new Bundle();
        bundle.putSerializable("cart", productCart);
        bundle.putString("login","login");
        bundle.putDouble("price", prices);

        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                .replace(R.id.mainFragment, fragment).commit();
    }

       }
//        if (Utility.isNetworkConnected(getActivity())) {
//
//            ProductListener = new OnProcessCompleteListener() {
//
//                @Override
//                public void onSuccess(Object result) {
//                    mainApi = (MainApi) result;
//                    pro=   mainApi.getData();
//                 }
//
//                @Override
//                public void onFailure() {
//                    Utility.showFailureDialog(getActivity(), false);
//                }
//            };
//
//            ShowCartItem task = new ShowCartItem(getActivity(), ProductListener);
//            task.execute(String.valueOf(position),String.valueOf(num),Utility.getCurrentTimeStamp());
//
//        } else {
//            Utility.showValidateDialog(
//                    getResources().getString(R.string.failure_ws),
//                    getActivity());
//        }
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
    public void add(int num, int position) {
        arrayList.get(position);
        cartItemsModify.get(position).setcQuantity(num);
        saveAuth.setCartQuan(cartItemsModify);
if(saveAuth.getItemAdded()!=null)
    if(saveAuth.getItemAdded().size()>0) {
        for (int i = 0; i < saveAuth.getItemAdded().size(); i++) {
            if (arrayList.get(position).getID()==(saveAuth.getItemAdded().get(i).getId())) {
                saveAuth.getItemAdded().get(i).setNum(num);
            } else {
                itemAddedAlreadies.add(new ItemAddedAlready(arrayList.get(position).getID(), arrayList.get(position).getName(), num));

            }
        }
    }else {
        itemAddedAlreadies.add(new ItemAddedAlready(arrayList.get(position).getID(), arrayList.get(position).getName(), num));

    }
         Log.d("oo",itemAddedAlreadies.size()+"");
        saveAuth.setItemAdded(itemAddedAlreadies);
    }
}
