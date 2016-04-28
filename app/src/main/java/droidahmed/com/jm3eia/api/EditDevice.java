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

import android.os.AsyncTask;

import droidahmed.com.jm3eia.controller.Keys;


public class EditDevice extends AsyncTask<String, Void, Void> {

	private final static String URL = Keys.BASE_URL + "profile/deleteoffer";

	@Override
	protected Void doInBackground(String... params) {
		String responseJSON = null;

		try {
			responseJSON = makeRequest(params[0], params[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static String makeRequest(String user, String deviceToken)
			throws Exception {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(URL);
		StringBuilder total = new StringBuilder();
		JSONObject json = new JSONObject();
		json.put("User", user);
		json.put("DeviceToken", deviceToken);

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

	// private ProgressDialog dialog;
	// private OnProcessCompleteListener callback;
	// private Context context;
	// public EditDevice(Context context, OnProcessCompleteListener cb) {
	// dialog = new ProgressDialog(context);
	// this.callback = cb;
	// this.context = context;
	// }
	//
	// @Override
	// protected void onPreExecute() {
	// this.dialog.setMessage(context.getResources().getString(
	// R.string.loading_delete_offer));
	// dialog.setCancelable(false);
	// this.dialog.show();
	// }

	// @Override
	// protected void onPostExecute(WSResult result) {
	// if (dialog.isShowing()) {
	// dialog.dismiss();
	// }
	// if (result != null) {
	// callback.onSuccess(result);
	// } else {
	// callback.onFailure();
	// }
	// }
}
