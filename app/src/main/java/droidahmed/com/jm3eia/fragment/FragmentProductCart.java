package droidahmed.com.jm3eia.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.adapter.CartGridAdapter;
import droidahmed.com.jm3eia.adapter.CuCartListAdapter;
import droidahmed.com.jm3eia.adapter.CuListAdapter;
import droidahmed.com.jm3eia.api.AddCartItem;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        lstProduct = (GridView) view.findViewById(R.id.cart);
        pro = new ArrayList<>();
        // lstProduct.setAdapter(new CuListAdapter(getActivity(),pro));
        final OnCartListener onCartListener = (OnCartListener) this;
        productCarts = new ArrayList<>();
Bundle bundle = getArguments();
        productCarts = (ArrayList<ProductCart>) bundle.getSerializable("cart");
        btnRequest = (Button) view.findViewById(R.id.btnRequest);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        tvDeliver = (TextView) view.findViewById(R.id.tvDeliver);
        tvTotal = (TextView) view.findViewById(R.id.tvTotal);
        tvFinalTotal = (TextView) view.findViewById(R.id.tvFinalTotal);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        lstProduct.setAdapter(new CartGridAdapter(getActivity(), productCarts, onCartListener));
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
        tvTotal.setText(""+price);
        if (Utility.isNetworkConnected(getActivity())) {

            ProductListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                    mainApi = (MainApi) result;
                    pros = mainApi.getData();
                }

                @Override
                public void onFailure() {
                    Utility.showFailureDialog(getActivity(), false);
                }
            };

            AddCartItem task = new AddCartItem(getActivity(), ProductListener);
            task.execute(String.valueOf(position), String.valueOf(num), Utility.getCurrentTimeStamp());

        } else {
            Utility.showValidateDialog(
                    getResources().getString(R.string.failure_ws),
                    getActivity());
        }
    }
}
