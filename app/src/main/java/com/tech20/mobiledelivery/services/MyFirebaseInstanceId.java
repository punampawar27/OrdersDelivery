package com.tech20.mobiledelivery.services;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;


public class MyFirebaseInstanceId extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        String strFirebaseToken = FirebaseInstanceId.getInstance().getToken();
        PreferenceUtils.getINSTANCE(getApplicationContext()).putString(Constants.PreferenceConstants.FIREBASE_TOKEN,strFirebaseToken);
        Log.d(Constants.LogConstants.TAG_FIREBASE,"Firebase Token:"+strFirebaseToken);

        Intent fcmTokenScyncService = new Intent(getApplicationContext(), FcmTokenScyncService.class);
        startService(fcmTokenScyncService);

        //For running a task more than 10seconds or more go for JobScheduler/JobDispatcher
    }
}
