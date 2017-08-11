package droidahmed.com.jm3eia.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.http.util.EncodingUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.start.MainActivity;

/**
 * Created by ahmed on 3/1/2016.
 */
public class WebPaymentFragment extends Fragment {
    private WebView webView;
    String link;
    RelativeLayout reCart;
    @SuppressLint("JavascriptInterface")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web,container,false);
        reCart = (RelativeLayout) view.findViewById(R.id.reCart);
        reCart.setVisibility(View.GONE);

        webView = (WebView) view.findViewById(R.id.webView);

        webView = (WebView) view.findViewById(R.id.webView);

        String url = "https://jm3eia.com/ar/checkout?nostyle";
//        String postData = "AuthUserName=" + new StoreData(getActivity()).getAuthName() +
//                "&AuthPassword=" + new StoreData(getActivity()).getAuthPass();
//        Log.d("mmmm",postData);
        String postData = null;
        try {
            postData = "AuthUserName=" + URLEncoder.encode(new StoreData(getActivity()).getAuthName(), "UTF-8") +
                    "&AuthPassword=" + URLEncoder.encode(new StoreData(getActivity()).getAuthPass(), "UTF-8");


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //   webView.addJavascriptInterface(new Utility.WebAppInterface(getActivity()), "btnCheckout");
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

            Map<String, String> mapParams = new HashMap<String, String>();
            mapParams.put("AuthUserName", new StoreData(getActivity()).getAuthName());
            mapParams.put("AuthPassword", new StoreData(getActivity()).getAuthPass());

            Collection<Map.Entry<String, String>> postDatas = mapParams.entrySet();

            webview_ClientPost(webView, "https://jm3eia.com/ar/checkout?nostyle", postDatas);
     //   webView.postUrl(url, postData.getBytes());
        webView.loadUrl( "javascript:window.location.reload( true )" );
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                webView.reload();
//            }
//        }, 4000);
try{
    webView.addJavascriptInterface(new Object() {
        @JavascriptInterface
        public void performClick() {

        }
    }, "btnBackToHome");
}catch (Exception e){

}



        return view;

    }

    public static void webview_ClientPost(WebView webView, String url, Collection< Map.Entry<String, String>> postData){
        StringBuilder sb = new StringBuilder();

        sb.append("<html><head></head>");
        sb.append("<body onload='form1.submit()'>");
        sb.append(String.format("<form id='form1' action='%s' method='%s'>", url, "post"));
        for (Map.Entry<String, String> item : postData) {
            sb.append(String.format("<input name='%s' type='hidden' value='%s' />", item.getKey(), item.getValue()));
        }
        sb.append("</form></body></html>");

        webView.loadData(sb.toString(), "text/html", "UTF-8");
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().findViewById(R.id.imageToggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.showSecondaryMenu();
                } catch (Exception e) {

                }
            }
        });
        TextView tv = (TextView) getActivity().findViewById(R.id.textTitle);
        tv.setVisibility(View.GONE);
        ImageView img = (ImageView) getActivity().findViewById(R.id.logo);
        img.setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.imageToggleCategory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null)
                    mainActivity.toggle();
            }
        });
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getActivity().findViewById(R.id.imageToggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.showSecondaryMenu();
                } catch (Exception e) {

                }
            }
        });
        TextView tv = (TextView) getActivity().findViewById(R.id.textTitle);
        tv.setVisibility(View.GONE);
        ImageView img = (ImageView) getActivity().findViewById(R.id.logo);
        img.setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.imageToggleCategory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null)
                    mainActivity.toggle();
            }
        });
    }
}
