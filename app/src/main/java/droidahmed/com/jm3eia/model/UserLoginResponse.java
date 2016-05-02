package droidahmed.com.jm3eia.model;

import droidahmed.com.jm3eia.api.User;

/**
 * Created by ahmed on 4/21/2016.
 */
public class UserLoginResponse {
    private boolean success;
    private Object error;
    private UserLogin data;

    public UserLoginResponse(boolean success, Object error, UserLogin data) {
        this.success = success;
        this.error = error;
        this.data = data;
    }

    public UserLoginResponse() {
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

    public UserLogin getData() {
        return data;
    }

    public void setData(UserLogin data) {
        this.data = data;
    }
}
