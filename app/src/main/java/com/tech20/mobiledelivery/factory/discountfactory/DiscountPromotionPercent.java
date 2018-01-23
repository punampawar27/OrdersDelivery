package com.tech20.mobiledelivery.factory.discountfactory;


import com.tech20.mobiledelivery.models.Entities.EntityDiscount;
import com.tech20.mobiledelivery.utils.UtilsValidatePromotion;


public class DiscountPromotionPercent implements IDiscount{


    @Override
    public float calculatePriceAfterDiscountAndTax(EntityDiscount entityDiscount) {

        float itemPrice = entityDiscount.getItemPrice();
        float promotionAmount = itemPrice*(entityDiscount.getPromotionAmount()/100);
        int orderedQuantity = entityDiscount.getOrderedQuantity();
        float taxPerItem = entityDiscount.getTaxPerItem();


        if(UtilsValidatePromotion.validatePromotion(entityDiscount.getPromotionStartDate(),
                entityDiscount.getPromotionEndDate())){
            return orderedQuantity*((itemPrice+taxPerItem)-promotionAmount);
        }
        return orderedQuantity*(itemPrice+taxPerItem);
    }
}
