package com.tech20.mobiledelivery.helpers;


import com.tech20.mobiledelivery.retrofitclient.extrainventoryclient.ExtraInventoryResponse;

public class CalculateTaxedPrice {

    public static float taxedPrice(ExtraInventoryResponse extraItem){

        float itemPrice = Float.parseFloat(extraItem.getItemPrice());
        float taxedPercentage = Float.parseFloat(DecimalHelper.format(DecimalHelper.PATTERN,
                Float.parseFloat(extraItem.getTaxPercentage())))/100;
        float taxedPrice =0f;

        if(extraItem.getIsTaxApplied()){
            taxedPrice = itemPrice* taxedPercentage;
        }

        return taxedPrice;

    }

    public static float totalPriceAfterTax(ExtraInventoryResponse extraItem){
        float itemPrice = Float.parseFloat(extraItem.getItemPrice());
        float taxedPrice =taxedPrice(extraItem);
        return (itemPrice+taxedPrice);
    }
}
