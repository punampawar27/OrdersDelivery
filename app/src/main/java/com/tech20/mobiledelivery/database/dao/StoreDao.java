package com.tech20.mobiledelivery.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.tech20.mobiledelivery.database.dataentities.DbModelStore;
import com.tech20.mobiledelivery.helpers.Constants;


@Dao
public interface StoreDao {

    @Query("SELECT * FROM "+ Constants.DatabaseConstants.TABLE_STORE.TABLE_NAME_STORE+" WHERE "+
            Constants.DatabaseConstants.TABLE_STORE.COLUMN_NAME_STORE_ID+" LIKE :store_id")
    DbModelStore getStore(String store_id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DbModelStore store);

    @Delete
    void delete(DbModelStore store);
}
