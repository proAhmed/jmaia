package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed radwan on 8/9/2017.
 */

public class OrderAllowedResponse {

     private boolean success;
     private Object  error;
     private OrderAllowed data;

    public OrderAllowedResponse() {
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

    public OrderAllowed getData() {
        return data;
    }

    public void setData(OrderAllowed data) {
        this.data = data;
    }
}
