package com.mobile.veloconnecte.vcandroid.entities.base;

import android.provider.BaseColumns;

import java.io.Serializable;

/**
 * Created by guillaumetostain on 17/05/2017.
 */

public abstract class EntityBase implements Serializable {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static class EntityBaseEntry implements BaseColumns {
        public static final String COLUMN_NAME_ID = "id";
    }
}