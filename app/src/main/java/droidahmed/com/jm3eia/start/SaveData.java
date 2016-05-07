package droidahmed.com.jm3eia.start;

import org.json.JSONArray;

/**
 * Created by ahmed on 5/7/2016.
 */
public class SaveData {
    private static SaveData ourInstance = new SaveData();
    private JSONArray jsonArray;
    public static SaveData getInstance() {
        return ourInstance;
    }

    private SaveData() {
    }
    public JSONArray getSaved() {
                 return jsonArray;
             }
    public void setSaved(JSONArray jsonArray) {
                 this.jsonArray = jsonArray;
             }


}
