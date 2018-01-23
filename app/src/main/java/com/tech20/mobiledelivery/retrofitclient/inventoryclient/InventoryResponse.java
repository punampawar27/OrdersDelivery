package com.tech20.mobiledelivery.retrofitclient.inventoryclient;


import com.google.gson.annotations.SerializedName;

public class InventoryResponse {

    /*
       [
    {
        "ItemCode": "2641891349107",
        "ItemName": "Gas OG Wax",
        "Status": "Delivery",
        "Quantity": 1,
        "SingleUnitPrice": 0,
        "OrderId": 0,
        "package_size": "2 Gram"
    },
    {
        "ItemCode": "3938719664234",
        "ItemName": "Timewreck(Quarter Oz\r\n)",
        "Status": "Delivery",
        "Quantity": 1,
        "SingleUnitPrice": 0,
        "OrderId": 0,
        "package_size": "Bulk"
    },
    {
        "ItemCode": "4658178830984",
        "ItemName": "Pineapple Express(Ounce\r\n)",
        "Status": "Delivery",
        "Quantity": 2,
        "SingleUnitPrice": 0,
        "OrderId": 0,
        "package_size": "Bulk"
    }
]
     */

    @SerializedName("ItemCode")
    private String itemCode = null;

    @SerializedName("ItemName")
    private String itemName = null;

    @SerializedName("Status")
    private String status = null;

    @SerializedName("Quantity")
    private String quantity = null;

    @SerializedName("SingleUnitPrice")
    private String singleUnitPrice = null;

    @SerializedName("OrderId")
    private String OrderId = null;

    @SerializedName("package_size")
    private String packageSize = null;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSingleUnitPrice() {
        return singleUnitPrice;
    }

    public void setSingleUnitPrice(String singleUnitPrice) {
        this.singleUnitPrice = singleUnitPrice;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }
}
