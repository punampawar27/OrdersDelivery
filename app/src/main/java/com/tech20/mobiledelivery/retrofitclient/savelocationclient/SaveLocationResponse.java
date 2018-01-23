package com.tech20.mobiledelivery.retrofitclient.savelocationclient;

import com.google.gson.annotations.SerializedName;

public class SaveLocationResponse {

    /*
    {
    "IsDirtyFlag": true,
    "RouteId": 1561,
    "RouteStatus": "START"

    //or "RouteStatus": ""
    //or "RouteStatus": "END"
}
     */

    @SerializedName("IsDirtyFlag")
    private String isDirtyFlag = null;

    @SerializedName("RouteId")
    private String routeId = null;

    @SerializedName("RouteStatus")
    private String routeStatus = null;

    public String getIsDirtyFlag() {
        return isDirtyFlag;
    }

    public void setIsDirtyFlag(String isDirtyFlag) {
        isDirtyFlag = isDirtyFlag;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        routeId = routeId;
    }

    public String getRouteStatus() {
        return routeStatus;
    }

    public void setRouteStatus(String routeStatus) {
        routeStatus = routeStatus;
    }
}
