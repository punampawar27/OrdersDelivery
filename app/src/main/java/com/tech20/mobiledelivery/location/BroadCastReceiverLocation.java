package com.tech20.mobiledelivery.location;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.LocationResult;
import com.tech20.mobiledelivery.helpers.AndroidVersionUtil;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.UtilDateFormat;
import com.tech20.mobiledelivery.services.ServiceSaveLocation;
import com.tech20.mobiledelivery.utils.LoggerFile;

import java.util.Calendar;
import java.util.List;

public class BroadCastReceiverLocation extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(Constants.LogConstants.TAG_REC_LOCATION,"In Receiver Location");

        if(LocationResult.hasResult(intent)){
            LocationResult locationResult = LocationResult.extractResult(intent);
            List<Location> listLocation = locationResult.getLocations();

            if(listLocation!=null && listLocation.size()>0){

                LoggerFile.d(Constants.LogConstants.TAG_REC_LOCATION,"InBroacastLocation: "+listLocation.get(0).getLatitude()
                        +" :: "+listLocation.get(0).getLongitude()+" "
                        + UtilDateFormat.format(UtilDateFormat.yyyy_MM_dd_T_HH_mm_ss,Calendar.getInstance().getTime()));

                sendDriverLocation(context,
                        listLocation.get(0).getLongitude(),
                        listLocation.get(0).getLongitude(),
                        UtilDateFormat.getToday(),
                        false);
            }
        }
    }

    @SuppressLint("NewApi")
    private void sendDriverLocation(Context context,
                                    double lat,
                                    double lon,
                                    String deliveryDate,
                                    boolean isDeliveryLocation){

        Intent serviceIntent = new Intent(context, ServiceSaveLocation.class);
        serviceIntent.putExtra(ServiceSaveLocation.EXTRA_LAT,lat);
        serviceIntent.putExtra(ServiceSaveLocation.EXTRA_LON,lon);
        serviceIntent.putExtra(ServiceSaveLocation.EXTRA_DELIVERYDATE,deliveryDate);
        serviceIntent.putExtra(ServiceSaveLocation.EXTRA_ISDELIVERYLOCATION,isDeliveryLocation);

        if(AndroidVersionUtil.isBeforeOreo()){
            context.startService(serviceIntent);
        }else{
            context.startForegroundService(serviceIntent);
        }


    }
}
