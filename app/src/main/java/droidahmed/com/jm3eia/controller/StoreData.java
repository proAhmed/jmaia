package droidahmed.com.jm3eia.controller;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ahmed on 4/24/2016.
 */
public class StoreData {
    String DATABASE_NAME = "sand.ubicall";
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
      public StoreData(Context ctx) {
        super();
        this.context = ctx;

        sharedPreferences = context.getSharedPreferences(DATABASE_NAME,
                Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public void saveData(String category) {
        editor.putString("data", category);
        editor.apply();
    }
    public String getData() {
        return sharedPreferences.getString("data", "");
    }

    public void saveAuthName(String category) {
        editor.putString("AuthName", category);
        editor.apply();
    }
    public String getAuthName() {
        return sharedPreferences.getString("AuthName", "");
    }
    public void saveAuthPass(String category) {
        editor.putString("AuthPass", category);
        editor.apply();
    }
    public String getAuthPass() {
        return sharedPreferences.getString("AuthPass", "");
    }

    public void saveEmail(String category) {
        editor.putString("Email", category);
        editor.apply();
    }
    public String getEmail() {
        return sharedPreferences.getString("Email", "");
    }
    public void saveFullName(String category) {
        editor.putString("FullName", category);
        editor.apply();
    }
    public String getFullName() {
        return sharedPreferences.getString("FullName", "");
    }
    public void saveMobile(String category) {
        editor.putString("Mobile", category);
        editor.apply();
    }
    public String getMobile() {
        return sharedPreferences.getString("Mobile", "");
    }
    public void saveZone(String category) {
        editor.putString("Zone", category);
        editor.apply();
    }
    public String getZone() {
        return sharedPreferences.getString("Zone", "");
    }

    public void saveWidget(String category) {
        editor.putString("Widget", category);
        editor.apply();
    }
    public String getWidget() {
        return sharedPreferences.getString("Widget", "");
    }

    public void saveStreet(String category) {
        editor.putString("Street", category);
        editor.apply();
    }
    public String getStreet() {
        return sharedPreferences.getString("Street", "");
    }

    public void saveGada(String category) {
        editor.putString("Gada", category);
        editor.apply();
    }
    public String getGada() {
        return sharedPreferences.getString("Gada", "");
    }
    public void saveHouse(String category) {
        editor.putString("House", category);
        editor.apply();
    }
    public String getHouse() {
        return sharedPreferences.getString("House", "");
    }

    public void savLogin(String category) {
        editor.putString("login", category);
        editor.apply();
    }
    public String getLogin() {
        return sharedPreferences.getString("login", "");
    }

    public void saveCartAdded(int cart) {
        editor.putInt("cart", cart);
        editor.apply();
    }
    public int getCartAdded() {
        return sharedPreferences.getInt("cart", 0);
    }

}
