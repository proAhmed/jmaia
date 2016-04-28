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
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import droidahmed.com.jm3eia.controller.Keys;


public class UploadImages extends AsyncTask<ArrayList<String>, Void, WSResult> {

	private final static String URL = Keys.BASE_URL
			+ "general/multiuploadimage";
	InputStream is;

	@Override
	protected WSResult doInBackground(ArrayList<String>... params) {
		String responseJSON = null;
		WSResult obj = null;

		try {
			responseJSON = uploadSlike(params[0]);

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
				obj = gson.fromJson(responseJSON, WSResult.class);
			} catch (com.google.gson.JsonSyntaxException ex) {
				ex.printStackTrace();
			}
		}
		return obj;
	}

	private String uploadSlike(ArrayList<String> bitmapOrg) {

		StringBuilder total = new StringBuilder();

		try {

			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpPost httpost = new HttpPost(URL);
			JSONObject json = new JSONObject();
			json.put("Image[]", new JSONArray(bitmapOrg));

			StringEntity se = new StringEntity(json.toString());
			httpost.setEntity(se);
			httpost.setHeader("Content-type", "multipart/form-data");
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
