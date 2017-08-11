package droidahmed.com.jm3eia.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.api.AddDeviceApi;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.controller.Utility;

public class SplashScreen extends AppCompatActivity {
    private Timer timer;
    private OnProcessCompleteListener addData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_splash_screen);
        try {
            timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            timer.cancel();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            new StoreData(SplashScreen.this).setAdd("data");
                            startActivity(intent);
                            finish();

                        }

                    });
                }
            }, 1500);
        } catch (Exception e) {

        }
    }

//        if("data".equalsIgnoreCase(new StoreData(this).getAdd())){
////                  }else {
////            try{
////                timer = new Timer();
////                timer.schedule(new TimerTask() {
////
////                    @Override
////                    public void run() {
////                        runOnUiThread(new Runnable() {
////                            public void run() {
////                                timer.cancel();
////
////                                loadData();
////                            }
////
////                        });
////                    }
////                }, 1000);
////            }catch (Exception e){
////
////            }
////
////        }
//        }

//        private void loadData(){
//            if(Utility.isNetworkConnected(this)){
//                addData = new OnProcessCompleteListener() {
//                    @Override
//                    public void onSuccess(Object result) {
//                        try{
//                            timer = new Timer();
//                            timer.schedule(new TimerTask() {
//
//                                @Override
//                                public void run() {
//                                    runOnUiThread(new Runnable() {
//                                        public void run() {
//                                            timer.cancel();
//                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                             new StoreData(SplashScreen.this).setAdd("data");
//                                            startActivity(intent);
//                                            finish();
//
//                                        }
//
//                                    });
//                                }
//                            }, 1500);
//                        }catch (Exception e){
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure() {
//                        try{
//                            timer = new Timer();
//                            timer.schedule(new TimerTask() {
//
//                                @Override
//                                public void run() {
//                                    runOnUiThread(new Runnable() {
//                                        public void run() {
//                                            timer.cancel();
//                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                             startActivity(intent);
//                                            finish();
//
//                                        }
//
//                                    });
//                                }
//                            }, 1500);
//                        }catch (Exception e){
//
//                        }
//                    }
//                };
//                AddDeviceApi addDeviceApi = new AddDeviceApi(SplashScreen.this,addData);
//                addDeviceApi.execute();
//            }
//        }

}
