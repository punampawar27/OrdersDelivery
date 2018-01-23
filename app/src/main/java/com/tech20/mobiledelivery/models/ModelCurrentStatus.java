package com.tech20.mobiledelivery.models;

import com.tech20.mobiledelivery.enums.EnumAppState;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.helpers.UtilDateFormat;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.RestClient;
import com.tech20.mobiledelivery.retrofitclient.dutyclient.DutyBody;
import com.tech20.mobiledelivery.retrofitclient.dutyclient.DutyClient;
import com.tech20.mobiledelivery.retrofitclient.dutyclient.DutyResponse;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;


public class ModelCurrentStatus {

    public interface IStatusChanged{

        void onStatusChangedSuccess(DutyResponse dutyResponse);
        void onStatusChandedFailed(ErrorMessage errorMessage);
    }

    private IStatusChanged statusChanged = null;
    private PreferenceUtils preferenceUtils = null;
    private Converter<ResponseBody, ErrorMessage> errorConverter = null;


    public void setStatus(IStatusChanged statusChanged,PreferenceUtils preferenceUtils,boolean isStatusOn){

        this.statusChanged = statusChanged;
        this.preferenceUtils = preferenceUtils;
        this.errorConverter = RestClient.getErrorConverter();;

        DutyBody dutyBody = new DutyBody();
        dutyBody.setDriverId(preferenceUtils.getString(Constants.PreferenceConstants.DRIVER_ID));
        dutyBody.setIsAvailableToday(isStatusOn);
        dutyBody.setDeliveryDate(UtilDateFormat.getToday());

        DutyClient dutyClient = RestClient.createServiceWithHeaders(DutyClient.class,preferenceUtils);
        Call<DutyResponse> callDuty = dutyClient.setStatus(dutyBody);


        callDuty.enqueue(isStatusOn?callBackForDutyOn:callBackForDutyOff);

    }

    private Callback<DutyResponse> callBackForDutyOn = new Callback<DutyResponse>() {
        @Override
        public void onResponse(Call<DutyResponse> call, Response<DutyResponse> response) {

            if(response.isSuccessful()){

                DutyResponse dutyResponse = response.body();
                //Change state of application
                preferenceUtils.putInteger(Constants.PreferenceConstants.APP_STATE, EnumAppState.LOGGEDIN_ON_DUTY.getValue());
                preferenceUtils.putString(Constants.PreferenceConstants.SYNC_DATE, UtilDateFormat.getToday());
                preferenceUtils.putString(Constants.PreferenceConstants.ROUTE_ID, dutyResponse.getRouteId());

                statusChanged.onStatusChangedSuccess(dutyResponse);

            }else{

                ErrorMessage errorMessage = null;
                try {
                    errorMessage = errorConverter.convert(response.errorBody());
                } catch (IOException e) {
                    e.printStackTrace();
                    errorMessage = ErrorMessage.getBlankError();
                }
                statusChanged.onStatusChandedFailed(errorMessage);
            }
        }

        @Override
        public void onFailure(Call<DutyResponse> call, Throwable t) {

            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(t.getMessage());
            statusChanged.onStatusChandedFailed(errorMessage);
        }
    };

    private Callback<DutyResponse> callBackForDutyOff = new Callback<DutyResponse>() {
        @Override
        public void onResponse(Call<DutyResponse> call, Response<DutyResponse> response) {

            if(response.isSuccessful()){

                DutyResponse dutyResponse = response.body();
                statusChanged.onStatusChangedSuccess(dutyResponse);

            }else{

                ErrorMessage errorMessage = null;
                try {
                    errorMessage = errorConverter.convert(response.errorBody());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                statusChanged.onStatusChandedFailed(errorMessage);
            }
        }

        @Override
        public void onFailure(Call<DutyResponse> call, Throwable t) {

            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(t.getMessage());
            statusChanged.onStatusChandedFailed(errorMessage);
        }
    };

}
