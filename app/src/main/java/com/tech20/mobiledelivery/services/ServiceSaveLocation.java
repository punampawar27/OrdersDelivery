package com.tech20.mobiledelivery.services;


import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.models.ModelSaveDriverLocation;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.savelocationclient.SaveLocationResponse;
import com.tech20.mobiledelivery.utils.NotificationBuilder;
import com.tech20.mobiledelivery.utils.UtilNotification;
import com.tech20.mobiledelivery.utils.UtilSaveRouteStatus;

public class ServiceSaveLocation extends Service{

    public static final String EXTRA_LAT="EXTRA_LAT";
    public static final String EXTRA_LON="EXTRA_LON";
    public static final String EXTRA_DELIVERYDATE="EXTRA_DELIVERYDATE";
    public static final String EXTRA_ISDELIVERYLOCATION="EXTRA_ISDELIVERYLOCATION";

    private PreferenceUtils prefUtils = null;

    public ServiceSaveLocation(){

    }

    @Override
    public void onCreate() {
        super.onCreate();
        prefUtils = PreferenceUtils.getINSTANCE(getApplicationContext());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        showNotification(this,getResources().getString(R.string.app_name),
                getResources().getString(R.string.syncing_location));


        double lat = intent.getDoubleExtra(EXTRA_LAT,0d);
        double lon = intent.getDoubleExtra(EXTRA_LON,0d);
        String deliveryDate = intent.getStringExtra(EXTRA_DELIVERYDATE);
        boolean isDeliveryLocation = intent.getBooleanExtra(EXTRA_ISDELIVERYLOCATION,false);

        Log.d(Constants.LogConstants.TAG_REC_LOCATION,"Save Location:: Lat: "+lat
                +" Lon: "+lon
                +" DeliveryDate: "+deliveryDate
                +" isDeliveryLocation: "+isDeliveryLocation);

        prefUtils.putDouble(Constants.PreferenceConstants.LAST_LOCATOIN_LATTITUDE,lat);
        prefUtils.putDouble(Constants.PreferenceConstants.LAST_LOCATOIN_LONGITUDE,lon);

        new ModelSaveDriverLocation(new CallBackDriverLocation()).saveDriverLocation(lat,lon,deliveryDate,isDeliveryLocation,prefUtils);

        return startId;
    }


    private class CallBackDriverLocation implements ModelSaveDriverLocation.ISaveDriverLocation {

        @Override
        public void onLocationSavedSuccess(SaveLocationResponse saveLocationResponse) {
            UtilSaveRouteStatus.saveRoutStatus(prefUtils,saveLocationResponse.getRouteId(),saveLocationResponse.getRouteStatus());

            stopSelf();
        }

        @Override
        public void onLocationSavedFail(ErrorMessage errorMessage) {
            //Location Update Failed, No fallback for this bug
                /*
                    Proposed solution for this bug should be, save location in database (cache locations),
                    Send all location to server, and on successful sync delete from cache.
                 */
            stopSelf();

        }
    }
    @SuppressLint("NewApi")
    private void showNotification(Context context, String title, String message){

        NotificationBuilder builder = new NotificationBuilder();
        builder.setContext(context);
        builder.setIcon(R.drawable.icon);
        builder.setMessage(message);
        builder.setTitle(title);

        startForeground(Constants.NOTIFICATION.NOTIFICATION_ID_SERVICESAVE_FORGROUND,builder.build());
    }

}
