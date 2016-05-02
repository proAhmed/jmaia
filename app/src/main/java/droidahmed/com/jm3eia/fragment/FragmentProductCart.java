package droidahmed.com.jm3eia.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.account.CartNotAuth;
import droidahmed.com.jm3eia.account.SignIn;
import droidahmed.com.jm3eia.adapter.CartGridAdapter;
import droidahmed.com.jm3eia.adapter.CuCartListAdapter;
import droidahmed.com.jm3eia.adapter.CuListAdapter;
import droidahmed.com.jm3eia.api.AddCartItem;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.AllProducts;
import droidahmed.com.jm3eia.model.MainApi;
import droidahmed.com.jm3eia.model.Product;
import droidahmed.com.jm3eia.model.ProductCart;
import droidahmed.com.jm3eia.start.MainActivity;

/**
 * Created by ahmed on 3/15/2016.
 */
public class FragmentProductCart extends Fragment implements OnCartListener {
    GridView lstProduct;
    ArrayList<AllProducts> pro;
    Button btnRequest, btnCancel;
    TextView tvDeliver, tvTotal, tvFinalTotal;
    private OnProcessCompleteListener ProductListener;
    MainApi mainApi;
    AllProducts[] pros;
ArrayList<ProductCart>productCarts;
    ArrayList<ProductCart>productCartItems;
static double pricessss;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        lstProduct = (GridView) view.findViewById(R.id.cart);
        pro = new ArrayList<>();
        // lstProduct.setAdapter(new CuListAdapter(getActivity(),pro));
        final OnCartListener onCartListener = (OnCartListener) this;
        productCarts = new ArrayList<>();
        productCartItems = new ArrayList<>();
Bundle bundle = getArguments();
        productCarts = (ArrayList<ProductCart>) bundle.getSerializable("cart");
        btnRequest = (Button) view.findViewById(R.id.btnRequest);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        tvDeliver = (TextView) view.findViewById(R.id.tvDeliver);
        tvTotal = (TextView) view.findViewById(R.id.tvTotal);
        tvFinalTotal = (TextView) view.findViewById(R.id.tvFinalTotal);
        pricessss=bundle.getDouble("price");
        tvTotal.setText(""+pricessss);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pricessss>10){
                    dialog();

                }else{
                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.price_total),Toast.LENGTH_LONG).show();
                }

            }
        });
        ArrayList<ProductCart> result = new ArrayList<ProductCart>();
        Set<String> titles = new HashSet<String>();

        for( ProductCart item : productCarts ) {
            if( titles.add( item.getAllProducts().getAlias()) ) {
                result.add( item );
            }
        }
        lstProduct.setAdapter(new CartGridAdapter(getActivity(), result, onCartListener));
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
        getActivity().findViewById(R.id.logo).setVisibility(View.GONE);
        getActivity().findViewById(R.id.textTitle).setVisibility(View.VISIBLE);
        TextView tv = (TextView) getActivity().findViewById(R.id.textTitle);
        tv.setText(getResources().getString(R.string.my_cart));
    }

    @Override
    public void onAddCart(int position, int num,boolean watch,double price) {
        pricessss +=price;
        productCarts.get(position).getAllProducts();
        productCartItems.add(new ProductCart(productCarts.get(position).getAllProducts(), num));
        Log.d("uuu", productCartItems.toString());
        if(watch==true){
            FragmentProductCart  fragment =   new FragmentProductCart();
            Bundle bundle = new Bundle();
            bundle.putSerializable("cart",productCartItems);
            bundle.putDouble("price",pricessss);
if(bundle.getDouble("price")==0){
    tvTotal.setText(""+price);

}
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
//                    mainApi = (MainApi) result;
//                    pros = mainApi.getData();
//                }
//
//                @Override
//                public void onFailure() {
//                    Utility.showFailureDialog(getActivity(), false);
//                }
//            };
//
//            AddCartItem task = new AddCartItem(getActivity(), ProductListener);
//            task.execute(String.valueOf(position), String.valueOf(num), Utility.getCurrentTimeStamp());
//
//        } else {
//            Utility.showValidateDialog(
//                    getResources().getString(R.string.failure_ws),
//                    getActivity());
//        }
    }
    private void dialog(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.cart_dialog);
        dialog.setTitle("Title...");

        // set the custom dialog components - text, image and button

        Button btnNotAuth = (Button) dialog.findViewById(R.id.btnNotAuth);
        // if button is clicked, close the custom dialog
        btnNotAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CartNotAuth.class);
                 startActivity(intent);

                dialog.dismiss();
            }
        });
        Button btnAuth = (Button) dialog.findViewById(R.id.btnAuthCart);
        // if button is clicked, close the custom dialog
        btnAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!new StoreData(getActivity()).getAuthName().equals("")){

                }else{
                    Intent intent = new Intent(getActivity(), SignIn.class);
                    intent.putExtra("cart_request","cart_request");
                    startActivity(intent);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}
