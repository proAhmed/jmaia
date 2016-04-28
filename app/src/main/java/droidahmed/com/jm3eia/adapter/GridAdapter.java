package droidahmed.com.jm3eia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.model.Product;


/**
 * Created by ahmed on 1/13/2016.
 */
public class GridAdapter extends BaseAdapter {

    private ArrayList<Product> userTypes;
    private Context context;

    public GridAdapter(Context ctx, ArrayList<Product> userTypes) {
        this.userTypes = userTypes;
        this.context = ctx;
    }


    @Override
    public int getCount() {
        return userTypes.size();
    }

    @Override
    public Object getItem(int position) {
        return userTypes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        TextView tvListItem;
        ImageView imageGrid;

        if (convertView == null) {
      //      convertView = inflater.inflate(R.layout.card_items, parent, false);
        }

//        tvListItem = (TextView) convertView.findViewById(R.id.grTextView);
//        imageGrid = (ImageView) convertView.findViewById(R.id.grImageView);
//        tvListItem.setText(userTypes.get(position).getUserType());
//        imageGrid.setImageResource(userTypes.get(position).getUserTypeImage());


        return convertView;
    }
}
