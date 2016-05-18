package droidahmed.com.jm3eia.start;

import android.app.Application;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

import droidahmed.com.jm3eia.model.CartQuantity;
import droidahmed.com.jm3eia.model.ItemAddedAlready;
import droidahmed.com.jm3eia.model.ItemJson;

/**
 * Created by ahmed on 5/6/2016.
 */
public class SaveAuth extends Application {
   private JSONArray jsonProduct;
    private JSONObject jsonVisitor;
    private HashSet<ItemJson>itemJsons;
    private ArrayList<ItemAddedAlready> itemAdded;
    private ArrayList<CartQuantity> cartQuan;
    private int cancelPosition;
    private ArrayList<Integer> cartQuanPos;
    private ArrayList<CartQuantity> cartQuanDelete;
    ArrayList<CartQuantity> cartForAdd;

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

    public ArrayList<ItemAddedAlready> getItemAdded() {
        return itemAdded;
    }

    public void setItemAdded(ArrayList<ItemAddedAlready> itemAdded) {
        this.itemAdded = itemAdded;
    }

    public ArrayList<CartQuantity> getCartQuan() {
        return cartQuan;
    }

    public void setCartQuan(ArrayList<CartQuantity> cartQuan) {
        this.cartQuan = cartQuan;
    }

    public int getCancelPosition() {
        return cancelPosition;
    }

    public void setCancelPosition(int cancelPosition) {
        this.cancelPosition = cancelPosition;
    }

    public ArrayList<Integer> getCartQuanPos() {
        return cartQuanPos;
    }

    public void setCartQuanPos(ArrayList<Integer> cartQuanPos) {
        this.cartQuanPos = cartQuanPos;
    }

    public ArrayList<CartQuantity> getCartQuanDelete() {
        return cartQuanDelete;
    }

    public void setCartQuanDelete(ArrayList<CartQuantity> cartQuanDelete) {
        this.cartQuanDelete = cartQuanDelete;
    }

    public ArrayList<CartQuantity> getCartForAdd() {
        return cartForAdd;
    }

    public void setCartForAdd(ArrayList<CartQuantity> cartForAdd) {
        this.cartForAdd = cartForAdd;
    }
}
