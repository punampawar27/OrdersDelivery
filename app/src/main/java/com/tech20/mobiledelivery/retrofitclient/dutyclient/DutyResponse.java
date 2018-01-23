package com.tech20.mobiledelivery.retrofitclient.dutyclient;

import com.google.gson.annotations.SerializedName;


public class DutyResponse {

    @SerializedName("RouteId")
    private String routeId = null;


    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
}
