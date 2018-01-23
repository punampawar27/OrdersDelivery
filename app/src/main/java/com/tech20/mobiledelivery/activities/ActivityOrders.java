package com.tech20.mobiledelivery.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.adapters.AdapterOrders;
import com.tech20.mobiledelivery.contracts.ContractOrders;
import com.tech20.mobiledelivery.contracts.ContractOrders.IViewOrders;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dataentities.DbModelOrders;
import com.tech20.mobiledelivery.enums.EnumOrderStatus;
import com.tech20.mobiledelivery.enums.EnumPushNotificationType;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.NetworkHelper;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.helpers.ToastUtils;
import com.tech20.mobiledelivery.helpers.UtilDateFormat;
import com.tech20.mobiledelivery.presenter.PresneterOrders;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.ordersclient.Order;
import com.tech20.mobiledelivery.retrofitclient.ordersclient.OrdersResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ActivityOrders extends BaseActivity implements IViewOrders {

    //Database not implementedyet for Orders
    private ContractOrders.IPresenterOrders iPresenterOrders = null;
    private RecyclerView recyclerView = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_orders);
        initView();
        getOrders();
    }

    private void initView(){

        setupToolbar(findViewById(R.id.detail_toolbar),getString(R.string.title_deliveries));

        iPresenterOrders = new PresneterOrders();
        iPresenterOrders.attach(this);

        recyclerView = findViewById(R.id.recyclerViewOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

    }

    private void getOrders(){
        if(NetworkHelper.isOnline(this)){

            PreferenceUtils.getINSTANCE(getApplicationContext()).putBoolean(Constants.PreferenceConstants.IS_ORDER_REFRESHED,false);
            iPresenterOrders.getOrders(DatabaseHouse.getSingleTon(getApplicationContext()),
                    PreferenceUtils.getINSTANCE(getApplicationContext()),
                    getTodayDateString());
        }else{
            ToastUtils.showSnackBar(findViewById(android.R.id.content),getString(R.string.err_network));
        }

    }

    private void startOrderDetail(DbModelOrders order){

        Intent intent = new Intent(this,ActivityOrderDetails.class);
        intent.putExtra(ActivityOrderDetails.EXTRA_ORDER,order);
        startActivity(intent);

    }

    private String getTodayDateString(){

        return  UtilDateFormat.format(UtilDateFormat.yyyy_MM_dd_T_HH_mm_ss,
                Calendar.getInstance().getTime());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_refresh:
                getOrders();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_orders,menu);

        return true;
    }

    @Override
    public void onOrdersLoadedSuccess(List<DbModelOrders> listOrders) {
        Log.d(Constants.LogConstants.TAG_WASTE,"Number of Orders : "+(listOrders!=null?listOrders.size():0));

        recyclerView.setAdapter(new AdapterOrders(onClick,this,(listOrders!=null)?listOrders:new ArrayList <>()));

    }

    @Override
    public void onOrdersLoadedFail(ErrorMessage errorMessage) {
        ToastUtils.showSnackBar(findViewById(android.R.id.content), TextUtils.isEmpty(errorMessage.getMessage())?getString(R.string.err_network):errorMessage.getMessage());
    }

    private View.OnClickListener onClick = (View v)->{

        DbModelOrders orders = null;
        switch (v.getId()){
            case R.id.imgCall:
                orders = ((AdapterOrders)recyclerView.getAdapter()).getItem(Integer.parseInt(String.valueOf(v.getTag())));

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + orders.getContactNumber()));
                startActivity(intent);

                break;
            case R.id.imgMap:

                orders = ((AdapterOrders)recyclerView.getAdapter()).getItem(Integer.parseInt(String.valueOf(v.getTag())));
                showAddressOnMap(orders.getShippingAddress());
                break;

            case R.id.relParent:

                orders = ((AdapterOrders)recyclerView.getAdapter()).getItem(Integer.parseInt(String.valueOf(v.getTag())));

                //Only show order details if order is assigned or placed
                if(orders.getStatus() == EnumOrderStatus.Assigned.getValue() ||
                        orders.getStatus() == EnumOrderStatus.Placed.getValue() ||
                        orders.getStatus() == EnumOrderStatus.UnDelivered.getValue()){
                    startOrderDetail(orders);
                }

                break;
        }
    };

    private void showAddressOnMap(String shippingAddress){

        String uri = Constants.MAPCONST.MAP_STRING + shippingAddress;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(PreferenceUtils.getINSTANCE(this).getBoolean(Constants.PreferenceConstants.IS_ORDER_REFRESHED)){
            getOrders();
        }
        register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unRegister();
    }

    private void register(){
        registerReceiver(receiverPushNotificationBroadcast,new IntentFilter(Constants.INTENTACTIONS.ACTION_PUSH_RECEIVED));
    }

    private void unRegister(){
        unregisterReceiver(receiverPushNotificationBroadcast);
    }
    private BroadcastReceiver receiverPushNotificationBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Constants.INTENTEXTRAS.EXTRA_PUSHNOTIFICATOIN_FLAG
            getOrders();
        }
    };
}
