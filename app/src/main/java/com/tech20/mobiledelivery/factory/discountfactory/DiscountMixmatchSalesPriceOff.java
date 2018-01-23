package com.tech20.mobiledelivery.factory.discountfactory;


import com.tech20.mobiledelivery.models.Entities.EntityDiscount;

public class DiscountMixmatchSalesPriceOff implements IDiscount{


    @Override
    public float calculatePriceAfterDiscountAndTax(EntityDiscount entityDiscount) {

        int discountQuantity = entityDiscount.getDiscountQuantity();
        int orderedQuantity =  entityDiscount.getOrderedQuantity();
        float discountAmount = entityDiscount.getDiscountAmount();
        float itemPrice = entityDiscount.getItemPrice();
        float taxPerItem = entityDiscount.getTaxPerItem();

        int quantityNOTEligibleForDiscount = orderedQuantity%discountQuantity;
        int batchQuantity = (orderedQuantity/discountQuantity);
        float totalTax =orderedQuantity*taxPerItem;

        if(orderedQuantity<discountQuantity){
            return (orderedQuantity*itemPrice)+totalTax;
        }

        //If whole order is eligible for discount
        //then calculate batch price andd
        //add tax per item
        float batchCalculation = batchQuantity*discountAmount;

        if(quantityNOTEligibleForDiscount ==0){
            return batchCalculation+totalTax;
        }

        return batchCalculation
                +(quantityNOTEligibleForDiscount*itemPrice)
                +totalTax;

    }

}
