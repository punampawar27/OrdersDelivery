package com.tech20.mobiledelivery.factory.discountfactory;


import com.tech20.mobiledelivery.models.Entities.EntityDiscount;

public interface IDiscount {

    /*
    "DiscountType":"Mix and Match : Sales Price",
      "DiscountAmount":15.0000000,
      "DiscountQuantity":2

       "PromotionType":"%",
      "PromotionValue":10.0000000,
      "PromotionStartDate":"2017-12-28T00:00:00",
      "PromotionEndDate":"2018-01-29T00:00:00"
     */
    float calculatePriceAfterDiscountAndTax(EntityDiscount entityDiscount);
}
