package com.tech20.mobiledelivery.contracts;

import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dataentities.DbModelFaq;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;

import java.util.List;

public interface ContractFaq {

    interface IViewFaq extends IView{
        void onFaqLoaded(List<DbModelFaq> list);
        void onFaqLodingFaild(ErrorMessage errorMessage);
    }

    interface  IPresenterFaq extends IPresenter{

        void loadFaq(DatabaseHouse dataHouse, PreferenceUtils preferenceUtils);

    }
}
