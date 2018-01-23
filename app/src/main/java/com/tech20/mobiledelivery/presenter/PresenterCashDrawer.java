package com.tech20.mobiledelivery.presenter;

import android.util.Log;

import com.tech20.mobiledelivery.contracts.ContractCashDrawer;
import com.tech20.mobiledelivery.contracts.IView;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dataentities.DbModelCashDrawer;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.models.ModelCashDrawer;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;

import java.util.List;


public class PresenterCashDrawer implements ContractCashDrawer.IPresenterCashDrawer{

    private ContractCashDrawer.IViewCashDrawer iViewCashDrawer = null;

    @Override
    public void attach(IView view) {
        iViewCashDrawer = (ContractCashDrawer.IViewCashDrawer)view;
    }

    @Override
    public void dettach() {
        iViewCashDrawer = null;
    }

    @Override
    public void loadCashDrawer(String loaderMessage, DatabaseHouse dataHouse, PreferenceUtils prefUtils) {

        iViewCashDrawer.toggleProgressIndigator(true,null,loaderMessage);

        new ModelCashDrawer(iCashDrawerLoaded).getCashDrawer(dataHouse,
                prefUtils);
    }

    private ModelCashDrawer.ICashDrawerLoaded iCashDrawerLoaded = new ModelCashDrawer.ICashDrawerLoaded() {


        @Override
        public void onCashDrawerLoadedSuccess(List<DbModelCashDrawer> listCashDrawer, String total) {
            iViewCashDrawer.toggleProgressIndigator(false);

            if(listCashDrawer == null || listCashDrawer.size()<=0){

                iViewCashDrawer.onCashDrawerLoaded(null,total);
            }else{
                iViewCashDrawer.onCashDrawerLoaded(listCashDrawer,total);
            }
        }

        @Override
        public void onCashDrawerLoadedFail(ErrorMessage errorMessage) {
            iViewCashDrawer.toggleProgressIndigator(false);
            iViewCashDrawer.onCashDrawerFail(errorMessage);
        }
    };
}
