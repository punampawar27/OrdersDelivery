package com.tech20.mobiledelivery.retrofitclient.loginclient;

import com.tech20.mobiledelivery.helpers.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface LoginClient {
//    @Headers({"Content-Type: application/json", "Cache-Control: max-age=640000"})

    @POST(Constants.UrlConstants.METHOD_AUTHENTICATE_USER)
     Call<LoginResponse> login(@Body LoginBody entityLogin);

}
