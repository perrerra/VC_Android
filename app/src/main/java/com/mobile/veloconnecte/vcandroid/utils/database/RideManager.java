package com.mobile.veloconnecte.vcandroid.utils.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.mobile.veloconnecte.vcandroid.entities.*;
import com.mobile.veloconnecte.vcandroid.entities.base.EntityBase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by guillaumetostain on 17/05/2017.
 */

public class RideManager extends DatabaseManager {

    UserManager userManager;
    BikeManager bikeManager;

    public RideManager(Context context) {
        super(context);

    }

    public long insertRide(Ride ride){

        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Ride.RideEntry.COLUMN_NAME_START_DATE, ride.getStart_date().toString());
        values.put(Ride.RideEntry.COLUMN_NAME_END_DATE, ride.getEnd_date().toString());
        values.put(Ride.RideEntry.COLUMN_NAME_DESCRIPTION, ride.getDescription());
        values.put(Ride.RideEntry.COLUMN_NAME_USER_ID, ride.getUser().getId());
        values.put(Ride.RideEntry.COLUMN_NAME_BIKE_ID, ride.getBike().getId());

        long newRowId = db.insert(Ride.RideEntry.TABLE_NAME, null, values);
        ride.setId(newRowId);

        return newRowId;
    }

    public Ride getRideById(long id) {
        db = dbHelper.getReadableDatabase();

        String[] projection = {
                EntityBase.EntityBaseEntry.COLUMN_NAME_ID,
                Ride.RideEntry.COLUMN_NAME_START_DATE,
                Ride.RideEntry.COLUMN_NAME_END_DATE,
                Ride.RideEntry.COLUMN_NAME_DESCRIPTION,
                Ride.RideEntry.COLUMN_NAME_USER_ID,
                Ride.RideEntry.COLUMN_NAME_BIKE_ID
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
                Ride.RideEntry.TABLE_NAME,          // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // The sort order
        );

        cursor.moveToNext();

        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");

        String startDateStr = cursor.getString(
                cursor.getColumnIndexOrThrow(Ride.RideEntry.COLUMN_NAME_START_DATE));
        String endDateStr = cursor.getString(
                cursor.getColumnIndexOrThrow(Ride.RideEntry.COLUMN_NAME_END_DATE));

        Date startDate = null;
        Date endDate = null;


        try {
            startDate = dateFormat.parse(startDateStr);
            endDate = dateFormat.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long userId = cursor.getInt(
                cursor.getColumnIndexOrThrow(Ride.RideEntry.COLUMN_NAME_USER_ID));
        User user = userManager.getUserById(userId);

        long bikeId = cursor.getInt(
                cursor.getColumnIndexOrThrow(Ride.RideEntry.COLUMN_NAME_BIKE_ID));
        Bike bike = bikeManager.getBikeById(bikeId);

        Ride ride = new Ride();
        ride.setId(cursor.getLong(
                cursor.getColumnIndexOrThrow(
                        EntityBase.EntityBaseEntry.COLUMN_NAME_ID)));
        ride.setStart_date(startDate);
        ride.setStart_date(endDate);
        ride.setDescription(cursor.getString(
                cursor.getColumnIndexOrThrow(Ride.RideEntry.COLUMN_NAME_DESCRIPTION)));
        ride.setUser(user);
        ride.setBike(bike);

        cursor.close();

        return ride;
    }

    public List<Ride> getRidesByUserId(long id) {
        db = dbHelper.getReadableDatabase();

        String[] projection = {
                EntityBase.EntityBaseEntry.COLUMN_NAME_ID,
                Ride.RideEntry.COLUMN_NAME_START_DATE,
                Ride.RideEntry.COLUMN_NAME_END_DATE,
                Ride.RideEntry.COLUMN_NAME_DESCRIPTION,
                Ride.RideEntry.COLUMN_NAME_USER_ID,
                Ride.RideEntry.COLUMN_NAME_BIKE_ID
        };

        // Filter results WHERE clause
        String selection = Ride.RideEntry.COLUMN_NAME_USER_ID + " = ?";
        String[] selectionArgs = {
                String.valueOf(id)
        };

        // How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                Ride.RideEntry.TABLE_NAME,          // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // The sort order
        );

        List<Ride> rides = new ArrayList<>();

        while(cursor.moveToNext()) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");

            String startDateStr = cursor.getString(
                    cursor.getColumnIndexOrThrow(Ride.RideEntry.COLUMN_NAME_START_DATE));
            String endDateStr = cursor.getString(
                    cursor.getColumnIndexOrThrow(Ride.RideEntry.COLUMN_NAME_END_DATE));

            Date startDate = null;
            Date endDate = null;


            try {
                startDate = dateFormat.parse(startDateStr);
                endDate = dateFormat.parse(endDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            long userId = cursor.getInt(
                    cursor.getColumnIndexOrThrow(Ride.RideEntry.COLUMN_NAME_USER_ID));
            User user = userManager.getUserById(userId);

            long bikeId = cursor.getInt(
                    cursor.getColumnIndexOrThrow(Ride.RideEntry.COLUMN_NAME_BIKE_ID));
            Bike bike = bikeManager.getBikeById(bikeId);

            Ride ride = new Ride();
            ride.setId(cursor.getLong(
                    cursor.getColumnIndexOrThrow(
                            EntityBase.EntityBaseEntry.COLUMN_NAME_ID)));
            ride.setStart_date(startDate);
            ride.setStart_date(endDate);
            ride.setDescription(cursor.getString(
                    cursor.getColumnIndexOrThrow(Ride.RideEntry.COLUMN_NAME_DESCRIPTION)));
            ride.setUser(user);
            ride.setBike(bike);

            rides.add(ride);
        }

        return rides;
    }


}