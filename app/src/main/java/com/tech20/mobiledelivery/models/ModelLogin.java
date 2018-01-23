package com.tech20.mobiledelivery.models;

import android.util.Log;

import com.tech20.mobiledelivery.database.DataAccess;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dao.DriverDao;
import com.tech20.mobiledelivery.database.dao.StoreDao;
import com.tech20.mobiledelivery.database.dataentities.DbModelDriver;
import com.tech20.mobiledelivery.database.dataentities.DbModelStore;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.models.runnables.InsertRunnable;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.RestClient;
import com.tech20.mobiledelivery.retrofitclient.loginclient.LoginBody;
import com.tech20.mobiledelivery.retrofitclient.loginclient.LoginClient;
import com.tech20.mobiledelivery.retrofitclient.loginclient.LoginResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

/*
{
    "api_key": "d95f8196-6a02-458b-b7fd-1102a4525eba",
    "api_secret": "F9C4545E12BA699D0D69EEE1A5675B4B",
    "DriverId": 20,
    "storeDetails": {
        "id": 5141,
        "first_name": "FidelGlobal",
        "last_name": "",
        "shop_name": "FidelGlobal",
        "email": "fidel@fidelitservices.com",
        "contact_no": "0204865256",
        "address": {
            "line_1": "Test",
            "line_2": "",
            "city": "Pune",
            "state_code": "3",
            "country_code": "1",
            "postal_code": "422003                                            "
        }
    },
    "driverDetails": null
}
 */

/*
{
    "DriverId": 0,
    "api_key": "72d43a3e-5619-4093-90e8-ee4d62d6d8dd",
    "api_secret": "D9D8B8230DA3E7A27EDA7C5ADB89F03A",
    "storeDetails": {
        "id": 6630,
        "shop_name": "Cannapos",
        "email": "cannapos@cannapos.com",
        "contact_no": "45654565",
        "address": {
            "line_1": "Cannapos address",
            "line_2": "",
            "city": "cannapos",
            "state_code": "1",
            "country_code": "1",
            "postal_code": "41214121                                          "
        }
    },
    "address": null,
    "driverDetails": {
        "DriverId": 386,
        "FirstName": "pank",
        "LastName": "zan",
        "MobileNumber": "98749874",
        "device_id": "863854037854631922616973a11ae5eFMUSAYMZQCBMEMDU",
        "LicenceNo": "1234512234",
        "UserName": "pankz",
        "Password": "",
        "ClientId": 6630,
        "isDeleted": false,
        "RouteId": 0,
        "VehicleNumber": null,
        "isAvailable": false,
        "SessionId": "ABC",
        "AddressLine1": "address adress",
        "AddressLine2": "",
        "City": "Pune",
        "State": "maharastr",
        "ZipCode": 123451234,
        "Country": "India"
    },
    "MobileNumber": null,
    "device_id": null,
    "LicenceNo": null,
    "VehicleNumber": null,
    "UserName": null,
    "RouteId": 0,
    "AddressLine1": null,
    "AddressLine2": null,
    "City": null,
    "State": null,
    "ZipCode": 0,
    "Country": null
}
 */
//Domain Logic
public class ModelLogin {

    private ICallBackLogin iCallBackLogin = null;

    public interface ICallBackLogin{

        void onCallBackSuccess(LoginResponse loginResponse);
        void onCallBackFail(ErrorMessage errorMessage);
    }

    ICallBackLogin callBackAppExecutor = new ICallBackLogin() {
        @Override
        public void onCallBackSuccess(LoginResponse loginResponse) {
            iCallBackLogin.onCallBackSuccess(loginResponse);
        }

        @Override
        public void onCallBackFail(ErrorMessage errorMessage) {

        }
    };

    public void getLogin(final LoginBody loginBody, final DatabaseHouse dataHouse,
                         final PreferenceUtils preferenceUtils, final ICallBackLogin iCallBackLogin){

        this.iCallBackLogin = iCallBackLogin;
        LoginClient loginClient = RestClient.createService(LoginClient.class);
        Call<LoginResponse> callLogin = loginClient.login(loginBody);
        final Converter<ResponseBody, ErrorMessage> errorConverter = RestClient.getErrorConverter();

        callLogin.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()){
                    LoginResponse loginResponse = response.body();

                    Log.d(Constants.LogConstants.TAG_RESPONSE,"Request: "+loginBody.toString()+"\n"+response.toString()
                            +"\n"+" loginResonse:"+((loginResponse!=null)?loginResponse.toString():"null")+"\n"
                            +"code:"+response.code()+"\n"+"errorBody:"+(response.errorBody()!=null?response.errorBody():"null"));

                    //Save data on thread
                    DataAccess.execute(new InsertRunnable(dataHouse,preferenceUtils,loginResponse, callBackAppExecutor));

                }else{

                    ErrorMessage error = null;
                    try {

                        error = errorConverter.convert(response.errorBody());

                        Log.d(Constants.LogConstants.TAG_RESPONSE,"error: "+error.getMessage());

                    } catch (IOException e) {
                        e.printStackTrace();
                        error = ErrorMessage.getBlankError();
                    }finally {
                        iCallBackLogin.onCallBackFail(error);
                    }


                }


            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d(Constants.LogConstants.TAG_RESPONSE,t.getMessage());

                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setMessage(t.getMessage());
                iCallBackLogin.onCallBackFail(errorMessage);
            }
        });

    }

}
