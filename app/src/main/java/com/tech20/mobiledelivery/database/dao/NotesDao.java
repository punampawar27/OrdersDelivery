package com.tech20.mobiledelivery.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.tech20.mobiledelivery.database.dataentities.DbModelNotes;
import com.tech20.mobiledelivery.helpers.Constants;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM "+ Constants.DatabaseConstants.TABLE_NOTES.TABLE_NAME_NOTES
            +" WHERE "+Constants.DatabaseConstants.TABLE_NOTES.COLUMN_NAME_ORDERID+" LIKE :orderId")
    List<DbModelNotes> getNotes(String orderId);

    @Insert(onConflict = REPLACE)
    void insertAll(List<DbModelNotes> listNotes);

    @Delete
    void delete(DbModelNotes model);

    @Delete
    void delete(List<DbModelNotes> models);

    @Query("DELETE FROM "+Constants.DatabaseConstants.TABLE_NOTES.TABLE_NAME_NOTES)
    void deleteAll();
}
