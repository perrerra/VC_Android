package com.mobile.veloconnecte.vcandroid.dao.daointerface;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by guillaumetostain on 17/05/2017.
 */

public interface IDaoBase {
    long insert(String table, ContentValues values);
    Cursor selectById(long id, String table, String[] projection);
    Cursor select(String table, String[] projection);
    void delete(long id, String table);
    int update(long id, String table, ContentValues values);
}