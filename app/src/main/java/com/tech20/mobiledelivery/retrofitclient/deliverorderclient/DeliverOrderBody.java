package com.tech20.mobiledelivery.retrofitclient.deliverorderclient;


import com.google.gson.annotations.SerializedName;
import com.tech20.mobiledelivery.retrofitclient.ordersclient.OrderNote;

import java.util.List;

public class DeliverOrderBody {

    /*
    {
  "Latitude": 18.555784,
  "Longitude": 73.794556,
  "RouteId": 35,
  "IsDeliveryLocation": "false",
  "DriverId": 1,
     "Items":[
        {
        "package_size": ".5 Gram",
        "price": 20,
        "tax": 2.8,
        "item_code": "5243966951500",
        "item_name": "Banna OG Wax",
        "Discount": 0,
        "quantity": 2,
        "StockItemCode": "65"
    }
     ],
  "OrderDeliveryNote": {
     "DateCreated": "2017-12-26 12:28:51.9095758",
     "Note": "Delivered"
  },
  "Status": "Delivered",
  "DeliveryDate": "2017-12-26 00:00:00.0000000",
  "OrderId": 60

}
     */

    @SerializedName("Latitude")
    private String latitude = null;

    @SerializedName("Longitude")
    private String longitude = null;

    @SerializedName("RouteId")
    private String routeId = null;

    @SerializedName("IsDeliveryLocation")
    private String isDeliveryLocation = null;

    @SerializedName("DriverId")
    private String driverId = null;

    @SerializedName("OrderDeliveryNote")
    private OrderDeliveryNote orderNote = null;

    @SerializedName("Items")
    private List<OrderDeliveryExtraItems> itemsList = null;

    @SerializedName("Status")
    private String status = null;

    @SerializedName("DeliveryDate")
    private String deliveryDate = null;

    @SerializedName("OrderId")
    private String orderId = null;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getIsDeliveryLocation() {
        return isDeliveryLocation;
    }

    public void setIsDeliveryLocation(String isDeliveryLocation) {
        this.isDeliveryLocation = isDeliveryLocation;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public OrderDeliveryNote getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(OrderDeliveryNote orderNote) {
        this.orderNote = orderNote;
    }

    public List<OrderDeliveryExtraItems> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<OrderDeliveryExtraItems> itemsList) {
        this.itemsList = itemsList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public static class OrderDeliveryNote{
        @SerializedName("Note")
        private String Note = null;

        @SerializedName("DateCreated")
        private String DateCreated = null;

        public String getNote() {
            return Note;
        }

        public void setNote(String note) {
            Note = note;
        }

        public String getDateCreated() {
            return DateCreated;
        }

        public void setDateCreated(String dateCreated) {
            DateCreated = dateCreated;
        }
    }

    public static class OrderDeliveryExtraItems{

        @SerializedName("package_size")
        private String packageSize = null;

        @SerializedName("price")
        private String price = null;

        @SerializedName("tax")
        private String tax = null;

        @SerializedName("item_code")
        private String itemCode = null;

        @SerializedName("item_name")
        private String itemName = null;

        @SerializedName("Discount")
        private String discount = null;

        @SerializedName("quantity")
        private String quantity = null;

        @SerializedName("StockItemCode")
        private String stockItemCode = null;


        public String getPackageSize() {
            return packageSize;
        }

        public void setPackageSize(String packageSize) {
            this.packageSize = packageSize;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTax() {
            return tax;
        }

        public void setTax(String tax) {
            this.tax = tax;
        }

        public String getItemCode() {
            return itemCode;
        }

        public void setItemCode(String itemCode) {
            this.itemCode = itemCode;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getStockItemCode() {
            return stockItemCode;
        }

        public void setStockItemCode(String stockItemCode) {
            this.stockItemCode = stockItemCode;
        }
    }
}
