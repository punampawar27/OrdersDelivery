package com.tech20.mobiledelivery.factory.discountfactory;


import com.tech20.mobiledelivery.models.Entities.EntityDiscount;

public class NoDiscount implements IDiscount{


    @Override
    public float calculatePriceAfterDiscountAndTax(EntityDiscount entityDiscount) {
        float itemPrice = entityDiscount.getItemPrice();
        int orderedQuantity = entityDiscount.getOrderedQuantity();
        float taxPerItem = entityDiscount.getTaxPerItem();

        return orderedQuantity*(itemPrice+taxPerItem);
    }
}
