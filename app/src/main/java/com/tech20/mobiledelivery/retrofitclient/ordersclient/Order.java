package com.tech20.mobiledelivery.retrofitclient.ordersclient;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {

    /*
    {
                "OrderId": 2152,
                "InvoiceNo": 48,
                "ExpectedDeliveryDate": "2017-12-14T00:00:00",
                "ExpectedDeliveryTime": "Anytime",
                "ActualDeliveryDate": "0001-01-01T00:00:00",
                "SequenceNo": 1,
                "Status": 1,
                "PaymentReceived": false,
                "CustomerName": "Elaina Head",
                "ContactNumber": "(708) 515-7776",
                "ShippingAddress": "4388 Norman R Bobins  Spokane WA 99211",
                "IsTaxExempted": false,
                "SubTotal": 105,
                "Tax": 7.35,
                "Discount": -2.25,
                "DeliveryAttempts": 0,
                "IsDirty": true,
                "order_items": [
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
                    },
                    {
                        "OrderItemId": 3310,
                        "OrderId": 2152,
                        "PlaceOrderId": 0,
                        "item_code": "4422356131020",
                        "item_name": "Jack Frost(Eighth Oz\r\n)",
                        "quantity": 1,
                        "price": 60,
                        "base_price": 60,
                        "package_size": "Bulk",
                        "isOnSportOrder": false,
                        "routeInvenotoryId": 0,
                        "Tax": 0,
                        "OrderType": 1,
                        "StockItemCode": "25",
                        "Discount": 0
                    }
                ],
                 "OrderNote": [
                    {
                        "OrderDeliveryNotesId": 1822,
                        "OrderId": 2152,
                        "OrderNote": "This is whole order Notes",
                        "DateCreated": "2017-12-14T12:17:14.7230837"
                    }
                ]
            }

     */

    @SerializedName("OrderId")
    private String orderId = null;

    @SerializedName("InvoiceNo")
    private String invoiceNo = null;

    @SerializedName("ExpectedDeliveryDate")
    private String expectedDeliveryDate = null;

    @SerializedName("ExpectedDeliveryTime")
    private String expectedDeliveryTime = null;

    @SerializedName("ActualDeliveryDate")
    private String actualDeliveryDate = null;

    @SerializedName("SequenceNo")
    private String sequenceNo = null;

    @SerializedName("Status")
    private int status = 0;

    @SerializedName("PaymentReceived")
    private String paymentReceived = null;

    @SerializedName("CustomerName")
    private String customerName = null;

    @SerializedName("ContactNumber")
    private String contactNumber = null;

    @SerializedName("ShippingAddress")
    private String shippingAddress = null;

    @SerializedName("IsTaxExempted")
    private String isTaxExempted = null;

    @SerializedName("SubTotal")
    private String subTotal = null;

    @SerializedName("Tax")
    private String tax = null;

    @SerializedName("Discount")
    private String discount = null;

    @SerializedName("DeliveryAttempts")
    private String deliveryAttempts = null;

    @SerializedName("IsDirty")
    private String isDirty = null;

    @SerializedName("VerifyIdentity")
    private boolean isVerifiedIdentity= false;

    @SerializedName("order_items")
    private List<OrderItem> orderItemList = null;

    @SerializedName("Note")
    private List<OrderNote> notesList = null;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(String expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public String getExpectedDeliveryTime() {
        return expectedDeliveryTime;
    }

    public void setExpectedDeliveryTime(String expectedDeliveryTime) {
        this.expectedDeliveryTime = expectedDeliveryTime;
    }

    public String getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(String actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public String getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(String sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPaymentReceived() {
        return paymentReceived;
    }

    public void setPaymentReceived(String paymentReceived) {
        this.paymentReceived = paymentReceived;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getIsTaxExempted() {
        return isTaxExempted;
    }

    public void setIsTaxExempted(String isTaxExempted) {
        this.isTaxExempted = isTaxExempted;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDeliveryAttempts() {
        return deliveryAttempts;
    }

    public void setDeliveryAttempts(String deliveryAttempts) {
        this.deliveryAttempts = deliveryAttempts;
    }

    public String getIsDirty() {
        return isDirty;
    }

    public void setIsDirty(String isDirty) {
        this.isDirty = isDirty;
    }

    public boolean isVerifiedIdentity() {
        return isVerifiedIdentity;
    }

    public void setVerifiedIdentity(boolean verifiedIdentity) {
        isVerifiedIdentity = verifiedIdentity;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public List<OrderNote> getNotesList() {
        return notesList;
    }

    public void setNotesList(List<OrderNote> notesList) {
        this.notesList = notesList;
    }
}
