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
import java.io.InputStream;
import java.io.InputStreamReader;

import droidahmed.com.jm3eia.controller.Keys;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.model.AddDeviceResponse;


public class AddDeviceApi extends AsyncTask<String, Void, AddDeviceResponse> {

	private final static String URL = Keys.BASE_URL + "/general/add_device";
 	private OnProcessCompleteListener callback;
	private Context context;

	public AddDeviceApi(Context context, OnProcessCompleteListener cb) {
 		callback = cb;
		Log.d("oooouu",URL);

		this.context = context;
	}

	@Override
	protected void onPreExecute() {
 	}

	@Override
	protected AddDeviceResponse doInBackground(String... params) {
		String responseJSON = null;
		AddDeviceResponse obj = null;

		try {
			responseJSON = makeRequest(params[0]);
			Log.d("oooo",responseJSON);
 		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.d("oooo1",e.toString());
			e.printStackTrace();
		}

		Gson gson = new Gson();
		if (responseJSON != null && responseJSON.length() > 1) {

			GsonBuilder gb = new GsonBuilder();
			gb.serializeNulls();
			gson = gb.create();
			try {
				obj = gson.fromJson(responseJSON, AddDeviceResponse.class);
			} catch (com.google.gson.JsonSyntaxException ex) {
				ex.printStackTrace();
			}
		}

		return obj;
	}

	@Override
	protected void onPostExecute(AddDeviceResponse result) {

		if (result != null&&((AddDeviceResponse)result).isSuccess()) {
			callback.onSuccess(result);
		} else if(result != null&&!((AddDeviceResponse)result).isSuccess()) {
			callback.onSuccess(  result );
		}else{
			callback.onFailure();
		}
	}

	public  String makeRequest(String send)
			throws Exception {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(URL);
		StringBuilder total = new StringBuilder();
		JSONObject json = new JSONObject();

		json.put("DeviceToken", send);
		json.put("DeviceType", "1");

		Log.d("ooobbb",json.toString());

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
