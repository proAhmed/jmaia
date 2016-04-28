package droidahmed.com.jm3eia.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import droidahmed.com.jm3eia.controller.Keys;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;


public class UploadImage extends
		AsyncTask<ArrayList<String>, Void, ArrayList<String>> {

	private final static String URL = Keys.BASE_URL + "general/imagefromtext";
	InputStream is;
	private ProgressDialog dialog;
	private OnProcessCompleteListener callback;

	public UploadImage(Context context, OnProcessCompleteListener cb) {
		dialog = new ProgressDialog(context);
		callback = cb;
	}

	protected void onPreExecute() {
		this.dialog.setMessage("Loading ...");
		this.dialog.setCancelable(false);
		this.dialog.show();
	}

	@Override
	protected ArrayList<String> doInBackground(ArrayList<String>... params) {
		String responseJSON = null;

		ArrayList<String> obj = new ArrayList<String>();

		ArrayList<String> bitmaps = params[0];

		for (int i = 0; i < bitmaps.size(); i++) {
			try {

				responseJSON = uploadSlike(bitmaps.get(i));

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

					WSResult obj1 = gson.fromJson(responseJSON, WSResult.class);
					if (obj1 != null && obj1.getResult() != null
							&& obj1.getResult().contains("Media")) {
						obj.add(obj1.getResult());
					}
				} catch (com.google.gson.JsonSyntaxException ex) {
					ex.printStackTrace();
				}
			}
		}

		return obj;
	}

	@Override
	protected void onPostExecute(ArrayList<String> result) {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
		if (result != null) {
			callback.onSuccess(result);
		} else {
			callback.onFailure();
		}
	}

	private String uploadSlike(String bitmapOrg) {

		StringBuilder total = new StringBuilder();

		try {

			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpPost httpost = new HttpPost(URL);
			JSONObject json = new JSONObject();
			json.put("Image", bitmapOrg);

			StringEntity se = new StringEntity(json.toString());
			httpost.setEntity(se);
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
		} catch (Exception e) {
			e.getMessage();
		}
		return total.toString();
	}
}
