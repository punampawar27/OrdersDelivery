package com.tech20.mobiledelivery.retrofitclient.customersclient;

import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.retrofitclient.extrainventoryclient.ExtraInventoryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CustomersClient {

    @GET(Constants.UrlConstants.METHOD_GET_CUSTOMER+"{routId}")
    Call<List<CustomersResponse>> getCustomers(@Path("routId") String routId, @Query("driverId") String driverId);
}
