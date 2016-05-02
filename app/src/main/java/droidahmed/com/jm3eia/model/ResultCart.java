package droidahmed.com.jm3eia.model;

import java.util.ArrayList;

/**
 * Created by ahmed on 4/30/2016.
 */
public class ResultCart {
    private boolean   success;
    private Object    error;
    private ArrayList<CartItem> data;

    public ResultCart() {
    }

    public ResultCart(boolean success, Object error, ArrayList<CartItem> data) {
        this.success = success;
        this.error = error;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public ArrayList<CartItem> getData() {
        return data;
    }

    public void setData(ArrayList<CartItem> data) {
        this.data = data;
    }
}
