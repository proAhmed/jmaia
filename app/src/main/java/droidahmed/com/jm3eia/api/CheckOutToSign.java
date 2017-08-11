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
import java.util.Objects;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.Keys;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.CartQuantity;
import droidahmed.com.jm3eia.model.UserLoginResponse;


public class CheckOutToSign extends AsyncTask<Object, Void, UserLoginResponse> {

	private final static String URL = Keys.BASE_URL + "/profile/signin";
	private ProgressDialog dialog;
	private OnProcessCompleteListener callback;
	private Context context;

	public CheckOutToSign(Context context, OnProcessCompleteListener cb) {
		dialog = new ProgressDialog(context);
		callback = cb;
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		this.dialog.setMessage(context.getResources().getString(
				R.string.loading_signin));
		this.dialog.setCancelable(false);
		this.dialog.show();
	}

	@Override
	protected UserLoginResponse doInBackground(Object... params) {
		String responseJSON = null;
		UserLoginResponse obj = null;

		try {
			responseJSON = makeRequest(params[0], params[1],params[2]);
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
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
		if (result != null&&((UserLoginResponse)result).isSuccess()) {
			callback.onSuccess(result);
		}else{
			callback.onFailure();
		}
	}

	public static String makeRequest(Object ... param)
			throws Exception {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(URL);
		StringBuilder total = new StringBuilder();
		JSONObject json = new JSONObject();
JSONArray jsonArray = new JSONArray();
		json.put("UserName", (String)param[0]);
		json.put("Password", (String)param[1]);
		if(!param[2].equals("")) {
			for(int i=0;i< ((ArrayList<CartQuantity>)param[2]).size();i++){
				JSONObject jsonCart = new JSONObject();
				jsonCart.put("ID",((ArrayList<CartQuantity>)param[2]).get(i).getID());
				jsonCart.put("Quantity",((ArrayList<CartQuantity>)param[2]).get(i).getcQuantity());
				jsonCart.put("CreatedDate", Utility.getCurrentTimeStamp());
				jsonArray.put(jsonCart);
			}
 			json.put("CartItems", jsonArray);
 		}
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
