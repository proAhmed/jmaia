package droidahmed.com.jm3eia.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.model.Product;


/**
 * Created by ahmed on 1/8/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private ArrayList<Product> dataSet;
  //  RecycleOnClick recycleOnClick;
    public static class MyViewHolder extends RecyclerView.ViewHolder {


      ImageView img,imgShare,imgAdd  ;
        TextView tvPrice,tvColdOr,tvRemote,tvVoltage,tvFilter,tvAnti;
        public MyViewHolder(View itemView) {
            super(itemView);
//            this.img = (ImageView) itemView.findViewById(R.id.textViewName);
//             this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
    public CustomAdapter(ArrayList<Product> data) {
        this.dataSet = data;
     //   this.recycleOnClick = recycleOnClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);

       // view.setOnClickListener(GridFragment.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

//        TextView tvPrice = holder.textViewName;
//         ImageView img = holder.imageViewIcon;
//        ImageView imgShare = holder.imageViewIcon;
//        ImageView imgAdd = holder.imageViewIcon;

//        textViewName.setText(dataSet.get(listPosition).getUserType());
//         imageView.setImageResource(dataSet.get(listPosition).getUserTypeImage());
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                recycleOnClick.onClick(listPosition);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
