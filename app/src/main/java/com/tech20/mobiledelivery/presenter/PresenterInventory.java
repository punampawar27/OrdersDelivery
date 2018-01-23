package com.tech20.mobiledelivery.presenter;

import com.tech20.mobiledelivery.contracts.ContractInventory;
import com.tech20.mobiledelivery.contracts.IView;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.models.ModelInventory;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.inventoryclient.InventoryResponse;

import java.util.List;


public class PresenterInventory implements ContractInventory.IPresenterInventory {

    private ContractInventory.IViewInventory iViewInventory = null;
    @Override
    public void attach(IView view) {
        iViewInventory = (ContractInventory.IViewInventory)view;
    }

    @Override
    public void dettach() {
        iViewInventory =null;
    }

    @Override
    public void loadInventory(DatabaseHouse dataouse, PreferenceUtils preferenceUtils) {

        iViewInventory.toggleProgressIndigator(true);
        new ModelInventory(iInventoryLoad).getInventory(dataouse,preferenceUtils);
    }

    private ModelInventory.IInventoryLoad iInventoryLoad = new ModelInventory.IInventoryLoad() {
        @Override
        public void onInventoryLoadSuccess(List<InventoryResponse> inventoryResponseList) {

            iViewInventory.toggleProgressIndigator(false);
            iViewInventory.onInventoryLoaded(inventoryResponseList);
        }

        @Override
        public void onInventoryLoadFail(ErrorMessage errorMessage) {

            iViewInventory.toggleProgressIndigator(false);
            iViewInventory.onInventoryLoadFail(errorMessage);
        }
    };
}
