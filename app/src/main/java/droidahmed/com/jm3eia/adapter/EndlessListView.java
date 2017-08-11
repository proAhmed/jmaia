package droidahmed.com.jm3eia.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.List;

import droidahmed.com.jm3eia.model.CartQuantity;

/**
 * Created by ahmed on 7/23/2016.
 */
public class EndlessListView extends GridView implements AbsListView.OnScrollListener {

     private boolean isLoading=true;
    private EndlessListener listener;
    private EndlessAdapter adapter;

    public EndlessListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setOnScrollListener(this);
    }

    public EndlessListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnScrollListener(this);
    }

    public EndlessListView(Context context) {
        super(context);
        this.setOnScrollListener(this);
    }

    public void setListener(EndlessListener listener) {
        this.listener = listener;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

        if (getAdapter() == null)
            return ;
        Log.d("ppppp","ppp"+ isLoading);

        if (getAdapter().getCount() == 0)
            return ;

        int l = visibleItemCount + firstVisibleItem;
        if (l >= totalItemCount && isLoading) {
            // It is time to add new data. We call the listener
           isLoading = false;
            listener.loadData();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {}




    public void setAdapter(EndlessAdapter adapter) {
        super.setAdapter(adapter);
        this.adapter = adapter;
     }


    public void addNewData(List<CartQuantity> data,boolean check) {

        Log.d("ppppp",data.toString());

        if(check){
            Log.d("ppppp",data.toString());

//            adapter.addAll(data);
//        adapter.notifyDataSetChanged();
        }
        isLoading = true;
    }


    public EndlessListener setListener() {
        return listener;
    }


    public static interface EndlessListener {
        public void loadData() ;
    }

}
