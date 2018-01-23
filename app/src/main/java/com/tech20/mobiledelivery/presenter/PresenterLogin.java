package com.tech20.mobiledelivery.presenter;

import com.tech20.mobiledelivery.contracts.ContractLogin;
import com.tech20.mobiledelivery.contracts.IView;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.models.Entities.EntityLogin;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.models.ModelLogin;
import com.tech20.mobiledelivery.retrofitclient.loginclient.LoginBody;
import com.tech20.mobiledelivery.retrofitclient.loginclient.LoginResponse;


public class PresenterLogin implements ContractLogin.IPresenterLogin,ModelLogin.ICallBackLogin {

    private ContractLogin.IViewLogin iViewLogin = null;

    public PresenterLogin(){
    }
    @Override
    public void attach(IView view) {
        iViewLogin = (ContractLogin.IViewLogin)view;
    }

    @Override
    public void dettach() {
        iViewLogin = null;
    }



    @Override
    public void onCallBackSuccess(LoginResponse loginResponse) {
        iViewLogin.toggleProgressIndigator(false);
        iViewLogin.loginSuccess();
    }

    @Override
    public void onCallBackFail(ErrorMessage errorMessage) {

        iViewLogin.toggleProgressIndigator(false);
        iViewLogin.loginFailed(errorMessage);
    }


    @Override
    public void login(EntityLogin entity, DatabaseHouse databaseHouse, PreferenceUtils preferenceUtils) {
        iViewLogin.toggleProgressIndigator(true);

        ModelLogin modelLogin = new ModelLogin();
        LoginBody loginBody = new LoginBody();
        loginBody.setDeviceID(entity.getFireBaseRegKey());
        loginBody.setUsername(entity.getUsername());
        loginBody.setStoreId(entity.getStoreId());
        loginBody.setSessionId(entity.getDeviceId());
        loginBody.setSessionUpdated(entity.getIsSessionUpdated());
        loginBody.setPass(entity.getPass());


        modelLogin.getLogin(loginBody,databaseHouse, preferenceUtils,this);
    }
}
