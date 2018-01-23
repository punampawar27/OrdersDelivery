package com.tech20.mobiledelivery.database.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.tech20.mobiledelivery.database.dataentities.DbModelOrderInventory;
import com.tech20.mobiledelivery.helpers.Constants;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface OrderItemsDao {

    @Query("SELECT * FROM "+ Constants.DatabaseConstants.TABLE_ORDER_ITEMS.TABLE_NAME_ORDERITEMS
            +" WHERE "+ Constants.DatabaseConstants.TABLE_ORDER_ITEMS.COLUMN_NAME_ORDERID+" LIKE :orderId")
    List<DbModelOrderInventory> getOrderItems(String orderId);

    @Insert(onConflict = REPLACE)
    void insertAll(List<DbModelOrderInventory> listOrders);

    @Delete
    void delete(DbModelOrderInventory model);

    @Delete
    void delete(List<DbModelOrderInventory> listOrders);

    @Query("DELETE FROM "+Constants.DatabaseConstants.TABLE_ORDER_ITEMS.TABLE_NAME_ORDERITEMS)
    void deleteAll();
}
