package com.tech20.mobiledelivery.models;

import android.util.Log;

import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.RestClient;
import com.tech20.mobiledelivery.retrofitclient.savelocationclient.SaveLocationBody;
import com.tech20.mobiledelivery.retrofitclient.savelocationclient.SaveLocationClient;
import com.tech20.mobiledelivery.retrofitclient.savelocationclient.SaveLocationResponse;
import com.tech20.mobiledelivery.utils.UtilSaveRouteStatus;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;


public class ModelSaveDriverLocation {


    /*
    jsonBody.put("DriverId", driverId);
        jsonBody.put("RouteId", routeId);
        jsonBody.put("DeliveryDate", getCurrentTimeStamp());
        jsonBody.put("Longitude", lon);
        jsonBody.put("Latitude", lat);
        jsonBody.put("IsDeliveryLocation", isDelivery);
     */

    public interface ISaveDriverLocation{
        void onLocationSavedSuccess(SaveLocationResponse saveLocationResponse);
        void onLocationSavedFail(ErrorMessage errorMessage);
    }

    private ISaveDriverLocation iSaveDriverLocation = null;
    private Converter<ResponseBody,ErrorMessage> errorConverter = null;

    public ModelSaveDriverLocation(ISaveDriverLocation iSaveDriverLocation){
        this.iSaveDriverLocation = iSaveDriverLocation;
    }

    public void saveDriverLocation(double lat, double lon, String deliveryDate, boolean isDeliveryLocation, PreferenceUtils prefUtils){


        SaveLocationBody body = new SaveLocationBody();
        body.setDriverId(prefUtils.getString(Constants.PreferenceConstants.DRIVER_ID));
        body.setRouteId(prefUtils.getString(Constants.PreferenceConstants.ROUTE_ID));
        body.setDeliveryDate(deliveryDate);
        body.setLatitude(String.valueOf(lat));
        body.setLongitude(String.valueOf(lon));
        body.setIsDeliveryLocation(String.valueOf(isDeliveryLocation));

        errorConverter = RestClient.getErrorConverter();
        SaveLocationClient client = RestClient.createServiceWithHeaders(SaveLocationClient.class,prefUtils);
        Call<SaveLocationResponse> call = client.saveLocation(body);

        call.enqueue(new CallBackSaveLocation(iSaveDriverLocation,prefUtils,errorConverter));

    }

    private static class CallBackSaveLocation implements Callback<SaveLocationResponse>{

        private ISaveDriverLocation iSaveDriverLocation = null;
        private PreferenceUtils preferenceUtils = null;
        private Converter<ResponseBody,ErrorMessage> errorConverter = null;

        private CallBackSaveLocation(ISaveDriverLocation iSaveDriverLocation,
                                     PreferenceUtils preferenceUtils,
                                     Converter<ResponseBody,ErrorMessage> errorConverter){
            this.iSaveDriverLocation = iSaveDriverLocation;
            this.preferenceUtils = preferenceUtils;
            this.errorConverter = errorConverter;

        }
        @Override
        public void onResponse(Call<SaveLocationResponse> call, Response<SaveLocationResponse> response) {

            if(response.isSuccessful()){

                SaveLocationResponse body = response.body();
                saveRoute(body.getRouteId(),body.getRouteStatus());
                iSaveDriverLocation.onLocationSavedSuccess(body);

            }else{
                ErrorMessage errorMessage = null;

                try {
                    errorMessage = errorConverter.convert(response.errorBody());
                } catch (IOException e) {
                    e.printStackTrace();

                    errorMessage = ErrorMessage.getBlankError();
                }finally {
                    iSaveDriverLocation.onLocationSavedFail(errorMessage);
                }
            }

        }

        @Override
        public void onFailure(Call<SaveLocationResponse> call, Throwable t) {

            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(t.getMessage());

            iSaveDriverLocation.onLocationSavedFail(errorMessage);
        }

        private void saveRoute(String routeId,String routeStatus){


            UtilSaveRouteStatus.saveRoutStatus(preferenceUtils,routeId,routeStatus);
        }
    }
}
