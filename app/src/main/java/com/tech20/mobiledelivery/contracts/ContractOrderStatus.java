package com.tech20.mobiledelivery.contracts;

public interface ContractOrderStatus {

    interface IViewOrderStatus extends IView{

    }

    interface IPresenterOrderStatus extends IPresenter{

        void submitOrder();
    }
}
