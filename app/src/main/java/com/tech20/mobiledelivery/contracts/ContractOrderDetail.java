package com.tech20.mobiledelivery.contracts;


import com.tech20.mobiledelivery.database.dataentities.DbModelOrders;
import com.tech20.mobiledelivery.models.Entities.EntitySelectedOrderInventory;

import java.util.List;

public interface ContractOrderDetail {

    interface IViewOrderDetail extends IView{

        void showExtraInventory(List<EntitySelectedOrderInventory> listSelectedExtraItems);
        void showOrderStatus(DbModelOrders dbModelOrders);
        void showTotal(float total);
    }

    interface IPresenterOrderDetail extends IPresenter{
        void reCalculateTotal(float mOriginalTotal,List<EntitySelectedOrderInventory> listSelectedExtraItems);
    }
}
