package com.tech20.mobiledelivery.models;

import android.util.Log;

import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.RestClient;
import com.tech20.mobiledelivery.retrofitclient.logoutclient.LogoutBody;
import com.tech20.mobiledelivery.retrofitclient.logoutclient.LogoutClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;


public class ModelLogout {

    public interface ICallBackLogout{

        void onLogoutSuccess();
        void onLogoutFaill(ErrorMessage errorMessage);
    }
    public void logout(final ICallBackLogout callBackLogout, PreferenceUtils preferenceUtils){

        String driverId = preferenceUtils.getString(Constants.PreferenceConstants.DRIVER_ID);
        String storeId = preferenceUtils.getString(Constants.PreferenceConstants.STORE_ID);
        LogoutBody logoutBody = new LogoutBody();
        logoutBody.setDriverId(driverId);
        logoutBody.setStoreId(storeId);

        Log.d(Constants.LogConstants.TAG_WASTE,logoutBody.getDriverId()+" : "+logoutBody.getStoreId());
        final Converter<ResponseBody, ErrorMessage> errorConverter = RestClient.getErrorConverter();

        LogoutClient logoutClient = RestClient.createServiceWithHeaders(LogoutClient.class,preferenceUtils);
        Call<Void> callLogout = logoutClient.logoutPost(logoutBody);

        callLogout.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if(response.isSuccessful()){

                    callBackLogout.onLogoutSuccess();
                }else{

                    ErrorMessage error = null;
                    try {

                        error = errorConverter.convert(response.errorBody());

                        Log.d(Constants.LogConstants.TAG_RESPONSE,"error: "+error.getMessage());

                    } catch (IOException e) {
                        e.printStackTrace();
                        error = ErrorMessage.getBlankError();
                    }finally {
                        callBackLogout.onLogoutFaill(error);
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(Constants.LogConstants.TAG_RESPONSE,t.getMessage());

                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setMessage(t.getMessage());
                callBackLogout.onLogoutFaill(errorMessage);
            }
        });

    }

}
