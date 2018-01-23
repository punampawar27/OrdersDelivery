package com.tech20.mobiledelivery.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.tech20.mobiledelivery.ApplicationMobileDelivery;
import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.adapters.AdapterHomeScreen;
import com.tech20.mobiledelivery.contracts.ContractHome;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dataentities.DbModelDriver;
import com.tech20.mobiledelivery.database.dataentities.DbModelStore;
import com.tech20.mobiledelivery.dialogs.DialogAlert;
import com.tech20.mobiledelivery.dialogs.DialogAlert.IDialogLogoutCallback;
import com.tech20.mobiledelivery.dialogs.DialogChangePassword;
import com.tech20.mobiledelivery.enums.EnumAppState;
import com.tech20.mobiledelivery.enums.EnumDialogType;
import com.tech20.mobiledelivery.enums.EnumOrderStatus;
import com.tech20.mobiledelivery.executors.AppExecutor;
import com.tech20.mobiledelivery.helpers.AndroidVersionUtil;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.HelperSaveBitmap;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.helpers.ToastUtils;
import com.tech20.mobiledelivery.location.LocationHelper;
import com.tech20.mobiledelivery.models.ModelHome;
import com.tech20.mobiledelivery.presenter.PresenterHome;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.services.DeleteDataForOffDutyIntentService;
import com.tech20.mobiledelivery.services.FcmTokenScyncService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ActivityHome extends BaseActivity implements ContractHome.IViewHome,OnClickListener,IDialogLogoutCallback,DialogChangePassword.IDialogChangePasswordCallback {

    private ContractHome.IPresenterHome iPresenterHome = null;

    private TextView mTxtShopAddress = null;
    private DrawerLayout mDrawerLayout = null;
    private NavigationView navigationView = null;
    private ActionBarDrawerToggle actionBarToggle = null;

    private TextView txtNavUsername =null ,txtNavEmail = null;
    private ImageView imageViewProfileHeader = null;
    private RecyclerView recyclerView = null;



    private DbModelStore store = null;
    private DbModelDriver driver = null;
    private AdapterHomeScreen adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.drawer_layout);
        initView();
        init();
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarToggle.syncState();

        navigationView.getMenu().findItem(R.id.nav_version_name).setTitle(AndroidVersionUtil.getVersionName(getApplicationContext()));
    }

    private void init(){
        iPresenterHome = new PresenterHome();
        iPresenterHome.attach(this);
        iPresenterHome.showAddress(DatabaseHouse.getSingleTon(getApplicationContext()), PreferenceUtils.getINSTANCE(getApplicationContext()));

        adapter = new AdapterHomeScreen(addHomeOptionsList(),onRecyclerItemClick);
        recyclerView.setAdapter(adapter);

    }

    private void initView(){

        setupToolbar((Toolbar)findViewById(R.id.detail_toolbar),getString(R.string.title_green_force_mobile));
        mTxtShopAddress = (TextView)findViewById(R.id.tv_shop_address);
        ((ImageView)findViewById(R.id.call)).setOnClickListener(this);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        actionBarToggle = new DrawerToggle(this,mDrawerLayout,R.string.open_drawer,R.string.closed_drawer);

        mDrawerLayout.addDrawerListener(actionBarToggle);
        navigationView.setNavigationItemSelectedListener(navigationListener);

    }

    private List<AdapterHomeScreen.HomeContentItem> addHomeOptionsList(){

        String[] arrOptionsName = new String[]{getString(R.string.home_option_deliveries),
                getString(R.string.home_option_inventory),
                getString(R.string.home_option_customers),
                getString(R.string.home_option_cash_drawer)};
        int[] arrOptionsIcons = new int[]{R.drawable.deliveryicon,
                                        R.drawable.inventoryicon,
                                        R.drawable.customericon,
                                        R.drawable.cashicon};


        List<AdapterHomeScreen.HomeContentItem> list = new ArrayList();
        for(int i=0;i<arrOptionsName.length;i++){
            AdapterHomeScreen.HomeContentItem item = new AdapterHomeScreen.HomeContentItem();
            item.setItemName(arrOptionsName[i]);
            item.setItemImageSource(arrOptionsIcons[i]);
            list.add(item);
        }
        Log.d(Constants.LogConstants.TAG_WASTE,"ListSize:"+list.size());
        return list;

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_off_duty:
                    offDuty();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                break;

        }
        return true;
    }

    private  void offDuty(){
        iPresenterHome.isAssignedDeliverdExist(iGetOrderStatus,DatabaseHouse.getSingleTon(getApplicationContext()),
                PreferenceUtils.getINSTANCE(getApplicationContext()));
    }

    private OnClickListener onRecyclerItemClick = (v)->{


        int position = Integer.parseInt(String.valueOf(v.getTag()));
        if(getString(R.string.home_option_deliveries).equalsIgnoreCase(adapter.getItem(position).getItemName())){

            Intent intent = new Intent(this,ActivityOrders.class);
            startActivity(intent);

        }else if(getString(R.string.home_option_customers).equalsIgnoreCase(adapter.getItem(position).getItemName())){

            Intent intent = new Intent(this,ActivityCustomers.class);
            startActivity(intent);

        }else if(getString(R.string.home_option_cash_drawer).equalsIgnoreCase(adapter.getItem(position).getItemName())){
            Intent intent = new Intent(this,ActivityCashDrawer.class);
            startActivity(intent);

        }else if(getString(R.string.home_option_inventory).equalsIgnoreCase(adapter.getItem(position).getItemName())){
            Intent intent = new Intent(this,ActivityInventory.class);
            startActivity(intent);
        }

    };
    private void showDialogOffDuty(){

        Bundle bundle = new Bundle();
        bundle.putString(DialogAlert.EXTRA_TITLE,getString(R.string.dialog_offduty_title));
        bundle.putString(DialogAlert.EXTRA_MESSAGE,getString(R.string.dialog_offduty_message));
        bundle.putString(DialogAlert.EXTRA_POSITIVE_TEXT,getString(R.string.yes));
        bundle.putString(DialogAlert.EXTRA_NEGATIVE_TEXT,getString(R.string.no));
        bundle.putInt(DialogAlert.EXTRA_TYPE, EnumDialogType.DIALOG_OFFDUTY.getValue());

        DialogAlert dialogOffDuty = new DialogAlert();
        dialogOffDuty.setArguments(bundle);
        dialogOffDuty.show(getFragmentManager(),null);

    }
    public void showDialogOrdersStatusMessage(){

        Bundle bundle = new Bundle();
        bundle.putString(DialogAlert.EXTRA_TITLE,getString(R.string.dialog_pending_orders_title));
        bundle.putString(DialogAlert.EXTRA_MESSAGE,getString(R.string.dialog_offduty_order_status_message));
        bundle.putString(DialogAlert.EXTRA_POSITIVE_TEXT,getString(R.string.ok));
        bundle.putInt(DialogAlert.EXTRA_TYPE, EnumDialogType.DIALOG_ORDER_EXIST.getValue());

        DialogAlert dialogOffDuty = new DialogAlert();
        dialogOffDuty.setArguments(bundle);
        dialogOffDuty.show(getFragmentManager(),null);


    }



    private void navHeader(NavigationView navView){

        View view = navView.getHeaderView(0);

        txtNavEmail = ((TextView)view.findViewById(R.id.username));
        txtNavEmail.setText(driver.getFirst_name());

        txtNavUsername = ((TextView)view.findViewById(R.id.textemail));
        txtNavUsername.setText(driver.getLast_name());

        imageViewProfileHeader = ((ImageView)view.findViewById(R.id.imageViewProfileHeader));
        imageViewProfileHeader.setOnClickListener(this);

        applyImage(imageViewProfileHeader,((ApplicationMobileDelivery)getApplicationContext()).getDownloadImageDir(),
                PreferenceUtils.getINSTANCE(getApplicationContext())
                        .getString(Constants.PreferenceConstants.PROFILE_PIC_NAME,getProfilePicFileName()));
    }



    @Override
    public void showAddress(DbModelStore store,DbModelDriver driver) {

        this.store=store;
        this.driver = driver;
        navHeader(navigationView);
        //Received Store
        mTxtShopAddress.setText(Html.fromHtml("<B>" + store.getShop_name() + "</B>"
                + "<br>" + store.getAddress_line_1() + (TextUtils.isEmpty(store.getAddress_line_2())?"":", " +store.getAddress_line_2())
                + "<br>" + store.getCity() + " " + store.getPostal_code()));
    }

    @Override
    public void onDutyStatusChanged(EnumAppState enumAppState) {

        if(EnumAppState.LOGGEDIN_OFF_DUTY == enumAppState){
            Intent intentService = new Intent(this,DeleteDataForOffDutyIntentService.class);
            startService(intentService);

            LocationHelper.getInstance(getApplicationContext()).stopLocation();

            Intent intent = new Intent(this,ActivityCurrentStatus.class);
            startActivity(intent);
            finish();
        }

    }

    private void startImagePicker(){
        Intent intentImagePicker = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        this.startActivityForResult(intentImagePicker, Constants.REQUESTCODES.REQUEST_IMAGE_PICKER_REQUEST_CODE);

    }

    @Override
    public void onResponseFailed(ErrorMessage errorMessage) {
        ToastUtils.showSnackBar(findViewById(android.R.id.content),errorMessage.getMessage());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.call:
                String strPhoneNumber = store.getContact_no();
                if (TextUtils.isEmpty(strPhoneNumber)) {
                    ToastUtils.showSnackBar(v,getString(R.string.contact_not_available));
                } else {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + strPhoneNumber));
                    startActivity(intent);
                }
                break;
            case R.id.imageViewProfileHeader:
                startImagePicker();
                break;
        }
    }

    @Override
    public void onDialogAlertButtonPositive(EnumDialogType enumType) {

                if(enumType == EnumDialogType.DIALOG_OFFDUTY){
                    iPresenterHome.goOffDuty(PreferenceUtils.getINSTANCE(getApplicationContext()),null,getString(R.string.prg_messgae));
                }else if(enumType == EnumDialogType.DIALOG_ORDER_EXIST){
                    Log.d("WASTE","EnumDialogType.DIALOG_ORDER_EXIST: "+enumType.getValue());
                }

    }

    @Override
    public void onDialogAlertButtonNegative(EnumDialogType enumType) {
        if(enumType == EnumDialogType.DIALOG_OFFDUTY){
            //Do nothing
        }
    }

    @Override
    public void onDialogChangePasswordSuccess() {
        //Password changed
        ToastUtils.showSnackBar(findViewById(android.R.id.content),getString(R.string.passoword_success));
    }

    @Override
    public void onDialogChangePasswordFail(ErrorMessage errorMessage) {
        //do nothing Paswword change failed
    }

    private class DrawerToggle extends ActionBarDrawerToggle{

        public DrawerToggle(Activity activity, DrawerLayout drawerLayout,int openDrawerContentDescRes, int closeDrawerContentDescRes) {
            super(activity, drawerLayout, openDrawerContentDescRes, closeDrawerContentDescRes);
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
        }
    }

    private NavigationView.OnNavigationItemSelectedListener navigationListener = (MenuItem menu)->{

        mDrawerLayout.closeDrawers();
        switch (menu.getItemId()){
            case R.id.nav_my_profile:
                ToastUtils.showSnackBar(findViewById(android.R.id.content),"My profile");
                gotoActivityMyProfile();
                break;
            case R.id.nav_change_password:
                showDialogChangePassword();
                break;
            case R.id.nav_faq:
                showFaq();
                break;
            case R.id.nav_off_duty:
                offDuty();
                break;
        }
        return true;
    };

    private void gotoActivityMyProfile(){

        Intent intent = new Intent(this,ActivityMyProfile.class);
        intent.putExtra(ActivityMyProfile.EXTRA_DRIVER,driver);
        intent.putExtra(ActivityMyProfile.EXTRA_ROUT_ID,
                PreferenceUtils.getINSTANCE(this).getString(Constants.PreferenceConstants.ROUTE_ID));
        startActivity(intent);
    }


    private void showDialogChangePassword(){

        DialogChangePassword dialogChangePassword =  DialogChangePassword.getInstance(driver.getDriverId());
        dialogChangePassword.show(getFragmentManager(),"");

    }

    private void showFaq(){
        Intent intent = new Intent(this,ActivityFaq.class);
        startActivity(intent);
    }
    private String getProfilePicFileName(){
        return Constants.FILECONSTANTS.PROFILE_PIC_PREPEND+driver.getDriverId();
    }
    private void saveImage(Uri imageUri) {

        PreferenceUtils preferenceUtils = PreferenceUtils.getINSTANCE(getApplicationContext());

        String fname = preferenceUtils.getString(Constants.PreferenceConstants.PROFILE_PIC_NAME,getProfilePicFileName());

        HelperSaveBitmap saveBitmap = new HelperSaveBitmap(iSavedBitmap,imageUri, this,getFilesDir().getAbsolutePath(),fname);
        saveBitmap.asynchronusSave();
    }

    private HelperSaveBitmap.ISavedBitmap iSavedBitmap = new HelperSaveBitmap.ISavedBitmap() {
        @Override
        public void savedBitmap() {
            applyImage(imageViewProfileHeader,((ApplicationMobileDelivery)getApplicationContext()).getDownloadImageDir(),
                    PreferenceUtils.getINSTANCE(getApplicationContext())
                            .getString(Constants.PreferenceConstants.PROFILE_PIC_NAME,getProfilePicFileName()));
        }

        @Override
        public void savedBitmapFailed(Exception exc) {

        }
    };
    private void applyImage(final ImageView img,String filePath,String fileName){

        if(new File(filePath,fileName).exists()){
            AppExecutor.getINSTANCE().getDiskIO().execute(new Runnable() {
                @Override
                public void run() {

                    Bitmap bmp = BitmapFactory.decodeFile(filePath+File.separator+fileName);

                    AppExecutor.getINSTANCE().getMainThread().execute(()->{
                        img.setImageBitmap(bmp);
                    });
                }
            });
        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case Constants.REQUESTCODES.REQUEST_IMAGE_PICKER_REQUEST_CODE:
                if(resultCode == RESULT_OK){
                    final Uri imageUri = data.getData();
                    saveImage(imageUri);
                }else{
                    ToastUtils.showSnackBar(findViewById(android.R.id.content), getString(R.string.not_picked_image));
                }

                break;
        }
    }


    ModelHome.IGetOrderStatus iGetOrderStatus =  new ModelHome.IGetOrderStatus() {
        @Override
        public void isOrderExist(boolean status) {
            if(status){
                showDialogOrdersStatusMessage();
            }else{
                showDialogOffDuty();
            }
        }
    };



}
