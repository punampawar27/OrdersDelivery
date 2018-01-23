package com.tech20.mobiledelivery.activities;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.adapters.AdapterOrderDetails;
import com.tech20.mobiledelivery.contracts.ContractOrderDetail;
import com.tech20.mobiledelivery.contracts.ContractOrderDetail.IViewOrderDetail;
import com.tech20.mobiledelivery.database.dataentities.DbModelNotes;
import com.tech20.mobiledelivery.database.dataentities.DbModelOrderInventory;
import com.tech20.mobiledelivery.database.dataentities.DbModelOrders;
import com.tech20.mobiledelivery.dialogs.DialogAlert;
import com.tech20.mobiledelivery.enums.EnumDialogType;
import com.tech20.mobiledelivery.enums.EnumPushNotificationType;
import com.tech20.mobiledelivery.enums.EnumRouteStatus;
import com.tech20.mobiledelivery.executors.AppExecutor;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.ItemOffsetDecoration;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.helpers.ToastUtils;
import com.tech20.mobiledelivery.models.Entities.EntitySelectedOrderInventory;
import com.tech20.mobiledelivery.presenter.PresenterOrderDetail;

import java.util.ArrayList;
import java.util.List;

import static com.tech20.mobiledelivery.enums.EnumDialogType.*;

public class ActivityOrderDetails extends BaseActivity implements IViewOrderDetail,DialogAlert.IDialogLogoutCallback {

    public static final String EXTRA_ORDER= "EXTRA_ORDER";


    private PreferenceUtils prefUtils = null;

    private List<EntitySelectedOrderInventory> listOrderItems = null;
    private List<EntitySelectedOrderInventory> listSelectedExtraItems = null;
    private AdapterOrderDetails adpaterOrderDetails = null;
    private float mOriginalTotal = 0f;

    private RecyclerView recOrderItems = null;
    private TextView txtValueTotal = null,txtDiscount=null;
    private FloatingActionButton floatRightTick,floatShopMore = null;
    private CheckBox chkIsVerified = null;

    private DbModelOrders dbModelOrders = null;
    private ContractOrderDetail.IPresenterOrderDetail iPresenterOrderDetail = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        initView();
        readBundleExtras(()->{
            setupToolbar(findViewById(R.id.detail_toolbar),dbModelOrders.getCustomerName());
            init();
        });
    }

    private void readBundleExtras(Runnable runnable){
        listOrderItems = new ArrayList<>();
        dbModelOrders = getIntent().getExtras().getParcelable(EXTRA_ORDER);
        mOriginalTotal = Float.parseFloat(dbModelOrders.getSubTotal())
                +Float.parseFloat(dbModelOrders.getDiscount())
                +(Boolean.parseBoolean(dbModelOrders.getIsTaxExempted())?0f:Float.parseFloat(dbModelOrders.getTax()));

        AppExecutor.getINSTANCE().getDiskIO().execute(()->{
            List<DbModelOrderInventory> orderItems = dbModelOrders.getListOrderItems();

            for(DbModelOrderInventory orderInventory:orderItems){
                EntitySelectedOrderInventory selected = new EntitySelectedOrderInventory();
                selected.setItemCode(orderInventory.getItemCode());
                selected.setItemName(orderInventory.getItemName());
                selected.setPackageSize(orderInventory.getPackageSize());
                selected.setPrice(orderInventory.getPrice());
                selected.setQuantity(String.valueOf((int)Float.parseFloat(orderInventory.getQuantity())));
                selected.setTax(orderInventory.getTax());
                selected.setDiscount(orderInventory.getDiscount());
                selected.setStockItemCode(orderInventory.getStockItemCode());

                listOrderItems.add(selected);

                AppExecutor.getINSTANCE().getMainThread().execute(runnable);
            }
        });
    }

    private void initView(){
        prefUtils = PreferenceUtils.getINSTANCE(this);
        iPresenterOrderDetail = new PresenterOrderDetail();
        iPresenterOrderDetail.attach(this);

        recOrderItems = findViewById(R.id.recOrderItems);
        txtValueTotal = findViewById(R.id.txtValueTotal);
        txtDiscount = findViewById(R.id.txtDiscount);

        floatRightTick = findViewById(R.id.floatRightTick);
        floatShopMore = findViewById(R.id.floatShopMore);
        chkIsVerified = findViewById(R.id.chkIsVerified);

        recOrderItems.setLayoutManager(new LinearLayoutManager(this));
        recOrderItems.addItemDecoration(new ItemOffsetDecoration((int)getResources().getDimension(R.dimen.list_item_space)));
        floatRightTick.setOnClickListener(onClick);
        floatShopMore.setOnClickListener(onClick);

        floatRightTick.setOnLongClickListener((View v)->{
            floatRightTick.setOnTouchListener(new FloatDragListener());
            return true;
        });

        floatShopMore.setOnLongClickListener((View v)->{
            floatShopMore.setOnTouchListener(new FloatDragListener());
            return true;
        });
    }

    private void init(){

        setAdapter(listOrderItems);
        chkIsVerified.setChecked(dbModelOrders.isVerified());
        showDiscount();

    }

    private void showDiscount(){

        String discount = dbModelOrders.getDiscount();

        if(TextUtils.isEmpty(discount) || Float.parseFloat(discount)<=0f){
            return;
        }

        txtDiscount.setVisibility(View.VISIBLE);
        txtDiscount.setText(getString(R.string.discount)+" "+getString(R.string.currency_sign)+" "+dbModelOrders.getDiscount());
    }

    private void toggleDoneButton(String routStatus){

        if(EnumRouteStatus.START.name().equalsIgnoreCase(routStatus)){
            floatRightTick.setVisibility(View.VISIBLE);
            floatShopMore.setVisibility(View.VISIBLE);
        }else {
            floatRightTick.setVisibility(View.INVISIBLE);
            floatShopMore.setVisibility(View.INVISIBLE);
        }
    }
    private void showNotes(ArrayList<DbModelNotes> list){

       /* if(list == null || list.isEmpty()){
            ToastUtils.showSnackBar(findViewById(android.R.id.content),getString(R.string.no_note_available));
        }*/
        Intent intent = new Intent(this,ActivityNotes.class);
        intent.putParcelableArrayListExtra(ActivityNotes.EXTRA_NOTES,list);
        startActivity(intent);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_order_details,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_notes:
               // ToastUtils.showSnackBar(findViewById(android.R.id.content),"Notes Feature Coming Soon");
                showNotes(new ArrayList<>(dbModelOrders.getListNotes()));
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    private View.OnClickListener onClick = (v)->{

        switch (v.getId()){
            case R.id.floatShopMore:
                showExtraInventory(listSelectedExtraItems);
                break;
            case R.id.floatRightTick:
                showOrderStatus(dbModelOrders);
                break;
        }
    };

    @Override
    public void showExtraInventory(List<EntitySelectedOrderInventory> listSelectedExtraItems){

        Intent intent = new Intent(this,ActivityExtraInventory.class);
        intent.putExtra(ActivityExtraInventory.EXTRA_TOTAL,mOriginalTotal);

        if(listSelectedExtraItems!=null)
        intent.putParcelableArrayListExtra(ActivityExtraInventory.EXTRA_SELECTED_INVENTORY,new ArrayList<>(listSelectedExtraItems));

        startActivityForResult(intent,
                                Constants.REQUESTCODES.REQUEST_CODE_FOR_RESULT_EXTRA_INVENTROY);
    }

    @Override
    public void showOrderStatus(DbModelOrders dbModelOrders){

        Intent intent = new Intent(this,ActivityDeliverOrder.class);
        intent.putExtra(ActivityDeliverOrder.EXTRA_ORDER,dbModelOrders);
        intent.putExtra(ActivityDeliverOrder.EXTRA_ORDERED_EXTRA_ITEMS,
                listSelectedExtraItems!=null?new ArrayList<>(listSelectedExtraItems):new ArrayList<>());

        startActivity(intent);
    }

    @Override
    public void showTotal(float total) {
        txtValueTotal.setText(getString(R.string.currency_sign)+" "+String.valueOf(total));
    }


    private class FloatDragListener implements View.OnTouchListener{

        float dX,dY;
        int lastEvent;

        private FloatDragListener(){

        }


        @Override
        public boolean onTouch(View view, MotionEvent event) {

            switch (event.getActionMasked()) {

                case MotionEvent.ACTION_DOWN:
                    dX = view.getX() - event.getRawX();
                    dY = view.getY() - event.getRawY();
                    lastEvent = event.getAction();
                    break;

                case MotionEvent.ACTION_UP:
                    lastEvent = event.getAction();
                    view.setOnTouchListener(null);
                    break;

                case MotionEvent.ACTION_MOVE:
                    view.setY(event.getRawY() + dY);
                    view.setX(event.getRawX() + dX);
                    lastEvent = event.getAction();
                    break;
            }
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            if(requestCode ==
                    Constants.REQUESTCODES.REQUEST_CODE_FOR_RESULT_EXTRA_INVENTROY){

                    listSelectedExtraItems =data.getExtras().getParcelableArrayList(ActivityExtraInventory.EXTRA_SELECTED_INVENTORY);
                    List<EntitySelectedOrderInventory> allOrderItems = new ArrayList<>();
                    allOrderItems.addAll(listOrderItems);

                    if(listSelectedExtraItems!=null)
                        allOrderItems.addAll(listSelectedExtraItems);

                    setAdapter(allOrderItems);
                }
        }
    }

    private void setAdapter(List<EntitySelectedOrderInventory> allOrderItems){

        adpaterOrderDetails = new AdapterOrderDetails(allOrderItems,getString(R.string.currency_sign),
                R.drawable.shape_white_border_grey,
                R.drawable.shape_blue_border_grey);
        recOrderItems.setAdapter(adpaterOrderDetails);

        //Calculate total with Order Total+ Order Tax+ total of added extra items with tax
        iPresenterOrderDetail.reCalculateTotal(mOriginalTotal,
                listSelectedExtraItems);
    }
    @Override
    public void onBackPressed() {
      if(listSelectedExtraItems == null || listSelectedExtraItems.size()<=0){
          super.onBackPressed();
      }else{
          showDeleteExtraInventoryDialog();
      }
    }

    private void showDeleteExtraInventoryDialog(){

        Bundle bundle = new Bundle();
        bundle.putString(DialogAlert.EXTRA_MESSAGE,getString(R.string.dialog_delete_extra_item));
        bundle.putString(DialogAlert.EXTRA_TITLE,getString(R.string.dialog_title_delete_extra_item));
        bundle.putString(DialogAlert.EXTRA_POSITIVE_TEXT,getString(R.string.delete));
        bundle.putString(DialogAlert.EXTRA_NEGATIVE_TEXT,getString(R.string.cancel));
        bundle.putInt(DialogAlert.EXTRA_TYPE, DIALOG_DELETE_EXTRAITEM.getValue());

        DialogAlert alert = new DialogAlert();
        alert.setArguments(bundle);
        alert.show(getFragmentManager(),null);
    }

    @Override
    public void onDialogAlertButtonPositive(EnumDialogType enumType) {
            switch (enumType){
                case DIALOG_DELETE_EXTRAITEM:
                    super.onBackPressed();
                    break;
            }
    }

    @Override
    public void onDialogAlertButtonNegative(EnumDialogType enumType) {
        switch (enumType){
            case DIALOG_DELETE_EXTRAITEM:
                //do nothing
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        register();
        toggleDoneButton(prefUtils.getString(Constants.PreferenceConstants.ROUTE_STATUS));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unRegister();
    }

    private void register(){
        registerReceiver(broadcastReceiver,new IntentFilter(Constants.INTENTACTIONS.ACTION_PUSH_RECEIVED));
    }

    private void unRegister(){
        unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent == null){
                return;
            }

            EnumPushNotificationType enumType = EnumPushNotificationType
                                                    .getEnumPushNotificationType(intent.getExtras()
                                                    .getInt(Constants.INTENTEXTRAS.EXTRA_PUSHNOTIFICATOIN_FLAG));

            if(enumType == EnumPushNotificationType.ROUTESTARTED){
                toggleDoneButton(EnumRouteStatus.START.name());
            }
        }
    };
}
