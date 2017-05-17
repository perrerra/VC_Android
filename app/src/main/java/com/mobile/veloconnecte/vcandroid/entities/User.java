package com.mobile.veloconnecte.vcandroid.entities;

import com.mobile.veloconnecte.vcandroid.entities.base.EntityBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guillaumetostain on 17/05/2017.
 */

public class User extends EntityBase {

    private String username;
    private String password;
    private String email;
    private Boolean is_admin;
    private List<Bike> bikes;
    private List<Ride> rides;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List<Bike> bikes) {
        this.bikes = bikes;
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public User() {
        this.bikes = new ArrayList<>();
        this.rides = new ArrayList<>();
    }

    public User(String username, String password, String email, Boolean is_admin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.is_admin = is_admin;
    }

    public static class UserEntry {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_IS_ADMIN = "is_admin";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                        EntityBase.EntityBaseEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                        UserEntry.COLUMN_NAME_USERNAME + " TEXT," +
                        UserEntry.COLUMN_NAME_PASSWORD + " TEXT," +
                        UserEntry.COLUMN_NAME_EMAIL + " TEXT," +
                        UserEntry.COLUMN_NAME_IS_ADMIN + " NUMERIC);";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME + ";";
    }
}