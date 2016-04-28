package droidahmed.com.jm3eia.model;

import java.util.ArrayList;

/**
 * Created by ahmed on 4/22/2016.
 */
    public class MainApi {
    private boolean success;
   //  private ArrayList<String> error;
    private  AllProducts[] data;

    public MainApi(boolean success , AllProducts[]  data) {
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

    public AllProducts[]  getData() {
        return data;
    }

    public void setData(AllProducts[]  data) {
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
