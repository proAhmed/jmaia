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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.OnLoadCompleteListener;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.model.HistoryModelMain;
import droidahmed.com.jm3eia.model.OrderAllowedResponse;


public class GetAllowOrder extends AsyncTask<String, Void, OrderAllowedResponse> {

	private ProgressDialog dialog;
	private OnLoadCompleteListener callback;
	private Context context;

	public GetAllowOrder(Context context, OnLoadCompleteListener cb) {
		dialog = new ProgressDialog(context);
		callback = cb;
		this.context = context;
   	}

	@Override
	protected void onPreExecute() {
//		this.dialog.setMessage(context.getResources().getString(
//				R.string.loading_proudct));
//		this.dialog.setCancelable(false);
//		this.dialog.show();
	}

	@Override
	protected OrderAllowedResponse doInBackground(String... params) {

		String responseJSON = null;
		try {
			responseJSON = makeRequest();
			Log.d("aaaarrrr",responseJSON);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new Gson();

		OrderAllowedResponse obj = null;
		if (responseJSON != null && responseJSON.length() > 1) {

			GsonBuilder gb = new GsonBuilder();
			gb.serializeNulls();
			gson = gb.create();
			try {
				obj = gson.fromJson(responseJSON, OrderAllowedResponse.class);
			} catch (com.google.gson.JsonSyntaxException ex) {
				ex.printStackTrace();
			}
		}
		return obj;

	}

	@Override
	protected void onPostExecute(OrderAllowedResponse result) {
//		if (dialog.isShowing()) {
//			dialog.dismiss();
//		}
		if (result != null) {
			callback.onSuccess(result);
		} else {
			callback.onFailure();
		}
	}

	public String makeRequest() throws Exception {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		String URL = "https://jm3eia.com/API/ar/general/settings";
		HttpPost httpost = new HttpPost(URL);
		StringBuilder total = new StringBuilder();
		JSONObject json = new JSONObject();

		json.put("AuthUserName",new StoreData(context).getAuthName());
		json.put("AuthPassword",new StoreData(context).getAuthPass());
		InputStreamEntity entity = null;
		try {
			InputStream is = new ByteArrayInputStream(json.toString().getBytes(
					"UTF-8"));

			entity = new InputStreamEntity(is, is.available());

		} catch (IOException e) {

			e.printStackTrace();
		}

		httpost.setEntity(entity);

		httpost.setHeader("Content-type", "application/json");
		HttpResponse response = httpclient.execute(httpost);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

			InputStream inStream = response.getEntity().getContent();
			BufferedReader r = new BufferedReader(new InputStreamReader(
					inStream), 8000);
			String line;
			while ((line = r.readLine()) != null) {
				total.append(line);
			}
			inStream.close();
		}
		return total.toString();

	}
}
