package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 5/9/2016.
 */
public class MainChangeUserData {
    private boolean success;
    private Object error;
    private ChangeData data;

    public MainChangeUserData() {
    }

    public MainChangeUserData(boolean success, Object error, ChangeData data) {
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

    public ChangeData getData() {
        return data;
    }

    public void setData(ChangeData data) {
        this.data = data;
    }
}
