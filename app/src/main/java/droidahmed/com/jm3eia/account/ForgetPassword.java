package droidahmed.com.jm3eia.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import droidahmed.com.jm3eia.R;

public class ForgetPassword extends Fragment {
Button btnForgetPass;
    EditText edForgetPass;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forget_password,container,false);

        btnForgetPass = (Button) view.findViewById(R.id.btnForgetPass);
        edForgetPass = (EditText) view.findViewById(R.id.edForgetPass);
        btnForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;

    }


}
