package com.tech20.mobiledelivery.contracts;

import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.models.Entities.EntityLogin;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;

/**
 * Created by fidel25 on 11/29/2017.
 */

public interface ContractLogin {

    interface IViewLogin extends IView{
        void loginSuccess();
        void loginFailed(ErrorMessage errorMessage);
    }
    interface IPresenterLogin<E extends EntityLogin> extends IPresenter {

        void login(E entity, DatabaseHouse databaseHouse, PreferenceUtils preferenceUtils);
    }
}
