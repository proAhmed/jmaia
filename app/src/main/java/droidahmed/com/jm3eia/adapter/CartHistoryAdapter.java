package droidahmed.com.jm3eia.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.OnAddItem;
import droidahmed.com.jm3eia.controller.OnCancelOrder;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.CartQuantity;


/**
 * Created by ahmed on 1/19/2016.
 */
public class CartHistoryAdapter extends BaseAdapter {

    ArrayList<CartQuantity>  _choices;
    private Context context;
     OnCartListener onCartListener;
    OnCancelOrder onCancelOrder;
    OnAddItem onAddItem;
    Utility utility;

    public CartHistoryAdapter(Context context, ArrayList<CartQuantity> _choices, OnCartListener onCartListener, OnCancelOrder onCancelOrder, OnAddItem onAddItem) {
        this.context = context;
        this._choices = _choices;
         this.onCartListener = onCartListener;
        this.onCancelOrder = onCancelOrder;
        this.onAddItem  =onAddItem;
        utility = new Utility();
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
        final int[] cartItem = {1};
        final double[] price = {0};

        final boolean[] cartWatch = {false};
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final TextView tvName,tvPrice,item_change,tvTotalPrice;
        ImageView imgProduct,imgAdd,imgDelete;
         final EditText edNumber;
        ImageView cancel_order;
         if (convertView == null) {
           convertView = inflater.inflate(R.layout.item_cart_mod, parent, false);
        }
        item_change = (TextView) convertView.findViewById(R.id.item_change);

        imgProduct = (ImageView) convertView.findViewById(R.id.imgProduct);
        imgAdd = (ImageView) convertView.findViewById(R.id.imgAdd);
        imgDelete = (ImageView) convertView.findViewById(R.id.imgDelete);
         edNumber = (EditText)  convertView.findViewById(R.id.edNumber);
     cancel_order  = (ImageView) convertView.findViewById(R.id.imgCancel);
        if( Locale.getDefault().getDisplayLanguage().equals("العربية")) {

            edNumber.setText("" + utility.arabicNumaricCode(String.valueOf(_choices.get(position).getcQuantity())));
        }else{
            edNumber.setText("" + _choices.get(position).getcQuantity());

        }

//        imgAccept.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 edNumber.getText();
//                 if (!cartWatch[0]) {
//                     CartQuantity cartQuantity =_choices.get(position);
//                     cartQuantity.setcQuantity(Integer.parseInt(edNumber.getText().toString()));
//                     onCartListener.onAddCart(cartQuantity, Integer.parseInt(edNumber.getText().toString()), false, price[0]);
//                     cartWatch[0] = true;
//                 } else {
//                     cartWatch[0] = false;
//                     CartQuantity cartQuantity =_choices.get(position);
//                     cartQuantity.setcQuantity(Integer.parseInt(edNumber.getText().toString()));
//                     onCartListener.onAddCart(cartQuantity, Integer.parseInt(edNumber.getText().toString()), true, price[0]);
//                     price[0] = _choices.get(position).getPrice() * cartItem[0];
//
//                 }
//
//             }
//         });
        cancel_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelOrder.cancel(_choices.get(position));
            }
        });
        tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
        tvTotalPrice = (TextView) convertView.findViewById(R.id.tvTotalPrice);
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(edNumber.getText().toString())==1){
                    ++cartItem[0];

                }else{
                    cartItem[0]= Integer.parseInt(edNumber.getText().toString())   ;
                    ++cartItem[0];
                }
                if( Locale.getDefault().getDisplayLanguage().equals("العربية")){
                    edNumber.setText("" + utility.arabicNumaricCode(String.valueOf(cartItem[0])));
                }
                else{
                    edNumber.setText("" + cartItem[0]);

                }                price[0] = _choices.get(position).getPrice()*cartItem[0];

                onAddItem.add(Integer.parseInt(edNumber.getText().toString()), _choices.get(position));


                 tvTotalPrice.setText(String.format("%.3f", (_choices.get(position).getPrice() * cartItem[0])) + " " + context.getResources().getString(R.string.dr));

            }
        });
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("uuuooo",cartItem[0]+"");
if (Integer.parseInt(edNumber.getText().toString()) >1){
    if(Integer.parseInt(edNumber.getText().toString())==1){
        --cartItem[0];

    }else{
        cartItem[0]= Integer.parseInt(edNumber.getText().toString())   ;
        --cartItem[0];
    }
    if( Locale.getDefault().getDisplayLanguage().equals("العربية")){
        edNumber.setText("" + utility.arabicNumaricCode(String.valueOf(cartItem[0])));
    }
    else{
        edNumber.setText("" + cartItem[0]);

    }
     price[0] = _choices.get(position).getPrice()*cartItem[0];
    onAddItem.add(Integer.parseInt(edNumber.getText().toString()), _choices.get(position));
     tvTotalPrice.setText(String.format("%.3f", (_choices.get(position).getPrice() * cartItem[0])) + " " + context.getResources().getString(R.string.dr));
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        tvPrice.setTextLocale(Locale.ENGLISH);
        tvTotalPrice.setTextLocale(Locale.ENGLISH);
    }
}
            }
        });
        tvName = (TextView) convertView.findViewById(R.id.tvName);

        tvName.setText(_choices.get(position).getName());
        tvPrice.setText(String.format("%.3f", _choices.get(position).getPrice()) + " " + context.getResources().getString(R.string.dr));

         tvTotalPrice.setText(String.format("%.3f", (_choices.get(position).getPrice() * _choices.get(position).getcQuantity())) + " " + context.getResources().getString(R.string.dr));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tvPrice.setTextLocale(Locale.ENGLISH);
            tvTotalPrice.setTextLocale(Locale.ENGLISH);
        }
             Picasso.with(context).load("http://jm3eia.com/" + _choices.get(position).getPicture()).into(imgProduct);




        return convertView;
    }
}
