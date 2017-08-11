package droidahmed.com.jm3eia.adapter;

import android.content.Context;
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
import droidahmed.com.jm3eia.controller.OnAddItem;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.OnItemListener;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.CartQuantity;
import droidahmed.com.jm3eia.model.ItemAddedAlready;


/**
 * Created by ahmed on 1/19/2016.
 */
public class ProGridAdapterMod extends BaseAdapter  {

    ArrayList<CartQuantity>  _choices;
    private Context context;
  OnItemListener onItemListener;
    OnCartListener onCartListener;
    ArrayList<ItemAddedAlready> addedAlreadies;
    OnAddItem onAddItem;
    public ProGridAdapterMod(Context context, ArrayList<CartQuantity> _choices, OnItemListener onItemListener, OnCartListener onCartListener
            , ArrayList<ItemAddedAlready> addedAlreadies, OnAddItem onAddItem) {
        this.context = context;
        this._choices = _choices;
        this.onItemListener = onItemListener;
        this.onCartListener = onCartListener;
        this.addedAlreadies = addedAlreadies;
        this.onAddItem = onAddItem;

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
    boolean[] cartWatch= {false};
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int[] cartItem = {1};
        final double[] price = {0};
         LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final TextView tvName,tvPrice,item_change,tvOldPrice;
        ImageView imgProduct,imgAdd,imgDelete;
        final LinearLayout imgCart;
        final EditText edNumber;
        RelativeLayout gridClickable;
        if (convertView == null) {
           convertView = inflater.inflate(R.layout.item_request, parent, false);
        }
        item_change  = (TextView) convertView.findViewById(R.id.item_change);

//        if(Utility.widthScreen(context)>=580){
//
//            android.view.ViewGroup.LayoutParams layoutParams = imgProduct.getLayoutParams();
//            layoutParams.width = 200;
//            layoutParams.height = 260;
//            imgProduct.setLayoutParams(layoutParams);
//        }else  if(Utility.widthScreen(context)>=760){
//
//            android.view.ViewGroup.LayoutParams layoutParams = imgProduct.getLayoutParams();
//            layoutParams.width = 260;
//            layoutParams.height = 320;
//            imgProduct.setLayoutParams(layoutParams);
//
//        }
        imgProduct = (ImageView) convertView.findViewById(R.id.productCart);
        imgAdd = (ImageView) convertView.findViewById(R.id.imgAdd);
        imgDelete = (ImageView) convertView.findViewById(R.id.imgDelete);
        imgCart = (LinearLayout) convertView.findViewById(R.id.imgCart);
        edNumber = (EditText)  convertView.findViewById(R.id.edNumber);
        edNumber.setText("" + _choices.get(position).getcQuantity());
if(_choices.get(position).getAdded()==1){
//    item_change.setText(context.getResources().getString(R.string.see_cart));

}else{
//    item_change.setText(context.getResources().getString(R.string.add_cart));

}

         imgCart.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 edNumber.getText();
                 if (cartWatch[0] == false) {
                     onCartListener.onAddCart(_choices.get(position), Integer.parseInt(edNumber.getText().toString()), false, price[0]);
//                     item_change.setText(context.getResources().getString(R.string.see_cart));
                     cartWatch[0] = true;
                 } else {
                     cartWatch[0] = false;
                     onCartListener.onAddCart(_choices.get(position), Integer.parseInt(edNumber.getText().toString()), true, price[0]);
                 }

             }
         });
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++cartItem[0];
                edNumber.setText("" + cartItem[0]);

                price[0] = _choices.get(position).getPrice() * cartItem[0];
                onAddItem.add(Integer.parseInt(edNumber.getText().toString()), _choices.get(position));

            }
        });
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if (cartItem[0] >1){
    --cartItem[0];
    edNumber.setText("" + cartItem[0]);
    price[0] = _choices.get(position).getPrice()*cartItem[0];
    onAddItem.add(Integer.parseInt(edNumber.getText().toString()), _choices.get(position));

}
            }
        });
        tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
        tvOldPrice = (TextView) convertView.findViewById(R.id.tvOldPrice);

        tvName.setText(_choices.get(position).getName());
//        tvPrice.setText( String.format("%.3f", _choices.get(position).getPrice())+" "+context.getResources().getString(R.string.dr));
//        tvOldPrice.setText( String.format("%.3f", _choices.get(position).getOldPrice())+" "+context.getResources().getString(R.string.dr));
        gridClickable = (RelativeLayout) convertView.findViewById(R.id.gridClickable);
        gridClickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onClick(position, false);
            }
        });
            if(Utility.widthScreen(context)>=580) {
                Picasso.with(context).load("http://jm3eia.com/" + _choices.get(position).getPicture()).resize(115,150).into(imgProduct);


    } else if(Utility.widthScreen(context)>=760){
                Picasso.with(context).load("http://jm3eia.com/" + _choices.get(position).getPicture()).resize(150,190).into(imgProduct);

            }else{
                Picasso.with(context).load("http://jm3eia.com/" + _choices.get(position).getPicture()).into(imgProduct);

            }

        return convertView;
    }


//    @Override
//    public boolean isEnabled(int position) {
//
//        return true;
//    }
}
