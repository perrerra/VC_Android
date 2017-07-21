package com.mobile.veloconnecte.vcandroid.utils.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mobile.veloconnecte.vcandroid.entities.Bike;
import com.mobile.veloconnecte.vcandroid.entities.Measurment;
import com.mobile.veloconnecte.vcandroid.entities.Ride;
import com.mobile.veloconnecte.vcandroid.entities.User;

/**
 * Created by guillaumetostain on 17/05/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "vcandroid.db";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //context.deleteDatabase(DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        executeBatchSql(db, SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        executeBatchSql(db, SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void executeBatchSql(SQLiteDatabase db, String[] statements){
        for(String sql : statements){
            db.execSQL(sql);
        }
    }

    private final static String[] SQL_CREATE_ENTRIES = {
            User.UserEntry.SQL_CREATE_ENTRIES
            , Bike.BikeEntry.SQL_CREATE_ENTRIES
            , Ride.RideEntry.SQL_CREATE_ENTRIES
            , Measurment.MeasurmentEntry.SQL_CREATE_ENTRIES};

    private final static String[] SQL_DELETE_ENTRIES = {
            User.UserEntry.SQL_DELETE_ENTRIES
            , Bike.BikeEntry.SQL_DELETE_ENTRIES
            , Ride.RideEntry.SQL_DELETE_ENTRIES
            , Measurment.MeasurmentEntry.SQL_DELETE_ENTRIES};
}