package droidahmed.com.jm3eia.fragment;

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

import java.util.ArrayList;

import droidahmed.com.jm3eia.R;

/**
 * Created by ahmed on 3/1/2016.
 */
public class SettingFragment extends Fragment {
    EditText edUserName,edName,edPass,edEmail,edPhone,edAddressOne,edAddressTwo,edAddressStreet,edAddressNum;
    Button btnSave,btnCancel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.activity_sign_up,container,false);




        return view;

    }

}
