package droidahmed.com.jm3eia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.model.AllProducts;
import droidahmed.com.jm3eia.model.Product;


/**
 * Created by ahmed on 1/19/2016.
 */
public class CuListAdapter extends BaseAdapter {

    ArrayList<AllProducts>  _choices;
    private Context context;


    public CuListAdapter(Context context,  ArrayList<AllProducts>  _choices) {
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
        TextView tvName,tvPrice;
        ImageView imgProduct;
        RelativeLayout gridClickable;
        if (convertView == null) {
           convertView = inflater.inflate(R.layout.main_items, parent, false);
        }

        imgProduct = (ImageView) convertView.findViewById(R.id.imgProduct);
        tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
        tvName.setText(_choices.get(position).getName());
        tvPrice.setText(_choices.get(position).getPrice()+"");

        Picasso.with(context).load("http://jm3eia.com/" + _choices.get(position).getPicture()).placeholder(R.drawable.place_holder_list).into(imgProduct);
        return convertView;
    }
}
