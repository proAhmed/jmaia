package droidahmed.com.jm3eia.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.adapter.CuListAdapter;
import droidahmed.com.jm3eia.controller.OnAddItem;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.AllProducts;
import droidahmed.com.jm3eia.model.CartItem;
import droidahmed.com.jm3eia.model.CartQuantity;
import droidahmed.com.jm3eia.model.ItemAddedAlready;
import droidahmed.com.jm3eia.model.ItemJson;
import droidahmed.com.jm3eia.model.ProductCart;
import droidahmed.com.jm3eia.start.MainActivity;
import droidahmed.com.jm3eia.start.SaveAuth;

/**
 * Created by ahmed on 3/15/2016.
 */
public class FragmentProductDetails extends Fragment implements OnCartListener,OnAddItem {
      GridView gridView;
      ImageView imgProduct;
    TextView tvName,tvCode,tvBrand,tvCategory;
    ArrayList<CartQuantity>related;
     ArrayList<CartQuantity> cartItems;
     ArrayList<ProductCart>productCart;
    static  double pricess;
    HashSet<AllProducts> baseHashSet;
    HashSet<ItemJson>itemHashSet;
    SaveAuth saveAuth;
    boolean add;
    ArrayList<CartQuantity>cartItemsModify;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        gridView = (GridView) view.findViewById(R.id.grid);
        final OnCartListener onCartListener = (OnCartListener) this;
        cartItems = new ArrayList<>();
        productCart = new ArrayList<>();
        baseHashSet = new HashSet<>();
        itemHashSet = new HashSet<>();
        cartItemsModify = new ArrayList<>();
        saveAuth = (SaveAuth) getActivity().getApplicationContext();

        Bundle bundle = getArguments();
        CartQuantity allProducts = (CartQuantity) bundle.getSerializable("products");
        Log.d("uuu", allProducts.getName());

        tvName = (TextView) view.findViewById(R.id.proName);
        tvCode = (TextView) view.findViewById(R.id.proCode);
        tvCategory = (TextView) view.findViewById(R.id.proCategory);
        tvBrand = (TextView) view.findViewById(R.id.proBrand);
        imgProduct = (ImageView) view.findViewById(R.id.imgPro);
        related = new ArrayList<>();
        related = (ArrayList<CartQuantity>) bundle.getSerializable("related-product");
        assert allProducts != null;
        tvName.setText(allProducts.getName()+"");
        tvCode.setText(allProducts.getCode()+"");
        tvCategory.setText(allProducts.getCategoryName()+"");
        tvBrand.setText(allProducts.getBrandName()+"");
        Picasso.with(getActivity()).load("http://jm3eia.com/" +allProducts.getPicture()).placeholder(R.drawable.place_holder_list).into(imgProduct);

        gridView.setAdapter(new CuListAdapter(getActivity(),related,onCartListener));

         return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

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
        try {
            if (!watch) {

                pricess += price;

                related.get(position);
                cartItems.add(related.get(position));
                Log.d("uuu", productCart.toString());
                ItemJson itemJson = new ItemJson(cartItems.get(position).getID(), num, Utility.getCurrentTimeStamp());
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
            } else if (isAdd()) {

                FragmentProductCart fragment = new FragmentProductCart();
                Bundle bundle = new Bundle();
                bundle.putSerializable("cart", productCart);
                bundle.putDouble("price", pricess);

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
        related.get(position).setcQuantity(num);
        saveAuth.setCartQuan(related);
    }
}
