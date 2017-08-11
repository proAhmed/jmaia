package droidahmed.com.jm3eia.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.OnChildClick;
import droidahmed.com.jm3eia.controller.OnItemListener;
import droidahmed.com.jm3eia.model.CategoryParent;

/**
 * Created by ahmed on 5/28/2016.
 */
public class ExpandableListSubAdapters extends BaseExpandableListAdapter {

    private Context _context;
    private List<CategoryParent> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, ArrayList<CategoryParent>> _listDataChild;
    OnItemListener onItemListener;
OnChildClick onChildClick;
    public ExpandableListSubAdapters(Context context, List<CategoryParent> listDataHeader,
                                     HashMap<String, ArrayList<CategoryParent>> listChildData,
                                     OnItemListener onItemListener, OnChildClick onChildClick) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.onItemListener =onItemListener;
        this.onChildClick =onChildClick;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        Log.d("pppch",""+this._listDataChild.get(this._listDataHeader.get(groupPosition).getAlias())
                .get(childPosititon));
        return this._listDataChild.get(this._listDataHeader.get(groupPosition).getAlias())
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = ((CategoryParent) getChild(groupPosition, childPosition)).getAlias();

//        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_child_right, null);
//        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.name);
        LinearLayout lin = (LinearLayout) convertView.findViewById(R.id.lin);

        txtListChild.setText(childText);

        lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!((CategoryParent) getChild(groupPosition, childPosition)).isParent()){
                    onChildClick.onClick(((CategoryParent) getChild(groupPosition, childPosition)));
                }else{
                    Toast.makeText(_context,"short",Toast.LENGTH_SHORT).show();

                }
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(this._listDataChild.get(this._listDataHeader.get(groupPosition).getAlias())==null){
            return 0;
        }else{
            return this._listDataChild.get(this._listDataHeader.get(groupPosition).getAlias())
                    .size();
        }

    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = ((CategoryParent) getGroup(groupPosition)).getAlias();
//        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_menu_right_mod, null);
//        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.name);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_image);
        Picasso.with(_context).load("http://jm3eia.com/" +((CategoryParent) getGroup(groupPosition)).getPicture()).into(imageView);
        LinearLayout lin = (LinearLayout) convertView.findViewById(R.id.lin);
        lblListHeader.setText(headerTitle);
        ImageView arrow_image = (ImageView) convertView.findViewById(R.id.arrow_image);
        if (isExpanded) {
            arrow_image.setImageResource(R.drawable.arrow_down);
        } else {
            arrow_image.setImageResource(R.drawable.arrow);
        }
         if (!((CategoryParent) getGroup(groupPosition)).isParent()) {
            lin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemListener.onClick(((CategoryParent) getGroup(groupPosition)).getID(), false);
                }
            });
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
