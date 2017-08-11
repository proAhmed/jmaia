package droidahmed.com.jm3eia.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.OnAddItem;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.OnItemListener;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.CartQuantity;
import droidahmed.com.jm3eia.model.ItemAddedAlready;

/**
 * Created by ahmed on 7/23/2016.
 */
public class EndlessAdapter extends ArrayAdapter<CartQuantity> {

     private int layoutId;
    ArrayList<CartQuantity>  _choices;
    private Context context;
    OnItemListener onItemListener;
    OnCartListener onCartListener;
    ArrayList<ItemAddedAlready> addedAlreadies;
    OnAddItem onAddItem;
    boolean[] cartWatch= {false};

    Utility utility;

    public EndlessAdapter(Context context, ArrayList<CartQuantity> _choices, int layoutId, OnItemListener onItemListener,OnCartListener onCartListener
            ,ArrayList<ItemAddedAlready> addedAlreadies,OnAddItem onAddItem) {
        super(context, layoutId, _choices);
this.layoutId = layoutId;
        this.context = context;
        this._choices = _choices;
        this.onItemListener = onItemListener;
        this.onCartListener = onCartListener;
        this.addedAlreadies = addedAlreadies;
        this.onAddItem = onAddItem;
        utility = new Utility();
    }
    @Override
    public int getCount() {
        return _choices.size() ;
    }

    @Override
    public CartQuantity getItem(int position) {
        return _choices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return _choices.get(position).hashCode();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
         final int[] cartItem = {1};
        final double[] price = {0};

        final TextView tvName,tvPrice,item_change,tvOldPrice;
        ImageView imgProduct,imgAdd,imgDelete;
        final LinearLayout imgCart;
        final EditText edNumber;
        RelativeLayout gridClickable;
             LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutId, parent, false);


        // We should use class holder pattern
//        TextView tv = (TextView) result.findViewById(R.id.txt1);
//        tv.setText(itemList.get(position).getName());


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
//edNumber.addTextChangedListener(new TextWatcher() {
//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        try {
//            if( Locale.getDefault().getDisplayLanguage().equals("العربية")) {
//
//                edNumber.setText("" + utility.arabicNumaricCode(String.valueOf(s)));}
//        }catch (Exception e){
//
//        }
//    }
//
//    @Override
//    public void afterTextChanged(Editable s) {
//
//    }
//});
        if( Locale.getDefault().getDisplayLanguage().equals("العربية")) {

            edNumber.setText("" + utility.arabicNumaricCode(String.valueOf(_choices.get(position).getcQuantity())));
        }else{
            edNumber.setText("" + _choices.get(position).getcQuantity());

        }
        if(_choices.get(position).getAdded()==1){
            item_change.setText(context.getResources().getString(R.string.see_cart));

        }else{
            item_change.setText(context.getResources().getString(R.string.add_cart));

        }

        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edNumber.getText();
                if (cartWatch[0] == false) {
                    onCartListener.onAddCart(_choices.get(position), Integer.parseInt(edNumber.getText().toString()), false, price[0]);
                    item_change.setText(context.getResources().getString(R.string.see_cart));
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
        ArrayList<Integer> integerArrayList = new ArrayList<>();
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
//        if(Utility.widthScreen(context)>=480) {
         Picasso.with(context).load("http://jm3eia.com/" + _choices.get(position).getPicture()).into(imgProduct);
//
//
//        }else
//        if(Utility.widthScreen(context)>=580) {
//            Picasso.with(context).load("http://jm3eia.com/" + _choices.get(position).getPicture()).resize(190,170).into(imgProduct);
//
//
//        } else if(Utility.widthScreen(context)>=760){
//            Picasso.with(context).load("http://jm3eia.com/" + _choices.get(position).getPicture()).resize(230,200).into(imgProduct);
//
//        }
//        else if(Utility.widthScreen(context)>=1280){
//            Picasso.with(context).load("http://jm3eia.com/" + _choices.get(position).getPicture()).resize(350,310).into(imgProduct);
//
//        }else{
//            Picasso.with(context).load("http://jm3eia.com/" + _choices.get(position).getPicture()).into(imgProduct);
//
//        }

        return convertView;

    }

}
