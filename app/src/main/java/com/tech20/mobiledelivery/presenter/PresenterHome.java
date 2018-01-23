package com.tech20.mobiledelivery.presenter;

import com.tech20.mobiledelivery.contracts.ContractHome;
import com.tech20.mobiledelivery.contracts.IView;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dataentities.DbModelDriver;
import com.tech20.mobiledelivery.database.dataentities.DbModelStore;
import com.tech20.mobiledelivery.enums.EnumAppState;
import com.tech20.mobiledelivery.enums.EnumDialogType;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.models.ModelCurrentStatus;
import com.tech20.mobiledelivery.models.ModelHome;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.dutyclient.DutyResponse;

public class PresenterHome implements ContractHome.IPresenterHome{

    private ContractHome.IViewHome iViewHome = null;
    @Override
    public void attach(IView view) {
        iViewHome = (ContractHome.IViewHome)view;
    }

    @Override
    public void dettach() {
        iViewHome = null;
    }

    @Override
    public void showAddress(DatabaseHouse databaseHouse, PreferenceUtils preferenceUtils) {
        new ModelHome().getStore(databaseHouse,preferenceUtils, iGetStoreAndDriver);
    }

    @Override
    public void goOffDuty(PreferenceUtils preferenceUtils, String progressTitle, String progressMessage) {
        iViewHome.toggleProgressIndigator(true,progressTitle,progressMessage);
        new ModelCurrentStatus().setStatus(callbackCurrentStatus, preferenceUtils, false);
    }

    @Override
    public void isAssignedDeliverdExist(ModelHome.IGetOrderStatus getOrderStatus, DatabaseHouse databaseHouse, PreferenceUtils preferenceUtils) {
        new ModelHome().getOrderStatus(getOrderStatus,databaseHouse,preferenceUtils);
    }


    ModelHome.IGetStore iGetStoreAndDriver = (DbModelStore stored, DbModelDriver driver)->{
        iViewHome.showAddress(stored,driver);
    };

    private ModelCurrentStatus.IStatusChanged callbackCurrentStatus
            = new ModelCurrentStatus.IStatusChanged() {
        @Override
        public void onStatusChangedSuccess(DutyResponse dutyResponse) {
            iViewHome.toggleProgressIndigator(false);
            iViewHome.onDutyStatusChanged(EnumAppState.LOGGEDIN_OFF_DUTY);
        }

        @Override
        public void onStatusChandedFailed(ErrorMessage errorMessage) {
            iViewHome.toggleProgressIndigator(false);
            iViewHome.onResponseFailed(errorMessage);
        }
    };


}
