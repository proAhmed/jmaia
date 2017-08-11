package droidahmed.com.jm3eia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.adapter.HistoryAdapter;
import droidahmed.com.jm3eia.api.GetHistoryCart;
import droidahmed.com.jm3eia.controller.DatabaseHelper;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.CartItemResponse;
import droidahmed.com.jm3eia.model.HistoryModel;
import droidahmed.com.jm3eia.model.HistoryModelMain;

/**
 * Created by ahmed on 1/21/2017.
 */
public class HistoryCartFragment extends Fragment {

    ListView listView;
    private OnProcessCompleteListener ProductListener;
    ArrayList<HistoryModel> historyModels;
    ImageView imgCart;
    TextView tvNum;
    DatabaseHelper databaseHelper;
    RelativeLayout reCart;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history,container,false);
        declare(view);
        getHistory();
        return view;
    }

    private void declare(View view){
        listView = (ListView) view.findViewById(R.id.lstHistory);
        tvNum = (TextView) view.findViewById(R.id.tvNum);
        reCart = (RelativeLayout) view.findViewById(R.id.reCart);
        databaseHelper = new DatabaseHelper(getActivity());
        addNum();
        historyModels = new ArrayList<>();
        imgCart = (ImageView) view.findViewById(R.id.imgCart);
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction().add(R.id.mainFragment,new FragmentProductCart(),"").commit();
            }
        });
    }

    private void getHistory(){
        if (Utility.isNetworkConnected(getActivity())) {

            ProductListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                  HistoryModelMain historyModelMain = (HistoryModelMain) result;
//
                    historyModels =  historyModelMain.getData();
                    Log.d("rrroooo",""+historyModels.size());
                    listView.setAdapter(new HistoryAdapter(getActivity(),historyModels));

                }

                @Override
                public void onFailure() {
                    Utility.showFailureDialog(getActivity(), false);
                }
            };

            GetHistoryCart task = new GetHistoryCart(getActivity(), ProductListener);
            task.execute();

        } else {
            Utility.showValidateDialog(
                    getResources().getString(R.string.failure_ws),
                    getActivity());
        }
    }
    int value;
    private void addNum(){
        if(databaseHelper.getCart()!=null){
            value = databaseHelper.getCart().size();
        }
        if(value!=0){
            tvNum.setText(value+"");
            reCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .add(R.id.mainFragment,new FragmentProductCart(),"").addToBackStack("").commit();
                }
            });
        }
    }
}
