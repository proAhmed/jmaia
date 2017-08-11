package droidahmed.com.jm3eia.model;

import java.util.ArrayList;

/**
 * Created by ahmed on 1/21/2017.
 */
public class HistoryModelMain {


  private boolean success;
  private  Object error;
  private ArrayList<HistoryModel> data;

  public HistoryModelMain() {
  }

  public HistoryModelMain(boolean success, Object error, ArrayList<HistoryModel> data) {
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

  public ArrayList<HistoryModel> getData() {
    return data;
  }

  public void setData(ArrayList<HistoryModel> data) {
    this.data = data;
  }
}
