package droidahmed.com.jm3eia.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.OnAddItem;
import droidahmed.com.jm3eia.controller.OnCancelOrder;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.OnItemListener;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.CartQuantity;
import droidahmed.com.jm3eia.model.ItemAddedAlready;

/**
 * Created by ahmed on 7/19/2016.
 */
public class RelatedAdapter extends RecyclerView
        .Adapter<RelatedAdapter
        .DataObjectHolder> {
    ArrayList<CartQuantity>  _choices;
    private Context context;
    OnItemListener onItemListener;
    OnCartListener onCartListener;
    ArrayList<ItemAddedAlready> addedAlreadies;
    OnAddItem onAddItem;
    Utility utility;
    private int layoutId;
     public static class DataObjectHolder extends RecyclerView.ViewHolder {
         TextView tvName, tvPrice, item_change, tvOldPrice;
         ImageView imgProduct, imgAdd, imgDelete;
         LinearLayout imgCart;
         EditText edNumber;
         RelativeLayout gridClickable;

         public DataObjectHolder(View convertView) {
             super(convertView);
             item_change = (TextView) convertView.findViewById(R.id.item_change);
             imgProduct = (ImageView) convertView.findViewById(R.id.imgProduct);
             imgAdd = (ImageView) convertView.findViewById(R.id.imgAdd);
             imgDelete = (ImageView) convertView.findViewById(R.id.imgDelete);
             imgCart = (LinearLayout) convertView.findViewById(R.id.imgCart);
             edNumber = (EditText) convertView.findViewById(R.id.edNumber);
             imgProduct = (ImageView) convertView.findViewById(R.id.imgProduct);
             tvName = (TextView) convertView.findViewById(R.id.tvName);
             tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
             tvOldPrice = (TextView) convertView.findViewById(R.id.tvOldPrice);
             gridClickable = (RelativeLayout) convertView.findViewById(R.id.gridClickable);

         }
     }
    public RelatedAdapter(Context context,int layoutId, ArrayList<CartQuantity> _choices, OnItemListener onItemListener,OnCartListener onCartListener
            ,ArrayList<ItemAddedAlready> addedAlreadies,OnAddItem onAddItem) {
        this.context = context;
        this._choices = _choices;
        this.onItemListener = onItemListener;
        this.onCartListener = onCartListener;
        this.addedAlreadies = addedAlreadies;
        this.onAddItem = onAddItem;
        utility = new Utility();
        this.layoutId = layoutId;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layoutId, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }
     int[] cartItem = {1};
     double[] price = {0};

    final boolean[] cartWatch = {false};
    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {


        if( Locale.getDefault().getDisplayLanguage().equals("العربية")) {

            holder.   edNumber.setText("" + utility.arabicNumaricCode(String.valueOf(_choices.get(position).getcQuantity())));
        }else{
            holder.   edNumber.setText("" + _choices.get(position).getcQuantity());

        }
        Log.d("seen",""+_choices.get(position).getSeen());
        if(_choices.get(position).getSeen()==1){
            holder.   item_change.setText(context.getResources().getString(R.string.see_cart));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                holder.    imgCart.setBackground(context.getResources().getDrawable(R.drawable.market_bar));
            }else {
                holder.    imgCart.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.market_bar));

            }
        }else{
            holder.  item_change.setText(context.getResources().getString(R.string.add_cart));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                holder.  imgCart.setBackground(context.getResources().getDrawable(R.drawable.market_icon));
            }else {
                holder.  imgCart.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.market_icon));

            }
        }

        holder. imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.  edNumber.getText();
                if(_choices.get(position).getSeen()==0){
                    holder. item_change.setText(context.getResources().getString(R.string.see_cart));
                    if(holder. item_change.getText().equals(context.getResources().getString(R.string.see_cart))) {
                        holder.  imgCart.setBackgroundColor(context.getResources().getColor(R.color.green));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            holder.  imgCart.setBackground(context.getResources().getDrawable(R.drawable.market_bar));
                        } else {
                            holder.  imgCart.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.market_bar));

                        }
                    } }
                if (!cartWatch[0]) {
                    onCartListener.onAddCart(_choices.get(position), Integer.parseInt(holder. edNumber.getText().toString()), false, price[0]);
                    cartWatch[0] = true;

                } else {
                    cartWatch[0] = false;
                    onCartListener.onAddCart(_choices.get(position), Integer.parseInt(holder. edNumber.getText().toString()), true, price[0]);
                }

            }
        });
        holder. imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(holder. edNumber.getText().toString()) == 1) {
                    ++cartItem[0];

                } else {
                    cartItem[0] = Integer.parseInt(holder. edNumber.getText().toString());
                    ++cartItem[0];
                }
                if( Locale.getDefault().getDisplayLanguage().equals("العربية")) {

                    holder. edNumber.setText("" + utility.arabicNumaricCode(String.valueOf(cartItem[0])));
                }else{
                    holder.  edNumber.setText("" + cartItem[0]);

                }
                price[0] = _choices.get(position).getPrice() * cartItem[0];
                onAddItem.add(Integer.parseInt(holder. edNumber.getText().toString()), _choices.get(position));

            }
        });
        holder.  imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartItem[0] > 1) {
                    if (Integer.parseInt(holder. edNumber.getText().toString()) == 1) {
                        --cartItem[0];

                    } else {
                        cartItem[0] = Integer.parseInt(holder. edNumber.getText().toString());
                        --cartItem[0];
                    }
                    if( Locale.getDefault().getDisplayLanguage().equals("العربية")){
                        holder.  edNumber.setText("" + utility.arabicNumaricCode(String.valueOf(cartItem[0])));
                    }
                    else{
                        holder.  edNumber.setText("" + cartItem[0]);

                    }
                    price[0] = _choices.get(position).getPrice() * cartItem[0];
                    onAddItem.add(Integer.parseInt(holder. edNumber.getText().toString()), _choices.get(position));

                }
            }
        });
        Typeface type = Typeface.createFromAsset(context.getAssets(), "roboto-thin.ttf");
        holder. tvName.setTypeface(type);


        String str = _choices.get(position).getName();


        holder.   tvName.setText(_choices.get(position).getName());
        Locale locale = new Locale("en");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            holder.    tvPrice.setTextLocale(locale);
            holder.    tvOldPrice.setTextLocale(locale);
        }
        holder. tvPrice.setText( String.format("%.3f", _choices.get(position).getPrice())+" "+context.getResources().getString(R.string.dr));
        holder.  tvOldPrice.setText( String.format("%.3f", _choices.get(position).getOldPrice())+" "+context.getResources().getString(R.string.dr));

       holder. gridClickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onClick(position, false);
            }
        });
        Picasso.with(context).load("http://jm3eia.com/" + _choices.get(position).getPicture()).into(holder. imgProduct);







    }

    @Override
    public int getItemCount() {
        return _choices.size();
    }


}
