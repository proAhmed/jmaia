package droidahmed.com.jm3eia.model;

import java.util.ArrayList;

/**
 * Created by ahmed on 5/18/2016.
 */
public class AboutPage {

    private boolean success;
    private Object error;
    private StaticPageData  data;

    public AboutPage(boolean success, Object error, StaticPageData data) {
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

    public StaticPageData getData() {
        return data;
    }

    public void setData(StaticPageData data) {
        this.data = data;
    }
}
