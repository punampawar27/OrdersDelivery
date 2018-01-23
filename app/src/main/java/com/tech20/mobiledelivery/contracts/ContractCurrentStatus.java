package com.tech20.mobiledelivery.contracts;

import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.enums.EnumAppState;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;

/**
 * Created by fidel25 on 12/05/2017.
 */

public interface ContractCurrentStatus{

    interface IViewCurrentStatus extends IView{

        void onSwipedToDutySuccess();
        void onLogoutSuccess();
        void onResponseFailed(ErrorMessage errorMessage);
    }
    interface IPresenterCurrentStatus extends IPresenter{

        void onSwipedToOnDuty(PreferenceUtils preferenceUtils, EnumAppState enumAppState);
        void onClickLogout(DatabaseHouse datahouse,PreferenceUtils preferenceUtils);
    }

}
