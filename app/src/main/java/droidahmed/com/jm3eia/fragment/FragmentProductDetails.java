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
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.model.AllProducts;
import droidahmed.com.jm3eia.model.Product;
import droidahmed.com.jm3eia.start.MainActivity;

/**
 * Created by ahmed on 3/15/2016.
 */
public class FragmentProductDetails extends Fragment {
     EditText edSearch;
    ExpandableListAdapter listAdapter;
    GridView gridView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ImageView imgProduct;
    TextView tvName,tvCode,tvBrand,tvCategory;
    ArrayList<AllProducts>related;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        gridView = (GridView) view.findViewById(R.id.grid);
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

        gridView.setAdapter(new CuListAdapter(getActivity(),related));



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

}
