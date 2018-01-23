package com.tech20.mobiledelivery.retrofitclient.cashdrawerclient;

import com.tech20.mobiledelivery.helpers.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CashDrawerClient {

    @POST(Constants.UrlConstants.METHOD_GET_CASH_DRAWER)
    Call<List<CashDrawerResponse>> getCashDrawer(@Body CashDrawerBody body);
}
