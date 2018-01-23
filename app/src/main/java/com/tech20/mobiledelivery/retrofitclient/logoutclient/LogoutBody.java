package com.tech20.mobiledelivery.retrofitclient.logoutclient;

import com.google.gson.annotations.SerializedName;
import com.tech20.mobiledelivery.helpers.Constants;


public class LogoutBody {

    @SerializedName(Constants.UrlConstants.POST_PARAMS_DRIVERID)
    private String driverId = null;

    @SerializedName(Constants.UrlConstants.POST_PARAMS_STOREID)
    private String storeId = null;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
