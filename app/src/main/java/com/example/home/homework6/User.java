package com.example.home.homework6;

import android.content.ContentValues;
import android.provider.BaseColumns;


/**
 * Created by Home on 4/5/16.
 */
public class User implements BaseColumns {

    private String mName;
    private long mUserId;


    public static final String TABLE_NAME = "users";
    public static final String COL_NAME = "name";


    public User(String name) {
        mName = name;
        mUserId = Long.parseLong(this._ID);
    }

    public String getName() {
        return mName;
    }

    public long getUserId(){
        return mUserId;
    }


    public ContentValues getContentValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(_ID, mUserId);
        contentValues.put(COL_NAME, mName);
        return contentValues;
    }
}
