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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import droidahmed.com.jm3eia.R;
  import droidahmed.com.jm3eia.adapter.ProGridAdapter;
 import droidahmed.com.jm3eia.api.GetProductByCategory;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.OnItemListener;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.AllProducts;
import droidahmed.com.jm3eia.model.CartItem;
import droidahmed.com.jm3eia.model.CartItemResponse;
import droidahmed.com.jm3eia.model.MainApi;
import droidahmed.com.jm3eia.model.Product;
import droidahmed.com.jm3eia.model.ProductCart;
import droidahmed.com.jm3eia.start.MainActivity;

/**
 * Created by ahmed on 3/15/2016.
 */
public class FragmentSubCategoryProduct extends Fragment  implements OnItemListener,OnCartListener {
    GridView lstProduct;
    EditText edSearch;
     AllProducts[] pro;
    private OnProcessCompleteListener ProductListener;
    MainApi mainApi;
    ArrayList<CartItem> cartItems;
    CartItemResponse cartItemResponse;
    ArrayList<AllProducts> arrayList;
    ArrayList<ProductCart>productCart;
    static  double pricesss;

    int id;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product,container,false);
        lstProduct = (GridView) view.findViewById(R.id.lstProduct);
        arrayList = new ArrayList<>();
        final OnItemListener onItemListener= this;
        final OnCartListener onCartListener = (OnCartListener) this;
        cartItems = new ArrayList<>();
        productCart = new ArrayList<>();

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
       lstProduct.setAdapter(new ProGridAdapter(getActivity(),arrayList,onItemListener,onCartListener));


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
        bundle.putSerializable("products", arrayList.get(position));
        ArrayList<AllProducts>related =new ArrayList<>();
        for (int i =0;i<arrayList.size();i++){
            if(arrayList.get(position).getCategoryID()==arrayList.get(i).getCategoryID())
                related.add(arrayList.get(i));
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
        pricesss +=price;

        productCart.add(new ProductCart(arrayList.get(position), num));
        Log.d("uuu", productCart.toString());
        if(watch==true){
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
//            AddCartItem task = new AddCartItem(getActivity(), ProductListener);
//            task.execute(String.valueOf(position),String.valueOf(num),Utility.getCurrentTimeStamp());
//
//        } else {
//            Utility.showValidateDialog(
//                    getResources().getString(R.string.failure_ws),
//                    getActivity());
//        }
    }
}
