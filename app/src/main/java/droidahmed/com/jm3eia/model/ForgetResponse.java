package droidahmed.com.jm3eia.model;

import java.util.Objects;

/**
 * Created by ahmed on 6/19/2016.
 */
public class ForgetResponse {
    private boolean success;
    private Objects error;
    private String data;

    public ForgetResponse(boolean success, Objects error, String data) {
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

    public Objects getError() {
        return error;
    }

    public void setError(Objects error) {
        this.error = error;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
