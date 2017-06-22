package com.mobile.veloconnecte.vcandroid.utils.database;

import android.content.ContentValues;
import android.content.Context;

import com.mobile.veloconnecte.vcandroid.entities.Measurment;

/**
 * Created by guillaumetostain on 21/06/2017.
 */

public class MeasurmentManager extends DatabaseManager {

    public MeasurmentManager(Context context){
        super(context);
    }

    public long insertMeasurment(Measurment measurment){

        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Measurment.MeasurmentEntry.COLUMN_NAME_MEASURMENT_DATE, measurment.getMeasurment_date().toString());
        values.put(Measurment.MeasurmentEntry.COLUMN_NAME_LAT, measurment.getLat());
        values.put(Measurment.MeasurmentEntry.COLUMN_NAME_LNG, measurment.getLng());
        values.put(Measurment.MeasurmentEntry.COLUMN_NAME_SPEED, measurment.getSpeed());
        values.put(Measurment.MeasurmentEntry.COLUMN_NAME_BUMP, measurment.getBump());
        values.put(Measurment.MeasurmentEntry.COLUMN_NAME_AIR_QUALITY, measurment.getAir_quality());
        values.put(Measurment.MeasurmentEntry.COLUMN_NAME_SLOAP, measurment.getSloap());
        values.put(Measurment.MeasurmentEntry.COLUMN_NAME_ROTATION, measurment.getRotation());
        values.put(Measurment.MeasurmentEntry.COLUMN_NAME_RIDE_ID, measurment.getRide().getId());

        long newRowId = db.insert(Measurment.MeasurmentEntry.TABLE_NAME, null, values);
        measurment.setId(newRowId);

        return newRowId;
    }
}
