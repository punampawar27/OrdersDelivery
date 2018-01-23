package com.tech20.mobiledelivery.retrofitclient.customersclient;


import com.google.gson.annotations.SerializedName;

public class CustomersResponse {

    @SerializedName("CustomerName")
    private String customerName = null;

    @SerializedName("ContactNumber")
    private String contactNumber = null;

    @SerializedName("ShippingAddress")
    private String shippingAddress = null;

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
}
