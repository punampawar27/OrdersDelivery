package com.tech20.mobiledelivery.retrofitclient.savedeviceidclient;

import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.retrofitclient.logoutclient.LogoutBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SaveDeviceIdClient {

    //String URL = WebRequestConstants.METHOD_LOGOUT + "?driverId=" + driverId + "&storeId=" + storeId;
    @POST( Constants.UrlConstants.METHOD_SAVE_DEVICE_ID)
    Call<Void> saveDeviceIdPost(@Body SaveDeviceIdBody saveDeviceIdBody);

}
