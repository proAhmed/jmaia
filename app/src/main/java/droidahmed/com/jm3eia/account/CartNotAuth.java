package droidahmed.com.jm3eia.account;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import droidahmed.com.jm3eia.R;

public class CartNotAuth extends AppCompatActivity {
EditText edName,edMobile,edZone,edWidget,edStreet,edGada,edNum;
    Button btn;
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
        btn = (Button) findViewById(R.id.btnSave);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}
