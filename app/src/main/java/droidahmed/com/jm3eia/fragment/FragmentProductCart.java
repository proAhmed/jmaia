package droidahmed.com.jm3eia.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.adapter.CuCartListAdapter;
import droidahmed.com.jm3eia.adapter.CuListAdapter;
import droidahmed.com.jm3eia.model.Product;
import droidahmed.com.jm3eia.start.MainActivity;

/**
 * Created by ahmed on 3/15/2016.
 */
public class FragmentProductCart extends Fragment {
    ListView lstProduct;
    EditText edSearch;
    ArrayList<Product>pro;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product,container,false);
        lstProduct = (ListView) view.findViewById(R.id.lstProduct);
        pro = new ArrayList<>();
        pro.add(new Product("","",1));
        pro.add(new Product("","",1));
        pro.add(new Product("","",1));
        pro.add(new Product("","",1));

        lstProduct.setAdapter(new CuCartListAdapter(getActivity(),pro));
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
   TextView tv= (TextView) getActivity().findViewById(R.id.textTitle);
        tv.setText(getResources().getString(R.string.my_cart));
    }
}
