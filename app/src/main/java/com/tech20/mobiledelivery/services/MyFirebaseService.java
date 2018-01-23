package com.tech20.mobiledelivery.services;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.enums.EnumPushNotificationType;
import com.tech20.mobiledelivery.enums.EnumRouteStatus;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.utils.NotificationBuilder;
import com.tech20.mobiledelivery.utils.UtilNotification;
import com.tech20.mobiledelivery.utils.UtilSaveRouteStatus;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class MyFirebaseService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


        String MAP_KEY_TITLE="Title";
        String MAP_KEY_MESSAGE="Message";
        String MAP_KEY_ROUTE_ID="Id";

        String TITLE_ROUTE_ASSIGNED   ="Route Assigned";
        String TITLE_ROUTE_STARTED    ="Route started";
        String TITLE_ROUTE_ENDED      ="Route ended";
        String TITLE_ORDER_CANCELLED  ="Order Cancelled";
        String TITLE_ORDER_STATUS     ="Order Status";
        String MSG_UNDELIVERED        ="Order is undelivered.";

        Map<String,String> map = remoteMessage.getData();
        if(map!=null && map.size() > 0){
            String title    = map.get(MAP_KEY_TITLE);
            String message  = map.get(MAP_KEY_MESSAGE);
            String routeId  = map.get(MAP_KEY_ROUTE_ID);

            if(TextUtils.isEmpty(title)){
                return;
            }

            showNotification(getApplicationContext(),title,message);

            if(TITLE_ROUTE_ASSIGNED.equalsIgnoreCase(title)){
                //Save Route ASSIGNED
                UtilSaveRouteStatus.saveRoutStatus(PreferenceUtils
                                .getINSTANCE(getApplicationContext()),
                        routeId, EnumRouteStatus.ASSIGNED.name());

                sendBroadCast(EnumPushNotificationType.ROUTEASSIGNED,
                        Constants.INTENTACTIONS.ACTION_PUSH_RECEIVED);

            }else if(TITLE_ROUTE_STARTED.equalsIgnoreCase(title)){
                    //Save Route STARTED

                UtilSaveRouteStatus.saveRoutStatus(PreferenceUtils
                                .getINSTANCE(getApplicationContext()),
                        routeId, EnumRouteStatus.START.name());

                sendBroadCast(EnumPushNotificationType.ROUTESTARTED,
                        Constants.INTENTACTIONS.ACTION_PUSH_RECEIVED);

            }else if(TITLE_ROUTE_ENDED.equalsIgnoreCase(title)){
                //Save Route Ended


                UtilSaveRouteStatus.saveRoutStatus(PreferenceUtils
                                .getINSTANCE(getApplicationContext()),
                        routeId, EnumRouteStatus.END.name());

                sendBroadCast(EnumPushNotificationType.ROUTEENDED,
                        Constants.INTENTACTIONS.ACTION_PUSH_RECEIVED);

            }else if(TITLE_ORDER_STATUS.equalsIgnoreCase(title) &&
                    MSG_UNDELIVERED.equalsIgnoreCase(message)){

                //Order Undelivered, broadcast EnumPushNotification
                PreferenceUtils.getINSTANCE(getApplicationContext())
                                            .putBoolean(Constants.PreferenceConstants.IS_ORDER_REFRESHED,true);
                sendBroadCast(EnumPushNotificationType.ORDERUNDELIVERED,
                            Constants.INTENTACTIONS.ACTION_PUSH_RECEIVED);

            }else if(TITLE_ORDER_CANCELLED.equalsIgnoreCase(title)){

                //Order Cancelled, broadcast EnumPushNotification
                PreferenceUtils.getINSTANCE(getApplicationContext())
                                .putBoolean(Constants.PreferenceConstants.IS_ORDER_REFRESHED,true);
                sendBroadCast(EnumPushNotificationType.ORDERCANCELLED,
                                Constants.INTENTACTIONS.ACTION_PUSH_RECEIVED);
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append("From: "+remoteMessage.getFrom()+"\n");
        builder.append("To: "+remoteMessage.getTo()+"\n");
        builder.append("MessageId: "+remoteMessage.getMessageId()+"\n");
        builder.append("MessageType: "+remoteMessage.getMessageType()+"\n");
        builder.append("Notification: "+remoteMessage.getNotification()+"\n");

        builder.append("Data: "+"\n");
           for(Map.Entry<String,String> entry :map.entrySet()){
               builder.append("  "+entry.getKey()+":"+entry.getValue()+"\n");
           }

        builder.append("CollapseKey: "+remoteMessage.getCollapseKey()+"\n");

        Log.d(Constants.LogConstants.TAG_FIREBASE,"Firbase Message:"+builder.toString());
        //For running a task more than 10seconds or more go for JobScheduler/JobDispatcher



    }

    private void sendBroadCast(EnumPushNotificationType enumPushNotificationType, String action){
        Intent intent = new Intent(action);
        intent.putExtra(Constants.INTENTEXTRAS.EXTRA_PUSHNOTIFICATOIN_FLAG,enumPushNotificationType.getValue());
        sendBroadcast(intent);
    }

    @SuppressLint("NewApi")
    private void showNotification(Context context, String title, String message){

        NotificationBuilder builder = new NotificationBuilder();
        builder.setContext(context);
        builder.setIcon(R.drawable.icon);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setVibrateOn(true);

        UtilNotification.showNotification(getApplicationContext(),builder.build(),Constants.NOTIFICATION.NOTIFICATION_ID_PUSH_DATA);


    }
}
