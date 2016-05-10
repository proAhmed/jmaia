package droidahmed.com.jm3eia.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.adapter.ProGridAdapter;
import droidahmed.com.jm3eia.api.GetProductByCategory;
import droidahmed.com.jm3eia.controller.OnAddItem;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.OnItemListener;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.AllProducts;
import droidahmed.com.jm3eia.model.CartItem;
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

        saveAuth = (SaveAuth) getActivity().getApplicationContext();
        Bundle bundle = getArguments();
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
if(saveAuth.getCartQuan()!=null) {
    for (int ii = 0; ii < saveAuth.getCartQuan().size(); ii++) {
        if (cartItemsModify.get(i).getID() ==saveAuth.getCartQuan().get(ii).getID()){
            cartItemsModify.get(i).setcQuantity(saveAuth.getCartQuan().get(ii).getcQuantity());
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
//        MainActivity mainActivity = new MainActivity();
//        mainActivity.ui("Products");
        getActivity().findViewById(R.id.imageToggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
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

    @Override
    public void onAddCart(int position, int num,boolean watch,double price) {
        if(!watch) {
            pricesss += price;

            productCart.add(new ProductCart(arrayList.get(position), num));
            Log.d("uuu", productCart.toString());
            arrayList.get(position);
             ItemJson itemJson = new ItemJson(arrayList.get(position).getID(), num, Utility.getCurrentTimeStamp());
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
        }else if(isAdd()){
            FragmentProductCart  fragment =   new FragmentProductCart();
            Bundle bundle = new Bundle();
            bundle.putSerializable("cart",productCart);
            bundle.putDouble("price",pricesss);

            fragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                    .replace(R.id.mainFragment, fragment).commit();
        }
//        if (Utility.isNetworkConnected(getActivity())) {
//
//            ProductListener = new OnProcessCompleteListener() {
//
//                @Override
//                public void onSuccess(Object result) {
//                    cartItemResponse = (CartItemResponse) result;
//                    cartItems=   cartItemResponse.getData();
//                }
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
        itemAddedAlreadies.add(new ItemAddedAlready(arrayList.get(position).getID(), arrayList.get(position).getName(), num));
        Log.d("oo",itemAddedAlreadies.toString());
        saveAuth.setItemAdded(itemAddedAlreadies);
    }
}
