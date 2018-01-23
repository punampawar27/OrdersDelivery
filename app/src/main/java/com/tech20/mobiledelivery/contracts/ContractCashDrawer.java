package com.tech20.mobiledelivery.contracts;

import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dataentities.DbModelCashDrawer;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;

import java.util.List;

public interface ContractCashDrawer {

    interface IViewCashDrawer extends IView{

        void onCashDrawerLoaded(List<DbModelCashDrawer> list,String total);
        void onCashDrawerFail(ErrorMessage errorMessage);
    }

    interface IPresenterCashDrawer extends IPresenter{

        void loadCashDrawer(String loaderMessage,DatabaseHouse dataHouse, PreferenceUtils prefUtils);
    }
}
