package droidahmed.com.jm3eia.model;

import java.util.ArrayList;

import droidahmed.com.jm3eia.api.User;

/**
 * Created by ahmed on 4/21/2016.
 */
public class UserResponse {
    private boolean success;
    private Object error;
    private User data;

    public UserResponse(boolean success, Object error, User data) {
        this.success = success;
        this.error = error;
        this.data = data;
    }

    public UserResponse() {
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

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
