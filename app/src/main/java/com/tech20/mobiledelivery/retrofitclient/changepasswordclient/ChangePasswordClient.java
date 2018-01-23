package com.tech20.mobiledelivery.retrofitclient.changepasswordclient;

import com.tech20.mobiledelivery.helpers.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by fidel25 on 12/11/2017.
 */

public interface ChangePasswordClient {

    @POST(Constants.UrlConstants.METHOD_CHANGE_PASSWORD)
    Call<Void> changePassword(@Body ChangePasswordBody changePasswordBody);
}
