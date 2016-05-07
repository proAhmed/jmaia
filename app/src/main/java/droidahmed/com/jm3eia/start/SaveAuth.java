package droidahmed.com.jm3eia.start;

import android.app.Application;

import org.json.JSONArray;

/**
 * Created by ahmed on 5/6/2016.
 */
public class SaveAuth extends Application {
   private JSONArray jsonProduct;

    public JSONArray getJsonProduct() {
        return jsonProduct;
    }

    public void setJsonProduct(JSONArray jsonProduct) {
        this.jsonProduct = jsonProduct;
    }
}
