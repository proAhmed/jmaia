package droidahmed.com.jm3eia.model;

import java.util.ArrayList;

/**
 * Created by ahmed on 5/18/2016.
 */
public class RulesPage {
    private boolean success;
    private Object error;
    private ArrayList<StaticPageData> data;

    public RulesPage(boolean success, Object error, ArrayList<StaticPageData> data) {
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

    public ArrayList<StaticPageData> getData() {
        return data;
    }

    public void setData(ArrayList<StaticPageData> data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
