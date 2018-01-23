package com.tech20.mobiledelivery.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.tech20.mobiledelivery.database.dao.CashDrawerDao;
import com.tech20.mobiledelivery.database.dao.CustomerDao;
import com.tech20.mobiledelivery.database.dao.DriverDao;
import com.tech20.mobiledelivery.database.dao.FaqDao;
import com.tech20.mobiledelivery.database.dao.NotesDao;
import com.tech20.mobiledelivery.database.dao.OrderDao;
import com.tech20.mobiledelivery.database.dao.OrderItemsDao;
import com.tech20.mobiledelivery.database.dao.SqliteMasterDao;
import com.tech20.mobiledelivery.database.dao.StoreDao;
import com.tech20.mobiledelivery.database.dataentities.DbModelCashDrawer;
import com.tech20.mobiledelivery.database.dataentities.DbModelCustomers;
import com.tech20.mobiledelivery.database.dataentities.DbModelDriver;
import com.tech20.mobiledelivery.database.dataentities.DbModelFaq;
import com.tech20.mobiledelivery.database.dataentities.DbModelNotes;
import com.tech20.mobiledelivery.database.dataentities.DbModelOrders;
import com.tech20.mobiledelivery.database.dataentities.DbModelStore;
import com.tech20.mobiledelivery.database.dataentities.DbModelOrderInventory;
import com.tech20.mobiledelivery.helpers.Constants;

import java.util.List;

@Database(entities = {DbModelDriver.class, DbModelStore.class, DbModelFaq.class,
                    DbModelCustomers.class, DbModelCashDrawer.class,
        DbModelOrders.class, DbModelOrderInventory.class,
        DbModelNotes.class},version = 3)
public abstract class DatabaseHouse extends RoomDatabase{

    private static DatabaseHouse INSTANCE = null;

    public abstract DriverDao getDriverDao();
    public abstract StoreDao getStoreDao();
    public abstract SqliteMasterDao getSqliteDao();
    public abstract FaqDao getFaqDao();
    public abstract CustomerDao getCustomerDao();
    public abstract CashDrawerDao getCashDrawerDao();
    public abstract OrderDao getOrderDao();
    public abstract OrderItemsDao getOrderItemsDao();
    public abstract NotesDao getNotesDao();

    public static DatabaseHouse getSingleTon(Context applicationContext){


        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(applicationContext,DatabaseHouse.class, Constants.DatabaseConstants.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public void deleteAllTables(List<String> tableNames){
        //This has to execute on a thread
        for(String table:tableNames){
            INSTANCE.query("DELETE FROM "+table,null);
        }

    }
}
