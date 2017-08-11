package droidahmed.com.jm3eia.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.adapter.NotifyAdapters;
import droidahmed.com.jm3eia.controller.DatabaseHelper;
import droidahmed.com.jm3eia.model.Notification;
import droidahmed.com.jm3eia.start.MainActivity;

/**
 * Created by ahmed on 3/1/2016.
 */
public class NotifyDetailsFragment extends Fragment {


    DatabaseHelper databaseHelper;
    TextView tvNum, tvTitle, tvContent;
    String title, content;
    RelativeLayout reCart;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notify_details_fragment, container, false);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        reCart = (RelativeLayout) view.findViewById(R.id.reCart);
        tvNum = (TextView) view.findViewById(R.id.tvNum);
        tvContent = (TextView) view.findViewById(R.id.tvContent);
        Bundle bundle = getArguments();
        title = bundle.getString("title", "");
        content = bundle.getString("content", "");
        tvContent.setText(content);
        tvTitle.setText(title);
        databaseHelper = new DatabaseHelper(getActivity());


        addNum();

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

    private void addNum() {
        if (databaseHelper.getCart() != null) {
            value = databaseHelper.getCart().size();

            if (value != 0) {
                tvNum.setText(value + "");
                reCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .add(R.id.mainFragment, new FragmentProductCart(), "").addToBackStack("").commit();
                    }
                });
            }
        }
    }
}
