package com.tech20.mobiledelivery.utils;


import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.helpers.AndroidVersionUtil;
import com.tech20.mobiledelivery.helpers.Constants;

public class UtilNotification {

    public static void showNotification(Context context,Notification notification,int id){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, notification);
    }

}
