package com.tech20.mobiledelivery.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.adapters.AdapterFAQ;
import com.tech20.mobiledelivery.contracts.ContractFaq;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dataentities.DbModelFaq;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.helpers.ToastUtils;
import com.tech20.mobiledelivery.presenter.PresneterFaq;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class ActivityFaq extends BaseActivity implements ContractFaq.IViewFaq{

    private RecyclerView recyclerViewFaq = null;
    private PresneterFaq iPresenterFaq = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_faq);

        initView();
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenterFaq.dettach();
    }

    private void init(){

       iPresenterFaq.loadFaq(DatabaseHouse.getSingleTon(getApplicationContext()), PreferenceUtils.getINSTANCE(getApplicationContext()));
    }
    private void initView(){

        setupToolbar(findViewById(R.id.detail_toolbar),getString(R.string.title_faq));
        iPresenterFaq = new PresneterFaq();
        iPresenterFaq.attach(this);

        recyclerViewFaq = (RecyclerView)findViewById(R.id.recyclerViewFaq);
    }
    @Override
    public void onFaqLoaded(List<DbModelFaq> list) {

        if(list!=null && list.size()>0){

            DividerItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
            recyclerViewFaq.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewFaq.setAdapter(new AdapterFAQ(list));
            recyclerViewFaq.addItemDecoration(itemDecoration);

            //recyclerViewFaq.setAdapter(new AdapterFAQ(addFakeFaq()));

        }else{
            ToastUtils.showSnackBar(findViewById(android.R.id.content),getString(R.string.error_no_faq));

        }
        Log.d(Constants.LogConstants.TAG_WASTE,(list==null || list.size()<=0)?"No FAQ":list.get(0).getQuestion());
    }

    @Override
    public void onFaqLodingFaild(ErrorMessage errorMessage) {
        ToastUtils.showSnackBar(findViewById(android.R.id.content),getString(R.string.error_no_faq));
    }

    private List<DbModelFaq> addFakeFaq(){

        List<DbModelFaq> list = new ArrayList<>();

        for(int i=0;i<20;i++){
            if(i%2==0){
                DbModelFaq faq = new DbModelFaq();
                faq.setQuestion("To be or Not to be is the question ?");
                faq.setAnswer("Question is the Answer an Asnwer is the question");
                list.add(faq);
            }else{
                DbModelFaq faq = new DbModelFaq();
                faq.setQuestion("What is the role of a product manager?");
                faq.setAnswer("The Product Manager is an important organizational role typically in a technology company. It is similar in concept to a brand manager at a consumer packaged goods company. The product manager is often considered the CEO of the product and is responsible for the strategy, roadmap, and feature definition for that product or product line. The position may also include marketing, forecasting, and profit and loss (P&L) responsibilities.");
                list.add(faq);

            }
        }
        return list;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
