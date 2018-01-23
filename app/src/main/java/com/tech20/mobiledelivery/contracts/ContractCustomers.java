package com.tech20.mobiledelivery.contracts;

import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.models.ModelCustomers;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;


public interface ContractCustomers {

    interface IViewCustomers extends IView{
        void onCustomerLoadSuccess();
        void onCustomerLoadFail(ErrorMessage errorMessage);
    }

    interface IPresenterCustomers extends IPresenter{
        void onCustomerContactClick();
        void loadCustomers(DatabaseHouse dataHouse, PreferenceUtils prefUtils);
        void getCustomersFromDatabse(DatabaseHouse dataHouse,ModelCustomers.ILoadCustomerFromDdatabase callBack);
    }
}
