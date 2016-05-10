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
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.Keys;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.model.ResponseChangeUserData;
import droidahmed.com.jm3eia.model.UpdateUser;


public class ChangeUserData extends AsyncTask<UpdateUser, Void, ResponseChangeUserData> {

	private final static String URL =  "https://jm3eia.com/API/ar/profile/changeuserdata";
	private ProgressDialog dialog;
	private OnProcessCompleteListener callback;
	private Context context;

	public ChangeUserData(Context context, OnProcessCompleteListener cb) {
		dialog = new ProgressDialog(context);
		callback = cb;
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		this.dialog.setMessage(context.getResources().getString(
				R.string.loading_change));
		this.dialog.setCancelable(false);
		this.dialog.show();
	}

	@Override
	protected ResponseChangeUserData doInBackground(UpdateUser... params) {
		String responseJSON = null;
		ResponseChangeUserData obj = null;

		try {
			responseJSON = makeRequest(params[0]);
			Log.d("ooo",responseJSON);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Gson gson = new Gson();
		if (responseJSON != null && responseJSON.length() > 1) {

			GsonBuilder gb = new GsonBuilder();
			gb.serializeNulls();
			gson = gb.create();
			try {
				obj = gson.fromJson(responseJSON, ResponseChangeUserData.class);
			} catch (com.google.gson.JsonSyntaxException ex) {
				ex.printStackTrace();
			}
		}

		return obj;
	}

	@Override
	protected void onPostExecute(ResponseChangeUserData result) {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
		if (result != null) {
			callback.onSuccess(result);
		} else {
			callback.onFailure();
		}
	}

	public static String makeRequest(UpdateUser user) throws Exception {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(URL);
		StringBuilder total = new StringBuilder();
		JSONObject json = new JSONObject();

		json.put("AuthUserName", user.getAuthUserName());
		json.put("AuthPassword", user.getAuthPassword());
		json.put("FullName", user.getFullName());
		json.put("UserName", user.getAuthUserName());
		json.put("Email", user.getEmail());
		json.put("Mobile", user.getMobile());
		json.put("Zone", user.getZone());
		json.put("Street", user.getStreet());
		json.put("Gada", user.getGada());
		json.put("House", user.getHouse());

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
