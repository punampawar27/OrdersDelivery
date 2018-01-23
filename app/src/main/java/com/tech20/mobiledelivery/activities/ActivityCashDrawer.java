package com.tech20.mobiledelivery.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.adapters.AdapterCashDrawer;
import com.tech20.mobiledelivery.contracts.ContractCashDrawer;
import com.tech20.mobiledelivery.contracts.ContractCashDrawer.IViewCashDrawer;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dataentities.DbModelCashDrawer;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.helpers.ToastUtils;
import com.tech20.mobiledelivery.presenter.PresenterCashDrawer;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;

import java.util.List;

public class ActivityCashDrawer extends BaseActivity implements IViewCashDrawer{

    private ContractCashDrawer.IPresenterCashDrawer iPresenterCashDrawer = null;
    private RecyclerView recyclerCashDrawer = null;
    private TextView txtValueTotal = null,textTotal = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cash_drawer);
        initView();
        init();

    }

    private void initView(){
        setupToolbar(findViewById(R.id.detail_toolbar),getString(R.string.title_cash_drawer));

        iPresenterCashDrawer = new PresenterCashDrawer();
        iPresenterCashDrawer.attach(this);

        txtValueTotal = (TextView)findViewById(R.id.txtValueTotal);
        textTotal = (TextView)findViewById(R.id.textTotal);

        recyclerCashDrawer = findViewById(R.id.recyclerCashDrawer);
        recyclerCashDrawer.setLayoutManager(new LinearLayoutManager(this));
        recyclerCashDrawer.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
    }


    private void init(){
        iPresenterCashDrawer.loadCashDrawer(getString(R.string.prg_load_cashdrawer_message),
                DatabaseHouse.getSingleTon(getApplicationContext()),
                PreferenceUtils.getINSTANCE(getApplicationContext()));
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

    private void setAdapter(List<DbModelCashDrawer> list){

        recyclerCashDrawer.setAdapter(new AdapterCashDrawer(list,
                ContextCompat.getColor(this,R.color.color_alice_blue),
                ContextCompat.getColor(this,R.color.colorWhite)));

    }
    private void setTotal(String total){
        textTotal.setVisibility(View.VISIBLE);
        txtValueTotal.setVisibility(View.VISIBLE);
        txtValueTotal.setText(total);
    }
    @Override
    public void onCashDrawerLoaded(List<DbModelCashDrawer> list,String total) {

        if(list == null || list.size()<=0){
            ToastUtils.showSnackBar(findViewById(android.R.id.content),getString(R.string.error_loading_cashdrawer));
        }else{
            Log.d("WASTE",""+list.get(0).getCash());
            setAdapter(list);
            setTotal(total);
        }

    }

    @Override
    public void onCashDrawerFail(ErrorMessage errorMessage) {

        Log.d("WASTE",""+errorMessage.getMessage());
        ToastUtils.showSnackBar(findViewById(android.R.id.content),errorMessage.getMessage());
    }
}
