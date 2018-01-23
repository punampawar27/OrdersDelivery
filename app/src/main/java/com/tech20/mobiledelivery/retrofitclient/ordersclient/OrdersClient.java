package com.tech20.mobiledelivery.retrofitclient.ordersclient;

import com.tech20.mobiledelivery.helpers.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrdersClient {

    @POST(Constants.UrlConstants.METHOD_GET_ORDERS)
    Call<List<OrdersResponse>> getOrders(@Body OrdersBody ordersBody);
}
