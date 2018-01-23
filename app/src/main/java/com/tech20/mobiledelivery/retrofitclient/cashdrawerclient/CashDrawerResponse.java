package com.tech20.mobiledelivery.retrofitclient.cashdrawerclient;


import com.google.gson.annotations.SerializedName;

public class CashDrawerResponse {

    /*
    [
   {
      "DriverCashDrawerId":674,
      "DriverId":386,
      "DeliveryDate":"2017-12-14T00:00:00",
      "Cash":225.0,
      "RouteId":1538,
      "ClientId":6630,
      "OrderId":2138
   },
   {
      "DriverCashDrawerId":675,
      "DriverId":386,
      "DeliveryDate":"2017-12-14T00:00:00",
      "Cash":107.25,
      "RouteId":1538,
      "ClientId":6630,
      "OrderId":2139
   }
]

     */
    @SerializedName("DriverCashDrawerId")
    private String driverCashDrawerId = null;

    @SerializedName("DriverId")
    private String driverId = null;

    @SerializedName("DeliveryDate")
    private String deliveryDate = null;

    @SerializedName("Cash")
    private String cash = null;

    @SerializedName("RouteId")
    private String routeId = null;

    @SerializedName("ClientId")
    private String clientId = null;

    @SerializedName("OrderId")
    private String orderId = null;

    public String getDriverCashDrawerId() {
        return driverCashDrawerId;
    }

    public void setDriverCashDrawerId(String driverCashDrawerId) {
        this.driverCashDrawerId = driverCashDrawerId;
    }

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

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
