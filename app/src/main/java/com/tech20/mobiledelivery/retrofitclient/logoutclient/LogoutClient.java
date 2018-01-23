package com.tech20.mobiledelivery.retrofitclient.logoutclient;

import com.tech20.mobiledelivery.helpers.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface LogoutClient {

    //String URL = WebRequestConstants.METHOD_LOGOUT + "?driverId=" + driverId + "&storeId=" + storeId;
    @POST(Constants.UrlConstants.METHOD_LOGOUT)
    Call<Void> logoutPost(@Body LogoutBody logoutBody);

}
