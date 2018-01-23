package com.tech20.mobiledelivery.presenter;


import com.tech20.mobiledelivery.contracts.ContractOrderDetail;
import com.tech20.mobiledelivery.contracts.IView;
import com.tech20.mobiledelivery.executors.AppExecutor;
import com.tech20.mobiledelivery.models.Entities.EntitySelectedOrderInventory;

import java.util.List;

public class PresenterOrderDetail implements ContractOrderDetail.IPresenterOrderDetail{


    private ContractOrderDetail.IViewOrderDetail iViewOrderDetail = null;

    @Override
    public void reCalculateTotal(float originalTotal,List<EntitySelectedOrderInventory> listSelectedExtraItems) {


        AppExecutor.getINSTANCE().getDiskIO().execute(()->{
            float calSubTotal = 0f;

            if(listSelectedExtraItems!=null){
                for(EntitySelectedOrderInventory ent:listSelectedExtraItems){
                    calSubTotal+=ent.getTotalPriceAfterTaxAndDiscount();
                }
            }


            final float total = calSubTotal+originalTotal;
            AppExecutor.getINSTANCE().getMainThread().execute(()->{
                iViewOrderDetail.showTotal(total);
            });
        });

    }

    @Override
    public void attach(IView view) {
        iViewOrderDetail = (ContractOrderDetail.IViewOrderDetail)view;
    }

    @Override
    public void dettach() {

    }
}
