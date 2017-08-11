package droidahmed.com.jm3eia.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.api.StaticPagesAbout;
import droidahmed.com.jm3eia.controller.DatabaseHelper;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.AboutPage;
import droidahmed.com.jm3eia.model.StaticPageData;
import droidahmed.com.jm3eia.start.MainActivity;

/**
 * Created by ahmed on 3/1/2016.
 */
public class AboutFragment extends Fragment {
    private OnProcessCompleteListener ProductListener;
    private  AboutPage aboutPage;
    private StaticPageData staticPageData;
    TextView tvAboutUs;
    ImageView imgCart;
    DatabaseHelper databaseHelper;
    TextView tvNum;
    RelativeLayout reCart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        tvAboutUs = (TextView) view.findViewById(R.id.tvAboutUs);
        imgCart = (ImageView) view.findViewById(R.id.imgCart);
        tvNum = (TextView) view.findViewById(R.id.tvNum);
        reCart = (RelativeLayout) view.findViewById(R.id.reCart);
        databaseHelper = new DatabaseHelper(getActivity());
        addNum();

        if (Utility.isNetworkConnected(getActivity())) {

            ProductListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                    aboutPage = (AboutPage) result;
                    staticPageData=   aboutPage.getData();
                    tvAboutUs.setText(Html.fromHtml(staticPageData.getContents()).toString());
                  }

                @Override
                public void onFailure() {
                    Utility.showFailureDialog(getActivity(), false);
                }
            };

            StaticPagesAbout task = new StaticPagesAbout(getActivity(), ProductListener);
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

    int value;
    private void addNum(){
        if(databaseHelper.getCart()!=null){
            value = databaseHelper.getCart().size();

        if(value!=0){
            tvNum.setText(value+"");
            Log.d("ccvvvooo",value+"");
            reCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .add(R.id.mainFragment,new FragmentProductCart(),"").addToBackStack("").commit();
                }
            });
        }
    }
    }
}
