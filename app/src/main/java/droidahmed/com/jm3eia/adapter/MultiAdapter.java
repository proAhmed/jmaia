package droidahmed.com.jm3eia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.model.SlidingMenuItem;

/**
 * Created by ahmed on 3/18/2016.
 */
public class MultiAdapter extends ArrayAdapter<SlidingMenuItem> {


        public static final int TYPE_ODD = 0;
        public static final int TYPE_EVEN = 1;
        public static final int TYPE_WHITE = 2;
        public static final int TYPE_BLACK = 3;
         private ArrayList<SlidingMenuItem> objects;

        @Override
        public int getViewTypeCount() {
            return 4;
        }

        @Override
        public int getItemViewType(int position) {
            return objects.size();
        }

        public MultiAdapter(Context context, int resource, ArrayList<SlidingMenuItem> objects) {
            super(context, resource, objects);
            this.objects = objects;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;
             int listViewItemType = getItemViewType(position);


            if (convertView == null) {

                if (objects.get(position).getPositions() == 1||objects.get(position).getPositions() == 2|objects.get(position).getPositions() == 3|objects.get(position).getPositions() == 4) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_menu, null);
                 } else if (objects.get(position).getPositions() == 6||objects.get(position).getPositions() == 7||objects.get(position).getPositions() == 8) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_menu_two, null);
                } else if (objects.get(position).getPositions() == 5||objects.get(position).getPositions() == 9) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_menu_four
                            , null);
                }else if (objects.get(position).getPositions() == 10) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_menu_three
                            , null);
                }else if (objects.get(position).getPositions() == 11) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_menu_three
                            , null);
                }

                TextView textView = (TextView) convertView.findViewById(R.id.text);
                viewHolder = new ViewHolder(textView);

                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.getText().setText(objects.get(position).getMenuItemName());

            return convertView;
        }



      public class ViewHolder {
        TextView text;

        public ViewHolder(TextView text) {
            this.text = text;
        }

        public TextView getText() {
            return text;
        }

        public void setText(TextView text) {
            this.text = text;
        }

    }

}

