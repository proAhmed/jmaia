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
import android.widget.ImageView;
import android.widget.Toast;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.api.ForgotPasswordApi;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.ForgetPassModel;
import droidahmed.com.jm3eia.model.UserLoginResponse;
import droidahmed.com.jm3eia.start.MainActivity;

public class ForgetPass extends AppCompatActivity {
    Button btnForgetPass;
    EditText edForgetPass;
    private OnProcessCompleteListener forgetListener;
    ForgetPassModel forgetP;
    ImageView imgClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);


        imgClose = (ImageView) findViewById(R.id.imageClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnForgetPass = (Button) findViewById(R.id.btnForgetPass);
        edForgetPass = (EditText) findViewById(R.id.edForgetPass);
        btnForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

    }
    private void validate() {

        String email = edForgetPass.getText().toString();

        if ( email.equals("")) {

            Utility.showValidateDialog(
                    getResources().getString(
                            R.string.registeration_validate1), ForgetPass.this);

        }else {
            forgetPass(email);
        }
        //   register(useName, password);

    }
    private void forgetPass(String email) {

        forgetListener = new OnProcessCompleteListener() {

            @Override
            public void onSuccess(Object result) {
                ForgetPassModel model = (ForgetPassModel) result;
                try {
                    forgetP = (ForgetPassModel) result;
                    if(forgetP.isSuccess()){
                    Toast.makeText(ForgetPass.this,model.getResult(),Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(ForgetPass.this,model.getError(),Toast.LENGTH_LONG).show();

                }
                    if (forgetP != null) {
                        if (forgetP.getResult() != null
                                ) {

                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                    ForgetPass.this);

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
                                                    Intent intent = new Intent(ForgetPass.this, MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            });
                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();


                        }
                    }
                }catch (Exception e){
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            ForgetPass.this);

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
                                            if(ForgetPass.this.getIntent().getExtras()!=null){

                                                Intent intent = new Intent(ForgetPass.this, MainActivity.class);
                                                intent.putExtra("CartAuth","CartAuth");
                                                startActivity(intent);
                                            }else{
                                                Intent intent = new Intent(ForgetPass.this, MainActivity.class);
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
                Utility.showFailureDialog(ForgetPass.this, true);
            }
        };
        ForgotPasswordApi callWS = new ForgotPasswordApi(ForgetPass.this, forgetListener);

        callWS.execute(email);
    }

}
