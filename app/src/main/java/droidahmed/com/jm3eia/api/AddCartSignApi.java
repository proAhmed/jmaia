package droidahmed.com.jm3eia.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.Keys;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.CartQuantity;
import droidahmed.com.jm3eia.model.UserLoginResponse;


public class AddCartSignApi extends AsyncTask<CartQuantity, Void, UserLoginResponse> {

	private final static String URL = "https://jm3eia.com/API/ar/cart/add";
	private OnProcessCompleteListener callback;
	private Context context;

	public AddCartSignApi(Context context, OnProcessCompleteListener cb) {
		callback = cb;
		this.context = context;
	}

	@Override
	protected void onPreExecute() {

	}

	@Override
	protected UserLoginResponse doInBackground(CartQuantity... params) {
		String responseJSON = null;
		UserLoginResponse obj = null;

		try {
			responseJSON = makeRequest(params[0]);
			Log.d("iiioooo",responseJSON);
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
				obj = gson.fromJson(responseJSON, UserLoginResponse.class);
			} catch (com.google.gson.JsonSyntaxException ex) {
				ex.printStackTrace();
			}
		}

		return obj;
	}

	@Override
	protected void onPostExecute(UserLoginResponse result) {

		if (result != null&&result.isSuccess()) {

			callback.onSuccess(result);
		}else{
			callback.onFailure();
		}

	}

	public  String makeRequest(CartQuantity  param)
			throws Exception {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(URL);
		StringBuilder total = new StringBuilder();
		JSONObject json = new JSONObject();

		json.put("Product",param.getID());
		json.put("Quantity",param.getcQuantity());

			json.put("AuthUserName",new StoreData(context).getAuthName());
			json.put("AuthPassword",new StoreData(context).getAuthPass());
Log.d("iiibbb",json.toString());
		InputStream is = new ByteArrayInputStream(json.toString().getBytes(
				"UTF-8"));

		InputStreamEntity entity = new InputStreamEntity(is, is.available());

		httpost.setEntity(entity);
		httpost.setHeader("Content-type", "application/json");
		HttpResponse response = (HttpResponse) httpclient.execute(httpost);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

			InputStream instream = response.getEntity().getContent();
			BufferedReader r = new BufferedReader(new InputStreamReader(
					instream), 8000);
			String line;
			while ((line = r.readLine()) != null) {
				total.append(line);
			}
			instream.close();
		}
		return total.toString();

	}

}
