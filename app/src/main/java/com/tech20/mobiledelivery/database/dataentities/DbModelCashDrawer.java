package com.tech20.mobiledelivery.database.dataentities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.tech20.mobiledelivery.helpers.Constants;

@Entity(tableName = Constants.DatabaseConstants.TABLE_CASHDRAWER.TABLE_NAME_CUSTOMER)
public class DbModelCashDrawer {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.DatabaseConstants.COLUMN_NAME_AUTOINCREMENT_ID)
    private long id=0;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_CASHDRAWER.COLUMN_NAME_DRAWERID)
    private String drawerId = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_CASHDRAWER.COLUMN_NAME_DRIVERID)
    private String driverId = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_CASHDRAWER.COLUMN_NAME_DELIVERYDATE)
    private String deliveryDate = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_CASHDRAWER.COLUMN_NAME_CASH)
    private String cash = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_CASHDRAWER.COLUMN_NAME_ROUTEID)
    private String routeId = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_CASHDRAWER.COLUMN_NAME_CLIENTID)
    private String clientId = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_CASHDRAWER.COLUMN_NAME_ORDERID)
    private String orderId = null;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDrawerId() {
        return drawerId;
    }

    public void setDrawerId(String drawerId) {
        this.drawerId = drawerId;
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
