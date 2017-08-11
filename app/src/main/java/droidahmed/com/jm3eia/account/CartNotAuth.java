package droidahmed.com.jm3eia.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.api.AddCartItemNotAuth;
import droidahmed.com.jm3eia.api.CheckOutForSignUser;
import droidahmed.com.jm3eia.controller.DatabaseHelper;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.CheckOutData;
import droidahmed.com.jm3eia.model.ResponseOfCheckOut;
import droidahmed.com.jm3eia.start.MainActivity;
import droidahmed.com.jm3eia.start.SaveAuth;

public class CartNotAuth extends AppCompatActivity {
EditText edName,edMobile,edZone,edWidget,edStreet,edGada,edNum;
    JSONArray jsonArray;
    JSONObject jsonObject;
    Button btn;
    SaveAuth saveAuth;
    JSONObject jsonObjectSend;
    private OnProcessCompleteListener ProductListener;
        DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_not_auth);
        edName = (EditText) findViewById(R.id.edCartUserName);
        edMobile = (EditText) findViewById(R.id.edCartPhone);
        edZone = (EditText) findViewById(R.id.edCartZone);
        edWidget = (EditText) findViewById(R.id.edCartWidget);
        edStreet = (EditText) findViewById(R.id.edCartStreet);
        edGada = (EditText) findViewById(R.id.edCartGada);
        edNum = (EditText) findViewById(R.id.edCartNum);
        saveAuth = (SaveAuth) getApplicationContext();
        btn = (Button) findViewById(R.id.btnSave);
        jsonObject = new JSONObject();
        databaseHelper = new DatabaseHelper(CartNotAuth.this);
        jsonObjectSend = new JSONObject();
             jsonArray = new JSONArray();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                   if( check()) {
                       jsonObject.put("FullName", edName.getText().toString());
                       jsonObject.put("Mobile", edMobile.getText().toString());
                       jsonObject.put("Zone", edZone.getText().toString());
                       jsonObject.put("Widget", edWidget.getText().toString());
                       jsonObject.put("Street", edStreet.getText().toString());
                       jsonObject.put("Gada", edGada.getText().toString());
                       jsonObject.put("House", edNum.getText().toString());
                      //  jsonArray.put(saveAuth.getJsonProduct());
                        jsonObjectSend.put("VisitorData", jsonObject);
                       jsonObjectSend.put("CartItems", saveAuth.getJsonProduct());


                     //  saveAuth.setJsonVisitor(jsonObject);

//                       FragmentProductCart fragment = new FragmentProductCart();
//                       Bundle bundles = new Bundle();
//                       bundles.putString("CartAuth","NonVisitor");
//                       getSupportFragmentManager().beginTransaction()
//                               .replace(R.id.mainFragment, fragment)
//                               .commitAllowingStateLoss();

                       sendNonVisitor(jsonObject);



                   }else{
                       Toast.makeText(CartNotAuth.this,getResources().getString(R.string.enter_data),Toast.LENGTH_LONG).show();
                   }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cart_not_auth, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private boolean check(){
        if(!edMobile.getText().toString().equals("")
        &&!edName.getText().toString().equals("")){
            return true;
        }else {
            return false;
        }
    }
    private void sendNonVisitor(JSONObject jsonObjects){
        saveAuth = (SaveAuth) getApplicationContext();
        saveAuth.getJsonVisitor();
        saveAuth.getJsonProduct();
        JSONObject jsonObjectSend  = new JSONObject();

        try {
            jsonObjectSend.put("VisitorData", jsonObjects);
            JSONArray jsonArray = new JSONArray();
            if(databaseHelper.getCart()!=null)
                if(databaseHelper.getCart().size()>0)
                    for(int i=0;i<databaseHelper.getCart().size();i++){
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("ID",databaseHelper.getCart().get(i).getID());
                        jsonObject.put("Quantity",databaseHelper.getCart().get(i).getcQuantity());
                        jsonObject.put("CreatedDate",Utility.getCurrentTimeStamp());
                        jsonArray.put(jsonObject);
                    }
            jsonObjectSend.put("CartItems",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (Utility.isNetworkConnected(CartNotAuth.this)) {

            ProductListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                    ResponseOfCheckOut     checkResponse = (ResponseOfCheckOut) result;
                    CheckOutData  checkOutDatas =   checkResponse.getData();
                     Toast.makeText(CartNotAuth.this, checkOutDatas.getMessage(),Toast.LENGTH_LONG).show();
                    databaseHelper.deleteCart();
                    databaseHelper.deleteCartAdd();
                    databaseHelper.removeCart();
                    databaseHelper.removeCartAdd();
                    databaseHelper.remove();
                    Intent intent = new Intent(CartNotAuth.this, MainActivity.class);
                     startActivity(intent);
                 }

                @Override
                public void onFailure() {
                    Utility.showFailureDialog(CartNotAuth.this, false);
                }
            };

            AddCartItemNotAuth task = new AddCartItemNotAuth(CartNotAuth.this, ProductListener);
            task.execute(jsonObjectSend);

        } else {
            Utility.showValidateDialog(
                    ("no internet"),
                    CartNotAuth.this);
        }

    }
}
