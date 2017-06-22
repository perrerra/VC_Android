package com.mobile.veloconnecte.vcandroid.entities;

import com.mobile.veloconnecte.vcandroid.entities.base.EntityBase;

import java.util.Date;

/**
 * Created by guillaumetostain on 21/06/2017.
 */

public class Measurment extends EntityBase {
    private Date measurment_date;
    private Double lat;
    private Double lng;
    private Double speed;
    private int bump;
    private Double air_quality;
    private Double sloap;
    private Double rotation;
    private Ride ride;

    public Date getMeasurment_date() {
        return measurment_date;
    }

    public void setMeasurment_date(Date measurment_date) {
        this.measurment_date = measurment_date;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public int getBump() {
        return bump;
    }

    public void setBump(int bump) {
        this.bump = bump;
    }

    public Double getAir_quality() {
        return air_quality;
    }

    public void setAir_quality(Double air_quality) {
        this.air_quality = air_quality;
    }

    public Double getSloap() {
        return sloap;
    }

    public void setSloap(Double sloap) {
        this.sloap = sloap;
    }

    public Double getRotation() {
        return rotation;
    }

    public void setRotation(Double rotation) {
        this.rotation = rotation;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public Measurment() {
    }

    public Measurment(Date measurment_date, Double lat, Double lng, Double speed, int bump, Double air_quality, Double sloap, Double rotation, Ride ride) {
        this.measurment_date = measurment_date;
        this.lat = lat;
        this.lng = lng;
        this.speed = speed;
        this.bump = bump;
        this.air_quality = air_quality;
        this.sloap = sloap;
        this.rotation = rotation;
        this.ride = ride;
    }

    public static class MeasurmentEntry {
        public static final String TABLE_NAME = "measurment";
        public static final String COLUMN_NAME_MEASURMENT_DATE = "measurment_date";
        public static final String COLUMN_NAME_LAT = "lat";
        public static final String COLUMN_NAME_LNG = "lng";
        public static final String COLUMN_NAME_SPEED = "speed";
        public static final String COLUMN_NAME_BUMP = "bump";
        public static final String COLUMN_NAME_AIR_QUALITY = "air_quality";
        public static final String COLUMN_NAME_SLOAP = "sloap";
        public static final String COLUMN_NAME_ROTATION = "rotation";
        public static final String COLUMN_NAME_RIDE_ID = "ride_id";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + MeasurmentEntry.TABLE_NAME + " (" +
                        EntityBase.EntityBaseEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                        MeasurmentEntry.COLUMN_NAME_MEASURMENT_DATE + " TEXT," +
                        MeasurmentEntry.COLUMN_NAME_LAT + " REAL," +
                        MeasurmentEntry.COLUMN_NAME_LNG + " REAL," +
                        MeasurmentEntry.COLUMN_NAME_SPEED + " REAL," +
                        MeasurmentEntry.COLUMN_NAME_BUMP + " INTEGER," +
                        MeasurmentEntry.COLUMN_NAME_AIR_QUALITY + " REAL," +
                        MeasurmentEntry.COLUMN_NAME_SLOAP + " REAL," +
                        MeasurmentEntry.COLUMN_NAME_ROTATION + " REAL," +
                        MeasurmentEntry.COLUMN_NAME_RIDE_ID + " INTEGER);";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + MeasurmentEntry.TABLE_NAME + ";";
    }
}

