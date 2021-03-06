package droidahmed.com.jm3eia.adapter;

import android.content.Context;
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

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.OnAddItem;
import droidahmed.com.jm3eia.controller.OnCancelOrder;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.CartQuantity;


/**
 * Created by ahmed on 1/19/2016.
 */
public class CartGridAdapterMod extends BaseAdapter {

    ArrayList<CartQuantity>  _choices;
    private Context context;
     OnCartListener onCartListener;
    OnCancelOrder onCancelOrder;
    OnAddItem onAddItem;
    public CartGridAdapterMod(Context context, ArrayList<CartQuantity> _choices, OnCartListener onCartListener, OnCancelOrder onCancelOrder, OnAddItem onAddItem) {
        this.context = context;
        this._choices = _choices;
         this.onCartListener = onCartListener;
        this.onCancelOrder = onCancelOrder;
        this.onAddItem  =onAddItem;
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
        final TextView tvName,item_change;
        ImageView imgProduct,imgAdd,imgDelete;
         final EditText edNumber;
        ImageView cancel_order;
         if (convertView == null) {
           convertView = inflater.inflate(R.layout.item_request, parent, false);
        }
        item_change = (TextView) convertView.findViewById(R.id.item_change);

        imgProduct = (ImageView) convertView.findViewById(R.id.productCart);
        imgAdd = (ImageView) convertView.findViewById(R.id.imgAdd);
       // imgDelete = (ImageView) convertView.findViewById(R.id.imgDelete);
         edNumber = (EditText)  convertView.findViewById(R.id.tvQuantity);
        cancel_order  = (ImageView) convertView.findViewById(R.id.imgDelete);
        edNumber.setText(_choices.get(position).getcQuantity() + "");

//             imgCart.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 edNumber.getText();
//                 if (cartWatch[0] == false) {
//                     onCartListener.onAddCart(position, Integer.parseInt(edNumber.getText().toString()), false, price[0]);
//                     item_change.setText(context.getResources().getString(R.string.see_cart));
//                     cartWatch[0] = true;
//                 } else {
//                     cartWatch[0] = false;
//                     onCartListener.onAddCart(position, Integer.parseInt(edNumber.getText().toString()), true, price[0]);
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
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ++cartItem[0];
                edNumber.setText(""+ cartItem[0]);
                price[0] = _choices.get(position).getPrice()*cartItem[0];
                onAddItem.add(cartItem[0], _choices.get(position));
            }
        });
//        imgDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//if (cartItem[0] >1){
//    --cartItem[0];
//    edNumber.setText("" + cartItem[0]);
//    price[0] = _choices.get(position).getPrice()*cartItem[0];
//    onAddItem.add(cartItem[0],_choices.get(position));
//
//}
//            }
//        });
        tvName = (TextView) convertView.findViewById(R.id.productName);
         tvName.setText(_choices.get(position).getName());
         Log.d("oo", "http://jm3eia.com/" + _choices.get(position).getPicture());

        if(Utility.widthScreen(context)>=580) {
            Picasso.with(context).load("http://jm3eia.com/" + _choices.get(position).getPicture()).resize(115,150).into(imgProduct);


        } else if(Utility.widthScreen(context)>=760){
            Picasso.with(context).load("http://jm3eia.com/" + _choices.get(position).getPicture()).resize(150,190).into(imgProduct);

        }else{
            Picasso.with(context).load("http://jm3eia.com/" + _choices.get(position).getPicture()).into(imgProduct);

        }

        return convertView;
    }
}
