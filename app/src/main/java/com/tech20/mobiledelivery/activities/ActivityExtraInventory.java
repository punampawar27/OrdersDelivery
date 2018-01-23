package com.tech20.mobiledelivery.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.adapters.AdapterExtraInventory;
import com.tech20.mobiledelivery.contracts.ContractExtraInventory;
import com.tech20.mobiledelivery.dialogs.DialogNumberScroller;
import com.tech20.mobiledelivery.executors.AppExecutor;
import com.tech20.mobiledelivery.factory.discountfactory.FactoryDiscount;
import com.tech20.mobiledelivery.factory.discountfactory.IDiscount;
import com.tech20.mobiledelivery.helpers.CalculateTaxedPrice;
import com.tech20.mobiledelivery.helpers.DecimalHelper;
import com.tech20.mobiledelivery.helpers.ItemOffsetDecoration;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.helpers.ToastUtils;
import com.tech20.mobiledelivery.models.Entities.EntityDiscount;
import com.tech20.mobiledelivery.models.Entities.EntitySelectedOrderInventory;
import com.tech20.mobiledelivery.presenter.PresenterExtraInventory;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.extrainventoryclient.ExtraInventoryResponse;

import java.util.ArrayList;
import java.util.List;

public class ActivityExtraInventory extends BaseActivity implements ContractExtraInventory.IViewExtraInventory,DialogNumberScroller.ISeekNumberSelected {

    public static final String EXTRA_TOTAL = "EXTRA_TOTAL";
    public static final String EXTRA_SELECTED_INVENTORY = "EXTRA_SELECTED_INVENTORY";

    private TextView txtValueSubTotal = null,txtValueTotal = null;
    private Button btnDOne = null;
    private RecyclerView recycleExtraInventory = null;
    private ContractExtraInventory.IPresenterExtraInventory iPresenterExtraInventory = null;

    private AdapterExtraInventory adapterExtraInventory = null;
    private List<EntitySelectedOrderInventory> listSelected = null;

    private float mOriginalTotal = 0f,mSubTotal = 0f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_extra_inventory);

        setupToolbar(findViewById(R.id.detail_toolbar),getString(R.string.title_extra_inventory));

        readBundle();
        initView();
        init();
        loadExtraInventory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenterExtraInventory.dettach();
    }

    private void readBundle(){

        Bundle bundle = getIntent().getExtras();
        if(bundle == null || !bundle.keySet().contains(EXTRA_TOTAL)){
            new Error("Should contain float Total in bundle");
        }

        mOriginalTotal = bundle.getFloat(EXTRA_TOTAL);
        listSelected = bundle.getParcelableArrayList(EXTRA_SELECTED_INVENTORY);
    }
    private void initView(){

        btnDOne = findViewById(R.id.btnExtraInventoryDone);
        txtValueTotal = findViewById(R.id.txtValueTotal);
        txtValueSubTotal = findViewById(R.id.txtValueSubTotal);
        recycleExtraInventory = findViewById(R.id.recycleExtraInventory);
        recycleExtraInventory.addItemDecoration(new ItemOffsetDecoration(getResources().getDimension(R.dimen.list_item_space)));
        recycleExtraInventory.setLayoutManager(new LinearLayoutManager(this));
        btnDOne.setOnClickListener(onClickDone);
    }

    private void init(){
        iPresenterExtraInventory = new PresenterExtraInventory();
        iPresenterExtraInventory.attach(this);

        txtValueTotal.setText(String.valueOf(mOriginalTotal));
        txtValueSubTotal.setText(String.valueOf(mSubTotal));
    }

    private void loadExtraInventory(){
        iPresenterExtraInventory.loadExtraInventory(PreferenceUtils.getINSTANCE(getApplicationContext()));
    }
    @Override
    public void loadExtraInventorySuccess(List<ExtraInventoryResponse> listExtraInventory) {

        if(listExtraInventory == null || listExtraInventory.size()<=0){
            ToastUtils.showSnackBar(findViewById(android.R.id.content),getString(R.string.error_no_extra_inventory));
            return;
        }

        if(listSelected!=null){
            AppExecutor.getINSTANCE().getDiskIO().execute(()->{

                    //To persist selected extrainventory
                    //Get Quantity from each selected item
                    //Set Quantity to ExtraInvenotry item if item matched

                for(EntitySelectedOrderInventory selectedInventory : listSelected){
                    for(ExtraInventoryResponse extResp:listExtraInventory){

                        if(selectedInventory.getItemCode().equalsIgnoreCase(extResp.getItemCode())){
                            extResp.setSelectedQuantity(Integer.parseInt(selectedInventory.getQuantity()));
                            extResp.setTotalPriceAfterTaxAndDiscount(selectedInventory.getTotalPriceAfterTaxAndDiscount());
                        }
                    }

                }

                AppExecutor.getINSTANCE().getMainThread().execute(()->{

                    setAdapter(listExtraInventory);
                });

            });
        }else{
            setAdapter(listExtraInventory);
        }


    }

    private void setAdapter(List<ExtraInventoryResponse> listExtraInventory){
        adapterExtraInventory = new AdapterExtraInventory(R.drawable.shape_blue_border_grey,
                R.drawable.shape_white_border_grey,
                this, onClickAddButtonRecyclerItem,listExtraInventory);
        recycleExtraInventory.setAdapter(adapterExtraInventory);

        iPresenterExtraInventory.reCalculculateTotal(listExtraInventory,mOriginalTotal);
    }
    @Override
    public void loadExtraInventoryFail(ErrorMessage errorMessage) {
        ToastUtils.showSnackBar(findViewById(android.R.id.content),errorMessage.getMessage());
    }

    @Override
    public void reloadTotal(float subTotal, float total) {

        setTotal(total,subTotal);
    }

    @Override
    public void finishActivityWithResult(List<EntitySelectedOrderInventory> list) {

        Intent resultIntent = new Intent();
            resultIntent.putParcelableArrayListExtra(EXTRA_SELECTED_INVENTORY,(list!=null)?new ArrayList<>(list):null);
            setResult(RESULT_OK,resultIntent);
            finish();
    }

    private void setTotal(float total,float subTotal){
        txtValueSubTotal.setText(String.valueOf(subTotal));
        txtValueTotal.setText(String.valueOf(total));

    }
    private View.OnClickListener onClickAddButtonRecyclerItem = (v)->{

        int pos = Integer.parseInt(String.valueOf(v.getTag()));
        ExtraInventoryResponse respo = adapterExtraInventory.getItem(pos);

        //Callback to seekNumberSelected(int number, int position, Dialog dialog)
        Bundle bundle = new Bundle();
        bundle.putInt(DialogNumberScroller.EXTRA_NUMBER,(respo.getSelectedQuantity()<=0?1:respo.getSelectedQuantity()));
        bundle.putInt(DialogNumberScroller.EXTRA_MAX,Integer.parseInt(respo.getQuantity()));
        bundle.putInt(DialogNumberScroller.EXTRA_POSITION,pos);

        DialogNumberScroller dialogNumberScroller = new DialogNumberScroller();
        dialogNumberScroller.setArguments(bundle);
        dialogNumberScroller.show(getFragmentManager(),"");
    };

    private View.OnClickListener onClickDone = (v)->{
        if(adapterExtraInventory ==null){
            iPresenterExtraInventory.loadSelectedExtraItems(null);
            return;
        }
        iPresenterExtraInventory.loadSelectedExtraItems(adapterExtraInventory.getList());
    };
    @Override
    public void seekNumberSelected(int number, int position, Dialog dialog) {

        //User clicks on Done button in Dialog Fragment
        ExtraInventoryResponse extraRespo = adapterExtraInventory.getItem(position);

        EntityDiscount entityDiscount = new EntityDiscount();
        entityDiscount.setPromotionStartDate(extraRespo.getPromotionStartDate());
        entityDiscount.setPromotionEndDate(extraRespo.getPromotionEndDate());
        entityDiscount.setPromotionAmount(Float.parseFloat(DecimalHelper.format(DecimalHelper.PATTERN,
                                            Float.parseFloat(extraRespo.getPromotionValue()))));
        entityDiscount.setDiscountAmount(Float.parseFloat(DecimalHelper.format(DecimalHelper.PATTERN,
                                            Float.parseFloat(extraRespo.getDiscountAmount()))));
        entityDiscount.setDiscountQuantity(Integer.parseInt(extraRespo.getDiscountQuantity()));
        entityDiscount.setItemPrice(Float.parseFloat(extraRespo.getItemPrice()));
        entityDiscount.setOrderedQuantity(number);
        entityDiscount.setTaxPerItem(CalculateTaxedPrice.taxedPrice(extraRespo));


        float totalPriceAfterTaxAndDiscount = Float.parseFloat(DecimalHelper.format(DecimalHelper.PATTERN,
                FactoryDiscount.getDiscount(extraRespo)
                        .calculatePriceAfterDiscountAndTax(entityDiscount)));

        extraRespo.setSelectedQuantity(number);
        extraRespo.setTotalPriceAfterTaxAndDiscount(totalPriceAfterTaxAndDiscount);
        adapterExtraInventory.notifyDataSetChanged();
        dialog.dismiss();

        iPresenterExtraInventory.reCalculculateTotal(adapterExtraInventory.getList(), mOriginalTotal);
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
