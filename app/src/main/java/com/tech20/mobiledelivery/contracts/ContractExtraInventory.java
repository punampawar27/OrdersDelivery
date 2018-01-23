package com.tech20.mobiledelivery.contracts;


import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.models.Entities.EntitySelectedOrderInventory;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.extrainventoryclient.ExtraInventoryResponse;

import java.util.List;

public interface ContractExtraInventory {

    interface IViewExtraInventory extends IView{
            void loadExtraInventorySuccess(List<ExtraInventoryResponse> listExtraInventory);
            void loadExtraInventoryFail(ErrorMessage errorMessage);
            void reloadTotal(float subTotal,float total);
            void finishActivityWithResult(List<EntitySelectedOrderInventory> list);
    }

    interface IPresenterExtraInventory extends IPresenter{
            void loadExtraInventory(PreferenceUtils preferenceUtils);
            void reCalculculateTotal(List<ExtraInventoryResponse> list,float total);
            void loadSelectedExtraItems(List<ExtraInventoryResponse> list);
    }
}
