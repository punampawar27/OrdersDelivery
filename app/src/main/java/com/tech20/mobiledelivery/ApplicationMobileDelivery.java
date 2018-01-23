package com.tech20.mobiledelivery;

import android.app.AlertDialog;
import android.app.Application;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;

import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dao.SqliteMasterDao;
import com.tech20.mobiledelivery.enums.EnumAppState;
import com.tech20.mobiledelivery.helpers.AndroidVersionUtil;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;

import java.io.File;
import java.util.List;

/**
 * Created by fidel25 on 11/29/2017.
 */

public class ApplicationMobileDelivery extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void toastDeviceConfig(boolean showDialog) {

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        if(showDialog){

            String msg = "Pixels:           " + displayMetrics.widthPixels + " X " + displayMetrics.heightPixels + "\n"
                    + "dpHeight:         " + dpHeight + "\n"
                    + "dpWidth:          " + dpWidth + "\n"
                    + "density:          " + displayMetrics.density + "\n"
                    + "densityDpi:       " + displayMetrics.densityDpi + "\n"
                    + "resourceFolder:   " + getResources().getString(R.string.screen_size)+"\n"
                    + "deviceId:         "+ AndroidVersionUtil.getUniqueDeviceIdentification(this);

            Log.d(Constants.LogConstants.TAG_WASTE, msg);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(msg);
            builder.create().show();
        }


        /*Crashlytics.setString("Pixels:", displayMetrics.widthPixels + " X " + displayMetrics.heightPixels);
        Crashlytics.setString("dpHeight:", ""+dpHeight);
        Crashlytics.setString("dpWidth:", ""+dpWidth);
        Crashlytics.setString("density:", ""+displayMetrics.density);
        Crashlytics.setString("densityDpi:", ""+displayMetrics.densityDpi);
        Crashlytics.setString("resourceFolder:", ""+getResources().getString(R.string.screen_size));*/


    }

    public EnumAppState getAppState(){
        return EnumAppState.getEnum(PreferenceUtils.getINSTANCE(getApplicationContext())
                .getInteger(Constants.PreferenceConstants.APP_STATE,EnumAppState.LOGGEDOUT.getValue()));
    }

    public void deleteAllPreference(){
        //This has to be execute on thread
        PreferenceUtils.getINSTANCE(getApplicationContext()).clearAllExceptFcmToken();
    }

    public void deleteAllTables(){
        SqliteMasterDao sqliteMasterDao = DatabaseHouse.getSingleTon(getApplicationContext()).getSqliteDao();

        List<String> list = sqliteMasterDao.getAllTables();
        DatabaseHouse.getSingleTon(getApplicationContext()).deleteAllTables(list);
    }

    public String getDownloadImageDir(){
        return getFilesDir().getAbsolutePath();
    }

    public String getLogFileDir(){
        return  Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"MobileDelivery";
    }
}
