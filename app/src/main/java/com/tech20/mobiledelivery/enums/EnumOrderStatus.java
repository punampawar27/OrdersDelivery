package com.tech20.mobiledelivery.enums;


public enum EnumOrderStatus {
    Assigned(0),Placed(1),Delivered(2),Cancelled(3),UnDelivered(4);

    private final int value;
    EnumOrderStatus(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
    public static EnumOrderStatus getEnum(int value){
        for(EnumOrderStatus state : EnumOrderStatus.values()){

            if(state.value == value){
                return state;
            }
        }
        return null;
    }

}
