package com.mobile.veloconnecte.vcandroid.utils.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.mobile.veloconnecte.vcandroid.entities.User;
import com.mobile.veloconnecte.vcandroid.entities.base.EntityBase;

/**
 * Created by guillaumetostain on 17/05/2017.
 */

public class UserManager extends DatabaseManager{
    public UserManager(Context context) {
        super(context);
    }

    public long insertUser(User user){

        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.UserEntry.COLUMN_NAME_USERNAME, user.getUsername());
        values.put(User.UserEntry.COLUMN_NAME_PASSWORD, user.getPassword());
        values.put(User.UserEntry.COLUMN_NAME_EMAIL, user.getEmail());
        values.put(User.UserEntry.COLUMN_NAME_IS_ADMIN, user.getIs_admin());

        long newRowId = db.insert(User.UserEntry.TABLE_NAME, null, values);
        user.setId(newRowId);

        return newRowId;
    }

    public User getUserByUsername(String username){
        db = dbHelper.getReadableDatabase();

        String[] projection = {
                EntityBase.EntityBaseEntry.COLUMN_NAME_ID,
                User.UserEntry.COLUMN_NAME_USERNAME,
                User.UserEntry.COLUMN_NAME_PASSWORD,
                User.UserEntry.COLUMN_NAME_EMAIL,
                User.UserEntry.COLUMN_NAME_IS_ADMIN
        };

        // Filter results WHERE clause
        String selection = User.UserEntry.COLUMN_NAME_USERNAME + " = ?";
        String[] selectionArgs = {
                String.valueOf(username)
        };

        // How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                User.UserEntry.TABLE_NAME,          // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // The sort order
        );

        cursor.moveToNext();

        User user = new User();
        user.setId(cursor.getLong(
                cursor.getColumnIndexOrThrow(
                        EntityBase.EntityBaseEntry.COLUMN_NAME_ID)));
        user.setUsername(cursor.getString(
                cursor.getColumnIndexOrThrow(User.UserEntry.COLUMN_NAME_USERNAME)));
        user.setPassword(cursor.getString(
                cursor.getColumnIndexOrThrow(User.UserEntry.COLUMN_NAME_PASSWORD)));
        user.setEmail(cursor.getString(
                cursor.getColumnIndexOrThrow(User.UserEntry.COLUMN_NAME_EMAIL)));
        user.setIs_admin(cursor.getInt(
                cursor.getColumnIndexOrThrow(User.UserEntry.COLUMN_NAME_IS_ADMIN)) != 0);

        cursor.close();

        return user;
    }

    public User getUserById(long id){
        db = dbHelper.getReadableDatabase();

        String[] projection = {
                EntityBase.EntityBaseEntry.COLUMN_NAME_ID,
                User.UserEntry.COLUMN_NAME_USERNAME,
                User.UserEntry.COLUMN_NAME_PASSWORD,
                User.UserEntry.COLUMN_NAME_EMAIL,
                User.UserEntry.COLUMN_NAME_IS_ADMIN
        };

        // Filter results WHERE clause
        String selection = EntityBase.EntityBaseEntry.COLUMN_NAME_ID + " = ?";
        String[] selectionArgs = {
                String.valueOf(id)
        };

        // How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                User.UserEntry.TABLE_NAME,          // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // The sort order
        );

        cursor.moveToNext();

        User user = new User();
        user.setId(cursor.getLong(
                cursor.getColumnIndexOrThrow(
                        EntityBase.EntityBaseEntry.COLUMN_NAME_ID)));
        user.setUsername(cursor.getString(
                cursor.getColumnIndexOrThrow(User.UserEntry.COLUMN_NAME_USERNAME)));
        user.setPassword(cursor.getString(
                cursor.getColumnIndexOrThrow(User.UserEntry.COLUMN_NAME_PASSWORD)));
        user.setEmail(cursor.getString(
                cursor.getColumnIndexOrThrow(User.UserEntry.COLUMN_NAME_EMAIL)));
        user.setIs_admin(cursor.getInt(
                cursor.getColumnIndexOrThrow(User.UserEntry.COLUMN_NAME_IS_ADMIN)) != 0);

        cursor.close();

        return user;
    }


}