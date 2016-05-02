package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 5/2/2016.
 */
public class ChangePassOutPut {
    private Object error;
    private boolean success;
    private String data;

    public ChangePassOutPut() {
    }

    public ChangePassOutPut(Object error, boolean success, String data) {
        this.error = error;
        this.success = success;
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
