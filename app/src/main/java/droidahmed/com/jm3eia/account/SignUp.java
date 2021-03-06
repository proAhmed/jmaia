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
import android.widget.ImageView;
import android.widget.Toast;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.api.CheckOutForSignUser;
import droidahmed.com.jm3eia.api.CheckOutRegister;
import droidahmed.com.jm3eia.controller.AddSignInCartService;
import droidahmed.com.jm3eia.controller.DatabaseHelper;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.CheckOutData;
import droidahmed.com.jm3eia.model.InputNewUser;
import droidahmed.com.jm3eia.model.ResponseOfCheckOut;
import droidahmed.com.jm3eia.model.User;
import droidahmed.com.jm3eia.model.UserLogin;
import droidahmed.com.jm3eia.model.UserResponse;
import droidahmed.com.jm3eia.start.MainActivity;
import droidahmed.com.jm3eia.start.SaveAuth;

public class SignUp extends AppCompatActivity {
    EditText edUserName, edName, edPass, edEmail, edPhone, edAddressOne, edAddressTwo, edAddressStreet, edAddressNum, edGada;
    Button btnSave;
    InputNewUser inputNewUser;
    private OnProcessCompleteListener registerListener;
    private UserResponse registerUser;
    SaveAuth saveAuth;
    ImageView imgClose;
    DatabaseHelper databaseHelper;
    boolean checkExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edUserName = (EditText) findViewById(R.id.edRegUserName);
        saveAuth = (SaveAuth) getApplicationContext();
        imgClose = (ImageView) findViewById(R.id.imageClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edName = (EditText) findViewById(R.id.edRegName);
        edPass = (EditText) findViewById(R.id.edRegPass);
        edEmail = (EditText) findViewById(R.id.edRegEmail);
        edPhone = (EditText) findViewById(R.id.edRegPhone);
        edAddressOne = (EditText) findViewById(R.id.edRegAddressOne);
        edAddressTwo = (EditText) findViewById(R.id.edRegAddressTwo);
        edAddressStreet = (EditText) findViewById(R.id.edRegAddressStreet);
        edAddressNum = (EditText) findViewById(R.id.edRegNum);
        edGada = (EditText) findViewById(R.id.edGada);
        btnSave = (Button) findViewById(R.id.btnSave);
        databaseHelper = new DatabaseHelper(SignUp.this);
        if (getIntent().getExtras() != null) {
            checkExtra = true;
        } else {
            checkExtra = false;
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                inputNewUser = new InputNewUser(edName.getText().toString(), edUserName.getText().toString(),
                        edPass.getText().toString(), edEmail.getText().toString(),
                        edPhone.getText().toString(), edAddressOne.getText().toString(),
                        edAddressTwo.getText().toString(), edAddressStreet.getText().toString(),
                        edGada.getText().toString(), edAddressNum.getText().toString());
                validate();
            }
        });
    }

    private void validate() {

        String email = edEmail.getText().toString();
        String password = edPass.getText().toString();
        String mobile = edPhone.getText().toString();
        String useName = edUserName.getText().toString();
        String fullName = edName.getText().toString();

        if (email == null || password == null || mobile == null
                || useName == null || fullName == null) {

            Utility.showValidateDialog(
                    SignUp.this.getResources().getString(
                            R.string.registeration_validate1), SignUp.this);

        } else if (!Utility.isEmailValid(email)) {
//
            Utility.showValidateDialog(
                    SignUp.this.getResources().getString(R.string.invalid_mail),
                    SignUp.this);

        } else if (password.length() < 8) {
            Utility.showValidateDialog(
                    SignUp.this.getResources()
                            .getString(R.string.invalid_password), SignUp.this);

        } else if (email != null && password != null && mobile != null
                && useName != null && fullName != null
                && Utility.isEmailValid(email)) {
            register();
        }
    }

    private void register() {

        registerListener = new OnProcessCompleteListener() {

            @Override
            public void onSuccess(Object result) {
                try {
                    registerUser = (UserResponse) result;

                    if (registerUser != null) {
                        if (registerUser.getData().getID() != null) {
                            final User user = registerUser.getData();
                            Utility.SaveData(SignUp.this, user.getUserName(), user.getAuthPassword(), user.getFullName(), user.getEmail()
                                    , user.getMobile(), user.getGada(), user.getWidget(), user.getZone(), user.getHouse(), user.getStreet());
                            new StoreData(SignUp.this).savLogin("login");
                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SignUp.this);

                            alertDialogBuilder
                                    .setMessage(getResources().getString(R.string.success_register))
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
                                                        callCheck();
                                                        Log.d("iii", "call");

                                                    } else {
                                                        if (getIntent().getExtras() == null && databaseHelper.getCart() != null) {
                                                            Log.d("iii1", "add");
                                                            Intent intent = new Intent(SignUp.this, AddSignInCartService.class);
                                                            startService(new Intent(intent));

                                                        } else {
                                                            Log.d("iii2", "intent");

                                                            Intent intent = new Intent(SignUp.this, MainActivity.class);
                                                            startActivity(intent);
                                                            finish();
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
                                if (registerUser.getData().getPassword() != null)
                                    message += registerUser.getData().getPassword();

                                Utility.showValidateDialog(message, SignUp.this);

                            }
                        }
                    }
                } catch (Exception e) {
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            SignUp.this);

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
                                            if (getIntent().getExtras() != null) {
                                                callCheck();

                                            } else {
                                                Intent intent = new Intent(SignUp.this, MainActivity.class);
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
                Utility.showFailureDialogLogin(SignUp.this, true);
            }
        };
        CheckOutRegister callWS = new CheckOutRegister(SignUp.this, registerListener);

        if (checkExtra && databaseHelper.getCart() != null) {

            callWS.execute(inputNewUser, databaseHelper.getCart());
        } else {
            callWS.execute(inputNewUser, "");

        }
    }

    private void callCheck() {

        Intent intent = new Intent(SignUp.this, MainActivity.class);
        intent.putExtra("CartAuth", "CartAuth");

        startActivity(intent);


    }
}
