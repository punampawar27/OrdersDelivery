package com.tech20.mobiledelivery.enums;


public enum EnumDialogType {
    DIALOG_LOGOUT(1),DIALOG_OFFDUTY(2),DIALOG_DELETE_EXTRAITEM(3),DIALOG_ORDER_EXIST(4);

    private final int value;
    EnumDialogType(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
    public static EnumDialogType getEnum(int value){

        for(EnumDialogType state:EnumDialogType.values()){
            if(state.getValue() == value){
                return state;
            }
        }
        return null;
    }

}
