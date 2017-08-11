package droidahmed.com.jm3eia.controller;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.fragment.FragmentSubCategoryProduct;
import droidahmed.com.jm3eia.model.CategoryParent;
import droidahmed.com.jm3eia.model.CategoryParentList;

/**
 * Created by anandbose on 09/06/15.
 */
public class ExpandableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int HEADER = 0;
    public static final int CHILD = 1;
    OnItemListener onItemListener;
    private List<CategoryParent> main;
    private List<CategoryParent> child;

    Context context;

    public ExpandableAdapter(List<CategoryParent> main, List<CategoryParent> child, OnItemListener onItemListener) {
        this.main = main;
        this.child = child;

        this.onItemListener = onItemListener;

    }

    public TextView header_title;

    View view = null;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int type) {

        context = parent.getContext();
//        float dp = context.getResources().getDisplayMetrics().density;
//        int subItemPaddingLeft = (int) (10 * dp);
//        int subItemPaddingTopAndBottom = (int) (1 * dp);

        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_menu_right, parent, false);
        ListHeaderViewHolder header = new ListHeaderViewHolder(view);
        header_title = (TextView) view.findViewById(R.id.name);

        return header;
//            case CHILD:
//                TextView itemTextView = new TextView(context);
//                itemTextView.setPadding(subItemPaddingLeft, subItemPaddingTopAndBottom, 0, subItemPaddingTopAndBottom);
//                itemTextView.setTextColor(Color.WHITE);
//                itemTextView.setLayoutParams(
//                        new ViewGroup.LayoutParams(
//                                ViewGroup.LayoutParams.MATCH_PARENT,
//                                ViewGroup.LayoutParams.WRAP_CONTENT));
//                return new RecyclerView.ViewHolder(itemTextView) {
//                };
    }

    boolean bb;

    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final CategoryParent item = main.get(position);

//        switch (item.head) {
//            case HEADER:

        final ListHeaderViewHolder itemController = (ListHeaderViewHolder) holder;
        itemController.refferalItem = item;
        itemController.viewLines.setVisibility(View.VISIBLE);
        if (item.getPicture() != null) {
            Picasso.with(context).load("http://jm3eia.com/" + item.getPicture()).placeholder(R.drawable.place_holder_list).into(itemController.image);

        }
        if (item.getKeywords().equals("parent")) {
//            Picasso.with(context).load("http://jm3eia.com/" +item.getPicture()).placeholder(R.drawable.place_holder_list).into(itemController.image);
            itemController.lin.setBackgroundColor(Color.parseColor("#EBEBED"));
            itemController.header_title.setTextColor(Color.parseColor("#CF5526"));

//            itemController.header_title.setBackgroundColor(context.getResources().getColor(R.color.black_background));
        } else {
            itemController.lin.setBackgroundColor(Color.parseColor("#CF5526"));
            itemController.header_title.setTextColor(Color.parseColor("#FFFFFF"));
            itemController.image.setVisibility(View.INVISIBLE);
        }

        itemController.header_title.setText(item.getAlias());

        itemController.header_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.getID();
                int index = position + 1;
                if (item.getParentID() == 0 && !item.isParent()) {
                    onItemListener.onClick(main.get(position).getCategoryID(), false);

                } else if (item.getParentID() != 0 && !item.isParent()) {
                    onItemListener.onClick(main.get(position).getCategoryID(), false);

                } else if (!bb) {
                    for (int i = 0; i < child.size(); i++) {

                        if (item.getID() == child.get(i).getParentID()) {
                            main.add(position + 1, child.get(i));

                        }

                    }
                    bb = true;

                    notifyDataSetChanged();
                } else {
                    for (int i = 0; i < child.size(); i++) {

                        if (item.getID() == child.get(i).getParentID()) {
                            main.remove(position + 1);

                        }
                        if (main.get(position).getPicture() != null) {
                            itemController.image.setVisibility(View.VISIBLE);

                        } else {

                        }
                    }
                    bb = false;

                    notifyDataSetChanged();

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return main.size();
    }

    private static class ListHeaderViewHolder extends RecyclerView.ViewHolder {
        public TextView header_title;
        public CategoryParent refferalItem;

        LinearLayout viewLine, viewLines, lin;
        ImageView image;

        public ListHeaderViewHolder(View itemView) {
            super(itemView);
            header_title = (TextView) itemView.findViewById(R.id.name);
            image = (ImageView) itemView.findViewById(R.id.item_image);
            viewLine = (LinearLayout) itemView.findViewById(R.id.viewLine);
            viewLines = (LinearLayout) itemView.findViewById(R.id.viewLines);
            lin = (LinearLayout) itemView.findViewById(R.id.lin);
        }
    }

}
