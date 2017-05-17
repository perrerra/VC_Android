package com.mobile.veloconnecte.vcandroid.dao.daointerface;

import com.mobile.veloconnecte.vcandroid.entities.base.EntityBase;

import java.util.List;

/**
 * Created by guillaumetostain on 17/05/2017.
 */

public interface IDaoEntity<T extends EntityBase> {
    void insert(T item);
    T selectById(long id);
    List<T> select();
    void delete(T item);
    void update(T item);
}
