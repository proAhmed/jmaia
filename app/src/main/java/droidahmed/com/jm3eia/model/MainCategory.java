package droidahmed.com.jm3eia.model;

import java.util.ArrayList;

/**
 * Created by ahmed on 4/22/2016.
 */
    public class MainCategory {
    private boolean success;
   //  private ArrayList<String> error;
    private ArrayList<CategoryParent>  data;

    public MainCategory(boolean success, ArrayList<CategoryParent> data) {
        this.success = success;
      //  this.error = error;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

//    public ArrayList<String> getError() {
//        return error;
//    }
//
//    public void setError(ArrayList<String> error) {
//        this.error = error;
//    }

    public ArrayList<CategoryParent>  getData() {
        return data;
    }

    public void setData(ArrayList<CategoryParent>  data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MainApi{" +
                "success=" + success +
                 ", data=" + data +
                '}';
    }
}
