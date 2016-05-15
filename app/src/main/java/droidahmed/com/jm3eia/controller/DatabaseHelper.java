package droidahmed.com.jm3eia.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

import droidahmed.com.jm3eia.model.CartCheck;
import droidahmed.com.jm3eia.model.CartQuantity;

/**
 * Created by ahmed on 5/14/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Table Names
    private static final String TABLE_ITEM = "item";

    // Common column names
    private static final String KEY_ID = "_id";

    // NOTES Table - column nmaes
    private static final String KEY_ITEM_ID = "id_item";
     private static final String KEY_ADD = "checks";
    private static final String KEY_ENTER = "enter";




    // Table Create Statements
    // Todo table create statement
//    private static final String CREATE_TABLE_ITEM = "CREATE TABLE "
//            + TABLE_ITEM + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ITEM_ID
//            + " TEXT, " + KEY_ADD + " TEXT, "+ KEY_ENTER + " TEXT)";

    String CREATE_TABLE_ITEM = "CREATE TABLE item ( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_item INTEGER, " +
            "enter INTEGER, "+
            "checks INTEGER )";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    SQLiteDatabase db;
    @Override
    public void onCreate(SQLiteDatabase db) {
     //   String CREATE_TABLE_ITEM ="CREATE TABLE item (_id INTEGER PRIMARY KEY AUTOINCREMENT,id_item INTEGER, checks INTEGER, enter INTEGER)";

        // creating required tables
        db.execSQL(CREATE_TABLE_ITEM);
      }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + "item");

        // create new tables
        onCreate(db);
    }

    /*
 * Creating a todo
 */
    public long createAdd(CartQuantity cartQuantity,int add,int enter) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_ID, cartQuantity.getID());
          values.put(KEY_ADD, add);
        values.put(KEY_ENTER, enter);


        // insert row
        long todo_id = db.insert("item", null, values);

        // assigning tags to todo

        return todo_id;
    }

    public CartCheck getItem(int item_id) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String selectQuery = "SELECT  * FROM " + "item" + " WHERE "
                    + KEY_ITEM_ID + " = " + item_id;

            Log.d(LOG, selectQuery);

            Cursor c = db.rawQuery(selectQuery, null);

            if (c != null)
                c.moveToFirst();

            CartCheck td = new CartCheck();
            assert c != null;
            td.setCartId(c.getInt(c.getColumnIndex(KEY_ITEM_ID)));
            td.setAdd((c.getInt(c.getColumnIndex(KEY_ADD))));
            td.setEnter(c.getInt(c.getColumnIndex(KEY_ENTER)));

            return td;
        }catch (Exception e){
            return null;
        }
    }

    public void delete(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from "+ TABLE_ITEM);

    }
    public int updateToDo(CartQuantity cartQuantity,int add,int enter) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_ID, cartQuantity.getID());
        values.put(KEY_ADD, add);
        values.put(KEY_ENTER, enter);


        // updating row
        return db.update("item", values, KEY_ITEM_ID + " = ?",
                new String[] { String.valueOf(cartQuantity.getID()) });
    }
}
