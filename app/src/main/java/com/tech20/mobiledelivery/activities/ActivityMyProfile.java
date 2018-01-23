package com.tech20.mobiledelivery.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.database.dataentities.DbModelDriver;


public class ActivityMyProfile extends BaseActivity {

    public static final String EXTRA_DRIVER="EXTRA_DRIVER";
    public static final String EXTRA_ROUT_ID="EXTRA_ROUT_ID";

    private TextView textName, textUsername, textRouteID, textLicenceNumber, textVehicleNumber, textContactNumber,
            textAddressLine1, textAddressLine2, textCity, textState, textZipCode, textCountry;

    private DbModelDriver entityDriver = null;
    private String routId = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);


        setupToolbar((Toolbar)findViewById(R.id.detail_toolbar),getString(R.string.title_my_profile));
        setHomeButtonEnabled(false);
        initView();
        init();
    }

    private void init(){
        entityDriver = getIntent().getExtras().getParcelable(EXTRA_DRIVER);
        routId = getIntent().getExtras().getString(EXTRA_ROUT_ID);

        textName.setText(entityDriver.getFirst_name() + " " + entityDriver.getLast_name());
        textUsername.setText(entityDriver.getUsername());
        textRouteID.setText(routId);
        textLicenceNumber.setText(entityDriver.getLicenceNo());
        textVehicleNumber.setText(entityDriver.getVehicleNumber());
        textContactNumber.setText(entityDriver.getMobileNumber());
        textAddressLine1.setText(entityDriver.getAddressLine1());
        textAddressLine2.setText(entityDriver.getAddressLine2());
        textCity.setText(entityDriver.getCity());
        textState.setText(entityDriver.getState());
        textZipCode.setText("" + entityDriver.getZipcode());
        textCountry.setText(entityDriver.getCountry());
    }

    private void initView(){
        textName = (TextView) findViewById(R.id.textName);
        textUsername = (TextView) findViewById(R.id.textUsername);
        textRouteID = (TextView) findViewById(R.id.textRouteID);
        textLicenceNumber = (TextView) findViewById(R.id.textLicenceNumber);
        textVehicleNumber = (TextView) findViewById(R.id.textVehicleNumber);
        textContactNumber = (TextView) findViewById(R.id.textContactNumber);
        textAddressLine1 = (TextView) findViewById(R.id.textAddressLine1);
        textAddressLine2 = (TextView) findViewById(R.id.textAddressLine2);
        textCity = (TextView) findViewById(R.id.textCity);
        textState = (TextView) findViewById(R.id.textState);
        textZipCode = (TextView) findViewById(R.id.textZipCode);
        textCountry = (TextView) findViewById(R.id.textCountry);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
