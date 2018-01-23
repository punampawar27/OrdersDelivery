package com.tech20.mobiledelivery.contracts;

import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dataentities.DbModelDriver;
import com.tech20.mobiledelivery.database.dataentities.DbModelStore;
import com.tech20.mobiledelivery.enums.EnumAppState;
import com.tech20.mobiledelivery.enums.EnumDialogType;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.models.Entities.EntityLogin;
import com.tech20.mobiledelivery.models.ModelHome;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;

/**
 * Created by fidel25 on 11/29/2017.
 */

public interface ContractHome {

    interface IViewHome extends IView{
        void showAddress(DbModelStore store, DbModelDriver driver);
        void onDutyStatusChanged(EnumAppState enumAppState);
        void onResponseFailed(ErrorMessage errorMessage);
    }
    interface IPresenterHome<E extends EntityLogin> extends IPresenter {
            void showAddress(DatabaseHouse databaseHouse, PreferenceUtils preferenceUtils);
            void goOffDuty(PreferenceUtils preferenceUtils,String progressTitle,String progressMessage);
            void isAssignedDeliverdExist(ModelHome.IGetOrderStatus getOrderStatus, DatabaseHouse databaseHouse, PreferenceUtils preferenceUtils);

    }
}
