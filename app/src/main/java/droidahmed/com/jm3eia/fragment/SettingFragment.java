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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.adapter.ProGridAdapter;
import droidahmed.com.jm3eia.api.ChangeUserData;
import droidahmed.com.jm3eia.api.GetHome;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.MainApi;
import droidahmed.com.jm3eia.model.ResponseChangeUserData;
import droidahmed.com.jm3eia.model.UpdateUser;
import droidahmed.com.jm3eia.start.MainActivity;

/**
 * Created by ahmed on 3/1/2016.
 */
public class SettingFragment extends Fragment {
    EditText edUserName,edName,edEmail,edPhone,edZone,edWidget,edStreet,edGada,edNum;
    Button btnSave,btnCancel;
    private OnProcessCompleteListener ProductListener;
    ResponseChangeUserData responseChange;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_setting,container,false);

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
        edPhone.setText(""+new StoreData(getActivity()).getMobile());
        edZone.setText(""+new StoreData(getActivity()).getZone());
        edStreet.setText(""+new StoreData(getActivity()).getStreet());
        edGada.setText("" + new StoreData(getActivity()).getGada());
        edNum.setText("" + new StoreData(getActivity()).getHouse());
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
                            responseChange = (ResponseChangeUserData) result;
                            responseChange.getData();

                            Toast.makeText(getActivity(), responseChange.getData(),Toast.LENGTH_LONG).show();

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
    public void onAttach(Context context) {
        super.onAttach(context);
     TextView tv = (TextView) getActivity().findViewById(R.id.textTitle);
        tv.setText(getActivity().getResources().getString(R.string.action_settings));
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
}
