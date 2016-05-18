package droidahmed.com.jm3eia.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
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
    private static final String TABLE_CART = "cart";
    private static final String TABLE_CART_ADD = "cart_add";

    // Common column names
    private static final String KEY_ID = "_id";

    // NOTES Table - column nmaes
    private static final String KEY_ITEM_ID = "id_item";
     private static final String KEY_ADD = "checks";
    private static final String KEY_ENTER = "enter";

     private static final String KEY_QUAN = "quan";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";


    // Table Create Statements
    // Todo table create statement
//    private static final String CREATE_TABLE_ITEM = "CREATE TABLE "
//            + TABLE_ITEM + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ITEM_ID
//            + " TEXT, " + KEY_ADD + " TEXT, "+ KEY_ENTER + " TEXT)";

    String CREATE_TABLE_ITEM = "CREATE TABLE item ( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_item INTEGER unique, " +
            "enter INTEGER, "+
            "checks INTEGER )";

    String CREATE_TABLE_CART = "CREATE TABLE cart ( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_item INTEGER unique, " +
            "quan INTEGER, "+
            "price DOUBLE ," +
            "name TEXT )";
    String CREATE_TABLE_CART_ADD = "CREATE TABLE cart_add ( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_item INTEGER unique, " +
            "quan INTEGER, "+

            "name TEXT )";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    SQLiteDatabase db;
    @Override
    public void onCreate(SQLiteDatabase db) {
     //   String CREATE_TABLE_ITEM ="CREATE TABLE item (_id INTEGER PRIMARY KEY AUTOINCREMENT,id_item INTEGER, checks INTEGER, enter INTEGER)";

        // creating required tables
        db.execSQL(CREATE_TABLE_ITEM);
        db.execSQL(CREATE_TABLE_CART);
        db.execSQL(CREATE_TABLE_CART_ADD);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + "item");
        db.execSQL("DROP TABLE IF EXISTS " + "cart");
        db.execSQL("DROP TABLE IF EXISTS " + "cart_add");

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
    public long createCart(CartQuantity cartQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_ID, cartQuantity.getID());
        values.put(KEY_QUAN, cartQuantity.getcQuantity());
        values.put(KEY_NAME, cartQuantity.getName());
        values.put(KEY_PRICE, cartQuantity.getPrice());



        // insert row
        long todo_id = db.insertWithOnConflict("cart", null, values, SQLiteDatabase.CONFLICT_IGNORE);

        // assigning tags to todo

        return todo_id;
    }
    public long createCartAdd(CartQuantity cartQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_ID, cartQuantity.getID());
        values.put(KEY_QUAN, cartQuantity.getcQuantity());
        values.put(KEY_NAME, cartQuantity.getName());



        // insert row
        long todo_id = db.insertWithOnConflict("cart_add", null, values, SQLiteDatabase.CONFLICT_IGNORE);

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
    public CartQuantity getCartItem(int item_id) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String selectQuery = "SELECT  * FROM " + "cart" + " WHERE "
                    + KEY_ITEM_ID + " = " + item_id;

            Log.d(LOG, selectQuery);

            Cursor c = db.rawQuery(selectQuery, null);

            if (c != null)
                c.moveToFirst();

            CartQuantity td = new CartQuantity();
            assert c != null;
            td.setID(c.getInt(c.getColumnIndex(KEY_ITEM_ID)));
            td.setcQuantity((c.getInt(c.getColumnIndex(KEY_QUAN))));

            return td;
        }catch (Exception e){
            return null;
        }
    }
    public CartQuantity getCartItemAdd(int item_id) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String selectQuery = "SELECT  * FROM " + "cart_add" + " WHERE "
                    + KEY_ITEM_ID + " = " + item_id;

            Log.d(LOG, selectQuery);

            Cursor c = db.rawQuery(selectQuery, null);

            if (c != null)
                c.moveToFirst();

            CartQuantity td = new CartQuantity();
            assert c != null;
            td.setID(c.getInt(c.getColumnIndex(KEY_ITEM_ID)));
            td.setcQuantity((c.getInt(c.getColumnIndex(KEY_QUAN))));

            return td;
        }catch (Exception e){
            return null;
        }
    }
    public ArrayList<CartQuantity> getCart() {
        try {
            ArrayList<CartQuantity> cartList = new ArrayList<>();

            SQLiteDatabase db = this.getReadableDatabase();

            String selectQuery = "SELECT  * FROM " + "cart" ;

            Log.d(LOG, selectQuery);

            Cursor c = db.rawQuery(selectQuery, null);

            if (c != null)
                c.moveToFirst();
            do {
                CartQuantity td = new CartQuantity();
            assert c != null;
            td.setID(c.getInt(c.getColumnIndex(KEY_ITEM_ID)));
            td.setcQuantity((c.getInt(c.getColumnIndex(KEY_QUAN))));
            td.setName(c.getString(c.getColumnIndex(KEY_NAME)));
             td.setPrice(c.getDouble(c.getColumnIndex(KEY_PRICE)));

                cartList.add(td)    ;
            } while (c.moveToNext());

            return cartList;
        }catch (Exception e){
            return null;
        }
    }
    public ArrayList<CartQuantity> getCartAdd() {
        try {
            ArrayList<CartQuantity> cartList = new ArrayList<>();

            SQLiteDatabase db = this.getReadableDatabase();

            String selectQuery = "SELECT  * FROM " + "cart_add" ;

            Log.d(LOG, selectQuery);

            Cursor c = db.rawQuery(selectQuery, null);

            if (c != null)
                c.moveToFirst();
            do {
                CartQuantity td = new CartQuantity();
                assert c != null;
                td.setID(c.getInt(c.getColumnIndex(KEY_ITEM_ID)));
                td.setcQuantity((c.getInt(c.getColumnIndex(KEY_QUAN))));
                td.setName(c.getString(c.getColumnIndex(KEY_NAME)));

                cartList.add(td)    ;
            } while (c.moveToNext());

            return cartList;
        }catch (Exception e){
            return null;
        }
    }
    public void delete(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from "+ TABLE_ITEM);

    }
    public void deleteCart(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from "+ TABLE_CART);

    }

    public void deleteCartAdd(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from "+ TABLE_CART_ADD);

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

    public int updateCart(CartQuantity cartQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_ID, cartQuantity.getID());
        values.put(KEY_QUAN, cartQuantity.getcQuantity());


        // updating row
        return db.update("cart", values, KEY_ITEM_ID + " = ?",
                new String[] { String.valueOf(cartQuantity.getID()) });
    }
    public int updateCartAdd(CartQuantity cartQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_ID, cartQuantity.getID());
        values.put(KEY_QUAN, cartQuantity.getcQuantity());


        // updating row
        return db.update("cart_add", values, KEY_ITEM_ID + " = ?",
                new String[] { String.valueOf(cartQuantity.getID()) });
    }
    public void deleteCart(long cart_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, KEY_ITEM_ID + " = ?",
                new String[] { String.valueOf(cart_id) });
    }
    public void deleteCartAddd(long cart_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART_ADD, KEY_ITEM_ID + " = ?",
                new String[] { String.valueOf(cart_id) });
    }
}
