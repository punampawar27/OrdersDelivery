package com.tech20.mobiledelivery.location;


import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class LocationHelper {

    private static LocationHelper INSTANCE = null;

    public static LocationHelper getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new LocationHelper(context);
        }

        return INSTANCE;
    }

    private final int REQUEST_CODE = 1001;
    private final int INTERVAL = 2 * 60 * 1000;
    private boolean isLocationStarted = false;

    private FusedLocationProviderClient mFusedClient = null;
    private LocationRequest locationRequest = null;

    private LocationHelper(){}

    private LocationHelper(Context context) {

        mFusedClient = LocationServices.getFusedLocationProviderClient(context);

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(INTERVAL);
        locationRequest.setInterval(INTERVAL);


    }

    public void startLocation() {

        if(isLocationStarted){
            return;
        }
        if (ActivityCompat.checkSelfPermission(mFusedClient.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mFusedClient.getApplicationContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            throw new Error("Check location permission");
        }
        isLocationStarted = true;
        mFusedClient.requestLocationUpdates(locationRequest, getPendngIntent(mFusedClient.getApplicationContext()));
    }

    public void stopLocation() {
        isLocationStarted = false;
        mFusedClient.removeLocationUpdates(getPendngIntent(mFusedClient.getApplicationContext()));
    }
    private PendingIntent getPendngIntent(Context context){

        Intent intentBroadcast = new Intent(context,BroadCastReceiverLocation.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,REQUEST_CODE,intentBroadcast,PendingIntent.FLAG_UPDATE_CURRENT);

        return pendingIntent;
    }
}
