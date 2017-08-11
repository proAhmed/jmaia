package droidahmed.com.jm3eia.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.OnAddItem;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.OnItemListener;
import droidahmed.com.jm3eia.controller.OnUpdateAdapter;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.AllProducts;
import droidahmed.com.jm3eia.model.CartQuantity;
import droidahmed.com.jm3eia.model.ItemAddedAlready;


/**
 * Created by ahmed on 1/19/2016.
 */
public class ProGridAdapter extends ArrayAdapter<CartQuantity> {

    ArrayList<CartQuantity>  _choices;
    private Context context;
  OnItemListener onItemListener;
    OnCartListener onCartListener;
    ArrayList<ItemAddedAlready> addedAlreadies;
    OnAddItem onAddItem;
    Utility utility;
    private int layoutId;

    public ProGridAdapter(Context context,int layoutId, ArrayList<CartQuantity> _choices, OnItemListener onItemListener,OnCartListener onCartListener
    ,ArrayList<ItemAddedAlready> addedAlreadies,OnAddItem onAddItem) {
        super(context, layoutId, _choices);
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
    public int getCount() {
        return _choices.size();
    }

    @Override
    public CartQuantity getItem(int position) {
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
           convertView = inflater.inflate(layoutId, parent, false);
        }
        item_change  = (TextView) convertView.findViewById(R.id.item_change);
        imgProduct = (ImageView) convertView.findViewById(R.id.imgProduct);

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
        imgProduct = (ImageView) convertView.findViewById(R.id.imgProduct);
        imgAdd = (ImageView) convertView.findViewById(R.id.imgAdd);
        imgDelete = (ImageView) convertView.findViewById(R.id.imgDelete);
        imgCart = (LinearLayout) convertView.findViewById(R.id.imgCart);
        edNumber = (EditText)  convertView.findViewById(R.id.edNumber);

        if( Locale.getDefault().getDisplayLanguage().equals("العربية")) {

            edNumber.setText("" + utility.arabicNumaricCode(String.valueOf(_choices.get(position).getcQuantity())));
        }else{
           edNumber.setText("" + _choices.get(position).getcQuantity());

        }
      Log.d("seen",""+_choices.get(position).getSeen());
 if(_choices.get(position).getSeen()==1){
    item_change.setText(context.getResources().getString(R.string.see_cart));
     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
         imgCart.setBackground(context.getResources().getDrawable(R.drawable.market_bar));
     }else {
         imgCart.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.market_bar));

     }
 }else{
    item_change.setText(context.getResources().getString(R.string.add_cart));
     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
         imgCart.setBackground(context.getResources().getDrawable(R.drawable.market_icon));
     }else {
         imgCart.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.market_icon));

     }
}

         imgCart.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 edNumber.getText();
                 if(_choices.get(position).getSeen()==0){
                     item_change.setText(context.getResources().getString(R.string.see_cart));
                     if(item_change.getText().equals(context.getResources().getString(R.string.see_cart))) {
                         imgCart.setBackgroundColor(context.getResources().getColor(R.color.green));
                         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                             imgCart.setBackground(context.getResources().getDrawable(R.drawable.market_bar));
                         } else {
                             imgCart.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.market_bar));

                         }
                     } }
                 if (!cartWatch[0]) {
                     onCartListener.onAddCart(_choices.get(position), Integer.parseInt(edNumber.getText().toString()), false, price[0]);
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
                if (Integer.parseInt(edNumber.getText().toString()) == 1) {
                    ++cartItem[0];

                } else {
                    cartItem[0] = Integer.parseInt(edNumber.getText().toString());
                    ++cartItem[0];
                }
                if( Locale.getDefault().getDisplayLanguage().equals("العربية")) {

                    edNumber.setText("" + utility.arabicNumaricCode(String.valueOf(cartItem[0])));
                }else{
                    edNumber.setText("" + cartItem[0]);

                }
                price[0] = _choices.get(position).getPrice() * cartItem[0];
                onAddItem.add(Integer.parseInt(edNumber.getText().toString()), _choices.get(position));

            }
        });
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartItem[0] > 1) {
                    if (Integer.parseInt(edNumber.getText().toString()) == 1) {
                        --cartItem[0];

                    } else {
                        cartItem[0] = Integer.parseInt(edNumber.getText().toString());
                        --cartItem[0];
                    }
                   if( Locale.getDefault().getDisplayLanguage().equals("العربية")){
                    edNumber.setText("" + utility.arabicNumaricCode(String.valueOf(cartItem[0])));
                   }
                    else{
                       edNumber.setText("" + cartItem[0]);

                   }
                    price[0] = _choices.get(position).getPrice() * cartItem[0];
                    onAddItem.add(Integer.parseInt(edNumber.getText().toString()), _choices.get(position));

                }
            }
        });
        Typeface type = Typeface.createFromAsset(context.getAssets(), "roboto-thin.ttf");

        tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvName.setTypeface(type);

        tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
        tvOldPrice = (TextView) convertView.findViewById(R.id.tvOldPrice);
         String str = _choices.get(position).getName();
 ArrayList<Integer>integerArrayList = new ArrayList<>();
//        if (_choices.get(position).getName().matches(".*\\d+.*")&& Locale.getDefault().getDisplayLanguage().equals("العربية")){
//              for (int n=0 ; n< _choices.get(position).getName().length() ; n++) {
//                 if (Character.isDigit(_choices.get(position).getName().charAt(n))) {
//                      integerArrayList.add(n);
//
//
//                 }
//
//             }
//            String numbergOnly = "n";
//            String numberOnly =  _choices.get(position).getName().substring(integerArrayList.get(0), integerArrayList.get(integerArrayList.size() - 1) + 1);
//            for (int n=0 ; n< numberOnly.length() ; n++) {
//                if (Character.isLetter(numberOnly.charAt(n))) {
//
//                      numbergOnly =    numberOnly.replaceAll(String.valueOf(numberOnly.charAt(n)), "");
//
//
//                }
//
//            }
//            Log.d("iiii11innn", numberOnly);
//
//            Log.d("iiiiinnn", numbergOnly);
//
//        }else {
            tvName.setText(_choices.get(position).getName());
//        }
        Locale locale = new Locale("en");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tvPrice.setTextLocale(locale);
            tvOldPrice.setTextLocale(locale);
        }
        tvPrice.setText( String.format("%.3f", _choices.get(position).getPrice())+" "+context.getResources().getString(R.string.dr));
        tvOldPrice.setText( String.format("%.3f", _choices.get(position).getOldPrice())+" "+context.getResources().getString(R.string.dr));

        gridClickable = (RelativeLayout) convertView.findViewById(R.id.gridClickable);
        gridClickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onClick(position, false);
            }
        });
             Picasso.with(context).load("http://jm3eia.com/" + _choices.get(position).getPicture()).into(imgProduct);




        return convertView;
    }

//    @Override
//    public boolean isEnabled(int position) {
//
//        return true;
//    }
}
