package com.example.home.homework6;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Home on 4/5/16.
 */
public class UserSQLiteHelper extends SQLiteOpenHelper {

    private static UserSQLiteHelper uInstance;

    public static final String DB_NAME = "userpins.db";

    public UserSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static UserSQLiteHelper getuInstance(Context context) {
        if (uInstance == null) {
            uInstance = new UserSQLiteHelper(context.getApplicationContext(), DB_NAME, null, 1);
        }
        return uInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + User.TABLE_NAME + " ( " +
                        User._ID + " BIGINT PRIMARY KEY, " +
                        User.COL_NAME + " TEXT )"
        );

        db.execSQL("CREATE TABLE " + Pin.TABLE_NAME + " ( " +
                        Pin._ID + " BIGINT PRIMARY KEY," +
                        Pin.COL_LAT + " BIGINT, " +
                        Pin.COL_LNG + " BIGINT, " +
                        Pin.COL_TITLE + " TEXT, " +
                        Pin.COL_SNIPPET + " TEXT, " +
                        Pin.COL_USER_NAME + " TEXT )"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + User.TABLE_NAME);
        db.execSQL("DROP TABLE " + Pin.TABLE_NAME);
        onCreate(db);

    }

    public String getCursorString(Cursor cursor, String colName) {
        return cursor.getString(cursor.getColumnIndex(colName));
    }

    public long getCursorLong(Cursor cursor, String colName) {
        return cursor.getLong(cursor.getColumnIndex(colName));
    }

    public double getCursorDouble(Cursor cursor, String colName) {
        return cursor.getDouble(cursor.getColumnIndex(colName));
    }

    public boolean checkUser(String userName) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + User.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                String name = getCursorString(cursor, User.COL_NAME);
                if (name.equals(userName)) {
                    cursor.close();
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }

    public User getUser(String userName){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + User.TABLE_NAME +
                " WHERE " + User.COL_NAME + " = " + userName, null);

        if(cursor.moveToFirst()) {
            do {
                return new User(userName);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return null;
    }


    public ArrayList<User> getAllUsers(){
        ArrayList<User> users = new ArrayList<User>();

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + User.TABLE_NAME, null);

        if(cursor.moveToFirst()){
            do {
                long id = getCursorLong(cursor, User._ID);
                String name = getCursorString(cursor, User.COL_NAME);
                users.add(new User(name));
            } while(cursor.moveToNext());
        }

        cursor.close();
        return users;
    }

    public ArrayList<Pin> getAllPins(String name){
        ArrayList<Pin> pins = new ArrayList<Pin>();

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + Pin.TABLE_NAME + "WHERE " +
                Pin.COL_USER_NAME + " = " + name, null);

        if(cursor.moveToFirst()){
            do {
                long id = getCursorLong(cursor, Pin._ID);
                double lat = getCursorDouble(cursor, Pin.COL_LAT);
                double lng = getCursorDouble(cursor, Pin.COL_LNG);
                String title = getCursorString(cursor, Pin.COL_TITLE);
                String snippet = getCursorString(cursor, Pin.COL_SNIPPET);
                String userName = getCursorString(cursor, Pin.COL_USER_NAME);
                pins.add(new Pin(lat, lng, title, snippet, userName));
            } while(cursor.moveToNext());
        }
        cursor.close();
        return pins;
    }

    public void insertUser(User user){
        getWritableDatabase().insert(User.TABLE_NAME, null, user.getContentValues());
    }

    public void insertPin(Pin pin){
        getWritableDatabase().insert(Pin.TABLE_NAME, null, pin.getContentValues());
    }


}
