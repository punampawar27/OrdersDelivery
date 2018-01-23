package com.tech20.mobiledelivery.retrofitclient.deliverorderclient;


import com.tech20.mobiledelivery.helpers.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface DeliverOrderClient {

    @PUT(Constants.UrlConstants.METHOD_UPDATE_DELIVERY_STATUS)
    Call<DeliverOrderResponse> sendOrderStatus(@Body DeliverOrderBody body);
}
