package com.tech20.mobiledelivery.database.dataentities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.tech20.mobiledelivery.helpers.Constants;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = Constants.DatabaseConstants.TABLE_ORDERS.TABLE_NAME_ORDERS,
    indices = {@Index(value = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_ORDER_ID,unique = true)})

public class DbModelOrders implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_ORDER_ID)
    @NonNull
    private String orderId = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_ROUTE_ID)
    private String routeId = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_INVOICENO)
    private String invoiceNo = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_EXPECTEDDELIVERYDATE)
    private String expectedDeliveryDate = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_EXPECTEDDELIVERYTIME)
    private int expectedDeliveryTime = -1;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_ACTUALDELIVERYDATE)
    private String actualDeliveryDate = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_SEQUENCENO)
    private String sequenceNo = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_STATUS)
    private int status = 0;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_PAYMENTRECEIVED)
    private String paymentReceived = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_CUSTOMERNAME)
    private String customerName = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_CONTACTNUMBER)
    private String contactNumber = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_SHIPPINGADDRESS)
    private String shippingAddress = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_ISTAXEXEMPTED)
    private String isTaxExempted = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_SUBTOTAL)
    private String subTotal = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_TAX)
    private String tax = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_DISCOUNT)
    private String discount = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_DELIVERYATTEMPTS)
    private String deliveryAttempts = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_ISDIRTY)
    private String isDirty = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_ISVERIFIED)
    private boolean isVerified = false;

    @Ignore
    private List<DbModelOrderInventory> listOrderItems = null;

    @Ignore
    private List<DbModelNotes> listNotes = null;

    public DbModelOrders(){

        //initialize to Read order items from readParcel
        listOrderItems = new ArrayList<>();
        listNotes = new ArrayList<>();
    }

    protected DbModelOrders(Parcel in) {

        this();
        routeId = in.readString();
        orderId = in.readString();
        invoiceNo = in.readString();
        expectedDeliveryDate = in.readString();
        expectedDeliveryTime = in.readInt();
        actualDeliveryDate = in.readString();
        sequenceNo = in.readString();
        status = in.readInt();
        paymentReceived = in.readString();
        customerName = in.readString();
        contactNumber = in.readString();
        shippingAddress = in.readString();
        isTaxExempted = in.readString();
        subTotal = in.readString();
        tax = in.readString();
        discount = in.readString();
        deliveryAttempts = in.readString();
        isDirty = in.readString();
        isVerified = in.readByte()!=0;
        in.readTypedList(listOrderItems, DbModelOrderInventory.CREATOR);
        in.readTypedList(listNotes,DbModelNotes.CREATOR);
    }

    public static final Creator<DbModelOrders> CREATOR = new Creator<DbModelOrders>() {
        @Override
        public DbModelOrders createFromParcel(Parcel in) {
            return new DbModelOrders(in);
        }

        @Override
        public DbModelOrders[] newArray(int size) {
            return new DbModelOrders[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(routeId);
        dest.writeString(orderId);
        dest.writeString(invoiceNo);
        dest.writeString(expectedDeliveryDate);
        dest.writeInt(expectedDeliveryTime);
        dest.writeString(actualDeliveryDate);
        dest.writeString(sequenceNo);
        dest.writeInt(status);
        dest.writeString(paymentReceived);
        dest.writeString(customerName);
        dest.writeString(contactNumber);
        dest.writeString(shippingAddress);
        dest.writeString(isTaxExempted);
        dest.writeString(subTotal);
        dest.writeString(tax);
        dest.writeString(discount);
        dest.writeString(deliveryAttempts);
        dest.writeString(isDirty);
        dest.writeByte((byte)(isVerified?1:0));
        dest.writeTypedList(listOrderItems);
        dest.writeTypedList(listNotes);

        Log.d(Constants.LogConstants.TAG_WASTE,"writeToParcel orderItems:"+listOrderItems.size());
        Log.d(Constants.LogConstants.TAG_WASTE,"writeToParcel notes:"+listNotes.size());
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(@NonNull String orderId) {
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

    public int getExpectedDeliveryTime() {
        return expectedDeliveryTime;
    }

    public void setExpectedDeliveryTime(int expectedDeliveryTime) {
        this.expectedDeliveryTime = expectedDeliveryTime;
    }

    public List<DbModelNotes> getListNotes() {
        return listNotes;
    }

    public void setListNotes(List<DbModelNotes> listNotes) {
        this.listNotes = listNotes;
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

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public List<DbModelOrderInventory> getListOrderItems() {
        return listOrderItems;
    }

    public void setListOrderItems(List<DbModelOrderInventory> listOrderItems) {
        this.listOrderItems = listOrderItems;
    }
}
