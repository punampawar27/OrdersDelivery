package com.tech20.mobiledelivery.retrofitclient.extrainventoryclient;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class ExtraInventoryResponse implements Parcelable{

    /*
    [
    {
        "RouteId": 1538,
        "ItemCode": "66",
        "Barcode": "8932244945317",
        "Quantity": 10,
        "ItemName": "Banna OG Wax",
        "ItemPrice": 40,
        "PromotionType": "",
        "PromotionValue": 0,
        "PromotionStartDate": "2017-12-12T00:00:00",
        "PromotionEndDate": "2017-12-12T00:00:00",
        "IsTaxApplied": false,
        "TaxPercentage": 0,
        "DiscountDesc": null,
        "DiscountType": null,
        "DiscountAmount": 0,
        "DiscountQuantity": 0,
        "PackageSize": "1 Gram"
    },
    {
        "RouteId": 1538,
        "ItemCode": "68",
        "Barcode": "5419301446061",
        "Quantity": 5,
        "ItemName": "Blue Dream Wax",
        "ItemPrice": 20,
        "PromotionType": "",
        "PromotionValue": 0,
        "PromotionStartDate": "2017-12-12T00:00:00",
        "PromotionEndDate": "2017-12-12T00:00:00",
        "IsTaxApplied": false,
        "TaxPercentage": 0,
        "DiscountDesc": null,
        "DiscountType": null,
        "DiscountAmount": 0,
        "DiscountQuantity": 0,
        "PackageSize": ".5 Gram"
    },
    {
        "RouteId": 1538,
        "ItemCode": "67",
        "Barcode": "1331753485370",
        "Quantity": 7,
        "ItemName": "Banna OG Wax",
        "ItemPrice": 70,
        "PromotionType": "",
        "PromotionValue": 0,
        "PromotionStartDate": "2017-12-12T00:00:00",
        "PromotionEndDate": "2017-12-12T00:00:00",
        "IsTaxApplied": false,
        "TaxPercentage": 0,
        "DiscountDesc": null,
        "DiscountType": null,
        "DiscountAmount": 0,
        "DiscountQuantity": 0,
        "PackageSize": "2 Gram"
    }
]
     */


    @SerializedName("RouteId")
    private String routeId = null;

    @SerializedName("ItemCode")
    private String itemCode = null;

    @SerializedName("Barcode")
    private String barcode = null;

    @SerializedName("Quantity")
    private String quantity = null;

    @SerializedName("ItemName")
    private String itemName = null;

    @SerializedName("ItemPrice")
    private String itemPrice = null;

    @SerializedName("PromotionValue")
    private String promotionValue = null;

    @SerializedName("PromotionStartDate")
    private String promotionStartDate = null;

    @SerializedName("PromotionEndDate")
    private String promotionEndDate = null;

    @SerializedName("PromotionType")
    private String promotionType = null;

    @SerializedName("IsTaxApplied")
    private boolean isTaxApplied = false;

    @SerializedName("TaxPercentage")
    private String taxPercentage = null;

    @SerializedName("DiscountDesc")
    private String discountDesc = null;

    @SerializedName("DiscountType")
    private String discountType = null;

    @SerializedName("DiscountAmount")
    private String discountAmount = null;

    @SerializedName("DiscountQuantity")
    private String discountQuantity = null;

    @SerializedName("PackageSize")
    private String PackageSize = null;

    //This is a flag for adapter
    private int selectedQuantity = 0;

    private float totalPriceAfterTaxAndDiscount = 0f;

    protected ExtraInventoryResponse(Parcel in) {
        routeId = in.readString();
        itemCode = in.readString();
        barcode = in.readString();
        quantity = in.readString();
        itemName = in.readString();
        itemPrice = in.readString();
        promotionType = in.readString();
        promotionValue = in.readString();
        promotionStartDate = in.readString();
        promotionEndDate = in.readString();
        isTaxApplied = in.readByte() != 0;
        taxPercentage = in.readString();
        discountDesc = in.readString();
        discountType = in.readString();
        discountAmount = in.readString();
        discountQuantity = in.readString();
        PackageSize = in.readString();
        selectedQuantity = in.readInt();
        totalPriceAfterTaxAndDiscount = in.readFloat();
    }

    public static final Creator<ExtraInventoryResponse> CREATOR = new Creator<ExtraInventoryResponse>() {
        @Override
        public ExtraInventoryResponse createFromParcel(Parcel in) {
            return new ExtraInventoryResponse(in);
        }

        @Override
        public ExtraInventoryResponse[] newArray(int size) {
            return new ExtraInventoryResponse[size];
        }
    };

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getPromotionValue() {
        return promotionValue;
    }

    public void setPromotionValue(String promotionValue) {
        this.promotionValue = promotionValue;
    }

    public String getPromotionStartDate() {
        return promotionStartDate;
    }

    public void setPromotionStartDate(String promotionStartDate) {
        this.promotionStartDate = promotionStartDate;
    }

    public String getPromotionEndDate() {
        return promotionEndDate;
    }

    public void setPromotionEndDate(String promotionEndDate) {
        this.promotionEndDate = promotionEndDate;
    }

    public boolean getIsTaxApplied() {
        return isTaxApplied;
    }

    public void setIsTaxApplied(boolean isTaxApplied) {
        this.isTaxApplied = isTaxApplied;
    }

    public String getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(String taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public String getDiscountDesc() {
        return discountDesc;
    }

    public void setDiscountDesc(String discountDesc) {
        this.discountDesc = discountDesc;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getDiscountQuantity() {
        return discountQuantity;
    }

    public void setDiscountQuantity(String discountQuantity) {
        this.discountQuantity = discountQuantity;
    }

    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }

    public String getPackageSize() {
        return PackageSize;
    }

    public void setPackageSize(String packageSize) {
        PackageSize = packageSize;
    }

    public int getSelectedQuantity() {
        return selectedQuantity;
    }

    public void setSelectedQuantity(int selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }

    public float getTotalPriceAfterTaxAndDiscount() {
        return totalPriceAfterTaxAndDiscount;
    }

    public void setTotalPriceAfterTaxAndDiscount(float totalPriceAfterTaxAndDiscount) {
        this.totalPriceAfterTaxAndDiscount = totalPriceAfterTaxAndDiscount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(routeId);
        dest.writeString(itemCode);
        dest.writeString(barcode);
        dest.writeString(quantity);
        dest.writeString(itemName);
        dest.writeString(itemPrice);
        dest.writeString(promotionType);
        dest.writeString(promotionValue);
        dest.writeString(promotionStartDate);
        dest.writeString(promotionEndDate);
        dest.writeByte((byte) (isTaxApplied ? 1 : 0));
        dest.writeString(taxPercentage);
        dest.writeString(discountDesc);
        dest.writeString(discountType);
        dest.writeString(discountAmount);
        dest.writeString(discountQuantity);
        dest.writeString(PackageSize);
        dest.writeInt(selectedQuantity);
        dest.writeFloat(totalPriceAfterTaxAndDiscount);
    }


}
