package com.tech20.mobiledelivery.retrofitclient.ordersclient;


import com.google.gson.annotations.SerializedName;

public class OrderNote {

    /*
    {
                        "OrderDeliveryNotesId": 1822,
                        "OrderId": 2152,
                        "OrderNote": "This is whole order Notes",
                        "DateCreated": "2017-12-14T12:17:14.7230837"
                    }
     */

    @SerializedName("OrderDeliveryNotesId")
    private String OrderDeliveryNotesId = null;

    @SerializedName("OrderId")
    private String OrderId = null;

    @SerializedName("Note")
    private String Note = null;

    @SerializedName("DateCreated")
    private String DateCreated = null;

    public String getOrderDeliveryNotesId() {
        return OrderDeliveryNotesId;
    }

    public void setOrderDeliveryNotesId(String orderDeliveryNotesId) {
        OrderDeliveryNotesId = orderDeliveryNotesId;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

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
