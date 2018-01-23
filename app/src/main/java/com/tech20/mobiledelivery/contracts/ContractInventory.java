package com.tech20.mobiledelivery.contracts;


import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.inventoryclient.InventoryResponse;

import java.util.List;

public interface ContractInventory {

    interface IViewInventory extends IView{

        void onInventoryLoaded(List<InventoryResponse> inventoryResponseList);
        void onInventoryLoadFail(ErrorMessage errorMessage);

    }

    interface IPresenterInventory extends IPresenter{
        void loadInventory(DatabaseHouse dataouse, PreferenceUtils preferenceUtils);
    }
}
