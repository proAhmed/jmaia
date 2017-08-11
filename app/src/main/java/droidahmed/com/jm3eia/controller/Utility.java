package droidahmed.com.jm3eia.controller;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.api.GetAllowOrder;
import droidahmed.com.jm3eia.model.CartQuantity;
import droidahmed.com.jm3eia.model.ItemJson;
import droidahmed.com.jm3eia.model.OrderAllowedResponse;

/**
 * Created by ahmed on 3/1/2016.
 */
public class Utility {
    private static AlertDialog.Builder LoadingDialogBuilder;

    public static String BitMapToString(Bitmap bitmap) {

        Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        scaled.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public static void hidKeyboard(Context mContext) {

        InputMethodManager inputManager = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(((Activity) mContext)
                        .getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void showValidateDialog(String message, Context mContext) {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        alertDialogBuilder.setMessage(message).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        alertDialogBuilder.setCancelable(true);
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void dissmesLoadingDialog() {
        LoadingDialogBuilder.setCancelable(true);
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static void showDialog(Context mContext) {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        alertDialogBuilder
                .setMessage(
                        mContext.getResources().getString(R.string.order_not_allowed))
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        alertDialogBuilder.setCancelable(true);

                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void showFailureDialog(Context mContext, final boolean back) {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        alertDialogBuilder
                .setMessage(
                        mContext.getResources().getString(R.string.failure_ws))
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        alertDialogBuilder.setCancelable(true);
                        if (back) {
                            //      Utility.deleteFormBackStack();
                        }
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public static void showFailureDialogLogin(Context mContext, final boolean back) {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        alertDialogBuilder
                .setMessage(
                        mContext.getResources().getString(R.string.login_error))
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        alertDialogBuilder.setCancelable(true);
                        if (back) {
                            //      Utility.deleteFormBackStack();
                        }
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        } else
            return true;
    }

    public static void call(Context context, int num) {
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (context.checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.


                    return;
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL);

                    intent.setData(Uri.parse("tel:" + num));
                    context.startActivity(intent);
                }
            } else {
                Intent intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:" + num));
                context.startActivity(intent);

            }
        } catch (Exception e) {

        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    @SuppressLint("SimpleDateFormat")
    public static String getCurrentTimeStampData() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static void SaveData(Context context, String authName, String authPass, String fullNAme, String email,
                                String mobile, String gada, String widget, String zone,
                                String house, String street) {
        StoreData storeData = new StoreData(context);
        storeData.saveAuthName(authName);
        storeData.saveAuthPass(authPass);
        storeData.saveFullName(fullNAme);
        storeData.saveEmail(email);
        storeData.saveMobile(mobile);
        storeData.saveGada(gada);
        storeData.saveWidget(widget);
        storeData.saveZone(zone);
        storeData.saveHouse(house);
        storeData.saveStreet(street);
    }

    private boolean check;

    public JSONArray jsonArrayCheck(JSONArray jsonArray, JSONObject jsonObject) {

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                if (jsonArray.getJSONObject(i).getInt("ID") == jsonObject.getInt("ID")) {
                    setCheck(false);
                    return jsonArray;
                } else {
                    setCheck(true);

                    return jsonArray.put(jsonObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return jsonArray;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public ArrayList<ItemJson> itemArrays(ArrayList<ItemJson> arrayList, ItemJson itemJson) {
        for (int i = 0; arrayList.size() > i; i++) {
            if (arrayList.get(i).getIdItem() == itemJson.getIdItem()) {
                return arrayList;
            } else {
                arrayList.add(itemJson);
                return arrayList;
            }
        }

        return arrayList;
    }

    public static int widthScreen(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        return width;
    }

    public String arabicNumaricCode(String value) {

        String value3 = value.replaceAll("0", "٠").replaceAll("1", "١").replaceAll("2", "٢").replaceAll("3", "٣").replaceAll("4", "٤").replaceAll("5", "٥")
                .replaceAll("6", "٦").replaceAll("7", "٧").replaceAll("8", "٨").replaceAll("9", "٩");

        return value3;

    }

    public StringBuilder arabicNumaricCodeM(String value) {
        StringBuilder sb = new StringBuilder();

        String value3 = value.replaceAll("0", "٠").replaceAll("1", "١").replaceAll("2", "٢").replaceAll("3", "٣").replaceAll("4", "٤").replaceAll("5", "٥")
                .replaceAll("6", "٦").replaceAll("7", "٧").replaceAll("8", "٨").replaceAll("9", "٩");
        sb.append(value3);
        return sb;
    }

    public void openWeb(Activity activity) {
        WebView webView = new WebView(activity);
        activity.setContentView(webView);
        String url = "https://jm3eia.com/ar/checkout?nostyle";
        String postData = "UserName=" + new StoreData(activity).getAuthName() +
                "&Password=" + new StoreData(activity).getAuthPass();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.addJavascriptInterface(new WebAppInterface(activity), "btnCheckout");
        webView.setWebViewClient(new WebViewClient());

//        webView.setWebViewClient(new WebViewClient()
//        {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url)
//            {
//                //view.loadUrl(url);
//                System.out.println("hello");
//                return true;
//            }
//        });
         webView.postUrl(url, EncodingUtils.getBytes(postData, "base64"));
        webView.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void performClick() {
                Log.d("LOGIN::", "Clicked");

            }
        }, "btnCheckout");
        Log.d("bbb", new StoreData(activity).getAuthName());
    }

    public void openWebLogin(Activity activity) {
        WebView webView = new WebView(activity);
        activity.setContentView(webView);
        String url = "https://jm3eia.com/ar/checkout?nostyle";
//        String postData = "Payment_Method="+2;
//        webView.postUrl(url, EncodingUtils.getBytes(postData, "base64"));

    }

    public class WebAppInterface {

        Activity mContext;

        /**
         * Instantiate the interface and set the context
         */
        WebAppInterface(Activity c) {
            mContext = c;
        }

        /**
         * Show a toast from the web page
         */
        @JavascriptInterface
        public void nextScreen(String pro_cat_id) {

            WebView webView = new WebView(mContext);
            mContext.setContentView(webView);
            String url = "https://jm3eia.com/ar/checkout?nostyle";
            webView.loadUrl(url);

        }
    }

}
