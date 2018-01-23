package com.tech20.mobiledelivery.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.tech20.mobiledelivery.database.dataentities.DbModelCustomers;
import com.tech20.mobiledelivery.database.dataentities.DbModelStore;
import com.tech20.mobiledelivery.helpers.Constants;

import java.util.List;

@Dao
public interface CustomerDao {

    @Query("SELECT * FROM "+ Constants.DatabaseConstants.TABLE_CUSTOMER.TABLE_NAME_CUSTOMER)
    List<DbModelCustomers> getCustomers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DbModelCustomers model);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<DbModelCustomers> models);

    @Query("DELETE FROM "+Constants.DatabaseConstants.TABLE_CUSTOMER.TABLE_NAME_CUSTOMER)
    void deleteAll();


}
