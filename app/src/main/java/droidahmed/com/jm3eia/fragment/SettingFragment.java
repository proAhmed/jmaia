package droidahmed.com.jm3eia.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.adapter.ProGridAdapter;
import droidahmed.com.jm3eia.api.ChangeUserData;
import droidahmed.com.jm3eia.api.GetHome;
import droidahmed.com.jm3eia.controller.DatabaseHelper;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.MainApi;
import droidahmed.com.jm3eia.model.MainChangeUserData;
import droidahmed.com.jm3eia.model.ResponseChangeUserData;
import droidahmed.com.jm3eia.model.UpdateUser;
import droidahmed.com.jm3eia.start.MainActivity;

/**
 * Created by ahmed on 3/1/2016.
 */
public class SettingFragment extends Fragment {
    EditText edUserName,edName,edEmail,edPhone,edZone,edWidget,edStreet,edGada,edNum,edUpdatePass;
    Button btnSave,btnCancel;
    private OnProcessCompleteListener ProductListener;
    MainChangeUserData responseChange;
    DatabaseHelper databaseHelper;
    TextView tvNum;
    RelativeLayout reCart;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_setting,container,false);
        edUpdatePass = (EditText) view.findViewById(R.id.edUpdatePass);
        edName = (EditText) view.findViewById(R.id.edUpdateFullName);
        edUserName = (EditText) view.findViewById(R.id.edUpdateUserName);
        edEmail = (EditText) view.findViewById(R.id.edUpdateEmail);
        edPhone = (EditText) view.findViewById(R.id.edUpdatePhone);
        edZone = (EditText) view.findViewById(R.id.edUpdateZone);
        edWidget = (EditText) view.findViewById(R.id.edUpdateWidget);
        edStreet = (EditText) view.findViewById(R.id.edUpdateStreet);
        edGada = (EditText) view.findViewById(R.id.edGada);
        edNum = (EditText) view.findViewById(R.id.edUpdateNum);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        edName.setText(""+new StoreData(getActivity()).getFullName());
        edUserName.setText(""+new StoreData(getActivity()).getAuthName());
        edEmail.setText(""+new StoreData(getActivity()).getEmail());
        edWidget.setText(""+new StoreData(getActivity()).getWidget());
        tvNum = (TextView) view.findViewById(R.id.tvNum);
        reCart = (RelativeLayout) view.findViewById(R.id.reCart);
        databaseHelper = new DatabaseHelper(getActivity());
        addNum();
        edPhone.setText(""+new StoreData(getActivity()).getMobile());
        edZone.setText(""+new StoreData(getActivity()).getZone());
        edStreet.setText(""+new StoreData(getActivity()).getStreet());
        edGada.setText("" + new StoreData(getActivity()).getGada());
        edNum.setText("" + new StoreData(getActivity()).getHouse());
        edUpdatePass.setText("" + new StoreData(getActivity()).getAuthPass());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateUser updateUser =new UpdateUser(edName.getText().toString(),
                        edUserName.getText().toString(),
                        edEmail.getText().toString(),
                        edPhone.getText().toString(),edZone.getText().toString(),
                        edWidget.getText().toString(),edStreet.getText().toString(),
                        edGada.getText().toString(),edNum.getText().toString(),
                        new StoreData(getActivity()).getAuthName(), new StoreData(getActivity()).getAuthPass() );

                if (Utility.isNetworkConnected(getActivity())) {

                    ProductListener = new OnProcessCompleteListener() {

                        @Override
                        public void onSuccess(Object result) {
                            responseChange = (MainChangeUserData) result;
                            responseChange.getData();

                            Toast.makeText(getActivity(), "تم تحديث البيانات بنجاح",Toast.LENGTH_LONG).show();
                            new StoreData(getActivity()).saveAuthName(edUserName.getText().toString());
                            new StoreData(getActivity()).saveEmail(edEmail.getText().toString());
                            new StoreData(getActivity()).saveWidget(edWidget.getText().toString());
                            new StoreData(getActivity()).saveMobile(edPhone.getText().toString());
                            new StoreData(getActivity()).saveZone(edZone.getText().toString());
                            new StoreData(getActivity()).saveStreet(edStreet.getText().toString());
                            new StoreData(getActivity()).saveGada(edGada.getText().toString());
                            new StoreData(getActivity()).saveHouse(edNum.getText().toString());
                            new StoreData(getActivity()).saveAuthPass(edUpdatePass.getText().toString());


                        }

                        @Override
                        public void onFailure() {
                            Utility.showFailureDialog(getActivity(), false);
                        }
                    };

                    ChangeUserData task = new ChangeUserData(getActivity(), ProductListener);
                    task.execute(updateUser);

                } else {
                    Utility.showValidateDialog(
                            getResources().getString(R.string.failure_ws),
                            getActivity());
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;

    }
    @Override
    public void onResume() {
        super.onResume();
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
    public void onAttach(Context context) {
        super.onAttach(context);
     TextView tv = (TextView) getActivity().findViewById(R.id.textTitle);
        tv.setText(getActivity().getResources().getString(R.string.action_settings));
        ImageView img = (ImageView) getActivity().findViewById(R.id.logo);
        img.setVisibility(View.INVISIBLE);
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
                if (mainActivity != null)
                    mainActivity.toggle();
            }
        });
    }
    int value;
    private void addNum(){
        if(databaseHelper.getCart()!=null){
            value = databaseHelper.getCart().size();

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
}
