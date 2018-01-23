package com.tech20.mobiledelivery.presenter;

import com.tech20.mobiledelivery.contracts.ContractDialogChangePassword;
import com.tech20.mobiledelivery.contracts.IView;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.models.ModelChangePassword;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;


public class PresenterDialogChangePassword implements ContractDialogChangePassword.IPresenterChangePassword {

    private ContractDialogChangePassword.IViewChangePassword iViewChangePassword = null;

    @Override
    public void attach(IView view) {
        iViewChangePassword = (ContractDialogChangePassword.IViewChangePassword) view;
    }

    @Override
    public void dettach() {
        iViewChangePassword = null;
    }

    @Override
    public void onChangePassword(String driverId,String oldPassword,String newPassword,PreferenceUtils preferenceUtils,
                                 String prgTitle,String prgMessage) {

        new ModelChangePassword(iPasswordChanged).changePassword(driverId,oldPassword,newPassword,preferenceUtils);
        iViewChangePassword.toggleProgressIndigator(true,prgTitle,prgMessage);
    }

    @Override
    public void onDiscard() {
        iViewChangePassword.dismiss();
    }

    private ModelChangePassword.IPasswordChanged iPasswordChanged = new ModelChangePassword.IPasswordChanged() {
        @Override
        public void onChangePasswordSuccess() {
            if(iViewChangePassword!=null){
                iViewChangePassword.toggleProgressIndigator(false);
                iViewChangePassword.onChangePasswordSuccess();
            }
        }

        @Override
        public void onChangePasswordFail(ErrorMessage errorMessage) {
                if(iViewChangePassword!=null){
                    iViewChangePassword.toggleProgressIndigator(false);
                    iViewChangePassword.onChangePasswordFail(errorMessage);
                }
        }
    };
}
