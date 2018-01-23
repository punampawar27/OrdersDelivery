package com.tech20.mobiledelivery.retrofitclient.loginclient;

import com.google.gson.annotations.SerializedName;

/**
 "username": "nick",
 "password": "1234",
 "storeId": "5141",
 "deviceID": "863854037854631922616973a11ae5eFMUSAYMZQCBMEMDU",
 "sessionId": "ABC",
 "updateSessionId": true
 */

public class LoginBody {

    @SerializedName("storeId")
    private String storeId=null;

    @SerializedName("username")
    private String username=null;

    @SerializedName("password")
    private String pass=null;

    @SerializedName("deviceID")
    private String deviceID=null;

    @SerializedName("sessionId")
    private String sessionId=null;

    @SerializedName("updateSessionId")
    private boolean isSessionUpdated =false;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

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

    public boolean getSessionUpdated() {
        return isSessionUpdated;
    }

    public void setSessionUpdated(boolean sessionUpdated) {
        this.isSessionUpdated = sessionUpdated;
    }

    @Override
    public String toString() {
        return "StoreId: "+storeId+" username: "+username+" pass: "+pass+" deviceID: "+deviceID+" sessionId: "+sessionId+" isSessionUpdated: "+isSessionUpdated;
    }
}
