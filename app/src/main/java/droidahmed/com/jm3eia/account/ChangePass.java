package droidahmed.com.jm3eia.account;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.api.ChangePassword;
import droidahmed.com.jm3eia.api.SignInApi;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.ChangePassOutPut;
import droidahmed.com.jm3eia.model.UserLogin;
import droidahmed.com.jm3eia.model.UserLoginResponse;
import droidahmed.com.jm3eia.model.UserResponse;
import droidahmed.com.jm3eia.start.MainActivity;

public class ChangePass extends AppCompatActivity {
EditText edOldPass,edNewPass,edRePass;
    Button btnChangePass;
    private OnProcessCompleteListener changeListener;
    private ChangePassOutPut changePass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        edOldPass = (EditText) findViewById(R.id.edOldPass);
        edNewPass = (EditText) findViewById(R.id.edNewPass);
        edRePass = (EditText) findViewById(R.id.edRePass);
        btnChangePass = (Button) findViewById(R.id.btnChangePass);
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

    }
    private void validate() {

        String strOld = edOldPass.getText().toString();
        String strNew = edNewPass.getText().toString();
        String strRePass = edRePass.getText().toString();

        if (  strOld.equals("") || strNew.equals("") || strRePass .equals("")  ) {

            Utility.showValidateDialog(
                    ChangePass.this.getResources().getString(
                            R.string.registeration_validate1), ChangePass.this);

        }
        register(strOld, strNew,strRePass);

    }
    private void register(String oldPass,String newPass,String reNewPass) {

        changeListener = new OnProcessCompleteListener() {

            @Override
            public void onSuccess(Object result) {
                try {
                    changePass = (ChangePassOutPut) result;

                    if (changePass != null) {
                        if (changePass.getData() != null
                                ) {
                             final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                    ChangePass.this);

                            alertDialogBuilder
                                    .setMessage(changePass.getData())
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
                                                    Intent intent = new Intent(ChangePass.this, MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            });
                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();

                        } else {

                                if (changePass.getError() != null) {
                                    String message = changePass.getError().toString();

                                    Utility.showValidateDialog(message, ChangePass.this);
                                }
                            }

                    }
                }catch (Exception e){
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            ChangePass.this);

                    alertDialogBuilder
                            .setMessage(((UserResponse) result).getError().toString())
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
                                            Intent intent = new Intent(ChangePass.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }

            @Override
            public void onFailure() {
                Utility.showFailureDialog(ChangePass.this, true);
            }
        };
        ChangePassword callWS = new ChangePassword(ChangePass.this, changeListener);

        callWS.execute(new StoreData(ChangePass.this).getAuthName(),new StoreData(ChangePass.this).getAuthPass(),
                oldPass,newPass,reNewPass);
    }
}
