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
 * Created by ahmed on 1/19/2016.
 */
public class CuCartListAdapter extends BaseAdapter {

    ArrayList<Product> _choices;
    private Context context;


    public CuCartListAdapter(Context context, ArrayList<Product> _choices) {
        this.context = context;
        this._choices = _choices;
    }

    @Override
    public int getCount() {
        return _choices.size();
    }

    @Override
    public Object getItem(int position) {
        return _choices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView textViewName;
        ImageView imageViewIcon;

        if (convertView == null) {
           convertView = inflater.inflate(R.layout.item_cart, parent, false);
        }

//        imageViewIcon = (ImageView) convertView.findViewById(R.id.imageProduct);
//        textViewName = (TextView) convertView.findViewById(R.id.tvSelect);


        return convertView;
    }
}
