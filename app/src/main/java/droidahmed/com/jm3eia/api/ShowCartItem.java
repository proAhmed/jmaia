package droidahmed.com.jm3eia.api;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.Keys;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.CartItemResponse;
import droidahmed.com.jm3eia.model.CartQuantity;


public class ShowCartItem extends AsyncTask<ArrayList<CartQuantity>, Void, CartItemResponse> {

	private final static String URL = "http://jm3eia.com/API/ar/cart";
	private ProgressDialog dialog;
	private OnProcessCompleteListener callback;
	private Context context;

	public ShowCartItem(Context context, OnProcessCompleteListener cb) {
		dialog = new ProgressDialog(context);
		callback = cb;
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		this.dialog.setMessage(context.getResources().getString(
				R.string.add_cart_laoding));
		this.dialog.setCancelable(false);
		this.dialog.show();
	}

	@SafeVarargs
	@Override
	protected final CartItemResponse doInBackground(ArrayList<CartQuantity>... params) {
		String responseJSON = null;
		CartItemResponse obj = null;

		try {
			responseJSON = makeRequest(params[0]);

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
				obj = gson.fromJson(responseJSON, CartItemResponse.class);
			} catch (com.google.gson.JsonSyntaxException ex) {
				ex.printStackTrace();
			}

		}

		return obj;
	}

	@Override
	protected void onPostExecute(CartItemResponse result) {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
		if (result != null) {
			callback.onSuccess(result);
		} else {
			callback.onFailure();
		}
	}

	public static String makeRequest(ArrayList<CartQuantity> cartQuantities) throws Exception {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(URL);
		StringBuilder total = new StringBuilder();
 		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		try {
			if(cartQuantities!=null)
		for(int i=0;i<cartQuantities.size();i++){
		JSONObject jsonObject = new JSONObject();

			jsonObject.put("ID", cartQuantities.get(i).getID());

			jsonObject.put("Quantity", cartQuantities.get(i).getcQuantity());
			jsonObject.put("CreatedDate", Utility.getCurrentTimeStamp());
			jsonArray.put(jsonObject);

	}} catch (JSONException e) {
		e.printStackTrace();
	}
		Log.d("json",jsonArray.toString());
		json.put("CartItems", jsonArray);


		InputStreamEntity entity = null;
		try {
			InputStream is = new ByteArrayInputStream(json.toString().getBytes(
					"UTF-8"));

			entity = new InputStreamEntity(is, is.available());

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

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
