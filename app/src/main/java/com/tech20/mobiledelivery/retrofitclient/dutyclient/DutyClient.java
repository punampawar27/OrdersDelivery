package com.tech20.mobiledelivery.retrofitclient.dutyclient;

import com.tech20.mobiledelivery.helpers.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DutyClient {

    @POST(Constants.UrlConstants.METHOD_START_DUTY)
    Call<DutyResponse> setStatus(@Body DutyBody dutyBody);
}
