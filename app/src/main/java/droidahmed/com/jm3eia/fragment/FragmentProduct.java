package droidahmed.com.jm3eia.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.adapter.CuListAdapter;
import droidahmed.com.jm3eia.adapter.CustomAdapter;
import droidahmed.com.jm3eia.adapter.ProGridAdapter;
import droidahmed.com.jm3eia.api.AddCartItem;
import droidahmed.com.jm3eia.api.CategoriesByParent;
import droidahmed.com.jm3eia.api.GetHome;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.OnItemListener;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.AllProducts;
import droidahmed.com.jm3eia.model.CartItem;
import droidahmed.com.jm3eia.model.CartItemResponse;
import droidahmed.com.jm3eia.model.MainApi;
import droidahmed.com.jm3eia.model.MainCategory;
import droidahmed.com.jm3eia.model.Product;
import droidahmed.com.jm3eia.model.ProductCart;
import droidahmed.com.jm3eia.start.MainActivity;

/**
 * Created by ahmed on 3/15/2016.
 */
public class FragmentProduct extends Fragment implements OnItemListener,OnCartListener {
     EditText edSearch;
    AllProducts[] pro;
    private OnProcessCompleteListener ProductListener;
     MainApi mainApi;
    private GridView lstProduct;
      ArrayList<AllProducts> arrayList;
    ArrayList<CartItem> cartItems;
    CartItemResponse cartItemResponse;
    ArrayList<ProductCart>productCart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        lstProduct = (GridView) view.findViewById(R.id.lstProduct);
       StoreData storeData =   new StoreData(getActivity());
        final OnItemListener onItemListener = (OnItemListener) this;
        final OnCartListener onCartListener = (OnCartListener) this;
        cartItems = new ArrayList<>();
        productCart = new ArrayList<>();
        //        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<AllProducts>>() {}.getType();
     //   ArrayList<AllProducts> arrayList = gson.fromJson(category, type);
        final Bundle bundle = getArguments();



//        lstProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (Utility.isNetworkConnected(getActivity())) {

            ProductListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                    mainApi = (MainApi) result;
                    pro=   mainApi.getData();
                    //   Log.d("iiii",pro.toString());
//                    Gson gson = new Gson();
//                    String json = gson.toJson(pro);
//                    StoreData storeData = new StoreData(MainActivity.this);
//                    storeData.saveData(json);

                    arrayList=    new ArrayList<>(Arrays.asList(pro));
                    lstProduct.setAdapter(new ProGridAdapter(getActivity(), arrayList,onItemListener,onCartListener));

                    lstProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View v,
                                                int position, long id) {

                        }
                    });



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
    public void onAddCart(int position, int num, boolean watch,double price) {
        arrayList.get(position);
        productCart.add(new ProductCart(arrayList.get(position), num));
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
