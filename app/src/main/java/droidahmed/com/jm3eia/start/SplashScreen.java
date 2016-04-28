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

public class SplashScreen extends AppCompatActivity {
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_splash_screen);
        try{
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        timer.cancel();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                         startActivity(intent);
                        finish();

                    }

                });
            }
        }, 1500);
        }catch (Exception e){

        }
        }


}
