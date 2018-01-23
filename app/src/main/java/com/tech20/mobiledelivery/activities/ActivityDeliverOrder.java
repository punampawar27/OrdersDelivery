package com.tech20.mobiledelivery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.contracts.ContractDeliverOrder;
import com.tech20.mobiledelivery.database.dataentities.DbModelOrders;
import com.tech20.mobiledelivery.enums.EnumOrderStatus;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.helpers.ToastUtils;
import com.tech20.mobiledelivery.models.Entities.EntitySelectedOrderInventory;
import com.tech20.mobiledelivery.presenter.PresenterDeliverOrder;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.deliverorderclient.DeliverOrderResponse;

import java.util.List;

public class ActivityDeliverOrder extends BaseActivity implements ContractDeliverOrder.IViewDeliverOrder {

    public static final String EXTRA_ORDER = "EXTRA_ORDER",
            EXTRA_ORDERED_EXTRA_ITEMS = "EXTRA_ORDERED_EXTRA_ITEMS";

    private RadioGroup radioGroup =null;
    private EditText edtReasonOther = null;
    private Button btnDone = null;

    private DbModelOrders dbOrder = null;
    private List<EntitySelectedOrderInventory> listExtraInventory = null;

    private ContractDeliverOrder.IPresenterDeliverOrder iPresenterDeliverOrder = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order_status);
        readExtra();
        setupToolbar(findViewById(R.id.detail_toolbar),dbOrder.getCustomerName());

        initView();
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenterDeliverOrder.dettach();
    }

    private void readExtra(){

        dbOrder = getIntent().getExtras().getParcelable(EXTRA_ORDER);
        listExtraInventory = getIntent().getExtras().getParcelableArrayList(EXTRA_ORDERED_EXTRA_ITEMS);

    }

    private void initView(){

        iPresenterDeliverOrder = new PresenterDeliverOrder();
        iPresenterDeliverOrder.attach(this);
        edtReasonOther = findViewById(R.id.edtReasonOther);
        radioGroup = findViewById(R.id.radioGroup);
        btnDone = findViewById(R.id.btnDone);

        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        btnDone.setOnClickListener(onClickListener);
    }
    private void init(){
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

    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = (RadioGroup group, int checkedId)->{

        switch (checkedId){
            case R.id.radioButtonDelivered:
                edtReasonOther.setVisibility(View.INVISIBLE);
                break;
                default:
                edtReasonOther.setVisibility(View.VISIBLE);
        }
    };

    private View.OnClickListener onClickListener = (v)->{
        switch (v.getId()){
            case R.id.btnDone:

                String note = getNotes(radioGroup.getCheckedRadioButtonId());
                String status = getStatus(radioGroup.getCheckedRadioButtonId());

                if(TextUtils.isEmpty(note)){
                    ToastUtils.showSnackBar(findViewById(android.R.id.content),getString(R.string.please_specify_reason));
                    return;
                }

                iPresenterDeliverOrder.updateOrderStatus(dbOrder.getOrderId(),
                        note,
                        PreferenceUtils.getINSTANCE(this),
                        status,
                        listExtraInventory);
                break;
        }
    };

    private String getStatus(int checkedRadioButton){
        switch (checkedRadioButton){
            case R.id.radioButtonDelivered:
                return EnumOrderStatus.Delivered.name();
            case R.id.radioButtonCustomerNotAvailable:
            case R.id.radioButtonInvalidAddress:
            case R.id.radioButtonOther:
                return EnumOrderStatus.UnDelivered.name();

            default:
                return EnumOrderStatus.UnDelivered.name();
        }
    }

    private String getNotes(int checkedRadioButton){
        switch (checkedRadioButton){
            case R.id.radioButtonDelivered:
                return EnumOrderStatus.Delivered.name();
            case R.id.radioButtonCustomerNotAvailable:
            case R.id.radioButtonInvalidAddress:
            case R.id.radioButtonOther:
                return edtReasonOther.getEditableText().toString();

            default:
                return edtReasonOther.getEditableText().toString();
        }
    }
    @Override
    public void orderUpdatedSucess(DeliverOrderResponse deliverOrderResponse) {

        ToastUtils.showSnackBar(findViewById(android.R.id.content),EnumOrderStatus.getEnum(deliverOrderResponse.getStatus()).name());

        Intent intentHome = new Intent(this,ActivityOrders.class);
        intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentHome);
    }

    @Override
    public void orderUpdatedFailed(ErrorMessage errorMessage) {
            ToastUtils.showSnackBar(findViewById(android.R.id.content),errorMessage.getMessage());
    }
}
