package droidahmed.com.jm3eia.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.model.AllProducts;
import droidahmed.com.jm3eia.model.Product;


/**
 * Created by ahmed on 1/19/2016.
 */
public class CuListAdapter extends BaseAdapter {

    ArrayList<AllProducts>  _choices;
    private Context context;
     OnCartListener onCartListener;

    public CuListAdapter(Context context,  ArrayList<AllProducts>  _choices, OnCartListener onCartListener) {
        this.context = context;
        this._choices = _choices;
        this.onCartListener = onCartListener;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final double[] price = {0};
        final int[] cartItem = {0};
        final boolean[] cartWatch = {false};

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView tvName,tvPrice;
        ImageView imgProduct;
        RelativeLayout gridClickable;
        ImageView imgAdd,imgDelete;
        final LinearLayout imgCart;
        final EditText edNumber;
        if (convertView == null) {
           convertView = inflater.inflate(R.layout.main_items, parent, false);
        }

        imgProduct = (ImageView) convertView.findViewById(R.id.imgProduct);
        tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
        tvName.setText(_choices.get(position).getName());
        tvPrice.setText(_choices.get(position).getPrice()+"");
        imgAdd = (ImageView) convertView.findViewById(R.id.imgAdd);
        imgDelete = (ImageView) convertView.findViewById(R.id.imgDelete);
        imgCart = (LinearLayout) convertView.findViewById(R.id.imgCart);
        edNumber = (EditText)  convertView.findViewById(R.id.edNumber);
        edNumber.setText("" + cartItem[0]);

        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edNumber.getText();
                if (cartWatch[0] == false) {
                    onCartListener.onAddCart(position, Integer.parseInt(edNumber.getText().toString()), false,price[0]);
                    imgCart.setBackgroundColor(Color.parseColor("#000000"));

                    cartWatch[0] = true;
                } else {
                    cartWatch[0] = false;
                    onCartListener.onAddCart(position, Integer.parseInt(edNumber.getText().toString()), true,price[0]);
                }
            }
        });
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++cartItem[0];
                edNumber.setText("" + cartItem[0]);
                price[0] = _choices.get(position).getPrice()*cartItem[0];

            }
        });
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartItem[0] > 0) {
                    --cartItem[0];
                    edNumber.setText("" + cartItem[0]);
                    price[0] = _choices.get(position).getPrice()*cartItem[0];

                }
            }
        });

        Picasso.with(context).load("http://jm3eia.com/" + _choices.get(position).getPicture()).placeholder(R.drawable.place_holder_list).into(imgProduct);
        return convertView;
    }
}
