package com.tech20.mobiledelivery.retrofitclient.savelocationclient;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fidel25 on 12/14/2017.
 */

public class SaveLocationBody {



    @SerializedName("DriverId")
    private String driverId = null;

    @SerializedName("RouteId")
    private String routeId = null;

    @SerializedName("DeliveryDate")
    private String deliveryDate = null;

    @SerializedName("Longitude")
    private String longitude = null;

    @SerializedName("Latitude")
    private String latitude = null;

    @SerializedName("IsDeliveryLocation")
    private String isDeliveryLocation = null;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getIsDeliveryLocation() {
        return isDeliveryLocation;
    }

    public void setIsDeliveryLocation(String isDeliveryLocation) {
        this.isDeliveryLocation = isDeliveryLocation;
    }
}
