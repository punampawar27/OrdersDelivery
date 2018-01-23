package com.tech20.mobiledelivery.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.RestClient;
import com.tech20.mobiledelivery.retrofitclient.logoutclient.LogoutBody;
import com.tech20.mobiledelivery.retrofitclient.logoutclient.LogoutClient;
import com.tech20.mobiledelivery.retrofitclient.savedeviceidclient.SaveDeviceIdBody;
import com.tech20.mobiledelivery.retrofitclient.savedeviceidclient.SaveDeviceIdClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by root on 17/1/18.
 */

public class FcmTokenScyncService extends Service{


    String tag = "FcmTokenScyncService";
    public static final String SERVICE_NAME="fcmtokensync_service";
    PreferenceUtils preferenceUtils;



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e(tag,"inside onStartCommand");
        preferenceUtils = PreferenceUtils.getINSTANCE(getApplicationContext());

        String driverId = preferenceUtils.getString(Constants.PreferenceConstants.DRIVER_ID);
        String storeId = preferenceUtils.getString(Constants.PreferenceConstants.STORE_ID);

        if( ! TextUtils.isEmpty(driverId) && ! TextUtils.isEmpty(storeId)) {
                syncFcmToken(preferenceUtils);
        }else {
            stopSelf();
        Log.e(tag,"service stopped");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void syncFcmToken(PreferenceUtils preferenceUtils){


        String sessionId = preferenceUtils.getString(Constants.PreferenceConstants.UNIQUE_INSTALLATION_ID);
        String deviceId = preferenceUtils.getString(Constants.PreferenceConstants.FIREBASE_TOKEN);

        Log.e(tag,deviceId);
        Log.e(tag,sessionId);


        SaveDeviceIdBody saveDeviceIdBody = new SaveDeviceIdBody();
        saveDeviceIdBody.setDeviceID(deviceId);
        saveDeviceIdBody.setSessionId(sessionId);

        ///   Log.d(Constants.LogConstants.TAG_WASTE,logoutBody.getDriverId()+" : "+logoutBody.getStoreId());

        final Converter<ResponseBody, ErrorMessage> errorConverter = RestClient.getErrorConverter();

        SaveDeviceIdClient saveDeviceIdClient = RestClient.createServiceWithHeaders(SaveDeviceIdClient.class,preferenceUtils);

        Call<Void> call = saveDeviceIdClient.saveDeviceIdPost(saveDeviceIdBody);


        call.enqueue(new Callback <Void>() {

            @Override
            public void onResponse(Call <Void> call, Response<Void> response) {

                if(response.isSuccessful()){

                    Log.e(Constants.LogConstants.TAG_RESPONSE,""+response.isSuccessful());

                    // callBackLogout.onLogoutSuccess();

                }else{

                    ErrorMessage error = null;

                    try {

                        error = errorConverter.convert(response.errorBody());
                        Log.d(Constants.LogConstants.TAG_RESPONSE,"error: "+error.getMessage());

                    } catch (IOException e) {

                        e.printStackTrace();
                        error = ErrorMessage.getBlankError();

                    }finally {

                       // callBackLogout.onLogoutFaill(error);

                    }
                }
                stopSelf();
            }

        @Override
        public void onFailure(Call <Void> call, Throwable t) {

            stopSelf();

        }
        });
    }

}
