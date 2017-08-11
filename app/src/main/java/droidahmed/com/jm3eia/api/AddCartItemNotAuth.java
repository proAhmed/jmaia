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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.model.CartItemResponse;
import droidahmed.com.jm3eia.model.ResponseOfCheckOut;


public class AddCartItemNotAuth extends AsyncTask<JSONObject, Void, ResponseOfCheckOut> {

	private final static String URL = "https://jm3eia.com/API/ar/checkout/buy";
	private ProgressDialog dialog;
	private OnProcessCompleteListener callback;
	private Context context;

	public AddCartItemNotAuth(Context context, OnProcessCompleteListener cb) {
		dialog = new ProgressDialog(context);
		callback = cb;
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		this.dialog.setMessage(context.getResources().getString(
				R.string.load_request));
		this.dialog.setCancelable(false);
		this.dialog.show();
	}

	@Override
	protected ResponseOfCheckOut doInBackground(JSONObject... params) {
		String responseJSON = null;
		ResponseOfCheckOut obj = null;

		try {
			responseJSON = makeRequest(params[0]);
			Log.d("ccccppp",responseJSON);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            Log.d("cccppp",e.toString());
		}

		Gson gson = new Gson();
		if (responseJSON != null && responseJSON.length() > 1) {

			GsonBuilder gb = new GsonBuilder();
			gb.serializeNulls();
			gson = gb.create();
			try {
				obj = gson.fromJson(responseJSON, ResponseOfCheckOut.class);
			} catch (com.google.gson.JsonSyntaxException ex) {
				ex.printStackTrace();
			}

		}

		return obj;
	}

	@Override
	protected void onPostExecute(ResponseOfCheckOut result) {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
		if (result != null) {
			callback.onSuccess(result);
		} else {
			callback.onFailure();
		}
	}

	public static String makeRequest(JSONObject jsonObject) throws Exception {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(URL);
		StringBuilder total = new StringBuilder();



		InputStreamEntity entity = null;
		try {
			InputStream is = new ByteArrayInputStream(jsonObject.toString().getBytes(
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
