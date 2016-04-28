package droidahmed.com.jm3eia.api;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.Keys;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;

public class FavoriteWS extends AsyncTask<String, Void, FavoriteItem[]> {

	private final static String URL = Keys.BASE_URL + "favorite";
	private ProgressDialog dialog;
	private OnProcessCompleteListener callback;
	private Context context;
	private boolean showDialog;

	public FavoriteWS(Context context, OnProcessCompleteListener cb,
			boolean showDialog) {
		dialog = new ProgressDialog(context);
		callback = cb;
		this.showDialog = showDialog;
		this.context = context;
	}
	
	@Override
	protected void onPreExecute() {
		if (showDialog) {
			this.dialog.setMessage(context.getResources().getString(
					R.string.loading_myfavorit));
			this.dialog.setCancelable(false);
			this.dialog.show();
		}
	}

	@Override
	protected FavoriteItem[] doInBackground(String... params) {

		String responseJSON = null;
		FavoriteItem[] obj = null;

		try {
			responseJSON = makeRequest(params[0], params[1]);
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
				obj = gson.fromJson(responseJSON, FavoriteItem[].class);
			} catch (com.google.gson.JsonSyntaxException ex) {
				ex.printStackTrace();

			}
		}

		return obj;
	}

	@Override
	protected void onPostExecute(FavoriteItem[] result) {
		if (showDialog && dialog.isShowing()) {
			dialog.dismiss();
		}
		if (result != null) {
			callback.onSuccess(result);
		} else {
			callback.onFailure();
		}
	}

	public static String makeRequest(String usename, String password)
			throws Exception {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(URL);
		StringBuilder total = new StringBuilder();
		JSONObject json = new JSONObject();

		json.put("AuthUserName", usename);
		json.put("AuthPassword", password);

		InputStream is = new ByteArrayInputStream(json.toString().getBytes(
				"UTF-8"));

		InputStreamEntity entity = new InputStreamEntity(is, is.available());
		httpost.setEntity(entity);
		httpost.setHeader("Content-type", "application/json");
		HttpResponse response = (HttpResponse) httpclient.execute(httpost);// localContext

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
