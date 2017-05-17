package com.mobile.veloconnecte.vcandroid.utils.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by guillaumetostain on 17/05/2017.
 */

public abstract class DatabaseManager {

    SQLiteDatabase db;
    DatabaseHelper dbHelper;

    public DatabaseManager(Context context){
        dbHelper = new DatabaseHelper(context);
    }
}
