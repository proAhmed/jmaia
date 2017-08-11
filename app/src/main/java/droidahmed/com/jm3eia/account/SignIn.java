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
import android.widget.Toast;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.api.CheckOutForSignUser;
import droidahmed.com.jm3eia.api.CheckOutToSign;
import droidahmed.com.jm3eia.controller.AddSignInCartService;
import droidahmed.com.jm3eia.controller.DatabaseHelper;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.CheckOutData;
import droidahmed.com.jm3eia.model.ResponseOfCheckOut;
import droidahmed.com.jm3eia.model.UserLogin;
import droidahmed.com.jm3eia.model.UserLoginResponse;
import droidahmed.com.jm3eia.start.MainActivity;
import droidahmed.com.jm3eia.start.SaveAuth;

public class SignIn extends AppCompatActivity {
    TextView tvRegister,tvForgetPass;
    EditText edUserName,edPass;
    Button btn;
    private OnProcessCompleteListener signListener,ProductListener;
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
                if(getIntent().getExtras()!=null){
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

        if (  password.equals("") || useName.equals("")  ) {

            Utility.showValidateDialog(
                    SignIn.this.getResources().getString(
                            R.string.registeration_validate1), SignIn.this);

        }else{
            register(useName, password);

        }




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

                                    if (registerUser.getData().isCartHasItems()) {
                                        Log.d("iii","call");
                                         callCheck();
                                    } else {
                                        if(getIntent().getExtras()==null&&databaseHelper.getCart()!=null) {
                                            Log.d("iii1","add");
                                            Intent intent = new Intent(SignIn.this,AddSignInCartService.class);
                                        startService(new Intent(intent));

                                        }else{
                                            Log.d("iii2","intent");

                                            Intent intent = new Intent(SignIn.this, MainActivity.class);
                                            startActivity(intent);
                                        }

                                    }
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

                            if(getIntent().getExtras()!=null){
                                callCheck();

                            }else {
                                Intent intent = new Intent(SignIn.this, MainActivity.class);
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
                Utility.showFailureDialogLogin(SignIn.this, true);
            }
        };
        CheckOutToSign callWS = new CheckOutToSign(SignIn.this, signListener);
            if(getIntent().getExtras()!=null&&databaseHelper.getCart()!=null) {
             callWS.execute(userName, password, databaseHelper.getCart());
        }else{
            callWS.execute(userName, password, "");

        }
    }
    private void callCheck(){
//        if (Utility.isNetworkConnected(SignIn.this)) {
//
//            ProductListener = new OnProcessCompleteListener() {
//
//                @Override
//                public void onSuccess(Object result) {
//                    ResponseOfCheckOut  checkResponse = (ResponseOfCheckOut) result;
//                    CheckOutData checkOutDatas =   checkResponse.getData();
//                    try {
//                         Toast.makeText(SignIn.this, checkOutDatas.getMessage(), Toast.LENGTH_LONG).show();
//
//                    }catch (Exception e){
//
//                    }
//                    databaseHelper.delete();
//                    databaseHelper.deleteCart();
//                    databaseHelper.deleteCartAdd();
//                    databaseHelper.remove();
//                    databaseHelper.removeCart();
//                    databaseHelper.removeCartAdd();
//                    new StoreData(SignIn.this).saveCartAdded(0);
//
//                    new StoreData(SignIn.this).setDialogType("");
//                    Intent intent = new Intent(SignIn.this, MainActivity.class);
//
//                    startActivity(intent);
//                }
//
//                @Override
//                public void onFailure() {
//                    Utility.showFailureDialog(SignIn.this, false);
//                }
//            };
//
//            CheckOutForSignUser task = new CheckOutForSignUser(SignIn.this, ProductListener);
//            task.execute(new StoreData(SignIn.this).getAuthName(),new StoreData(SignIn.this).getAuthPass());
//
//        } else {
//            Utility.showValidateDialog(
//                    getResources().getString(R.string.failure_ws),
//                    SignIn.this);
//        }
        Intent intent = new Intent(SignIn.this, MainActivity.class);
        intent.putExtra("CartAuth","CartAuth");

        startActivity(intent);
    }

    private void add(){
//        if (Utility.isNetworkConnected(SignIn.this)) {
//
//            ProductListener = new OnProcessCompleteListener() {
//
//                @Override
//                public void onSuccess(Object result) {
//                    ResponseOfCheckOut  checkResponse = (ResponseOfCheckOut) result;
//                    CheckOutData checkOutDatas =   checkResponse.getData();
//                    try {
//                        Toast.makeText(SignIn.this, checkOutDatas.getMessage(), Toast.LENGTH_LONG).show();
//
//                    }catch (Exception e){
//
//                    }
//
//                }
//
//                @Override
//                public void onFailure() {
//                    Utility.showFailureDialog(SignIn.this, false);
//                }
//            };
//
//
//        } else {
//            Utility.showValidateDialog(
//                    getResources().getString(R.string.failure_ws),
//                    SignIn.this);
//        }
        Intent intent = new Intent(SignIn.this, MainActivity.class);
        intent.putExtra("cart","cart");

        startActivity(intent);

    }
}
