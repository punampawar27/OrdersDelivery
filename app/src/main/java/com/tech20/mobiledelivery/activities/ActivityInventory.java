package com.tech20.mobiledelivery.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.adapters.AdapterFAQ;
import com.tech20.mobiledelivery.adapters.AdapterInventory;
import com.tech20.mobiledelivery.contracts.ContractInventory;
import com.tech20.mobiledelivery.contracts.ContractInventory.IViewInventory;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.helpers.ToastUtils;
import com.tech20.mobiledelivery.models.ModelInventory;
import com.tech20.mobiledelivery.presenter.PresenterInventory;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.inventoryclient.InventoryResponse;

import java.util.List;

public class ActivityInventory extends BaseActivity implements IViewInventory {

    //Database not implemented yet for Inventory
    private ContractInventory.IPresenterInventory iPresenterInventory = null;
    private RecyclerView recyclerViewInventory = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        initView();
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenterInventory.dettach();
    }

    private void initView(){

        setupToolbar(findViewById(R.id.detail_toolbar),getString(R.string.title_inventory));

        iPresenterInventory = new PresenterInventory();
        iPresenterInventory.attach(this);
        recyclerViewInventory = findViewById(R.id.recyclerViewInventory);
        recyclerViewInventory.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewInventory.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
    }

    private void init(){

        iPresenterInventory.loadInventory(DatabaseHouse.getSingleTon(getApplicationContext()),
                PreferenceUtils.getINSTANCE(getApplicationContext()));
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

    @Override
    public void onInventoryLoaded(List<InventoryResponse> inventoryResponseList) {

        if(inventoryResponseList == null || inventoryResponseList.size()<=0){
            ToastUtils.showSnackBar(findViewById(android.R.id.content),getString(R.string.error_loading_inventory));
        }else {
            recyclerViewInventory.setAdapter(new AdapterInventory(inventoryResponseList,
                    ContextCompat.getColor(this,R.color.colorWhite),
                    ContextCompat.getColor(this,R.color.color_alice_blue)));
        }

    }

    @Override
    public void onInventoryLoadFail(ErrorMessage errorMessage) {
        ToastUtils.showSnackBar(findViewById(android.R.id.content),errorMessage.getMessage());
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
