package com.tech20.mobiledelivery.enums;

public enum EnumAppState {
    LOGGEDOUT(0),LOGGEDIN_OFF_DUTY(1),LOGGEDIN_ON_DUTY(2);

    private final int value;
    EnumAppState(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
    public static EnumAppState getEnum(int value){

        for(EnumAppState state:EnumAppState.values()){
            if(state.getValue() == value){
                return state;
            }
        }
        return null;
    }

}
