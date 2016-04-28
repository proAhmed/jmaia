package droidahmed.com.jm3eia.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;

import droidahmed.com.jm3eia.controller.Keys;


public class UpadteProudect extends AsyncTask<String, Void, Void> {

	private final String URL = Keys.BASE_URL + "offer/v/";

	@Override
	protected Void doInBackground(String... params) {

		String responseJSON = null;
		try {
			responseJSON = invokeJSONWS(params[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String invokeJSONWS(String offerID) throws IOException {

		InputStream in = null;
		int response = -1;
		String responseJSON;
		URL url = new URL(URL + offerID);
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
