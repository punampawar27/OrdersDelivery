package com.tech20.mobiledelivery.enums;


public enum EnumExpectedDeliveryTime {
    Morning(1),AFTERNOON(2),Evening(3),Anytime(4);

    private final int value;
    EnumExpectedDeliveryTime(int value){
        this.value = value;
    }

    public static EnumExpectedDeliveryTime getEnum(int value){
        for(EnumExpectedDeliveryTime state : EnumExpectedDeliveryTime.values()){

            if(state.value == value){
                return state;
            }

        }
        return null;
    }


    public static EnumExpectedDeliveryTime getEnumByName(String name){
        for(EnumExpectedDeliveryTime state : EnumExpectedDeliveryTime.values()){

            if(state.name().equalsIgnoreCase(name)){
                return state;
            }

        }
        return null;
    }
}
