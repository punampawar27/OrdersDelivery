package com.tech20.mobiledelivery.retrofitclient.savedeviceidclient;

import com.google.gson.annotations.SerializedName;

/**
 "username": "nick",
 "password": "1234",
 "storeId": "5141",
 "deviceID": "863854037854631922616973a11ae5eFMUSAYMZQCBMEMDU",
 "sessionId": "ABC",
 "updateSessionId": true
 */

public class SaveDeviceIdBody {


    @SerializedName("deviceID")
    private String deviceID=null;

    @SerializedName("sessionId")
    private String sessionId=null;


    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return " deviceID: "+deviceID+" sessionId: "+sessionId;
    }
}
