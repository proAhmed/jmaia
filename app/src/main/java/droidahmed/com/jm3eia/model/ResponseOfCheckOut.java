package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 5/7/2016.
 */
public class ResponseOfCheckOut {

    private boolean success;
    private Object error;
    private CheckOutData data;

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

    public CheckOutData getData() {
        return data;
    }

    public void setData(CheckOutData data) {
        this.data = data;
    }
}
