package com.tech20.mobiledelivery.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.tech20.mobiledelivery.helpers.Constants;

import java.util.List;


@Dao
public interface SqliteMasterDao {

    @Query("SELECT " + Constants.DatabaseConstants.TABLE_SQLITE_MASTER.COLUMN_NAME_NAME+" FROM "
            + Constants.DatabaseConstants.TABLE_SQLITE_MASTER.TABLE_NAME_SQLITE_MASTER+
            " WHERE "+ Constants.DatabaseConstants.TABLE_SQLITE_MASTER.COLUMN_NAME_TYPE+" LIKE 'table'")
    List<String> getAllTables();
}
