package com.tech20.mobiledelivery.factory.discountfactory;


import android.text.TextUtils;

import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.retrofitclient.extrainventoryclient.ExtraInventoryResponse;

public class FactoryDiscount {

//Types of Discount
    //MixMatch SalesPriceOff
    //MixMatch PercentageOff
    //MixMatch Bulk
    //Promotion Percentage
    //Promotion SalesPrice

    public static IDiscount getDiscount(ExtraInventoryResponse extraInv){

        if(TextUtils.isEmpty(extraInv.getPromotionType())){

            return new NoDiscount();

        }else if(Constants.DiscountConstants.PROMOTION_TYPE_DOLLAR.equalsIgnoreCase(extraInv.getPromotionType())){

            return new DiscountPromotionDollar();

        }else if(Constants.DiscountConstants.PROMOTION_TYPE_PERCENT.equalsIgnoreCase(extraInv.getPromotionType())){

            return new DiscountPromotionPercent();

        }else if(Constants.DiscountConstants.MIXMATCH_PERCENTAGEPRICE.equalsIgnoreCase(extraInv.getDiscountType())){

            return new DiscountMixmatchPerentageOff();

        }else if(Constants.DiscountConstants.MIXMATCH_SALESPRICE.equalsIgnoreCase(extraInv.getDiscountType())){

            return new DiscountMixmatchSalesPriceOff();

        }else if(Constants.DiscountConstants.BULK.equalsIgnoreCase(extraInv.getDiscountType())){
            return new DiscountBulk();
        }

            //UnRecognised Discount type should throw error of nullpointer
            return null;
    }
}
