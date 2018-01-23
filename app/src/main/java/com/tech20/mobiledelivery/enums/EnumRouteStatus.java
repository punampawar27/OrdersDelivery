package com.tech20.mobiledelivery.enums;


public enum EnumRouteStatus {

    ASSIGNED(0),START(1),END(2),UNDEFINED(3);

    private final int value;
    EnumRouteStatus(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
    public EnumRouteStatus getEnum(int value){
        for(EnumRouteStatus state : EnumRouteStatus.values()){

            if(state.value == value){
                return state;
            }

        }
        return null;
    }
}
