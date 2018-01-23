package com.tech20.mobiledelivery.presenter;

import android.util.Log;

import com.tech20.mobiledelivery.contracts.ContractCurrentStatus;
import com.tech20.mobiledelivery.contracts.IView;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.enums.EnumAppState;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.models.ModelCurrentStatus;
import com.tech20.mobiledelivery.models.ModelLogout;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.dutyclient.DutyResponse;


public class PresenterCurrentStatus implements ContractCurrentStatus.IPresenterCurrentStatus {

    private ContractCurrentStatus.IViewCurrentStatus iViewCurrentStatus = null;
    private ModelLogout modelLogout = null;

    public PresenterCurrentStatus(){
        modelLogout = new ModelLogout();
    }
    @Override
    public void onSwipedToOnDuty(PreferenceUtils preferenceUtils, EnumAppState enumAppState) {

        iViewCurrentStatus.toggleProgressIndigator(true);
        Log.d(Constants.LogConstants.TAG_WASTE,"Presenter onSwipedToOnDuty");
        //When view has swiped

        new ModelCurrentStatus().setStatus(callbackCurrentStatus,
                preferenceUtils,
                (enumAppState.equals(EnumAppState.LOGGEDIN_ON_DUTY)?true:false));
    }

    @Override
    public void onClickLogout(DatabaseHouse datahouse, PreferenceUtils preferenceUtils) {

        iViewCurrentStatus.toggleProgressIndigator(true);
        modelLogout.logout(callBackLogout,preferenceUtils);
        //callback given to callBackLogout
    }


    @Override
    public void attach(IView view) {
        iViewCurrentStatus = (ContractCurrentStatus.IViewCurrentStatus)view;
    }

    @Override
    public void dettach() {
        iViewCurrentStatus = null;
    }

    private ModelLogout.ICallBackLogout callBackLogout = new ModelLogout.ICallBackLogout() {
        @Override
        public void onLogoutSuccess() {
            iViewCurrentStatus.onLogoutSuccess();
            iViewCurrentStatus.toggleProgressIndigator(false);
        }

        @Override
        public void onLogoutFaill(ErrorMessage errorMessage) {
            iViewCurrentStatus.onResponseFailed(errorMessage);
            iViewCurrentStatus.toggleProgressIndigator(false);
        }
    };

    private ModelCurrentStatus.IStatusChanged callbackCurrentStatus = new ModelCurrentStatus.IStatusChanged() {
        @Override
        public void onStatusChangedSuccess(DutyResponse dutyResponse) {
                iViewCurrentStatus.toggleProgressIndigator(false);
                iViewCurrentStatus.onSwipedToDutySuccess();
        }

        @Override
        public void onStatusChandedFailed(ErrorMessage errorMessage) {
            iViewCurrentStatus.onResponseFailed(errorMessage);
            iViewCurrentStatus.toggleProgressIndigator(false);
        }
    };
}
