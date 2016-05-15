package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 5/4/2016.
 */
public class ForgetPassModel {

 private String Result;
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

    public ForgetPassModel(String result) {
        Result = result;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }
}
