package com.tech20.mobiledelivery.retrofitclient.inventoryclient;


import com.tech20.mobiledelivery.helpers.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InventoryClient {

    @GET(Constants.UrlConstants.METHOD_GET_INVENTORY+"{routId}")
    Call<List<InventoryResponse>> getInventory(@Path("routId") String routId, @Query("driverId") String driverId);
}
