package com.tech20.mobiledelivery.models.Entities;

public class EntityDiscount {

    private float itemPrice = 0f;
    private float discountAmount = 0f;
    private float promotionAmount = 0f;
    private int discountQuantity = 0;
    private int orderedQuantity=0;
    private String promotionStartDate = null;
    private String promotionEndDate = null;
    private float taxPerItem = 0f;


    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getDiscountQuantity() {
        return discountQuantity;
    }

    public void setDiscountQuantity(int discountQuantity) {
        this.discountQuantity = discountQuantity;
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

    public float getTaxPerItem() {
        return taxPerItem;
    }

    public void setTaxPerItem(float taxPerItem) {
        this.taxPerItem = taxPerItem;
    }

    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public float getPromotionAmount() {
        return promotionAmount;
    }

    public void setPromotionAmount(float promotionAmount) {
        this.promotionAmount = promotionAmount;
    }
}
