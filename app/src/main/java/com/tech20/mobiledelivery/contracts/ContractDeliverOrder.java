package com.tech20.mobiledelivery.contracts;


import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dataentities.DbModelOrders;
import com.tech20.mobiledelivery.enums.EnumOrderStatus;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.models.Entities.EntitySelectedOrderInventory;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.deliverorderclient.DeliverOrderResponse;

import java.util.List;

public interface ContractDeliverOrder {

    interface IViewDeliverOrder extends IView{
            void orderUpdatedSucess(DeliverOrderResponse deliverOrderResponse);
            void orderUpdatedFailed(ErrorMessage errorMessage);
    }

    interface IPresenterDeliverOrder extends IPresenter{

        void updateOrderStatus(String orderId,String note,
                               PreferenceUtils preferenceUtils,
                               String status,
                               List<EntitySelectedOrderInventory> listOrderedInventory);
    }
}
