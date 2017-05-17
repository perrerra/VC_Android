package com.mobile.veloconnecte.vcandroid.entities;

import com.mobile.veloconnecte.vcandroid.entities.base.EntityBase;

import java.util.Date;

/**
 * Created by guillaumetostain on 17/05/2017.
 */

public class Ride extends EntityBase {

    private Date start_date;
    private Date end_date;
    private String description;
    private User user;
    private Bike bike;

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public Ride() {
    }

    public Ride(Date start_date, Date end_date, String description, User user, Bike bike) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
        this.user = user;
        this.bike = bike;
    }

    public static class RideEntry {
        public static final String TABLE_NAME = "ride";
        public static final String COLUMN_NAME_START_DATE = "start_date";
        public static final String COLUMN_NAME_END_DATE = "end_date";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_USER_ID = "user_id";
        public static final String COLUMN_NAME_BIKE_ID = "bike_id";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + RideEntry.TABLE_NAME + " (" +
                        EntityBase.EntityBaseEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                        RideEntry.COLUMN_NAME_START_DATE + " NUMERIC," +
                        RideEntry.COLUMN_NAME_END_DATE + " NUMERIC," +
                        RideEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                        RideEntry.COLUMN_NAME_USER_ID + " INTEGER," +
                        RideEntry.COLUMN_NAME_BIKE_ID + " INTEGER);";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + RideEntry.TABLE_NAME + ";";
    }
}