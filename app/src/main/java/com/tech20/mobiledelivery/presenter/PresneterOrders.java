package com.tech20.mobiledelivery.presenter;

import android.util.Log;

import com.tech20.mobiledelivery.contracts.ContractOrders;
import com.tech20.mobiledelivery.contracts.IView;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dataentities.DbModelOrders;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.models.ModelOrders;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.ordersclient.OrdersResponse;

import java.util.List;


public class PresneterOrders implements ContractOrders.IPresenterOrders {

    private ContractOrders.IViewOrders iViewOrders = null;

    @Override
    public void attach(IView view) {
        iViewOrders = (ContractOrders.IViewOrders)view;
    }

    @Override
    public void dettach() {
        iViewOrders = null;
    }

    @Override
    public void getOrders(DatabaseHouse dataHouse, PreferenceUtils prefUtils, String dateOfOrders) {

        new ModelOrders(iOrdersLoaded).getOrdersFromAppServer(dataHouse,prefUtils,dateOfOrders);
        iViewOrders.toggleProgressIndigator(true);
    }

    private ModelOrders.IOrdersLoaded iOrdersLoaded = new ModelOrders.IOrdersLoaded() {

        @Override
        public void onOrdersLoadedSuccess(List<DbModelOrders> list) {
            iViewOrders.toggleProgressIndigator(false);
            iViewOrders.onOrdersLoadedSuccess(list);
        }

        @Override
        public void onOrdersLoadedFail(ErrorMessage errorMessage) {
            iViewOrders.toggleProgressIndigator(false);
            iViewOrders.onOrdersLoadedFail(errorMessage);
        }
    };
}
