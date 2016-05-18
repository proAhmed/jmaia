package droidahmed.com.jm3eia.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.api.StaticPagesAbout;
import droidahmed.com.jm3eia.api.StaticPagesRules;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.AboutPage;
import droidahmed.com.jm3eia.model.RulesPage;
import droidahmed.com.jm3eia.model.StaticPageData;
import droidahmed.com.jm3eia.start.MainActivity;

/**
 * Created by ahmed on 3/1/2016.
 */
public class RulesFragment extends Fragment {
    private OnProcessCompleteListener ProductListener;
    private RulesPage rulesPage;
    private ArrayList<StaticPageData> staticPageData;
    TextView   tvRules2;
      @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rule, container, false);

           tvRules2 = (TextView) view.findViewById(R.id.tvRules2);

          if (Utility.isNetworkConnected(getActivity())) {

              ProductListener = new OnProcessCompleteListener() {

                  @Override
                  public void onSuccess(Object result) {
                      rulesPage = (RulesPage) result;
                      staticPageData=   rulesPage.getData();
                       tvRules2.setText(Html.fromHtml(staticPageData.get(1).getContents()).toString());

                  }

                  @Override
                  public void onFailure() {
                      Utility.showFailureDialog(getActivity(), false);
                  }
              };

              StaticPagesRules task = new StaticPagesRules(getActivity(), ProductListener);
              task.execute();

          } else {
              Utility.showValidateDialog(
                      getResources().getString(R.string.failure_ws),
                      getActivity());
          }


          return view;

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
