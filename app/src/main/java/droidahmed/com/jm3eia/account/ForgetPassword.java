package droidahmed.com.jm3eia.account;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.api.ForgotPasswordApi;
import droidahmed.com.jm3eia.api.SignInApi;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.ForgetPassModel;
import droidahmed.com.jm3eia.model.UserLogin;
import droidahmed.com.jm3eia.model.UserLoginResponse;
import droidahmed.com.jm3eia.start.MainActivity;

public class ForgetPassword extends Fragment {
Button btnForgetPass;
    EditText edForgetPass;
    private OnProcessCompleteListener forgetListener;
    ForgetPassModel forgetP;
Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forget_password,container,false);
        context = getContext();
        btnForgetPass = (Button) view.findViewById(R.id.btnForgetPass);
        edForgetPass = (EditText) view.findViewById(R.id.edForgetPass);
        btnForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;

    }
    private void validate() {

        String email = edForgetPass.getText().toString();

        if ( !email.equals("")) {

            Utility.showValidateDialog(
                    getResources().getString(
                            R.string.registeration_validate1),context );

        }
     //   register(useName, password);

    }
    private void register(String userName,String password) {

        forgetListener = new OnProcessCompleteListener() {

            @Override
            public void onSuccess(Object result) {
                try {
                    forgetP = (ForgetPassModel) result;

                    if (forgetP != null) {
                        if (forgetP.getResult() != null
                                ) {

                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                    getActivity());

                            alertDialogBuilder
                                    .setMessage("success")
                                    .setCancelable(false)
                                    .setPositiveButton("OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(
                                                        DialogInterface dialog,
                                                        int id) {
                                                    alertDialogBuilder
                                                            .setCancelable(true);
//                                                Fragment fragment = new SginInFragment(
//                                                        Keys.SIGN_IN);
//
//                                                MainActivity.frgManager
//                                                        .beginTransaction()
//                                                        .replace(
//                                                                android.R.id.tabcontent,
//                                                                fragment)
//                                                        .commit();
                                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            });
                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();


                        }
                    }
                }catch (Exception e){
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            getActivity());

                    alertDialogBuilder
                            .setMessage(((UserLoginResponse) result).getError().toString())
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog,
                                                int id) {
                                            alertDialogBuilder
                                                    .setCancelable(true);
//                                                Fragment fragment = new SginInFragment(
//                                                        Keys.SIGN_IN);
//
//                                                MainActivity.frgManager
//                                                        .beginTransaction()
//                                                        .replace(
//                                                                android.R.id.tabcontent,
//                                                                fragment)
//                                                        .commit();
                                            if(getActivity().getIntent().getExtras()!=null){

                                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                                intent.putExtra("CartAuth","CartAuth");
                                                startActivity(intent);
                                            }else{
                                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                                startActivity(intent);
                                            }

                                        }
                                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }

            @Override
            public void onFailure() {
                Utility.showFailureDialog(context, true);
            }
        };
        ForgotPasswordApi callWS = new ForgotPasswordApi(getActivity(), forgetListener);

        callWS.execute(userName,password);
    }
}
