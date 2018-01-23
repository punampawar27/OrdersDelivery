package com.tech20.mobiledelivery.factory.discountfactory;

import com.tech20.mobiledelivery.models.Entities.EntityDiscount;

public class DiscountMixmatchPerentageOff implements IDiscount{

    @Override
    public float calculatePriceAfterDiscountAndTax(EntityDiscount entityDiscount) {

        int discountQuantity = entityDiscount.getDiscountQuantity();
        int orderedQuantity =  entityDiscount.getOrderedQuantity();
        float itemPrice = entityDiscount.getItemPrice();
        float discountAmountPerItem = itemPrice*(entityDiscount.getDiscountAmount()/100);
        float taxPerItem = entityDiscount.getTaxPerItem();

        int quantityNOTEligibleForDiscount = orderedQuantity%discountQuantity;
        int batchQuantity = (orderedQuantity/discountQuantity);
        float totalTax =orderedQuantity*taxPerItem;

        if(orderedQuantity<discountQuantity){
            return (orderedQuantity*itemPrice)+totalTax;
        }

        float batchCalculation = (batchQuantity*discountQuantity*(itemPrice-discountAmountPerItem));

        //If whole order is eligible for discount
        if(quantityNOTEligibleForDiscount ==0){
            return batchCalculation+totalTax;
        }

        return batchCalculation
                +(quantityNOTEligibleForDiscount*itemPrice)
                +totalTax;
    }
}
