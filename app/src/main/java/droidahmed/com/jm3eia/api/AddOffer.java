package droidahmed.com.jm3eia.api;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.Keys;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.model.AddOfferInput;
import droidahmed.com.jm3eia.model.AddOfferModel;


public class AddOffer extends AsyncTask<AddOfferInput, Void, AddOfferModel> {

	private final static String URL = Keys.BASE_URL + "profile/addoffer";
	private ProgressDialog dialog;
	private OnProcessCompleteListener callback;
	private static Context context;

	public AddOffer(Context context, OnProcessCompleteListener cb) {
		dialog = new ProgressDialog(context);
		callback = cb;
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		this.dialog.setMessage(context.getResources().getString(
				R.string.loading_uploading_ad));
		this.dialog.setCancelable(false);
		this.dialog.show();
	}

	@Override
	protected AddOfferModel doInBackground(AddOfferInput... params) {
		String responseJSON = null;
		AddOfferModel obj = null;

		try {
			responseJSON = makeRequest(params[0]);
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
				obj = gson.fromJson(responseJSON, AddOfferModel.class);
			} catch (com.google.gson.JsonSyntaxException ex) {
				ex.printStackTrace();
			}
		}

		return obj;
	}

	@Override
	protected void onPostExecute(AddOfferModel result) {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
		if (result != null) {
			callback.onSuccess(result);
		} else {
			callback.onFailure();
		}
	}
 	public static String makeRequest(AddOfferInput obj) throws Exception {
String jsons ;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(URL);
		StringBuilder total = new StringBuilder();
		JSONObject json = new JSONObject();

		json.put("AuthUserName", obj.getAuthUserName());
		json.put("AuthPassword", obj.getAuthPassword());
		json.put("Category", obj.getCategoryID());
		json.put("City", obj.getCityID());
		json.put("Title", obj.getTitle());
		json.put("Contents", obj.getContents());
		json.put("Mobile", obj.getMobile());
		
		if (obj.getAllowComments().equalsIgnoreCase("true"))
			json.put("AllowComments", "1");
		else
			json.put("AllowComments", "0");

		if (!obj.getPicture().equals("-1")) {

			json.put("Picture", obj.getPicture());
			json.put("Pictures", new JSONArray(obj.getPictures()));
		}
		
		
//		jsons =json.toString();
//  //		InputStreamEntity entity = null;
////		try {
////			InputStream is = new ByteArrayInputStream(json.toString().getBytes(
////					"UTF-8"));
////
////			entity = new InputStreamEntity(is, is.available());
////
////		} catch (UnsupportedEncodingException e) {
////
////			e.printStackTrace();
////		} catch (IOException e) {
////
////			e.printStackTrace();
////		}
//
//		//httpost.setEntity(entity);	
//		//httpost.setHeader("", json.toString());
//
//	 
//		
//		
//	    StringEntity se = new StringEntity(json.toString());
//	 //   se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
//
//		httpost.setEntity(se);
////
//		
////	httpost.setHeader("Accept", "application/json");
//
//		HttpResponse response = (HttpResponse) httpclient.execute(httpost);
//
//		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//
//			InputStream instream = response.getEntity().getContent();
//			BufferedReader r = new BufferedReader(new InputStreamReader(
//					instream), 8000);
//			String line;
//			while ((line = r.readLine()) != null) {
//				total.append(line);
//			}
//			instream.close();
//		}
//		
		
//		OkHttpClient client = new OkHttpClient();
//
//		MediaType mediaType = MediaType.parse("application/octet-stream");
//		RequestBody body = RequestBody.create(mediaType, "{\r\n\t\"AuthUserName\": \"android\",\r\n\t\"AuthPassword\": \"android2014\",\r\n\t\"Category\": \"300\",\r\n\t\"City\": \"277\",\r\n\t\"Title\": \"إعلان جديد\",\r\n\t\"Contents\": \"محتوى الإعلان محتوى الإعلان محتوى الإعلان محتوى الإعلان \",\r\n\t\"Keywords\": \"إعلان, جديد\",\r\n\t\"Mobile\": \"01111055288\",\r\n\t\"Picture\": \"Media/Images/2015/01/24/14221147208311.jpg\",\r\n\t\"Pictures\": [\r\n\t\t\"Media/Images/2015/01/28/1422480003.jpg\",\r\n\t\t\"Media/Images/2015/01/28/1422480018.jpg\",\r\n\t\t\"Media/Images/2015/01/28/1422480034.jpg\",\r\n\t\t\"Media/Images/2015/01/28/1422480048.jpg\"\r\n\t],\r\n    \"AllowComments\": 1\r\n}\r\n");
//		Request request = new Request.Builder()
//		  .url("http://ksa4sale.com/API/profile/addoffer")
//		  .post(body)
//		  .addHeader("cache-control", "no-cache")
//		  .addHeader("postman-token", "f52a4f92-98b2-afdc-08c6-9b6af4050ad7")
//		  .build();
//
//		Response response = client.newCall(request).execute();
		
		InputStreamEntity entity = null;
		try {
			InputStream is = new ByteArrayInputStream(json.toString().getBytes(
					"UTF-8"));

			entity = new InputStreamEntity(is, is.available());

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		httpost.setEntity(entity);
		//httpost.setHeader("Content-type", "application/json");
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
		
		
//		return total.toString();
//
//	}
// 	private final OkHttpClient client = new OkHttpClient();
//
// 	  public void run() throws Exception {
// 	    RequestBody formBody = new FormEncodingBuilder()
// 	        .add("search", "Jurassic Park")
// 	        .build();
// 	    Request request = new Request.Builder()
// 	        .url("https://en.wikipedia.org/w/index.php")
// 	        .post(formBody)
// 	        .build();
//
// 	    Response response = client.newCall(request).execute();
// 	    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
// 	    System.out.println(response.body().string());
// 	  }

//public String execute() {
//    Map<String, String> comment = new HashMap<String, String>();
////    comment.put("subject", "Using the GSON library");
////    comment.put("message", "Using libraries is convenient.");
//    comment.put("AuthUserName","1221");
//    comment.put("AuthPassword","gfgfggf");
//    comment.put("Category","yyuuy");
//    comment.put("City", "yuuyyu");
//    comment.put("Title", "yyuyuuy");
//    comment.put("Contents", "nnnmm");
//    comment.put("Mobile", "uuiiu");
//    comment.put("AllowComments", "1");
//    comment.put("Picture", "");
//    comment.put("Pictures", "jjkkj");
//    String json = new GsonBuilder().create().toJson(comment, Map.class);
//    makeRequest(URL, json);
//	return json;
//}

public static HttpResponse makeRequest(String uri, String json) {
    try {
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setEntity(new StringEntity(json));
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        return new DefaultHttpClient().execute(httpPost);
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    } catch (ClientProtocolException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}
}
