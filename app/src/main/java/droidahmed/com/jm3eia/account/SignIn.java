package droidahmed.com.jm3eia.account;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.api.CheckOutToSign;
import droidahmed.com.jm3eia.controller.DatabaseHelper;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.fragment.FragmentProductCart;
import droidahmed.com.jm3eia.model.UserLogin;
import droidahmed.com.jm3eia.model.UserLoginResponse;
import droidahmed.com.jm3eia.start.MainActivity;
import droidahmed.com.jm3eia.start.SaveAuth;

public class SignIn extends AppCompatActivity {
    TextView tvRegister,tvForgetPass;
    EditText edUserName,edPass;
    Button btn;
    private OnProcessCompleteListener signListener;
    private UserLoginResponse registerUser;
    SaveAuth saveAuth;
Intent intent ;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        edUserName = (EditText) findViewById(R.id.edUserName);
        edPass = (EditText) findViewById(R.id.edPass);
intent = new Intent();
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        tvForgetPass = (TextView) findViewById(R.id.tvForget);
        saveAuth = (SaveAuth) getApplicationContext();
        databaseHelper = new DatabaseHelper(SignIn.this);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intent.getExtras()!=null){

                    Intent intent = new Intent(SignIn.this, SignUp.class);
                    intent.putExtra("CartAuth","CartAuth");
                    startActivity(intent);
                }else{
                    Intent intentRegister = new Intent(SignIn.this,SignUp.class);
                    startActivity(intentRegister);
                }

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
        if (registerUser.getData().getID() != null) {
            new StoreData(SignIn.this).savLogin("login");

            final UserLogin user = registerUser.getData();
            Utility.SaveData(SignIn.this,user.getUserName(),user.getAuthPassword(),user.getFullName(),user.getEmail()
                    ,user.getMobile(),user.getGada(),user.getWidget(),user.getZone(),user.getHouse(),user.getStreet());
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    SignIn.this);

            alertDialogBuilder
                    .setMessage(getResources().getString(R.string.success_login))
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
                                    if(user.isCartHasItems()){
                                        new StoreData(SignIn.this).saveCartAdded(1);
                                        intent.putExtra("CartAuth", "CartAuth");
                                    }

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
                            if(intent.getExtras()!=null){

                                FragmentProductCart fragment = new FragmentProductCart();
                                Bundle bundles = new Bundle();
                                bundles.putString("CartAuth", "CartAuth");
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.mainFragment, fragment)
                                        .commitAllowingStateLoss();
                            }else{
                                Intent intent = new Intent(SignIn.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }

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
        CheckOutToSign callWS = new CheckOutToSign(SignIn.this, signListener);
            if(getIntent().getExtras()!=null&&databaseHelper.getCart()!=null) {
Log.d("ooo1", "ppp");
            callWS.execute(userName, password, databaseHelper.getCart());
        }else{
            callWS.execute(userName, password,"");
                Log.d("ooo2", "ppp");

        }
    }

}
