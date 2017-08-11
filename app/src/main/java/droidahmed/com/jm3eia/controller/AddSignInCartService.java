package droidahmed.com.jm3eia.controller;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.api.AddCartSignApi;
import droidahmed.com.jm3eia.model.CartQuantity;
import droidahmed.com.jm3eia.model.CheckOutData;
import droidahmed.com.jm3eia.model.ResponseOfCheckOut;
import droidahmed.com.jm3eia.start.MainActivity;

/**
 * Created by ahmed on 12/20/2016.
 */
public class AddSignInCartService extends Service {

    Context context;
    OnProcessCompleteListener productListener;
    DatabaseHelper databaseHelper;
   static int ii=0;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
         databaseHelper = new DatabaseHelper(this);
        Log.d("iii",""+ databaseHelper.getCart().size());
       for(int i =0;i< databaseHelper.getCart().size();i++){
           ++ii;
           add( databaseHelper.getCart().get(i));

       }
    }

    private void add(CartQuantity cartQuantity){
        if (Utility.isNetworkConnected(this)) {

            productListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                    Log.d("nnn",ii+" ||  "+databaseHelper.getCart().size());
                    if(ii==databaseHelper.getCart().size()){
                         stopSelf();
                        Intent intents = new Intent(context, MainActivity.class);
                        intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intents);
                    }
                }

                @Override
                public void onFailure() {
                }
            };

            AddCartSignApi task = new AddCartSignApi(context, productListener);
            task.execute(cartQuantity);

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
