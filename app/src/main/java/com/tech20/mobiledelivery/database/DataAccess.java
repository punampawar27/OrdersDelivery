package com.tech20.mobiledelivery.database;

import android.support.annotation.NonNull;

import com.tech20.mobiledelivery.executors.AppExecutor;


public class DataAccess {

    public static void execute(@NonNull Runnable runnable){

        AppExecutor.getINSTANCE().getDiskIO().execute(runnable);
    }
}
