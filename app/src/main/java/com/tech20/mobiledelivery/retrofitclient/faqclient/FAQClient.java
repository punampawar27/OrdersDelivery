package com.tech20.mobiledelivery.retrofitclient.faqclient;

import com.tech20.mobiledelivery.helpers.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface FAQClient {

    @GET(Constants.UrlConstants.METHOD_GET_ALL_FAQ)
    Call<List<FAQResponse>> getFAQs(@Query("driverId") String driverId);

}
