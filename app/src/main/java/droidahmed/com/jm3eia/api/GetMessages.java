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

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.Keys;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;


public class GetMessages extends AsyncTask<String, Void, MessageItem[]> {

	private final static String URL = Keys.BASE_URL + "message";
	private ProgressDialog dialog;
	private OnProcessCompleteListener callback;
	private Context context;
	private boolean isShowDilaog;

	public GetMessages(Context context, OnProcessCompleteListener cb,
			boolean isShowDilaog) {
		dialog = new ProgressDialog(context);
		callback = cb;
		this.context = context;
		this.isShowDilaog = isShowDilaog;
	}

	@Override
	protected void onPreExecute() {
		if (isShowDilaog) {
			this.dialog.setMessage(context.getResources().getString(
					R.string.loading_get_messages));
			this.dialog.setCancelable(false);
			this.dialog.show();
		}
	}

	@Override
	protected MessageItem[] doInBackground(String... params) {
		String responseJSON = null;
		MessageItem[] obj = null;

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
				obj = gson.fromJson(responseJSON, MessageItem[].class);
			} catch (com.google.gson.JsonSyntaxException ex) {
				ex.printStackTrace();
			}
		}

		return obj;
	}

	@Override
	protected void onPostExecute(MessageItem[] result) {

		if (isShowDilaog && dialog.isShowing()) {
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

		HttpParams httpParams = new BasicHttpParams();

		ConnManagerParams.setTimeout(httpParams, 10000);
		HttpConnectionParams.setConnectionTimeout(httpParams, 50000);
		HttpConnectionParams.setSoTimeout(httpParams, 50000);

		DefaultHttpClient httpclient = new DefaultHttpClient(httpParams);
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
