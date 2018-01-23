package com.tech20.mobiledelivery.retrofitclient.ordersclient;

import com.google.gson.annotations.SerializedName;

public class OrdersBody {


    @SerializedName("DriverId")
    private String driverId = null;

    @SerializedName("DeliveryDate")
    private String deliveryDate = null;

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
}
