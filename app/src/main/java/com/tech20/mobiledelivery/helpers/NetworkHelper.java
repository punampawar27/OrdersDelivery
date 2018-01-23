package com.tech20.mobiledelivery.helpers;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.tech20.mobiledelivery.R;

public class NetworkHelper {

/**
 * Checks if is online.
 * If it returns false, show toast using NetworkHelper.noNetworkToast(Context) methode
 * @param cxt the cxt
 * @return true, if is online
 */
public static boolean isOnline(Context cxt) {
		ConnectivityManager cm = (ConnectivityManager) cxt
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo(); 
		
		if (netInfo != null && netInfo.isAvailable() && netInfo.isConnected()) { 
			Log.d("", "mode is online");
			return true;
		}
		return false;
	}
	
	/**
	 *not Implemented yet 
	 */	
	public static void noNetworkDialog(){
		
	}
	public static void noNetworkToast(Context context){
		Toast.makeText(context, getNoConnectionString(context), Toast.LENGTH_LONG).show();
	}

	public static String getNoConnectionString(Context context){

		return context.getResources().getString(R.string.no_connection);
	}

	public static boolean isGpsEnabled(Context context){
		LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}
}





















