package com.tech20.mobiledelivery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dataentities.DbModelCashDrawer;
import com.tech20.mobiledelivery.database.dataentities.DbModelOrders;
import com.tech20.mobiledelivery.dialogs.DialogChangePassword;
import com.tech20.mobiledelivery.dialogs.DialogChangePassword.IDialogChangePasswordCallback;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.helpers.UtilDateFormat;
import com.tech20.mobiledelivery.models.ModelCashDrawer;
import com.tech20.mobiledelivery.models.ModelCustomers;
import com.tech20.mobiledelivery.models.ModelExtraInventory;
import com.tech20.mobiledelivery.models.ModelInventory;
import com.tech20.mobiledelivery.models.ModelOrders;
import com.tech20.mobiledelivery.models.ModelSaveDriverLocation;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.customersclient.CustomersResponse;
import com.tech20.mobiledelivery.retrofitclient.extrainventoryclient.ExtraInventoryResponse;
import com.tech20.mobiledelivery.retrofitclient.inventoryclient.InventoryResponse;
import com.tech20.mobiledelivery.retrofitclient.ordersclient.OrdersResponse;
import com.tech20.mobiledelivery.retrofitclient.savelocationclient.SaveLocationResponse;

import java.util.List;

public class TestActivity extends AppCompatActivity implements IDialogChangePasswordCallback{

    private void saveFakeHeaders(){

        /*
        (Constants.UrlConstants.HEADER_API_KEY, preferenceUtils.getString(Constants.PreferenceConstants.API_KEY))
                        .addHeader(Constants.UrlConstants.HEADER_API_SECRET, preferenceUtils.getString(Constants.PreferenceConstants.API_SECRET))
                        .addHeader(Constants.UrlConstants.HEADER_SESSION_ID, preferenceUtils.getString(Constants.PreferenceConstants.UNIQUE_INSTALLATION_ID))
                        .addHeader(Constants.UrlConstants.HEADER_CONTENT_TYPE, "application/json")

                        Content-Type:
api_key:
api_secret:
session_id:
         */

        PreferenceUtils prefUtils = PreferenceUtils.getINSTANCE(getApplicationContext());

        prefUtils.putString(Constants.PreferenceConstants.API_KEY,"72d43a3e-5619-4093-90e8-ee4d62d6d8dd");
        prefUtils.putString(Constants.PreferenceConstants.API_SECRET,"D9D8B8230DA3E7A27EDA7C5ADB89F03A");
        prefUtils.putString(Constants.PreferenceConstants.UNIQUE_INSTALLATION_ID,"3521360640698481a21b46df0d0b1cf0653e42bf0ddff62");

        prefUtils.putString(Constants.PreferenceConstants.DRIVER_ID,"386");
        prefUtils.putString(Constants.PreferenceConstants.ROUTE_ID,"1561");
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.drawer_layout);

        saveFakeHeaders();
        hitInventory();

    }

    private void hitCashDrawer(){

        new ModelCashDrawer(new ModelCashDrawer.ICashDrawerLoaded() {


            @Override
            public void onCashDrawerLoadedSuccess(List<DbModelCashDrawer> listCashDrawer, String total) {
                Log.d("WASTE",""+listCashDrawer.get(0).getCash());
            }

            @Override
            public void onCashDrawerLoadedFail(ErrorMessage errorMessage) {

            }
        }).getCashDrawer(DatabaseHouse.getSingleTon(getApplicationContext()),
                PreferenceUtils.getINSTANCE(getApplicationContext()));
    }
    private void hitORders(){
        new ModelOrders(new ModelOrders.IOrdersLoaded() {


            @Override
            public void onOrdersLoadedSuccess(List<DbModelOrders> list) {
                Log.d("WASTE",""+list.get(0).getRouteId());

                Log.d("WASTE",""+list.get(0).getOrderId());

                Log.d("WASTE",""+list.get(0).getListNotes().get(0).getNote());
            }

            @Override
            public void onOrdersLoadedFail(ErrorMessage errorMessage) {

            }
        }).getOrdersFromAppServer(DatabaseHouse.getSingleTon(getApplicationContext()),
                PreferenceUtils.getINSTANCE(getApplicationContext()),
                UtilDateFormat.getToday());
    }

    private void hitExtraInventory(){
        new ModelExtraInventory(new ModelExtraInventory.IExtraInventoryLoaded() {
            @Override
            public void onExtraInventoryLoaded(List<ExtraInventoryResponse> listExtraInventory) {

                Log.d("WASTE",listExtraInventory.get(0).getItemName());
            }

            @Override
            public void onExtraInvenotryFail(ErrorMessage errorMessage) {

            }
        }).getExtraInventory(PreferenceUtils.getINSTANCE(getApplicationContext()));
    }
    private void hitInventory(){
        new ModelInventory(new ModelInventory.IInventoryLoad() {
            @Override
            public void onInventoryLoadSuccess(List<InventoryResponse> inventoryResponseList) {

                Log.d("WASTE",inventoryResponseList.get(0).getItemName());
            }

            @Override
            public void onInventoryLoadFail(ErrorMessage errorMessage) {
                Log.d("WASTE",errorMessage.getMessage());
            }
        }).getInventory(DatabaseHouse.getSingleTon(getApplicationContext()),
                PreferenceUtils.getINSTANCE(getApplicationContext()));
    }
    private void hitCustomers(){
        new ModelCustomers(new ModelCustomers.ICustomersLoad() {
            @Override
            public void onCustomersLoadedSuccess(List<CustomersResponse> list) {
                Log.d("WASTE",list.get(0).getCustomerName());
            }

            @Override
            public void onCustomersLoadedFail(ErrorMessage errorMessage) {

            }
        }).getCustomers(DatabaseHouse.getSingleTon(getApplicationContext()),PreferenceUtils.getINSTANCE(getApplicationContext()));
    }

    private void hitSaveLocation(){
        new ModelSaveDriverLocation(new ModelSaveDriverLocation.ISaveDriverLocation() {
            @Override
            public void onLocationSavedSuccess(SaveLocationResponse saveLocationResponse) {
                    Log.d("WASTE",saveLocationResponse.getIsDirtyFlag());
            }

            @Override
            public void onLocationSavedFail(ErrorMessage errorMessage) {

            }
        }).saveDriverLocation(12.23,45.23,UtilDateFormat.getToday(),false,
                PreferenceUtils.getINSTANCE(getApplicationContext()));
    }

    private void showDialogChangePassword(){

        DialogChangePassword dialogChangePassword =  DialogChangePassword.getInstance("20");
        dialogChangePassword.show(getFragmentManager(),"");

    }

    @Override
    public void onDialogChangePasswordSuccess() {

    }

    @Override
    public void onDialogChangePasswordFail(ErrorMessage errorMessage) {

    }

    @Override
    public void toggleProgressIndigator(boolean show) {

    }

    @Override
    public void toggleProgressIndigator(boolean show, String title, String message) {

    }
}
