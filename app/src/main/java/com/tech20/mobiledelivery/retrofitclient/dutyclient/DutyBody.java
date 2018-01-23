package com.tech20.mobiledelivery.retrofitclient.dutyclient;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fidel25 on 12/06/2017.
 */

public class DutyBody {

    @SerializedName("DriverId")
    private String driverId = null;

    @SerializedName("DeliveryDate")
    private String deliveryDate = null;

    @SerializedName("IsAvailableToday")
    private boolean IsAvailableToday = false;

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

    public boolean getIsAvailableToday() {
        return IsAvailableToday;
    }

    public void setIsAvailableToday(boolean isAvailableToday) {
        IsAvailableToday = isAvailableToday;
    }
}
