package com.tech20.mobiledelivery.database.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.tech20.mobiledelivery.database.dataentities.DbModelCashDrawer;
import com.tech20.mobiledelivery.database.dataentities.DbModelCustomers;
import com.tech20.mobiledelivery.helpers.Constants;

import java.util.List;

@Dao
public interface CashDrawerDao {

    @Query("SELECT * FROM "+ Constants.DatabaseConstants.TABLE_CASHDRAWER.TABLE_NAME_CUSTOMER)
    List<DbModelCashDrawer> getAllCashDrawer();

    @Insert
    void insertAll(List<DbModelCashDrawer> customers);

    @Insert
    void insert(DbModelCustomers customer);

    @Delete
    void delete(DbModelCustomers customer);

    @Query("DELETE FROM "+Constants.DatabaseConstants.TABLE_CASHDRAWER.TABLE_NAME_CUSTOMER)
    void deleteAll();
}
