package com.tech20.mobiledelivery.database.dataentities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.tech20.mobiledelivery.helpers.Constants;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = Constants.DatabaseConstants.TABLE_ORDER_ITEMS.TABLE_NAME_ORDERITEMS,
        indices = {@Index(value = Constants.DatabaseConstants.TABLE_ORDER_ITEMS.COLUMN_NAME_ORDER_ITEM_ID , unique = true)},
        foreignKeys = {@ForeignKey(entity = DbModelOrders.class,
                parentColumns = Constants.DatabaseConstants.TABLE_ORDERS.COLUMN_NAME_ORDER_ID,
                childColumns = Constants.DatabaseConstants.TABLE_ORDER_ITEMS.COLUMN_NAME_ORDERID,
                onDelete = CASCADE)})
public class DbModelOrderInventory implements Parcelable{

    @PrimaryKey
    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDER_ITEMS.COLUMN_NAME_ORDER_ITEM_ID)
    @NonNull
    private String orderItemId = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDER_ITEMS.COLUMN_NAME_ORDERID)
    private String orderId = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDER_ITEMS.COLUMN_NAME_PLACEORDERID)
    private String placeOrderId = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDER_ITEMS.COLUMN_NAME_ITEM_CODE)
    private String itemCode = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDER_ITEMS.COLUMN_NAME_ITEM_NAME)
    private String itemName = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDER_ITEMS.COLUMN_NAME_QUANTITY)
    private String quantity = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDER_ITEMS.COLUMN_NAME_PRICE)
    private String price = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDER_ITEMS.COLUMN_NAME_BASE_PRICE)
    private String basePrice = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDER_ITEMS.COLUMN_NAME_PACKAGE_SIZE)
    private String packageSize = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDER_ITEMS.COLUMN_NAME_ISONSPORTORDER)
    private String isOnSportOrder = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDER_ITEMS.COLUMN_NAME_ROUTEINVENOTORYID)
    private String routeInvenotoryId = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDER_ITEMS.COLUMN_NAME_TAX)
    private String tax = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDER_ITEMS.COLUMN_NAME_ORDERTYPE)
    private String orderType = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDER_ITEMS.COLUMN_NAME_STOCKITEMCODE)
    private String stockItemCode = null;

    @ColumnInfo(name = Constants.DatabaseConstants.TABLE_ORDER_ITEMS.COLUMN_NAME_DISCOUNT)
    private String discount = null;

    public DbModelOrderInventory(){}
    protected DbModelOrderInventory(Parcel in) {
        orderItemId = in.readString();
        orderId = in.readString();
        placeOrderId = in.readString();
        itemCode = in.readString();
        itemName = in.readString();
        quantity = in.readString();
        price = in.readString();
        basePrice = in.readString();
        packageSize = in.readString();
        isOnSportOrder = in.readString();
        routeInvenotoryId = in.readString();
        tax = in.readString();
        orderType = in.readString();
        stockItemCode = in.readString();
        discount = in.readString();
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(@NonNull String orderItemId) {
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

    public static final Creator<DbModelOrderInventory> CREATOR = new Creator<DbModelOrderInventory>() {
        @Override
        public DbModelOrderInventory createFromParcel(Parcel in) {
            return new DbModelOrderInventory(in);
        }

        @Override
        public DbModelOrderInventory[] newArray(int size) {
            return new DbModelOrderInventory[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(orderItemId);
        dest.writeString(orderId);
        dest.writeString(placeOrderId);
        dest.writeString(itemCode);
        dest.writeString(itemName);
        dest.writeString(quantity);
        dest.writeString(price);
        dest.writeString(basePrice);
        dest.writeString(packageSize);
        dest.writeString(isOnSportOrder);
        dest.writeString(routeInvenotoryId);
        dest.writeString(tax);
        dest.writeString(orderType);
        dest.writeString(stockItemCode);
        dest.writeString(discount);
    }
}


