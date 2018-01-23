package com.tech20.mobiledelivery.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tech20.mobiledelivery.enums.EnumAppState;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;


public class DeleteDataForOffDutyIntentService extends IntentService {

    public static final String SERVICE_NAME="delete_service";
    public DeleteDataForOffDutyIntentService()
    {
        this(SERVICE_NAME);
    }
    public DeleteDataForOffDutyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.d(Constants.LogConstants.TAG_WASTE,"Deleting For Off Duty");
        PreferenceUtils preferenceUtils = PreferenceUtils.getINSTANCE(getApplicationContext());

        //Change state to Off duty
        //Delete Route Id
        preferenceUtils.remove(Constants.PreferenceConstants.ROUTE_ID);

        //Save Off Duty State
        PreferenceUtils.getINSTANCE(getApplicationContext())
                .putInteger(Constants.PreferenceConstants.APP_STATE,
                            EnumAppState.LOGGEDIN_OFF_DUTY.getValue());



    }
}
