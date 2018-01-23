package com.tech20.mobiledelivery.models;


import com.tech20.mobiledelivery.executors.AppExecutor;
import com.tech20.mobiledelivery.helpers.CalculateTaxedPrice;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.RestClient;
import com.tech20.mobiledelivery.retrofitclient.extrainventoryclient.ExtraInventoryClient;
import com.tech20.mobiledelivery.retrofitclient.extrainventoryclient.ExtraInventoryResponse;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class ModelExtraInventory {

    public interface IExtraInventoryLoaded{
        void onExtraInventoryLoaded(List<ExtraInventoryResponse> listExtraInventory);
        void onExtraInvenotryFail(ErrorMessage errorMessage);
    }

    private Converter<ResponseBody,ErrorMessage> errorConverter = null;
    private IExtraInventoryLoaded iExtraInventoryLoaded = null;

    public ModelExtraInventory(IExtraInventoryLoaded iExtraInventoryLoaded){
        this.iExtraInventoryLoaded = iExtraInventoryLoaded;
    }
    public void getExtraInventory(PreferenceUtils prefUtils){


        errorConverter = RestClient.getErrorConverter();
        ExtraInventoryClient client = RestClient.createServiceWithHeaders(ExtraInventoryClient.class,prefUtils);

        Call<List<ExtraInventoryResponse>> call = client.getExtraInventory(prefUtils.getString(Constants.PreferenceConstants.ROUTE_ID),
                prefUtils.getString(Constants.PreferenceConstants.DRIVER_ID));

        call.enqueue(callBack);
    }


    private Callback<List<ExtraInventoryResponse>> callBack = new Callback<List<ExtraInventoryResponse>>() {
        @Override
        public void onResponse(Call<List<ExtraInventoryResponse>> call, Response<List<ExtraInventoryResponse>> response) {

            if(response.isSuccessful()){
                List<ExtraInventoryResponse> listExtraInventory = response.body();

                iExtraInventoryLoaded.onExtraInventoryLoaded(listExtraInventory);

            }else{

                ErrorMessage errorMessage = null;
                try {
                    errorMessage = errorConverter.convert(response.errorBody());
                } catch (IOException e) {
                    e.printStackTrace();
                    errorMessage = ErrorMessage.getBlankError();
                }finally {
                    //Call to Interface
                    iExtraInventoryLoaded.onExtraInvenotryFail(errorMessage);
                }

            }

        }

        @Override
        public void onFailure(Call<List<ExtraInventoryResponse>> call, Throwable t) {
            //Call to Interface
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(t.getMessage());
            iExtraInventoryLoaded.onExtraInvenotryFail(errorMessage);
        }
    };
}
