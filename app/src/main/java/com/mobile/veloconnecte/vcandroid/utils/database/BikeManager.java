package com.mobile.veloconnecte.vcandroid.utils.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.mobile.veloconnecte.vcandroid.entities.Bike;
import com.mobile.veloconnecte.vcandroid.entities.User;
import com.mobile.veloconnecte.vcandroid.entities.base.EntityBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guillaumetostain on 17/05/2017.
 */

public class BikeManager extends DatabaseManager {

    UserManager userManager;

    public BikeManager(Context context) {
        super(context);
    }

    public long insertBike(Bike bike){

        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Bike.BikeEntry.COLUMN_NAME_NAME, bike.getName());
        values.put(Bike.BikeEntry.COLUMN_NAME_TYPE, bike.getType());
        values.put(Bike.BikeEntry.COLUMN_NAME_WHEEL_SIZE, bike.getWheel_size());
        values.put(Bike.BikeEntry.COLUMN_NAME_USER_ID, bike.getUser().getId());

        long newRowId = db.insert(Bike.BikeEntry.TABLE_NAME, null, values);
        bike.setId(newRowId);

        return newRowId;
    }

    public Bike getBikeById(long id){
        db = dbHelper.getReadableDatabase();

        String[] projection = {
                EntityBase.EntityBaseEntry.COLUMN_NAME_ID,
                Bike.BikeEntry.COLUMN_NAME_NAME,
                Bike.BikeEntry.COLUMN_NAME_TYPE,
                Bike.BikeEntry.COLUMN_NAME_WHEEL_SIZE,
                Bike.BikeEntry.COLUMN_NAME_USER_ID
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
                Bike.BikeEntry.TABLE_NAME,          // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // The sort order
        );

        cursor.moveToNext();

        long userId = cursor.getInt(
                cursor.getColumnIndexOrThrow(Bike.BikeEntry.COLUMN_NAME_USER_ID));
        User user = userManager.getUserById(userId);

        Bike bike = new Bike();
        bike.setId(cursor.getLong(
                cursor.getColumnIndexOrThrow(
                        EntityBase.EntityBaseEntry.COLUMN_NAME_ID)));
        bike.setName(cursor.getString(
                cursor.getColumnIndexOrThrow(Bike.BikeEntry.COLUMN_NAME_NAME)));
        bike.setType(cursor.getString(
                cursor.getColumnIndexOrThrow(Bike.BikeEntry.COLUMN_NAME_TYPE)));
        bike.setWheel_size(cursor.getInt(
                cursor.getColumnIndexOrThrow(Bike.BikeEntry.COLUMN_NAME_WHEEL_SIZE)));
        bike.setUser(user);

        cursor.close();

        return bike;
    }

    public List<Bike> getBikesByUserId(long id){
        db = dbHelper.getReadableDatabase();

        String[] projection = {
                EntityBase.EntityBaseEntry.COLUMN_NAME_ID,
                Bike.BikeEntry.COLUMN_NAME_NAME,
                Bike.BikeEntry.COLUMN_NAME_TYPE,
                Bike.BikeEntry.COLUMN_NAME_WHEEL_SIZE,
                Bike.BikeEntry.COLUMN_NAME_USER_ID
        };

        // Filter results WHERE clause
        String selection = Bike.BikeEntry.COLUMN_NAME_USER_ID + " = ?";
        String[] selectionArgs = {
                String.valueOf(id)
        };

        // How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                Bike.BikeEntry.TABLE_NAME,          // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // The sort order
        );

        List<Bike> bikes = new ArrayList<>();

        while(cursor.moveToNext()) {

            long userId = cursor.getInt(
                    cursor.getColumnIndexOrThrow(Bike.BikeEntry.COLUMN_NAME_USER_ID));
            User user = userManager.getUserById(userId);

            Bike bike = new Bike();
            bike.setId(cursor.getLong(
                    cursor.getColumnIndexOrThrow(
                            EntityBase.EntityBaseEntry.COLUMN_NAME_ID)));
            bike.setName(cursor.getString(
                    cursor.getColumnIndexOrThrow(Bike.BikeEntry.COLUMN_NAME_NAME)));
            bike.setType(cursor.getString(
                    cursor.getColumnIndexOrThrow(Bike.BikeEntry.COLUMN_NAME_TYPE)));
            bike.setWheel_size(cursor.getInt(
                    cursor.getColumnIndexOrThrow(Bike.BikeEntry.COLUMN_NAME_WHEEL_SIZE)));
            bike.setUser(user);

            bikes.add(bike);
        }

        return bikes;
    }
}