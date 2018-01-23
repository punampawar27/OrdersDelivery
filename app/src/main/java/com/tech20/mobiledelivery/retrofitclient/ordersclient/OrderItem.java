package com.tech20.mobiledelivery.retrofitclient.ordersclient;

import com.google.gson.annotations.SerializedName;


public class OrderItem {
    /*
    {
                        "OrderItemId": 3309,
                        "OrderId": 2152,
                        "PlaceOrderId": 0,
                        "item_code": "3944839647971",
                        "item_name": "Atoms G5 Vaporizer",
                        "quantity": 1,
                        "price": 45,
                        "base_price": 45,
                        "package_size": "Ea",
                        "isOnSportOrder": false,
                        "routeInvenotoryId": 0,
                        "Tax": 0,
                        "OrderType": 1,
                        "StockItemCode": "57",
                        "Discount": 0
                    }
     */

    @SerializedName("OrderItemId")
    private String orderItemId = null;

    @SerializedName("OrderId")
    private String orderId = null;

    @SerializedName("PlaceOrderId")
    private String placeOrderId = null;

    @SerializedName("item_code")
    private String itemCode = null;

    @SerializedName("item_name")
    private String itemName = null;

    @SerializedName("quantity")
    private String quantity = null;

    @SerializedName("price")
    private String price = null;

    @SerializedName("base_price")
    private String basePrice = null;

    @SerializedName("package_size")
    private String packageSize = null;

    @SerializedName("isOnSportOrder")
    private String isOnSportOrder = null;

    @SerializedName("routeInvenotoryId")
    private String routeInvenotoryId = null;

    @SerializedName("Tax")
    private String tax = null;

    @SerializedName("OrderType")
    private String orderType = null;

    @SerializedName("StockItemCode")
    private String stockItemCode = null;

    @SerializedName("Discount")
    private String discount = null;

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPlaceOrderId() {
        return placeOrderId;
    }

    public void setPlaceOrderId(String placeOrderId) {
        this.placeOrderId = placeOrderId;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public String getIsOnSportOrder() {
        return isOnSportOrder;
    }

    public void setIsOnSportOrder(String isOnSportOrder) {
        this.isOnSportOrder = isOnSportOrder;
    }

    public String getRouteInvenotoryId() {
        return routeInvenotoryId;
    }

    public void setRouteInvenotoryId(String routeInvenotoryId) {
        this.routeInvenotoryId = routeInvenotoryId;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getStockItemCode() {
        return stockItemCode;
    }

    public void setStockItemCode(String stockItemCode) {
        this.stockItemCode = stockItemCode;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
