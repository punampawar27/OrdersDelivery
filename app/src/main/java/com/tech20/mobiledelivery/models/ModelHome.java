package com.tech20.mobiledelivery.models;

import android.arch.persistence.room.Database;
import android.util.Log;

import com.tech20.mobiledelivery.database.DataAccess;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dataentities.DbModelDriver;
import com.tech20.mobiledelivery.database.dataentities.DbModelOrders;
import com.tech20.mobiledelivery.database.dataentities.DbModelStore;
import com.tech20.mobiledelivery.enums.EnumOrderStatus;
import com.tech20.mobiledelivery.executors.AppExecutor;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;

import java.util.List;


public class ModelHome {

    public interface IGetStore{
        void getStore(DbModelStore stored,DbModelDriver driver);
    }
    public interface IGetOrderStatus{
        void isOrderExist(boolean status);
    }

    public IGetOrderStatus getOrderStatus = null;

    public void getStore(DatabaseHouse dataHouse, PreferenceUtils preferenceUtils,IGetStore iGetStore){

        DataAccess.execute(()-> {
            DbModelStore store  = dataHouse.getStoreDao().getStore(preferenceUtils.getString(Constants.PreferenceConstants.STORE_ID));
            DbModelDriver driver  = dataHouse.getDriverDao().getDriver(preferenceUtils.getString(Constants.PreferenceConstants.DRIVER_ID));

            AppExecutor.getINSTANCE().getMainThread().execute(new Runnable() {
                @Override
                public void run() {
                    iGetStore.getStore(store,driver);
                }
            });
        });
    }


    public void getOrderStatus(IGetOrderStatus getOrderStatus,DatabaseHouse databaseHouse,PreferenceUtils preferenceUtils){
        this.getOrderStatus = getOrderStatus;
        DataAccess.execute(new ModelHome.RunnableGetOrdersFromDatabase(getOrderStatus,databaseHouse,preferenceUtils.getString(Constants.PreferenceConstants.ROUTE_ID)));
    }


    private static class RunnableGetOrdersFromDatabase implements Runnable {
        DatabaseHouse databaseHouse;
        String routeId;
        boolean flag = false;
        IGetOrderStatus getOrderStatus = null;

        public RunnableGetOrdersFromDatabase(IGetOrderStatus getOrderStatus, DatabaseHouse databaseHouse,String routeId){
            this.databaseHouse = databaseHouse;
            this. routeId = routeId;
            this. getOrderStatus = getOrderStatus;
        }

        @Override
        public void run() {

            List<DbModelOrders> listOrder = databaseHouse.getOrderDao().getOrders(routeId);
            for (DbModelOrders dbModelOrders:listOrder) {
                if(EnumOrderStatus.Placed.getValue() == dbModelOrders.getStatus()){
                    flag = true;
                    break;
                }
            }

            AppExecutor.getINSTANCE().getMainThread().execute(()->{
                getOrderStatus.isOrderExist(flag);
            });
        }
    }

    }
