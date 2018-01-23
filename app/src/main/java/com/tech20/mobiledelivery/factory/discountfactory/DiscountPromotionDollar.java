package com.tech20.mobiledelivery.factory.discountfactory;

import com.tech20.mobiledelivery.models.Entities.EntityDiscount;
import com.tech20.mobiledelivery.utils.UtilsValidatePromotion;


public class DiscountPromotionDollar implements IDiscount{


    @Override
    public float calculatePriceAfterDiscountAndTax(EntityDiscount entityDiscount) {
        float itemPrice = entityDiscount.getItemPrice();
        float promotionAmount = entityDiscount.getPromotionAmount();
        int orderedQuantity = entityDiscount.getOrderedQuantity();
        float taxPerItem = entityDiscount.getTaxPerItem();


        if(UtilsValidatePromotion.validatePromotion(entityDiscount.getPromotionStartDate(),
                entityDiscount.getPromotionEndDate())){
            return orderedQuantity*((itemPrice+taxPerItem)-promotionAmount);
        }
        return orderedQuantity*(itemPrice+taxPerItem);
    }
}
