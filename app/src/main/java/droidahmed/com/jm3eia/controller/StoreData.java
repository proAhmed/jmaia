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
        String jsonString = sharedPreferences.getString("data", "");
        return jsonString;
    }






}
