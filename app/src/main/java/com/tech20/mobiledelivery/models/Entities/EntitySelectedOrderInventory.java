package com.tech20.mobiledelivery.models.Entities;


import android.os.Parcel;
import android.os.Parcelable;

public class EntitySelectedOrderInventory implements IEntity, Parcelable{

    public enum OrderItemType{
        ORDERED_INVENTORY,EXTRA_INVENTORY;
    }

    private String itemId = null;
    private String itemName = null;
    private String quantity = null;
    private String packageSize = null;
    private String price = null;
    private String tax = null;
    private String discount = null;
    private String barCode = null;
    private String stockItemCode = null;
    private float totalPriceAfterTaxAndDiscount = 0f;
    private int ITEM_TYPE=OrderItemType.ORDERED_INVENTORY.ordinal();

    public EntitySelectedOrderInventory(){}
    protected EntitySelectedOrderInventory(Parcel in) {
        itemId = in.readString();
        itemName = in.readString();
        quantity = in.readString();
        packageSize = in.readString();
        price = in.readString();
        tax = in.readString();
        discount = in.readString();
        barCode = in.readString();
        stockItemCode = in.readString();
        ITEM_TYPE = in.readInt();
        totalPriceAfterTaxAndDiscount = in.readFloat();
    }

    public static final Creator<EntitySelectedOrderInventory> CREATOR = new Creator<EntitySelectedOrderInventory>() {
        @Override
        public EntitySelectedOrderInventory createFromParcel(Parcel in) {
            return new EntitySelectedOrderInventory(in);
        }

        @Override
        public EntitySelectedOrderInventory[] newArray(int size) {
            return new EntitySelectedOrderInventory[size];
        }
    };

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

    public int getITEM_TYPE() {
        return ITEM_TYPE;
    }

    public void setITEM_TYPE(int ITEM_TYPE) {
        this.ITEM_TYPE = ITEM_TYPE;
    }

    public String getItemCode() {
        return itemId;
    }

    public void setItemCode(String itemId) {
        this.itemId = itemId;
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

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getStockItemCode() {
        return stockItemCode;
    }

    public void setStockItemCode(String stockItemCode) {
        this.stockItemCode = stockItemCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public float getTotalPriceAfterTaxAndDiscount() {
        return totalPriceAfterTaxAndDiscount;
    }

    public void setTotalPriceAfterTaxAndDiscount(float totalPriceAfterTaxAndDiscount) {
        this.totalPriceAfterTaxAndDiscount = totalPriceAfterTaxAndDiscount;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemId);
        dest.writeString(itemName);
        dest.writeString(quantity);
        dest.writeString(packageSize);
        dest.writeString(price);
        dest.writeString(tax);
        dest.writeString(discount);
        dest.writeString(barCode);
        dest.writeString(stockItemCode);
        dest.writeInt(ITEM_TYPE);
        dest.writeFloat(totalPriceAfterTaxAndDiscount);
    }
}
