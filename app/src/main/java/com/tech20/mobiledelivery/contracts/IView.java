package com.tech20.mobiledelivery.contracts;

/**
 * Created by fidel25 on 11/29/2017.
 */

public interface IView {
    void toggleProgressIndigator(boolean show);
    void toggleProgressIndigator(boolean show,String title,String message);
}
