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
import java.util.Locale;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.OnAddItem;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.OnItemListener;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.CartQuantity;
import droidahmed.com.jm3eia.model.ItemAddedAlready;
import droidahmed.com.jm3eia.model.Notification;


/**
 * Created by ahmed on 1/19/2016.
 */
public class NotifyAdapters extends BaseAdapter  {

    ArrayList<Notification>  _choices;
    private Context context;
       Utility utility;
    TextView tvTitle,tvContent;
    public NotifyAdapters(Context context, ArrayList<Notification> _choices) {
        this.context = context;
        this._choices = _choices;
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

         LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
           convertView = inflater.inflate(R.layout.notify_item, parent, false);
        }

        tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        tvContent = (TextView) convertView.findViewById(R.id.tvContent);
        tvTitle.setText(_choices.get(position).getTitle());
        tvContent.setText(_choices.get(position).getContent());

        return convertView;
    }


//    @Override
//    public boolean isEnabled(int position) {
//
//        return true;
//    }
}
