package com.mobile.veloconnecte.vcandroid.entities;

import com.mobile.veloconnecte.vcandroid.entities.base.EntityBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guillaumetostain on 17/05/2017.
 */

public class Bike extends EntityBase {

    private String name;
    private String type;
    private int wheel_size;
    private User user;
    private List<Ride> rides;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWheel_size() {
        return wheel_size;
    }

    public void setWheel_size(int wheel_size) {
        this.wheel_size = wheel_size;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public Bike(){
        this.rides = new ArrayList<>();
    }

    public Bike(String name, String type, int wheel_size, User user) {
        this.name = name;
        this.type = type;
        this.wheel_size = wheel_size;
        this.user = user;
    }

    public static class BikeEntry {
        public static final String TABLE_NAME = "bike";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_WHEEL_SIZE = "wheel_size";
        public static final String COLUMN_NAME_USER_ID = "user_id";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + BikeEntry.TABLE_NAME + " (" +
                        EntityBase.EntityBaseEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                        BikeEntry.COLUMN_NAME_NAME + " TEXT," +
                        BikeEntry.COLUMN_NAME_TYPE + " TEXT," +
                        BikeEntry.COLUMN_NAME_WHEEL_SIZE + " INTEGER," +
                        BikeEntry.COLUMN_NAME_USER_ID + " INTEGER);";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + BikeEntry.TABLE_NAME + ";";
    }
}