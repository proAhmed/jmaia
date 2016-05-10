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

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.Keys;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.model.InputNewUser;
import droidahmed.com.jm3eia.model.UserResponse;


public class CheckOutRegister extends AsyncTask<Object, Void, Object> {

	private String URL = Keys.BASE_URL + "/profile/register";
	private ProgressDialog dialog;
	private OnProcessCompleteListener callback;
	private Context context;

	public CheckOutRegister(Context context, OnProcessCompleteListener cb) {
		dialog = new ProgressDialog(context);
		callback = cb;
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		this.dialog.setMessage(context.getResources().getString(
				R.string.loading_register));
		this.dialog.setCancelable(false);
		this.dialog.show();
	}

	@Override
	protected UserResponse doInBackground(Object... params) {

		UserResponse obj = null;
		String responseJSON = null;

		try {
			responseJSON = makeRequest(URL, params[0]);
			Log.d("ioiio", responseJSON);

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

				obj = gson.fromJson(responseJSON, UserResponse.class);

			} catch (com.google.gson.JsonSyntaxException ex) {
				ex.printStackTrace();
			}
		}

		return obj;
	}

	@Override
	protected void onPostExecute(Object result) {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}

			if (result != null&&((UserResponse)result).isSuccess()) {
                callback.onSuccess(result);
            } else if(result != null&&!((UserResponse)result).isSuccess()) {
				callback.onSuccess(  result );
            }else{
				callback.onFailure();
			}

	}

	public static String makeRequest(String URL,Object ...  inputNewUser) throws Exception {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(URL);
		StringBuilder total = new StringBuilder();
		JSONObject json = new JSONObject();

		json.put("FullName", ((InputNewUser) inputNewUser[0]).getFullName());

		json.put("UserName", ((InputNewUser) inputNewUser[0]).getUserName());
		json.put("Password", ((InputNewUser) inputNewUser[0]).getPassword());
		json.put("Email", ((InputNewUser) inputNewUser[0]).getEmail());
		json.put("Mobile", ((InputNewUser) inputNewUser[0]).getMobile());
//		json.put("Picture", Picture);
		json.put("Zone", ((InputNewUser)inputNewUser[0]).getZone());
		json.put("Widget", ((InputNewUser) inputNewUser[0]).getWidget());
		json.put("Street", ((InputNewUser) inputNewUser[0]).getStreet());
		json.put("Gada", ((InputNewUser)inputNewUser[0]).getGada());
		json.put("House", ((InputNewUser)inputNewUser[0]).getHouse());
		if(!inputNewUser[10].equals("")){
			json.put("CartItems",(JSONArray) inputNewUser[2]);

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