package com.tech20.mobiledelivery.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.tech20.mobiledelivery.database.dataentities.DbModelDriver;
import com.tech20.mobiledelivery.helpers.Constants;

@Dao
public interface DriverDao {


    @Query("SELECT * FROM "+ Constants.DatabaseConstants.TABLE_DRIVER.TABLE_NAME_DRIVER+" WHERE "
            +Constants.DatabaseConstants.TABLE_DRIVER.COLUMN_NAME_DRIVER_ID+" LIKE :driverId")
    DbModelDriver getDriver(String driverId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DbModelDriver driver);

    @Delete
    void delete(DbModelDriver driver);


}
