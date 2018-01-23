package com.tech20.mobiledelivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.contracts.ContractCurrentStatus;
import com.tech20.mobiledelivery.customviews.CustomSwipeButton;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.dialogs.DialogAlert;
import com.tech20.mobiledelivery.enums.EnumAppState;
import com.tech20.mobiledelivery.enums.EnumDialogType;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.NetworkHelper;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.helpers.ToastUtils;
import com.tech20.mobiledelivery.location.LocationHelper;
import com.tech20.mobiledelivery.presenter.PresenterCurrentStatus;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.services.DeleteDataForLogoutIntentService;


public class ActivityCurrentStatus extends BaseActivity implements ContractCurrentStatus.IViewCurrentStatus,DialogAlert.IDialogLogoutCallback {

    private ContractCurrentStatus.IPresenterCurrentStatus iPresenterCurrentStatus = null;
    private CustomSwipeButton swipeButton = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_duty);
        //Set status of the applications
        PreferenceUtils.getINSTANCE(getApplicationContext())
                .putInteger(Constants.PreferenceConstants.APP_STATE,EnumAppState.LOGGEDIN_OFF_DUTY.getValue());

        isFetchLocation(false);
        initView();
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyView();
    }

    private void destroyView(){
        iPresenterCurrentStatus.dettach();
    }

    private void initView(){

        setupToolbar((Toolbar)findViewById(R.id.detail_toolbar),getString(R.string.title_current_status));
        setDisplayHomeAsUpEnabled(false);

        iPresenterCurrentStatus = new PresenterCurrentStatus();
        iPresenterCurrentStatus.attach(this);
        swipeButton = (CustomSwipeButton) findViewById(R.id.swipe_btn);

        swipeButton.setOnStateChangeListener(onStateChangeListener);

    }
    private void init(){

    }
    @Override
    public void onSwipedToDutySuccess() {

        //Start Fetching Location
        LocationHelper.getInstance(getApplicationContext()).startLocation();

        Log.d(Constants.LogConstants.TAG_WASTE,"View onSwipedToDutySuccess");
        startActivity(new Intent(this,ActivityHome.class));
        finish();
    }

    @Override
    public void onLogoutSuccess() {

       Intent intentDeleteAllTables = new Intent(getApplicationContext(), DeleteDataForLogoutIntentService.class);
       startService(intentDeleteAllTables);

       Intent intentActivityHome = new Intent(this,ActivityLogin.class);
       startActivity(intentActivityHome);
       finish();
    }

    @Override
    public void onResponseFailed(ErrorMessage errorMessage) {
        swipeButton.moveSlideBack();

        ToastUtils.showSnackBar(findViewById(android.R.id.content),errorMessage.getMessage());
    }

    private OnStateChangeListener onStateChangeListener = (boolean active)->{
        Log.d("onswip",""+active);

            swipeButton.setEnabledDrawable(getResources().getDrawable(R.drawable.circle));
            swipeButton.setGravity(Gravity.RIGHT);
        iPresenterCurrentStatus.onSwipedToOnDuty(PreferenceUtils.getINSTANCE(getApplicationContext()), EnumAppState.LOGGEDIN_ON_DUTY);

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_duty, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            showLogOutDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showLogOutDialog(){

        Bundle bundle = new Bundle();
        bundle.putString(DialogAlert.EXTRA_TITLE,getString(R.string.logout));
        bundle.putString(DialogAlert.EXTRA_MESSAGE,getString(R.string.current_status_logout_message));
        bundle.putString(DialogAlert.EXTRA_POSITIVE_TEXT,getString(R.string.yes));
        bundle.putString(DialogAlert.EXTRA_NEGATIVE_TEXT,getString(R.string.no));
        bundle.putInt(DialogAlert.EXTRA_TYPE, EnumDialogType.DIALOG_LOGOUT.getValue());

        DialogAlert dialogLogout = new DialogAlert();
        dialogLogout.setCancelable(true);
        dialogLogout.setArguments(bundle);
        dialogLogout.show(getFragmentManager(),"");

    }


    @Override
    public void onDialogAlertButtonPositive(EnumDialogType enumDialogType) {

        switch (enumDialogType){
            case DIALOG_LOGOUT:
                if(NetworkHelper.isOnline(getApplicationContext())){
                    iPresenterCurrentStatus.onClickLogout(DatabaseHouse.getSingleTon(getApplicationContext()),
                            PreferenceUtils.getINSTANCE(getApplicationContext()));

                }else{
                    NetworkHelper.noNetworkToast(getApplicationContext());
                }
                break;

        }
    }

    @Override
    public void onDialogAlertButtonNegative(EnumDialogType enumDialogType) {
        switch (enumDialogType) {
            case DIALOG_LOGOUT:
                //DoNothing
                break;
        }
    }
}
