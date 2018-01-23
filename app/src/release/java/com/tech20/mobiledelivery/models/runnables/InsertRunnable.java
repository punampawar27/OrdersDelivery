package com.tech20.mobiledelivery.models.runnables;

import android.util.Log;

import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dao.DriverDao;
import com.tech20.mobiledelivery.database.dao.StoreDao;
import com.tech20.mobiledelivery.database.dataentities.DbModelDriver;
import com.tech20.mobiledelivery.database.dataentities.DbModelStore;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.models.ModelLogin;
import com.tech20.mobiledelivery.retrofitclient.loginclient.LoginResponse;

/**
 * Created by fidel25 on 12/12/2017.
 */

public class InsertRunnable implements Runnable{

    private DatabaseHouse dataHouse;
    private PreferenceUtils preferenceUtils;
    private LoginResponse loginResponse;
    private ModelLogin.ICallBackLogin callBack = null;

    public InsertRunnable(DatabaseHouse dataHouse, PreferenceUtils preferenceUtils, LoginResponse loginResponse, ModelLogin.ICallBackLogin callBack) {
        this.dataHouse = dataHouse;
        this.preferenceUtils = preferenceUtils;
        this.loginResponse = loginResponse;
        this.callBack = callBack;
    }

    @Override
    public void run() {
        saveData(dataHouse,preferenceUtils,loginResponse,callBack);
    }

    private void saveData(DatabaseHouse dataHouse,PreferenceUtils preferenceUtils,LoginResponse loginResponse,ModelLogin.ICallBackLogin callback){

        //This should run in enque
        LoginResponse.StoreDetails storeDetails = loginResponse.getStoreDetails();
        LoginResponse.Address address = storeDetails.getAddress();
        StoreDao storeDao = dataHouse.getStoreDao();
        DriverDao driverDao = dataHouse.getDriverDao();


        //Store To Driver have OneToMany Relaionship
        //Inserting Store in Database
        DbModelStore dbModelStore = new DbModelStore();
        dbModelStore.setStore_id(Integer.parseInt(storeDetails.getId()));
        dbModelStore.setShop_name(storeDetails.getShop_name());
        dbModelStore.setEmail(storeDetails.getEmail());
        dbModelStore.setContact_no(storeDetails.getContact_no());
        dbModelStore.setAddress_line_1(address.getLine_1());
        dbModelStore.setAddress_line_2(address.getLine_2());
        dbModelStore.setCity(address.getCity());
        dbModelStore.setState_code(address.getState_code());
        dbModelStore.setCountry_code(address.getCountry_code());
        dbModelStore.setPostal_code(address.getPostal_code());
        storeDao.insert(dbModelStore);

        //Inserting Driver in Database
        DbModelDriver dbModelDriver = new DbModelDriver();
        dbModelDriver.setApi_key(loginResponse.getApi_key());
        dbModelDriver.setApi_secret(loginResponse.getApi_secret());
        dbModelDriver.setDriverId(loginResponse.getDriverId());
        dbModelDriver.setStore_id(Integer.parseInt(storeDetails.getId()));
        dbModelDriver.setFirst_name(storeDetails.getFirst_name());
        dbModelDriver.setLast_name(storeDetails.getLast_name());
        driverDao.insert(dbModelDriver);
        Log.d(Constants.LogConstants.TAG_WASTE,"Insert Finished");

        preferenceUtils.putString(Constants.PreferenceConstants.DRIVER_ID,dbModelDriver.getDriverId());
        preferenceUtils.putString(Constants.PreferenceConstants.STORE_ID,String.valueOf(dbModelDriver.getStore_id()));
        preferenceUtils.putString(Constants.PreferenceConstants.API_KEY,dbModelDriver.getApi_key());
        preferenceUtils.putString(Constants.PreferenceConstants.API_SECRET,dbModelDriver.getApi_secret());

        callBack.onCallBackSuccess(loginResponse);
    }
}
