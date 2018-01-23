package com.tech20.mobiledelivery.models;

import com.tech20.mobiledelivery.database.DataAccess;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dao.CustomerDao;
import com.tech20.mobiledelivery.database.dataentities.DbModelCustomers;
import com.tech20.mobiledelivery.executors.AppExecutor;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.RestClient;
import com.tech20.mobiledelivery.retrofitclient.customersclient.CustomersClient;
import com.tech20.mobiledelivery.retrofitclient.customersclient.CustomersResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class ModelCustomers {


    public interface ICustomersLoad{
        void onCustomersLoadedSuccess(List<CustomersResponse> list);
        void onCustomersLoadedFail(ErrorMessage errorMessage);
    }

    public interface ILoadCustomerFromDdatabase{
        void onLoadSuccess(List<DbModelCustomers> list);
    }

    private Converter<ResponseBody,ErrorMessage> errorConverter = null;
    private ICustomersLoad iCustomersLoad = null;
    private DatabaseHouse dataHouse = null;
    private PreferenceUtils preferenceUtils = null;

    public ModelCustomers(ICustomersLoad iCustomersLoad){
        this.iCustomersLoad = iCustomersLoad;
    }
    public void getCustomers(DatabaseHouse dataHouse, PreferenceUtils preferenceUtils){

        this.dataHouse = dataHouse;
        this.preferenceUtils = preferenceUtils;

        errorConverter = RestClient.getErrorConverter();
        CustomersClient client =  RestClient.createServiceWithHeaders(CustomersClient.class,preferenceUtils);

        Call<List<CustomersResponse>> call = client.getCustomers(preferenceUtils.getString(Constants.PreferenceConstants.ROUTE_ID),
                preferenceUtils.getString(Constants.PreferenceConstants.DRIVER_ID));

        call.enqueue(callBack);
    }

    private Callback<List<CustomersResponse>> callBack = new Callback<List<CustomersResponse>>() {
        @Override
        public void onResponse(Call<List<CustomersResponse>> call, Response<List<CustomersResponse>> response) {

            if(response.isSuccessful()){
                List<CustomersResponse> list = response.body();

                DataAccess.execute(new CustomerDataRunnable(list,dataHouse,iCustomersLoad));

            }else{

                ErrorMessage errorMessage = null;

                try {
                    errorMessage =  errorConverter.convert(response.errorBody());
                } catch (IOException e) {
                    e.printStackTrace();
                    errorMessage = ErrorMessage.getBlankError();
                }
                iCustomersLoad.onCustomersLoadedFail(errorMessage);
            }
        }

        @Override
        public void onFailure(Call<List<CustomersResponse>> call, Throwable t) {

            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(t.getMessage());
            iCustomersLoad.onCustomersLoadedFail(errorMessage);
        }
    };

    private static class CustomerDataRunnable implements Runnable{

        private  List<CustomersResponse> list = null;
        private DatabaseHouse dataHouse = null;
        private ICustomersLoad iCustomersLoad = null;

        CustomerDataRunnable(List<CustomersResponse> list,DatabaseHouse dataHouse,
                             ICustomersLoad iCustomersLoad){
            this.list = list;
            this.dataHouse = dataHouse;
            this.iCustomersLoad = iCustomersLoad;
        }
        @Override
        public void run() {
            CustomerDao dao = dataHouse.getCustomerDao();
            dao.deleteAll();

            List<DbModelCustomers> models = new ArrayList<>();
            for(CustomersResponse resp : list){

                DbModelCustomers customer = new DbModelCustomers();
                customer.setCustomerName(resp.getCustomerName());
                customer.setContactNumber(resp.getContactNumber());
                customer.setShippingAddress(resp.getShippingAddress());

                models.add(customer);
            }

            dao.insertAll(models);

            AppExecutor.getINSTANCE().getMainThread().execute(()->{
                iCustomersLoad.onCustomersLoadedSuccess(list);
            });
        }
    }

    public void getCustomersFromDatabase(DatabaseHouse dataHouse,ILoadCustomerFromDdatabase callBack){

        AppExecutor.getINSTANCE().getDiskIO().execute(()->{
            List<DbModelCustomers> list = dataHouse.getCustomerDao().getCustomers();

            AppExecutor.getINSTANCE().getMainThread().execute(()->{
                callBack.onLoadSuccess(list);
            });

        });
    }
}
