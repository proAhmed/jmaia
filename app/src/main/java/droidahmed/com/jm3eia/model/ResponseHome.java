package droidahmed.com.jm3eia.model;

import java.util.ArrayList;

/**
 * Created by ahmed on 4/3/2016.
 */
public class ResponseHome {
    private String success;
    private boolean error;
    private ArrayList<HomeModel> data;

    public ResponseHome(String success, boolean error, ArrayList<HomeModel> data) {
        this.success = success;
        this.error = error;
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ArrayList<HomeModel> getData() {
        return data;
    }

    public void setData(ArrayList<HomeModel> data) {
        this.data = data;
    }
}
