package com.tech20.mobiledelivery.contracts;

/**
 * Created by fidel25 on 11/29/2017.
 */

public interface IPresenter<V extends IView> {

    void attach(V view);
    void dettach();
}
