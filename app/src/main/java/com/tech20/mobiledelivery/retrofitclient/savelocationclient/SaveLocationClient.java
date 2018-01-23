package com.tech20.mobiledelivery.retrofitclient.savelocationclient;

import com.tech20.mobiledelivery.helpers.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SaveLocationClient {

    @POST(Constants.UrlConstants.METHOD_SAVE_DRIVER_LOCATION)
    Call<SaveLocationResponse> saveLocation(@Body SaveLocationBody body);
}
