package com.tech20.mobiledelivery.models;

import com.tech20.mobiledelivery.database.DataAccess;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dao.CashDrawerDao;
import com.tech20.mobiledelivery.database.dao.CustomerDao;
import com.tech20.mobiledelivery.database.dataentities.DbModelCashDrawer;
import com.tech20.mobiledelivery.executors.AppExecutor;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.helpers.UtilDateFormat;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.RestClient;
import com.tech20.mobiledelivery.retrofitclient.cashdrawerclient.CashDrawerBody;
import com.tech20.mobiledelivery.retrofitclient.cashdrawerclient.CashDrawerClient;
import com.tech20.mobiledelivery.retrofitclient.cashdrawerclient.CashDrawerResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;


public class ModelCashDrawer {

    public interface ICashDrawerLoaded{
        void onCashDrawerLoadedSuccess(List<DbModelCashDrawer> listCashDrawer,String total);
        void onCashDrawerLoadedFail(ErrorMessage errorMessage);
    }


    private ICashDrawerLoaded iCashDrawerLoaded = null;
    private Converter<ResponseBody,ErrorMessage> errorConverter = null;
    private DatabaseHouse databaseHouse = null;

    public ModelCashDrawer(ICashDrawerLoaded iCashDrawerLoaded){
        this.iCashDrawerLoaded=iCashDrawerLoaded;
    }

    public void getCashDrawer(DatabaseHouse databaseHouse, PreferenceUtils prefUtils){

        this.databaseHouse = databaseHouse;
        errorConverter = RestClient.getErrorConverter();
        CashDrawerClient cashClient = RestClient.createServiceWithHeaders(CashDrawerClient.class,prefUtils);


        CashDrawerBody cashDrawerBody = new CashDrawerBody();
        cashDrawerBody.setDeliveryDate(UtilDateFormat.getToday());
        cashDrawerBody.setDriverId(prefUtils.getString(Constants.PreferenceConstants.DRIVER_ID));
        cashDrawerBody.setRouteId(prefUtils.getString(Constants.PreferenceConstants.ROUTE_ID));

        Call<List<CashDrawerResponse>> call = cashClient.getCashDrawer(cashDrawerBody);
        call.enqueue(callBack);

    }

    private Callback<List<CashDrawerResponse>> callBack = new Callback<List<CashDrawerResponse>>() {
        @Override
        public void onResponse(Call<List<CashDrawerResponse>> call, Response<List<CashDrawerResponse>> response) {


            if(response.isSuccessful()){
                List<CashDrawerResponse> list = response.body();
                saveData(list);

            }else{

                ErrorMessage errorMessage = null;

                try {
                    errorMessage = errorConverter.convert(response.errorBody());
                } catch (IOException e) {
                    e.printStackTrace();
                    errorMessage = ErrorMessage.getBlankError();
                }finally {
                    iCashDrawerLoaded.onCashDrawerLoadedFail(errorMessage);
                }

            }
        }

        @Override
        public void onFailure(Call<List<CashDrawerResponse>> call, Throwable t) {

            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(t.getMessage());

            iCashDrawerLoaded.onCashDrawerLoadedFail(errorMessage);
        }
    };

    private void saveData(List<CashDrawerResponse> list){


        DataAccess.execute(()->{
            float floatTotal = 0f;
            //Cealr CashDrawer TAble
            CashDrawerDao cashDrawerDao =  databaseHouse.getCashDrawerDao();
            cashDrawerDao.deleteAll();

            List<DbModelCashDrawer> listDbCashDrawer = new ArrayList<>();

            for(CashDrawerResponse cashResp:list){
                DbModelCashDrawer dbCash = new DbModelCashDrawer();
                dbCash.setCash(cashResp.getCash());
                dbCash.setClientId(cashResp.getClientId());
                dbCash.setDeliveryDate(cashResp.getDeliveryDate());
                dbCash.setDrawerId(cashResp.getDriverCashDrawerId());
                dbCash.setDriverId(cashResp.getDriverId());
                dbCash.setOrderId(cashResp.getOrderId());
                dbCash.setRouteId(cashResp.getRouteId());

                listDbCashDrawer.add(dbCash);

                floatTotal+=Float.valueOf(dbCash.getCash());
            }
            //Insert new Cash
            cashDrawerDao.insertAll(listDbCashDrawer);

            //Just to b sure that data is loaded in database
            //Although we could user above List "listDbCashDrawer"
            List<DbModelCashDrawer> listDrawer = cashDrawerDao.getAllCashDrawer();
            AppExecutor.getINSTANCE().getMainThread().execute(new InnerRunnableLoadSuccess(floatTotal,listDrawer,iCashDrawerLoaded));
        });

    }

    private static class InnerRunnableLoadSuccess implements Runnable{

        private ICashDrawerLoaded iCashDrawerLoaded = null;
        private List<DbModelCashDrawer> listDrawer = null;
        float foatTotal = 0f;

        public InnerRunnableLoadSuccess(float foatTotal,List<DbModelCashDrawer> listDrawer,
                                        ICashDrawerLoaded iCashDrawerLoaded){
            this.foatTotal = foatTotal;
            this.listDrawer = listDrawer;
            this.iCashDrawerLoaded = iCashDrawerLoaded;
        }

        @Override
        public void run() {
            iCashDrawerLoaded.onCashDrawerLoadedSuccess(listDrawer,String.valueOf(foatTotal));
        }
    }
}
