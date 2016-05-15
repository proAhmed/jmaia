package droidahmed.com.jm3eia.model;

import java.util.ArrayList;

/**
 * Created by ahmed on 4/21/2016.
 */
public class CartItemResponse {
    private boolean success;
    private Object error;
    private ArrayList<CartQuantity> data;

    public CartItemResponse(boolean success, Object error, ArrayList<CartQuantity> data) {
        this.success = success;
        this.error = error;
        this.data = data;
    }

    public CartItemResponse() {
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

    public ArrayList<CartQuantity> getData() {
        return data;
    }

    public void setData(ArrayList<CartQuantity> data) {
        this.data = data;
    }
}
