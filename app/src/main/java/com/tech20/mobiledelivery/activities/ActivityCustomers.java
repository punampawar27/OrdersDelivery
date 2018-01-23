package com.tech20.mobiledelivery.activities;


import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.adapters.AdapterCustomers;
import com.tech20.mobiledelivery.contracts.ContractCustomers;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dataentities.DbModelCustomers;
import com.tech20.mobiledelivery.helpers.NetworkHelper;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.helpers.ToastUtils;
import com.tech20.mobiledelivery.presenter.PresenterCustomers;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;

import java.util.List;

public class ActivityCustomers extends BaseActivity implements ContractCustomers.IViewCustomers {

    private ContractCustomers.IPresenterCustomers iPresenter = null;
    private RecyclerView recyclerView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_customers);

        setupToolbar(findViewById(R.id.detail_toolbar),getString(R.string.title_customers));
        initView();
        init();
        loadCustomers();
    }

    @Override
    public void onCustomerLoadSuccess() {

        iPresenter.getCustomersFromDatabse(DatabaseHouse.getSingleTon(getApplicationContext()),
                (List<DbModelCustomers> list)->{
                    Log.d("WASTE",list.size()+" :: "+list.get(0).getContactNumber());

                    AdapterCustomers adapterCustomers = new AdapterCustomers(list,onClick,
                            ContextCompat.getColor(this
                                    ,R.color.color_alice_blue),
                            ContextCompat.getColor(this,
                                    R.color.colorWhite));

                    recyclerView.setAdapter(adapterCustomers);

                });
    }

    @Override
    public void onCustomerLoadFail(ErrorMessage errorMessage) {

        ToastUtils.showSnackBar(findViewById(android.R.id.content),errorMessage.getMessage());
    }

    private void initView(){
        iPresenter = new PresenterCustomers();
        iPresenter.attach(this);

        recyclerView = findViewById(R.id.recyclerViewCustomers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
    }

    private void init(){

    }

    private void loadCustomers(){

        if(NetworkHelper.isOnline(this)){
            iPresenter.loadCustomers(DatabaseHouse.getSingleTon(getApplicationContext()),
                    PreferenceUtils.getINSTANCE(getApplicationContext()));
        }else{
            ToastUtils.showSnackBar(findViewById(android.R.id.content),NetworkHelper.getNoConnectionString(this));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    private View.OnClickListener onClick = (View v)->{

        Integer pos = Integer.parseInt(String.valueOf(v.getTag()));
        DbModelCustomers customer = ((AdapterCustomers)recyclerView.getAdapter()).getItem(pos);

        Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + customer.getContactNumber()));
        startActivity(intentCall);

    };
}
