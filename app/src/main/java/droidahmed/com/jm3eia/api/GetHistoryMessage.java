package droidahmed.com.jm3eia.api;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import droidahmed.com.jm3eia.controller.Keys;


public class GetHistoryMessage extends AsyncTask<String, Void, MessageItem[]> {

	private final static String URL = Keys.BASE_URL + "message?User=";
	private ProgressDialog dialog;

	public GetHistoryMessage(Context context) {
		dialog = new ProgressDialog(context);
	}

	@Override
	protected MessageItem[] doInBackground(String... params) {
		String responseJSON = null;
		MessageItem[] obj = null;

		try {
			responseJSON = makeRequest(params[0], params[1], params[2]);
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
				obj = gson.fromJson(responseJSON, MessageItem[].class);
			} catch (com.google.gson.JsonSyntaxException ex) {
				ex.printStackTrace();
			}
		}

		return obj;
	}

	protected void onPreExecute() {
		this.dialog.setMessage("Progress start");
		this.dialog.show();
	}

	@Override
	protected void onPostExecute(MessageItem[] result) {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	public static String makeRequest(String usename, String password, String id)
			throws Exception {

		HttpParams httpParams = new BasicHttpParams();

		ConnManagerParams.setTimeout(httpParams, 10000);
		HttpConnectionParams.setConnectionTimeout(httpParams, 50000);
		HttpConnectionParams.setSoTimeout(httpParams, 50000);

		DefaultHttpClient httpclient = new DefaultHttpClient(httpParams);
		HttpPost httpost = new HttpPost(URL + id);
		StringBuilder total = new StringBuilder();
		JSONObject json = new JSONObject();

		json.put("AuthUserName", usename);
		json.put("AuthPassword", password);

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
