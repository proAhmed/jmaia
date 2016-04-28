package droidahmed.com.jm3eia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import droidahmed.com.jm3eia.R;

/**
 * Created by ahmed on 3/1/2016.
 */
public class WebFragment extends Fragment {
    private WebView webView;
    String link;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web,container,false);
        Bundle bundle = getArguments();
        link = bundle.getString("link");
        webView = (WebView) view.findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(link);
        webView.getSettings().setJavaScriptEnabled(true);

        return view;

    }
}
