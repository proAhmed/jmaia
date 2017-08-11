package droidahmed.com.jm3eia.model;

import java.util.ArrayList;

/**
 * Created by ahmed on 4/22/2016.
 */
    public class MainApi {
    private boolean success;
   //  private ArrayList<String> error;
    private  ArrayList<AllProducts> data;
    private  Pagination[] pages;

    public MainApi(boolean success ,ArrayList<AllProducts>  data) {
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

    public ArrayList<AllProducts>  getData() {
        return data;
    }

    public void setData(ArrayList<AllProducts>  data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MainApi{" +
                "success=" + success +
                 ", data=" + data +
                '}';
    }

    public Pagination[] getPages() {
        return pages;
    }

    public void setPages(Pagination[] pages) {
        this.pages = pages;
    }
}
