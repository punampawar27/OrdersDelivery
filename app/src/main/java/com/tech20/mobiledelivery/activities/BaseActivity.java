package com.tech20.mobiledelivery.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.Task;
import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.contracts.IView;
import com.tech20.mobiledelivery.dialogs.DialogAlert;
import com.tech20.mobiledelivery.enums.EnumDialogType;
import com.tech20.mobiledelivery.helpers.AndroidVersionUtil;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.NetworkHelper;
import com.tech20.mobiledelivery.location.LocationHelper;

public class BaseActivity extends AppCompatActivity implements IView, DialogAlert.IDialogLogoutCallback {

    private ProgressDialog progressDialog = null;
    private boolean isFetchLocation = true;

    @Override
    protected void onResume() {
        super.onResume();

        if(AndroidVersionUtil.isBeforeMarshmallow()){
            showGpsDialogLollipop();
        }else{
            showGpsDialog();
        }

    }

    protected void isFetchLocation(boolean isFetchLocation){
        this.isFetchLocation = isFetchLocation;
    }
    protected void showSpinner() {
        if (progressDialog == null)
            progressDialog = ProgressDialog.show(this, "", getString(R.string.please_wait), true);
        else if(!progressDialog.isShowing())
            progressDialog.show();
    }

    protected void showSpinner(String title,String message) {
        if (progressDialog == null)
            progressDialog = ProgressDialog.show(this, title, message, true);
        else if(!progressDialog.isShowing())
            progressDialog.show();
    }

    protected void hideSpinner() {
        if (progressDialog == null)
            return;

        if (progressDialog.isShowing()){
            progressDialog.dismiss();
            progressDialog = null;
        }

    }

    @Override
    public void toggleProgressIndigator(boolean show) {

        if(show){
            showSpinner();
        }else{
            hideSpinner();
        }
    }

    @Override
    public void toggleProgressIndigator(boolean show, String title, String message) {
        if(show){
            showSpinner(title,message);
        }else{
            hideSpinner();
        }
    }

    protected void setupToolbar(Toolbar toolbar,String title){
        if (toolbar == null) {
            throw new Error("Can't find tool bar, did you forget to add it in Activity layout file?");
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
    }

    protected void setDisplayHomeAsUpEnabled(boolean showHomeAsUp){
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp);
    }

    protected void setHomeButtonEnabled(boolean enabled){
        getSupportActionBar().setHomeButtonEnabled(enabled);
    }

    private void showGpsDialog(){
        if(!isFetchLocation){
            LocationHelper.getInstance(getApplicationContext()).stopLocation();
            return;
        }

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(LocationRequest.create());
        builder.setAlwaysShow(true); //this is the key ingredient to show dialog always when GPS is off

        Task<LocationSettingsResponse> result =
                LocationServices.getSettingsClient(this).checkLocationSettings(builder.build());


        result.addOnCompleteListener((@NonNull Task<LocationSettingsResponse> task)->{

            try {
                LocationSettingsResponse response = task.getResult(ApiException.class);
                // All location settings are satisfied. The client can initialize location
                // requests here.
                LocationHelper.getInstance(getApplicationContext()).startLocation();

            }catch (ApiException exception){
                switch (exception.getStatusCode()){

                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the
                        // user a dialog.
                        try {
                            // Cast to a resolvable exception.
                            ResolvableApiException resolvable = (ResolvableApiException) exception;
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            resolvable.startResolutionForResult(
                                    BaseActivity.this,
                                    Constants.REQUESTCODES.REQUEST_CHECK_SETTINGS);

                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        } catch (ClassCastException e) {
                            // Ignore, should be an impossible error.
                        }
                        break;

                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        Log.d(Constants.LogConstants.TAG_REC_LOCATION,"SETTINGS_CHANGE_UNAVAILABLE");
                        break;

                }
            }
        });


    }

    private void showGpsDialogLollipop(){
        if(!NetworkHelper.isGpsEnabled(this)) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(getString(R.string.dialog_title_gps_off));
            alertDialog.setMessage(getString(R.string.dialog_message_turn_on_gps));

            // On pressing Settings button
            alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                    return;
                }
            });

            // on pressing cancel button
            alertDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialog.show();
        }
    }

    @Override
    public void onDialogAlertButtonPositive(EnumDialogType enumType) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }

    @Override
    public void onDialogAlertButtonNegative(EnumDialogType enumType) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case Constants.REQUESTCODES.REQUEST_CHECK_SETTINGS:

                final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);

                switch (resultCode) {
                    case Activity.RESULT_OK:
                        // All required changes were successfully made
                        LocationHelper.getInstance(getApplicationContext()).startLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to

                        break;
                    default:
                        break;
                }
                break;
        }
    }


}
