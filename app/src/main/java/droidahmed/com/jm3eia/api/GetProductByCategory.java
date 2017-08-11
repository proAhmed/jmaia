package droidahmed.com.jm3eia.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.Keys;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.model.MainApi;

/**
 * Created by ahmed on 4/3/2016.
 */
public class GetProductByCategory extends AsyncTask<String,Void,MainApi > {
    private ProgressDialog dialog;
    private  String URL =  "http://jm3eia.com/API/ar/product/c/";
    private OnProcessCompleteListener callback;
    private Context context;
    public GetProductByCategory(Context context,int id ,OnProcessCompleteListener cb, int num) {
        dialog = new ProgressDialog(context);
        callback = cb;
        this.context = context;
        URL = URL+id+"?page="+num;
     }

    @Override
    protected void onPreExecute() {
        this.dialog.setMessage(context.getResources().getString(
                R.string.loading));
        this.dialog.setCancelable(false);
        this.dialog.show();
    }

    @Override
    protected MainApi doInBackground(String... params) {
        String responseJSON = null;
        MainApi obj = null;

        try {
            responseJSON = invokeJSONWS();
         } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Gson gson = new Gson();
        if (responseJSON != null && responseJSON.length() > 1) {

            GsonBuilder gb = new GsonBuilder();
            gb.serializeNulls();
            gson = gb.create();
            try {
                obj = gson.fromJson(responseJSON, MainApi.class);
            } catch (com.google.gson.JsonSyntaxException ex) {
                ex.printStackTrace();

            }

        }
        return obj;
    }

    @Override
    protected void onPostExecute(MainApi responseHome) {
        super.onPostExecute(responseHome);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }

        if (responseHome != null ) {
            callback.onSuccess(responseHome);
        } else {
            callback.onFailure();
        }
    }

    private String invokeJSONWS() throws IOException {
        HttpURLConnection httpConn = null;

        InputStream in = null;
        int response = -1;
        String responseJSON;
        java.net.URL url = new URL(URL);
        URLConnection conn = url.openConnection();
        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");

        try {
              httpConn = (HttpURLConnection) conn;
            httpConn.setRequestMethod("GET");
            httpConn.setConnectTimeout(Keys.TIMEOUT);
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);

            httpConn.connect();

            response = httpConn.getResponseCode();

            if (response == HttpURLConnection.HTTP_OK) {
                in = conn.getInputStream();
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }

            responseJSON = out.toString();

        } catch (Exception e) {
            throw new IOException("Error connecting");
        }finally {
            httpConn.disconnect();
        }
        return responseJSON;
    }
}
