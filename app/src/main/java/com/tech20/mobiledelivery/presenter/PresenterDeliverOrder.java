package com.tech20.mobiledelivery.presenter;


import com.tech20.mobiledelivery.contracts.ContractDeliverOrder;
import com.tech20.mobiledelivery.contracts.IView;
import com.tech20.mobiledelivery.database.dataentities.DbModelOrders;
import com.tech20.mobiledelivery.enums.EnumOrderStatus;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.models.Entities.EntitySelectedOrderInventory;
import com.tech20.mobiledelivery.models.ModelDeliverOrder;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.deliverorderclient.DeliverOrderResponse;

import java.util.List;

public class PresenterDeliverOrder implements ContractDeliverOrder.IPresenterDeliverOrder{

    private ContractDeliverOrder.IViewDeliverOrder iViewDeliverOrder = null;

    @Override
    public void attach(IView view) {
        iViewDeliverOrder = (ContractDeliverOrder.IViewDeliverOrder)view;
    }

    @Override
    public void dettach() {
        iViewDeliverOrder = null;
    }


    @Override
    public void updateOrderStatus(String orderId, String note,
                                  PreferenceUtils preferenceUtils,
                                  String status,
                                  List<EntitySelectedOrderInventory> listOrderedExtraInventory) {

        iViewDeliverOrder.toggleProgressIndigator(true);
        new ModelDeliverOrder(callBackDeliverOrder).submitOrder(preferenceUtils,orderId,
                                status,listOrderedExtraInventory,note);

    }



    private ModelDeliverOrder.ICallBackDeliverOrder callBackDeliverOrder = new ModelDeliverOrder.ICallBackDeliverOrder(){


        @Override
        public void deliverOrderSuccess(DeliverOrderResponse deliverOrderResponse) {
            iViewDeliverOrder.toggleProgressIndigator(false);
            iViewDeliverOrder.orderUpdatedSucess(deliverOrderResponse);
        }

        @Override
        public void deliverOrderFail(ErrorMessage errorMessage) {
            iViewDeliverOrder.toggleProgressIndigator(false);
            iViewDeliverOrder.orderUpdatedFailed(errorMessage);
        }
    };
}
