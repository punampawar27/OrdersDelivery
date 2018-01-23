package com.tech20.mobiledelivery.contracts;

import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;

/**
 * Created by fidel25 on 12/11/2017.
 */

public interface ContractDialogChangePassword {

    interface IViewChangePassword extends IView{
        void onChangePasswordSuccess();
        void onChangePasswordFail(ErrorMessage errorMessage);
        void dismiss();
    }

    interface IPresenterChangePassword extends IPresenter{
        void onChangePassword(String driverId,String oldPassword,String newPassword,PreferenceUtils preferenceUtils,String prgTitle,String prgMessage);
        void onDiscard();
    }
}
