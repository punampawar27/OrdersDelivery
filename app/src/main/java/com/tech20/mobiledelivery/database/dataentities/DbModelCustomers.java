package com.tech20.mobiledelivery.database.dataentities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.tech20.mobiledelivery.helpers.Constants;

@Entity(tableName = Constants.DatabaseConstants.TABLE_CUSTOMER.TABLE_NAME_CUSTOMER,
indices = @Index(value = Constants.DatabaseConstants.TABLE_CUSTOMER.COLUMN_NAME_CONTACT_NUMBER, unique = true))
public class DbModelCustomers {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.DatabaseConstants.COLUMN_NAME_AUTOINCREMENT_ID)
    private long id=0;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_CUSTOMER.COLUMN_NAME_CUSTOMER_NAME)
    private String customerName = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_CUSTOMER.COLUMN_NAME_CONTACT_NUMBER)
    private String contactNumber = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_CUSTOMER.COLUMN_NAME_SHIPPING_ADDRESS)
    private String shippingAddress = null;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
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
}
