package com.tech20.mobiledelivery.utils;


import com.tech20.mobiledelivery.helpers.UtilDateFormat;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class UtilsValidatePromotion {

    public static boolean validatePromotion(String strStartDate,String strEndDate){

        Date startDate = null,endDate = null;
        try {
            startDate = UtilDateFormat.format(UtilDateFormat.yyyy_MM_dd_T_HH_mm_ss,strStartDate);
            endDate = UtilDateFormat.format(UtilDateFormat.yyyy_MM_dd_T_HH_mm_ss,strEndDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(Calendar.getInstance().getTime().before(startDate) ||
                Calendar.getInstance().getTime().after(endDate)){
            return false;
        }

        return true;
    }
}
