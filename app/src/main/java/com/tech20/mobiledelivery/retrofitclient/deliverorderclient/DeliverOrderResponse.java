package com.tech20.mobiledelivery.retrofitclient.deliverorderclient;


import com.google.gson.annotations.SerializedName;
import com.tech20.mobiledelivery.retrofitclient.ordersclient.OrderNote;

import java.util.List;

public class DeliverOrderResponse {

    /*
    {
   "items": [
        {
          "OrderItemId": 92,
          "OrderId": 63,
          "PlaceOrderId": 0,
          "item_code": "5243966951500",
          "item_name": "Banna OG Wax",
          "quantity": 2,
          "price": 20,
          "base_price": 0,
          "package_size": ".5 Gram",
          "isOnSportOrder": false,
          "routeInvenotoryId": 0,
          "Tax": 2.8,
          "OrderType": 2,
          "StockItemCode": "65",
          "Discount": 0
      }
   ],
   "DriverId": 1,
   "RouteId": 5,
   "DeliveryDate": "2017-12-26T00:00:00",
   "Longitude": 73.794556,
   "Latitude": 18.555784,
   "ClientId": 1,
   "Status": 2,
   "OrderId": 60,
   "IsDeliveryLocation": false,
   "OrderDeliveryNote": {
       "OrderDeliveryNotesId": 11,
       "OrderId": 60,
       "Note": "Delivered",
       "DateCreated": "2017-12-26T12:28:51.9095758"
   }
}
     */

    @SerializedName("DriverId")
    private String driverId = null;

    @SerializedName("RouteId")
    private String RouteId = null;

    @SerializedName("DeliveryDate")
    private String DeliveryDate = null;

    @SerializedName("Longitude")
    private String Longitude = null;

    @SerializedName("Latitude")
    private String Latitude = null;

    @SerializedName("ClientId")
    private String ClientId = null;

    @SerializedName("Status")
    private int Status = 0;

    @SerializedName("OrderId")
    private String OrderId = null;

    @SerializedName("IsDeliveryLocation")
    private boolean IsDeliveryLocation = false;

    @SerializedName("items")
    private List<DeliveryOrderResponseExtraItem> items = null;

    @SerializedName("OrderDeliveryNote")
    private OrderNote orderDeliveryNote = null;

    public static class DeliveryOrderResponseExtraItem{

        @SerializedName("OrderItemId")
        private String OrderItemId = null;

        @SerializedName("OrderId")
        private String OrderId = null;

    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getRouteId() {
        return RouteId;
    }

    public void setRouteId(String routeId) {
        RouteId = routeId;
    }

    public String getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        DeliveryDate = deliveryDate;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public boolean isDeliveryLocation() {
        return IsDeliveryLocation;
    }

    public void setDeliveryLocation(boolean deliveryLocation) {
        IsDeliveryLocation = deliveryLocation;
    }

    public List<DeliveryOrderResponseExtraItem> getItems() {
        return items;
    }

    public void setItems(List<DeliveryOrderResponseExtraItem> items) {
        this.items = items;
    }

    public OrderNote getOrderDeliveryNote() {
        return orderDeliveryNote;
    }

    public void setOrderDeliveryNote(OrderNote orderDeliveryNote) {
        this.orderDeliveryNote = orderDeliveryNote;
    }
}
