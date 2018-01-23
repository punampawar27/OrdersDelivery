package com.tech20.mobiledelivery.models;

import android.util.Log;

import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.RestClient;
import com.tech20.mobiledelivery.retrofitclient.changepasswordclient.ChangePasswordBody;
import com.tech20.mobiledelivery.retrofitclient.changepasswordclient.ChangePasswordClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;


public class ModelChangePassword {


    public interface IPasswordChanged{
        void onChangePasswordSuccess();
        void onChangePasswordFail(ErrorMessage errorMessage);
    }

    private Converter<ResponseBody, ErrorMessage> errorConverter = null;
    private IPasswordChanged iPasswordChanged = null;

    public ModelChangePassword(IPasswordChanged iPasswordChanged){
        this.iPasswordChanged = iPasswordChanged;
    }
    public void changePassword(String driverId,String oldPass,String newPass,PreferenceUtils preferenceUtils){

        ChangePasswordBody body = new ChangePasswordBody();
        body.setStrdriverId(driverId);
        body.setStrnewPassword(newPass);
        body.setStrOldPassword(oldPass);

        ChangePasswordClient changePassClient = RestClient.createServiceWithHeaders(ChangePasswordClient.class,preferenceUtils);
        Call<Void> callChangePass = changePassClient.changePassword(body);
        errorConverter = RestClient.getErrorConverter();

        callChangePass.enqueue(callBack);
    }

    private Callback<Void> callBack = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {

            if(response.isSuccessful()){
                if(iPasswordChanged!=null){
                    iPasswordChanged.onChangePasswordSuccess();
                }
            }else{
                ErrorMessage error = null;
                try {

                    error = errorConverter.convert(response.errorBody());

                    Log.d(Constants.LogConstants.TAG_RESPONSE,"error: "+error.getMessage());

                } catch (IOException e) {
                    e.printStackTrace();
                    error = ErrorMessage.getBlankError();
                }finally {
                    iPasswordChanged.onChangePasswordFail(error);
                }

            }

        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {

            if(iPasswordChanged!=null){
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setMessage(t.getMessage());

                iPasswordChanged.onChangePasswordFail(errorMessage);
            }

        }
    };
}
