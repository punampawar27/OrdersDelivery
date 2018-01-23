package com.tech20.mobiledelivery.contracts;


import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dataentities.DbModelOrders;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.ordersclient.OrdersResponse;

import java.util.List;

public interface ContractOrders {

    interface IViewOrders extends IView{
        void onOrdersLoadedSuccess(List<DbModelOrders> listOrders);
        void onOrdersLoadedFail(ErrorMessage errorMessage);
    }

    interface IPresenterOrders extends IPresenter{

        void getOrders(DatabaseHouse dataHouse, PreferenceUtils prefUtils,String dateOfOrders);
    }
}
