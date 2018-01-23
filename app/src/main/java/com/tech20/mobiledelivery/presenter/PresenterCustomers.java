package com.tech20.mobiledelivery.presenter;

import com.tech20.mobiledelivery.contracts.ContractCustomers;
import com.tech20.mobiledelivery.contracts.IView;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.models.ModelCustomers;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.customersclient.CustomersResponse;

import java.util.List;


public class PresenterCustomers implements ContractCustomers.IPresenterCustomers {

    private ContractCustomers.IViewCustomers iViewCustomers = null;

    @Override
    public void attach(IView view) {
        iViewCustomers = (ContractCustomers.IViewCustomers)view;
    }

    @Override
    public void dettach() {
        iViewCustomers = null;
    }

    @Override
    public void onCustomerContactClick() {


    }

    @Override
    public void loadCustomers(DatabaseHouse dataHouse, PreferenceUtils prefUtils) {

        new ModelCustomers(iCustomersLoad).getCustomers(dataHouse,prefUtils);
        iViewCustomers.toggleProgressIndigator(true);
    }

    @Override
    public void getCustomersFromDatabse(DatabaseHouse dataHouse,ModelCustomers.ILoadCustomerFromDdatabase callBack) {

        new ModelCustomers(iCustomersLoad).getCustomersFromDatabase(dataHouse,callBack);
    }


    private ModelCustomers.ICustomersLoad iCustomersLoad = new ModelCustomers.ICustomersLoad() {
        @Override
        public void onCustomersLoadedSuccess(List<CustomersResponse> list) {
                    iViewCustomers.toggleProgressIndigator(false);
                    iViewCustomers.onCustomerLoadSuccess();
        }

        @Override
        public void onCustomersLoadedFail(ErrorMessage errorMessage) {
            iViewCustomers.toggleProgressIndigator(false);
            iViewCustomers.onCustomerLoadFail(errorMessage);
        }
    };
}
