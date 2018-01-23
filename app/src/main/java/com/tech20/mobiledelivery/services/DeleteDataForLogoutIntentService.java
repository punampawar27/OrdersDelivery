package com.tech20.mobiledelivery.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tech20.mobiledelivery.ApplicationMobileDelivery;
import com.tech20.mobiledelivery.helpers.Constants;


public class DeleteDataForLogoutIntentService extends IntentService {

    public static final String SERVICE_NAME="delete_service";

    public DeleteDataForLogoutIntentService() {

        this(SERVICE_NAME);
    }

    public DeleteDataForLogoutIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(Constants.LogConstants.TAG_WASTE,"Deleting For Logout");
        ((ApplicationMobileDelivery)getApplication()).deleteAllPreference();
        ((ApplicationMobileDelivery)getApplication()).deleteAllTables();
    }
}
