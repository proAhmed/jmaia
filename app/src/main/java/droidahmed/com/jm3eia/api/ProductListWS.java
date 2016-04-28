package droidahmed.com.jm3eia.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.Keys;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;


public class ProductListWS extends AsyncTask<String, Void, ProductListItem> {

	private String URL = Keys.BASE_URL + "offer/c/";
	private String city = "?City=";
	private ProgressDialog dialog;
	private OnProcessCompleteListener callback;
	private Context context;

	public ProductListWS(Context context, OnProcessCompleteListener cb) {
		dialog = new ProgressDialog(context);
		callback = cb;
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		this.dialog.setMessage(context.getResources().getString(
				R.string.loading_proudct));
		this.dialog.setCancelable(false);
		this.dialog.show();
	}

	@Override
	protected ProductListItem doInBackground(String... params) {

		String id = params[0];
		String cityid = params[1];
		String responseJSON = null;
		try {

			responseJSON = invokeJSONWS(id, cityid);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new Gson();

		ProductListItem obj = null;
		if (responseJSON != null && responseJSON.length() > 0) {

			GsonBuilder gb = new GsonBuilder();
			gb.serializeNulls();
			gson = gb.create();
			try {

				obj = gson.fromJson(responseJSON, ProductListItem.class);

			} catch (com.google.gson.JsonSyntaxException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		return obj;

	}

	@Override
	protected void onPostExecute(ProductListItem result) {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
		if (result != null) {
			callback.onSuccess(result);
		} else {
			callback.onFailure();
		}
	}

	private String invokeJSONWS(String id, String cityid) throws IOException {

		InputStream in = null;
		int response = -1;
		String responseJSON;
		URL += id;
		if (cityid != null && cityid.length() > 0)
			URL += (city + cityid);
		URL url = new URL(URL);
		URLConnection conn = url.openConnection();
		if (!(conn instanceof HttpURLConnection))
			throw new IOException("Not an HTTP connection");

		try {
			HttpURLConnection httpConn = (HttpURLConnection) conn;
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
		}
		return responseJSON;
	}
}
