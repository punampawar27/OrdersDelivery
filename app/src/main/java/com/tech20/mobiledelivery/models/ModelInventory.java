package com.tech20.mobiledelivery.models;


import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.RestClient;
import com.tech20.mobiledelivery.retrofitclient.inventoryclient.InventoryClient;
import com.tech20.mobiledelivery.retrofitclient.inventoryclient.InventoryResponse;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class ModelInventory {

        public interface IInventoryLoad{
            void onInventoryLoadSuccess(List<InventoryResponse> inventoryResponseList);
            void onInventoryLoadFail(ErrorMessage errorMessage);
        }

        private IInventoryLoad iInventoryLoad = null;
        private Converter<ResponseBody,ErrorMessage> converter = null;


        public ModelInventory(IInventoryLoad iInventoryLoad){
            this.iInventoryLoad = iInventoryLoad;
        }

        public void getInventory(DatabaseHouse dataHouse, PreferenceUtils prefUtils){

            converter = RestClient.getErrorConverter();
            InventoryClient inventoryClient = RestClient.createServiceWithHeaders(InventoryClient.class,prefUtils);

            Call<List<InventoryResponse>> call =  inventoryClient.getInventory(prefUtils.getString(Constants.PreferenceConstants.ROUTE_ID),
                                                                                prefUtils.getString(Constants.PreferenceConstants.DRIVER_ID));
            call.enqueue(callBack);

        }

        private Callback<List<InventoryResponse>> callBack = new Callback<List<InventoryResponse>>() {
            @Override
            public void onResponse(Call<List<InventoryResponse>> call, Response<List<InventoryResponse>> response) {

                if(response.isSuccessful()){

                    List<InventoryResponse> list = response.body();
                    iInventoryLoad.onInventoryLoadSuccess(list);

                }else{

                    ErrorMessage errorMessage = null;

                    try {
                        errorMessage = converter.convert(response.errorBody());
                    } catch (IOException e) {
                        e.printStackTrace();
                        errorMessage = ErrorMessage.getBlankError();
                    }finally {
                        iInventoryLoad.onInventoryLoadFail(errorMessage);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<InventoryResponse>> call, Throwable t) {

            }
        };

}
