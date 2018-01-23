package com.tech20.mobiledelivery.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.tech20.mobiledelivery.ApplicationMobileDelivery;
import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.enums.EnumAppState;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.location.LocationHelper;

import java.util.Arrays;


public class ActivitySplashScreen extends AppCompatActivity {

    private boolean isAllPermissionsGrant = true;
    private String []strRequiredPermissions = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.CALL_PHONE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.VIBRATE};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView tvSplash = (ImageView) findViewById(R.id.iv_splash_logo);
        Animation animation = AnimationUtils.loadAnimation(ActivitySplashScreen.this, android.R.anim.fade_in);
        animation.setDuration(4000);
        animation.setAnimationListener(animationListener);
        tvSplash.startAnimation(animation);

        //Initializing database
        DatabaseHouse.getSingleTon(getApplicationContext());
    }



    private Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

            showPermissionMessage();


        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        Log.d(Constants.LogConstants.TAG_WASTE,"requestCode:"+requestCode+" permission:"+ Arrays.toString(permissions)+" grantResult:"+Arrays.toString(grantResults));
        if(requestCode == Constants.RequestPermissionConstant.request_all_permission && grantResults.length>0){



            for(int i=0;i<grantResults.length;i++){
                if(grantResults[i]<0){
                    isAllPermissionsGrant = false;
                }
            }

            if(!isAllPermissionsGrant){
                showClosingAppMessge();
            }else{
               gotoLogin();
            }

        }

    }

    private void checkForPermission(){

        askCoarseLocationPermission();
    }

    private void askCoarseLocationPermission(){

        ActivityCompat.requestPermissions(this,
                strRequiredPermissions,
                Constants.RequestPermissionConstant.request_all_permission);
    }

    private void showPermissionMessage(){

        if(!isAllPermissionsGranted()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getResources().getString(R.string.permission_message));
            builder.setPositiveButton(R.string.ok,onClickPositiveButtonPermissionMessage);
            builder.setCancelable(false);
            builder.create().show();
        }else{
            gotoLogin();
        }
    }

    private void showClosingAppMessge(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.closing_message));
        builder.setPositiveButton(R.string.ok,onClickPositiveButtonClosingApp);
        builder.setCancelable(false);
        builder.create().show();
    }

    private boolean isAllPermissionsGranted(){

        for(String strPermission:strRequiredPermissions){
            if(ContextCompat.checkSelfPermission(this,strPermission)!= PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }

        return true;
    }
    private AlertDialog.OnClickListener onClickPositiveButtonPermissionMessage = new AlertDialog.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            checkForPermission();
        }
    };

    private AlertDialog.OnClickListener onClickPositiveButtonClosingApp = new AlertDialog.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            finish();
        }
    };

    private void gotoLogin(){

        EnumAppState appState = ((ApplicationMobileDelivery)getApplication()).getAppState();
        if(appState.equals(EnumAppState.LOGGEDOUT)){
            //Looged out
            startActivity(new Intent(getApplicationContext(),ActivityLogin.class));

        }else if(appState.equals(EnumAppState.LOGGEDIN_OFF_DUTY)){
            //Logged in but Off duty
            startActivity(new Intent(getApplicationContext(),ActivityCurrentStatus.class));

        }else{
            Log.d(Constants.LogConstants.TAG_WASTE,"Go To ActivityHome");
            //On Duty
            startActivity(new Intent(getApplicationContext(),ActivityHome.class));

        }
        finish();

    }
}
