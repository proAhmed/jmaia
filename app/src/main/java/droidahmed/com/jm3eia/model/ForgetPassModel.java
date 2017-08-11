package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 5/4/2016.
 */
public class ForgetPassModel {

 private String data;
    private String error;
    private boolean success;

    public ForgetPassModel() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ForgetPassModel(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
