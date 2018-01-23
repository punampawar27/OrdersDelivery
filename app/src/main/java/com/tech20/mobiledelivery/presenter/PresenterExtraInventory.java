package com.tech20.mobiledelivery.presenter;


import android.util.Log;

import com.tech20.mobiledelivery.contracts.ContractExtraInventory;
import com.tech20.mobiledelivery.contracts.IView;
import com.tech20.mobiledelivery.executors.AppExecutor;
import com.tech20.mobiledelivery.factory.discountfactory.FactoryDiscount;
import com.tech20.mobiledelivery.helpers.CalculateTaxedPrice;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.DecimalHelper;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.models.Entities.EntityDiscount;
import com.tech20.mobiledelivery.models.Entities.EntitySelectedOrderInventory;
import com.tech20.mobiledelivery.models.ModelExtraInventory;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.extrainventoryclient.ExtraInventoryResponse;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PresenterExtraInventory implements ContractExtraInventory.IPresenterExtraInventory {

    private ContractExtraInventory.IViewExtraInventory iView = null;
    private DecimalFormat decimalFormat = null;

    public PresenterExtraInventory(){
        decimalFormat = new DecimalFormat("##.00");
    }
    @Override
    public void attach(IView view) {
        iView = (ContractExtraInventory.IViewExtraInventory)view;
    }

    @Override
    public void dettach() {
        iView = null;
    }

    @Override
    public void loadExtraInventory(PreferenceUtils preferenceUtils) {

        iView.toggleProgressIndigator(true);
        new ModelExtraInventory(iExtraInventoryLoaded).getExtraInventory(preferenceUtils);
    }

    @Override
    public void reCalculculateTotal(List<ExtraInventoryResponse> list,float total) {

        float subTotal = 0f;

        for(ExtraInventoryResponse extraItem : list){

            if(extraItem.getSelectedQuantity()>0)
                subTotal+=extraItem.getTotalPriceAfterTaxAndDiscount();
        }
        total+=subTotal;
        iView.reloadTotal(subTotal,total);

    }

    @Override
    public void loadSelectedExtraItems(List<ExtraInventoryResponse> list) {

        if(list == null || list.size()<=0){
            iView.finishActivityWithResult(null);
            return;
        }
        iView.toggleProgressIndigator(true);
        List<EntitySelectedOrderInventory> selectedList = new ArrayList<>();

        AppExecutor.getINSTANCE().getDiskIO().execute(()->{
            for(ExtraInventoryResponse resp:list){

                if(resp.getSelectedQuantity()>0){

                    EntitySelectedOrderInventory extSelInv = new EntitySelectedOrderInventory();
                    extSelInv.setItemCode(resp.getItemCode());
                    extSelInv.setItemName(resp.getItemName());
                    extSelInv.setQuantity(String.valueOf(resp.getSelectedQuantity()));
                    extSelInv.setPrice(decimalFormat.format(Double.parseDouble(resp.getItemPrice())));
                    extSelInv.setPackageSize(resp.getPackageSize());
                    extSelInv.setITEM_TYPE(EntitySelectedOrderInventory.OrderItemType.EXTRA_INVENTORY.ordinal());
                    extSelInv.setTotalPriceAfterTaxAndDiscount(resp.getTotalPriceAfterTaxAndDiscount());
                    extSelInv.setTax(resp.getTaxPercentage());
                    extSelInv.setDiscount(resp.getDiscountAmount());
                    extSelInv.setBarCode(resp.getBarcode());

                    selectedList.add(extSelInv);
                }
            }

            AppExecutor.getINSTANCE().getMainThread().execute(()->{
                iView.toggleProgressIndigator(false);
                iView.finishActivityWithResult(selectedList);
            });
        });
    }

    private ModelExtraInventory.IExtraInventoryLoaded iExtraInventoryLoaded = new ModelExtraInventory.IExtraInventoryLoaded() {
        @Override
        public void onExtraInventoryLoaded(List<ExtraInventoryResponse> listExtraInventory) {

            iView.toggleProgressIndigator(false);
            Log.d(Constants.LogConstants.TAG_WASTE,""+listExtraInventory.size());
            iView.loadExtraInventorySuccess(listExtraInventory);
        }

        @Override
        public void onExtraInvenotryFail(ErrorMessage errorMessage) {

            iView.toggleProgressIndigator(false);
            iView.loadExtraInventoryFail(errorMessage);
        }
    };
}
