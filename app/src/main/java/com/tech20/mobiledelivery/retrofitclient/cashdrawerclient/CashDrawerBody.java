package com.tech20.mobiledelivery.retrofitclient.cashdrawerclient;

import com.google.gson.annotations.SerializedName;

public class CashDrawerBody {

    @SerializedName("DriverId")
    private String driverId = null;

    @SerializedName("DeliveryDate")
    private String deliveryDate = null;

    @SerializedName("RouteId")
    private String routeId = null;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
}
