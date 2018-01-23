package com.tech20.mobiledelivery.database.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.icu.text.Replaceable;

import com.tech20.mobiledelivery.database.dataentities.DbModelOrders;
import com.tech20.mobiledelivery.helpers.Constants;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface OrderDao {

    @Query("SELECT * FROM "+ Constants.DatabaseConstants.TABLE_ORDERS.TABLE_NAME_ORDERS+" WHERE "+
    Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_ROUTE_ID+" LIKE :routeId "+
    " ORDER BY "+ Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_STATUS+ ","+
    Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_EXPECTEDDELIVERYTIME)
    List<DbModelOrders> getOrders(String routeId);

    @Insert(onConflict = REPLACE)
    void insert(DbModelOrders dbOrder);

    @Insert(onConflict = REPLACE)
    void insertAll(List<DbModelOrders> listOrder);

    @Delete
    void delete(DbModelOrders order);

    @Delete
    void delete(List<DbModelOrders> listOrder);

    @Query("DELETE FROM "+Constants.DatabaseConstants.TABLE_ORDERS.TABLE_NAME_ORDERS)
    void deleteAll();
}
