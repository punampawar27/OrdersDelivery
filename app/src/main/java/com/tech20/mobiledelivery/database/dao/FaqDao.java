package com.tech20.mobiledelivery.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.tech20.mobiledelivery.database.dataentities.DbModelFaq;
import com.tech20.mobiledelivery.helpers.Constants;

import java.util.List;

@Dao
public interface FaqDao {

    @Query("SELECT * FROM "+ Constants.DatabaseConstants.TABLE_FAQ.TABLE_NAME_FAQ)
    List<DbModelFaq> getFaqs();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<DbModelFaq> listFaq);
}
