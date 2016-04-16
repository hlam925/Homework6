package com.example.home.homework6;

import android.content.ContentValues;
import android.provider.BaseColumns;

/**
 * Created by Home on 4/5/16.
 */
public class Pin implements BaseColumns {

//    private long mPinId;
    private double mLat;
    private double mLng;
    private String mTitle;
    private String mSnippet;
    private String mUserName;

    public static final String TABLE_NAME = "pins";
    public static final String COL_LAT = "latitude";
    public static final String COL_LNG = "longitude";
    public static final String COL_TITLE = "title";
    public static final String COL_SNIPPET = "snippet";
    public static final String COL_USER_NAME = "user name";

    public Pin(double lat, double lng, String title, String snippet, String userName){
//        mPinId = Long.parseLong(this._ID);
        mLat = lat;
        mLng = lng;
        mTitle = title;
        mSnippet = snippet;
        mUserName = userName;
    }


//    public long getPinId() {
//        return mPinId;
//    }

    public double getLat() {
        return mLat;
    }

    public double getLng() {
        return mLng;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSnippet() {
        return mSnippet;
    }

    public String getUserName() {
        return mUserName;
    }

    public ContentValues getContentValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_LAT, mLat);
        contentValues.put(COL_LNG, mLng);
        contentValues.put(COL_TITLE, mTitle);
        contentValues.put(COL_SNIPPET, mSnippet);
        contentValues.put(COL_USER_NAME, mUserName);
        return contentValues;
    }
}
