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
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.adapter.CuListAdapter;
import droidahmed.com.jm3eia.adapter.CustomAdapter;
import droidahmed.com.jm3eia.adapter.ProGridAdapter;
import droidahmed.com.jm3eia.api.GetHome;
import droidahmed.com.jm3eia.controller.OnItemListener;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.AllProducts;
import droidahmed.com.jm3eia.model.MainApi;
import droidahmed.com.jm3eia.model.Product;
import droidahmed.com.jm3eia.start.MainActivity;

/**
 * Created by ahmed on 3/15/2016.
 */
public class FragmentProduct extends Fragment implements OnItemListener {
     EditText edSearch;
    AllProducts[] pro;
    private OnProcessCompleteListener ProductListener;
     MainApi mainApi;
    private GridView lstProduct;
      ArrayList<AllProducts> arrayList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        lstProduct = (GridView) view.findViewById(R.id.lstProduct);
       StoreData storeData =   new StoreData(getActivity());
 //        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<AllProducts>>() {}.getType();
     //   ArrayList<AllProducts> arrayList = gson.fromJson(category, type);
        final Bundle bundle = getArguments();
        arrayList = (ArrayList<AllProducts>) bundle.getSerializable("array");
                            Gson gson = new Gson();
                    String json = gson.toJson(pro);
                     storeData.saveData(json);
        lstProduct.setAdapter(new ProGridAdapter(getActivity(), arrayList,this));

        lstProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity(),
                        ""+position, Toast.LENGTH_SHORT).show();
            }
        });

//        lstProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
}
