package droidahmed.com.jm3eia.start;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

import droidahmed.com.jm3eia.model.ItemAddedAlready;
import droidahmed.com.jm3eia.model.ItemJson;

/**
 * Created by ahmed on 5/7/2016.
 */
public class SaveData {
    private static SaveData ourInstance = new SaveData();
    private JSONArray jsonArray;
    private JSONArray jsonProduct;
    private JSONObject jsonVisitor;
    private HashSet<ItemJson> itemJsons;
    private ArrayList<ItemAddedAlready> itemAdded;
    public JSONArray getJsonProduct() {
        return jsonProduct;
    }

    public JSONObject getJsonVisitor() {
        return jsonVisitor;
    }

    public void setJsonVisitor(JSONObject jsonVisitor) {
        this.jsonVisitor = jsonVisitor;
    }

    public void setJsonProduct(JSONArray jsonProduct) {
        this.jsonProduct = jsonProduct;
    }

    public HashSet<ItemJson> getItemJsons() {
        return itemJsons;
    }

    public void setItemJsons(HashSet<ItemJson> itemJsons) {
        this.itemJsons = itemJsons;
    }
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

    public ArrayList<ItemAddedAlready> getItemAdded() {
        return itemAdded;
    }

    public void setItemAdded(ArrayList<ItemAddedAlready> itemAdded) {
        this.itemAdded = itemAdded;
    }
}
