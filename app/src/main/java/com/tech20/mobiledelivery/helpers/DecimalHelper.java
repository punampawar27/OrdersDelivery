package com.tech20.mobiledelivery.helpers;


import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DecimalHelper {

    public static final String PATTERN="##.##";
    private static DecimalFormat decimalFormat = null;


    public static String format(String pattern,Double number){
        if(decimalFormat == null){
            decimalFormat = new DecimalFormat(pattern);
            decimalFormat.setRoundingMode(RoundingMode.UP);
        }
        return decimalFormat.format(number);
    }

    public static String format(String pattern,float number){
        if(decimalFormat == null){
            decimalFormat = new DecimalFormat(pattern);
        }
        return decimalFormat.format(number);
    }
}
