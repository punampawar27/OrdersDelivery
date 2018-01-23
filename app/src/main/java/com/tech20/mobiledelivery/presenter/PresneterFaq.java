package com.tech20.mobiledelivery.presenter;

import com.tech20.mobiledelivery.contracts.ContractFaq;
import com.tech20.mobiledelivery.contracts.IView;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dataentities.DbModelFaq;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.models.ModelFAQ;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;

import java.util.List;

public class PresneterFaq implements ContractFaq.IPresenterFaq {

    private ContractFaq.IViewFaq iViewFaq = null;

    @Override
    public void attach(IView view) {
        this.iViewFaq = (ContractFaq.IViewFaq) view;
    }

    @Override
    public void dettach() {
        iViewFaq = null;
    }

    @Override
    public void loadFaq(DatabaseHouse dataHouse, PreferenceUtils preferenceUtils) {
        iViewFaq.toggleProgressIndigator(true);
        new ModelFAQ(ifaqCallback,dataHouse,preferenceUtils).getFAQ();
    }

    private ModelFAQ.IFAQCallback ifaqCallback = new ModelFAQ.IFAQCallback() {
        @Override
        public void onFAQResponseSucess(List<DbModelFaq> listFaq) {
            iViewFaq.toggleProgressIndigator(false);
            iViewFaq.onFaqLoaded(listFaq);
        }

        @Override
        public void onFAQResponseFail(ErrorMessage errorMessage) {
            iViewFaq.toggleProgressIndigator(false);
            iViewFaq.onFaqLodingFaild(errorMessage);
        }
    };
}
