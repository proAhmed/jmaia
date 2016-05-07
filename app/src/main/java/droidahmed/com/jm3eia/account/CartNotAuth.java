package droidahmed.com.jm3eia.account;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.adapter.CartGridAdapter;
import droidahmed.com.jm3eia.api.AddCartItem;
import droidahmed.com.jm3eia.api.AddCartItemNotAuth;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.CartItemResponse;
import droidahmed.com.jm3eia.start.SaveAuth;

public class CartNotAuth extends AppCompatActivity {
EditText edName,edMobile,edZone,edWidget,edStreet,edGada,edNum;
    JSONArray jsonArray;
    JSONObject jsonObject;
    Button btn;
    SaveAuth saveAuth;
    JSONObject jsonObjectSend;
    private OnProcessCompleteListener ProductListener;

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
                       jsonArray.put(jsonObject);
                     //  jsonArray.put(saveAuth.getJsonProduct());
                       Log.d("json", jsonArray.toString());
                       jsonObjectSend.put("VisitorData", jsonObject);
                       jsonObjectSend.put("CartItems", saveAuth.getJsonProduct());


                       if (Utility.isNetworkConnected(CartNotAuth.this)) {

                           ProductListener = new OnProcessCompleteListener() {

                               @Override
                               public void onSuccess(Object result) {
//                                   cartItemResponse = (CartItemResponse) result;
//                                   cartItemArrayList=   cartItemResponse.getData();
                                   //   Log.d("iiii",pro.toString());
//                    Gson gson = new Gson();
//                    String json = gson.toJson(pro);
//                    StoreData storeData = new StoreData(MainActivity.this);
//                    storeData.saveData(json);



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
                                   getResources().getString(R.string.failure_ws),
                                   CartNotAuth.this);
                       }





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
        if(!edGada.getText().toString().equals("")&&!edMobile.getText().toString().equals("")
        &&!edName.getText().toString().equals("")&&!edNum.getText().toString().equals("")
        &&!edStreet.getText().toString().equals("")&&!edWidget.getText().toString().equals("")
                &&!edZone.getText().toString().equals("")){
            return true;
        }else {
            return false;
        }
    }
}
