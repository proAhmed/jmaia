package droidahmed.com.jm3eia.account;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.api.Register;
import droidahmed.com.jm3eia.api.SignInApi;
import droidahmed.com.jm3eia.api.User;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.UserLogin;
import droidahmed.com.jm3eia.model.UserLoginResponse;
import droidahmed.com.jm3eia.model.UserResponse;
import droidahmed.com.jm3eia.start.MainActivity;

public class SignIn extends AppCompatActivity {
    TextView tvRegister,tvForgetPass;
    EditText edUserName,edPass;
    Button btn;
    private OnProcessCompleteListener signListener;
    private UserLoginResponse registerUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        edUserName = (EditText) findViewById(R.id.edUserName);
        edPass = (EditText) findViewById(R.id.edPass);

        tvRegister = (TextView) findViewById(R.id.tvRegister);
        tvForgetPass = (TextView) findViewById(R.id.tvForget);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(SignIn.this,SignUp.class);
                startActivity(intentRegister);
            }
        });

        tvForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this,ForgetPass.class);
                startActivity(intent);
            }
        });
        btn = (Button) findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edPass.getText().toString().equals("")&&!edUserName.getText().toString().equals("")){
                    validate();
                }
            }
        });

    }
    private void validate() {

         String password = edPass.getText().toString();
         String useName = edUserName.getText().toString();

        if (  password == null || useName == null  ) {

            Utility.showValidateDialog(
                    SignIn.this.getResources().getString(
                            R.string.registeration_validate1), SignIn.this);

        }
            register(useName,password);

    }

    private void register(String userName,String password) {

        signListener = new OnProcessCompleteListener() {

            @Override
            public void onSuccess(Object result) {
try {
    registerUser = (UserLoginResponse) result;

    if (registerUser != null) {
        if (registerUser.getData().getID() != null
                ) {
            UserLogin user = registerUser.getData();
            Utility.SaveData(SignIn.this,user.getUserName(),user.getAuthPassword(),user.getFullName(),user.getEmail()
                    ,user.getMobile(),user.getGada(),user.getWidget(),user.getZone(),user.getHouse(),user.getStreet());
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    SignIn.this);

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
                                    Intent intent = new Intent(SignIn.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        } else {
            if (registerUser.getData().getID() == null
                    || registerUser.getData().getID().contains("null")) {
                String message = " ";

                if (registerUser.getData().getFullName() != null)
                    message += registerUser.getData().getFullName();

                if (registerUser.getData().getUserName() != null)
                    message += registerUser.getData().getUserName();
                if (registerUser.getData().getEmail() != null)
                    message += registerUser.getData().getEmail();
                if (registerUser.getData().getAuthPassword() != null)
                    message += registerUser.getData().getAuthPassword();

                Utility.showValidateDialog(message, SignIn.this);

                }
             }
         }
          }catch (Exception e){
    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
            SignIn.this);

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
                            Intent intent = new Intent(SignIn.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
      AlertDialog alertDialog = alertDialogBuilder.create();
      alertDialog.show();
    }
            }

            @Override
            public void onFailure() {
                Utility.showFailureDialog(SignIn.this, true);
            }
        };
        SignInApi callWS = new SignInApi(SignIn.this, signListener);

        callWS.execute(userName,password);
    }

}
