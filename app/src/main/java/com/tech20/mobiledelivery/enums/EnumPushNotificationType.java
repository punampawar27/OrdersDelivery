package com.tech20.mobiledelivery.enums;

public enum EnumPushNotificationType {

    ROUTEASSIGNED(0),ROUTESTARTED(1),ROUTEENDED(2),ORDERCANCELLED(3),ORDERUNDELIVERED(4);

    private final int value;
    EnumPushNotificationType(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
    public static EnumPushNotificationType getEnumPushNotificationType(int value){

        for(EnumPushNotificationType state:values()){
            if(state.value == value){
                return state;
            }
        }
        return null;
    }
}
