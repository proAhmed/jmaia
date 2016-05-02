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
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.adapter.CuListAdapter;
import droidahmed.com.jm3eia.adapter.ExpandableListAdapter;
import droidahmed.com.jm3eia.adapter.ProGridAdapter;
import droidahmed.com.jm3eia.api.AddCartItem;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
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
public class FragmentProductDetails extends Fragment implements OnCartListener {
     EditText edSearch;
    ExpandableListAdapter listAdapter;
    GridView gridView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ImageView imgProduct;
    TextView tvName,tvCode,tvBrand,tvCategory;
    ArrayList<AllProducts>related;
    AllProducts[] pro;
    private OnProcessCompleteListener ProductListener;
    MainApi mainApi;
    ArrayList<CartItem> cartItems;
    CartItemResponse cartItemResponse;
    ArrayList<ProductCart>productCart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        gridView = (GridView) view.findViewById(R.id.grid);
        final OnCartListener onCartListener = (OnCartListener) this;
        cartItems = new ArrayList<>();
        productCart = new ArrayList<>();

        Bundle bundle = getArguments();
      AllProducts allProducts = (AllProducts) bundle.getSerializable("products");
        Log.d("uuu", allProducts.getName());

        tvName = (TextView) view.findViewById(R.id.proName);
        tvCode = (TextView) view.findViewById(R.id.proCode);
        tvCategory = (TextView) view.findViewById(R.id.proCategory);
        tvBrand = (TextView) view.findViewById(R.id.proBrand);
        imgProduct = (ImageView) view.findViewById(R.id.imgPro);
        related = new ArrayList<>();
        related = (ArrayList<AllProducts>) bundle.getSerializable("related-product");
        assert allProducts != null;
        tvName.setText(allProducts.getName()+"");
        tvCode.setText(allProducts.getCode()+"");
        tvCategory.setText(allProducts.getCategoryName()+"");
        tvBrand.setText(allProducts.getBrandName()+"");
        Picasso.with(getActivity()).load("http://jm3eia.com/" +allProducts.getPicture()).placeholder(R.drawable.place_holder_list).into(imgProduct);

        gridView.setAdapter(new CuListAdapter(getActivity(),related,onCartListener));



        // Listview Group click listener


        // Listview Group expanded listener


        // Listview Group collasped listener


        // Listview on child click listener

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
                mainActivity.showSecondaryMenu();
            }
        });

        getActivity().findViewById(R.id.imageToggleCategory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity mainActivity = (MainActivity) getActivity();
                if(mainActivity!=null)
                    mainActivity.toggle();
            }
        });

    }

    @Override
    public void onAddCart(int position, int num,boolean watch,double price) {

        related.get(position);
        productCart.add(new ProductCart(related.get(position), num));
        Log.d("uuu",productCart.toString());
        if(watch==true){
            FragmentProductCart  fragment =   new FragmentProductCart();
            Bundle bundle = new Bundle();
            bundle.putSerializable("cart",productCart);
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
