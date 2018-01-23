package com.tech20.mobiledelivery.utils;

import android.util.Log;

import com.tech20.mobiledelivery.enums.EnumRouteStatus;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;

public class UtilSaveRouteStatus {

    public static void saveRoutStatus(PreferenceUtils prefUtils,String routeId,String routStatus){

        Log.d(Constants.LogConstants.TAG_WASTE,"RouteId:"+routeId+" RoutStatus:"+routStatus);
        prefUtils.putString(Constants.PreferenceConstants.ROUTE_ID,routeId!=null?routeId.trim():routeId);
        prefUtils.putString(Constants.PreferenceConstants.ROUTE_STATUS,routStatus);
    }
}
