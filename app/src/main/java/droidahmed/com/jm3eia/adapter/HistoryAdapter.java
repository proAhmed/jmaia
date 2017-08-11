package droidahmed.com.jm3eia.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
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
import java.util.Collection;
import java.util.Locale;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.OnAddItem;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.OnItemListener;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.lib.InfiniteScrollAdapter;
import droidahmed.com.jm3eia.model.CartQuantity;
import droidahmed.com.jm3eia.model.HistoryModel;
import droidahmed.com.jm3eia.model.ItemAddedAlready;

public class HistoryAdapter extends BaseAdapter {
    private Context context;
      ArrayList<HistoryModel> historyModels;
     Utility utility;


    public HistoryAdapter(Context context , ArrayList<HistoryModel> historyModels) {
        this.context = context;
          this.historyModels = historyModels;
         utility = new Utility();
    }


    @Override
    public int getCount() {
        return historyModels.size();
    }

    @Override
    public Object getItem(int position) {
        return historyModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final TextView tvOrderHistory,tvTotalPrice,tvPaymentMethods;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.history_item, parent, false);
        }
        tvOrderHistory = (TextView) convertView.findViewById(R.id.tvOrderHistory);
        tvTotalPrice = (TextView) convertView.findViewById(R.id.tvTotalPrice);
        tvPaymentMethods = (TextView) convertView.findViewById(R.id.tvPaymentMethods);
        tvOrderHistory.setText(historyModels.get(position).getOrderDate());
        tvTotalPrice.setText(historyModels.get(position).getTotalPrice()+"");
        if(historyModels.get(position).getPayment_Method()==1){
            tvPaymentMethods.setText("تم الدفع عند الاستلام");

        }else {
            tvPaymentMethods.setText("تم الدفع ب knet");

        }

        return convertView;
    }


}
