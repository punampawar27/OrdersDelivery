package com.tech20.mobiledelivery.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.database.dataentities.DbModelNotes;
import com.tech20.mobiledelivery.helpers.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityNotes extends BaseActivity {

    public static final String EXTRA_NOTES = "EXTRA_NOTES";
    private final String NOTE = "NOTE";

    private ListView recyclerNotes = null;

    private List<DbModelNotes> lisNotes = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notes);

        readExtra();
        setupToolbar(findViewById(R.id.detail_toolbar),getString(R.string.title_note));
        initView();
        init();
    }

    private void readExtra(){
        lisNotes = getIntent().getExtras().getParcelableArrayList(EXTRA_NOTES);
        if(lisNotes == null || lisNotes.isEmpty()){
            ToastUtils.showSnackBar(findViewById(android.R.id.content),getString(R.string.no_note_available));
        }
    }

    private void initView(){
        recyclerNotes = findViewById(R.id.recyclerNotes);
    }

    private void init(){
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                getListMap(lisNotes),
                R.layout.row_notes,
                new String[]{NOTE},
                new int[]{R.id.txtNote});

        recyclerNotes.setAdapter(simpleAdapter);
    }
    private List<Map<String,String>> getListMap(List<DbModelNotes> lisNotes){

        List<Map<String,String>> listMap = new ArrayList<>();

        for(DbModelNotes note:lisNotes){

            Map<String,String> map = new HashMap<>();
            map.put(NOTE,note.getNote());
            listMap.add(map);
        }
        return listMap;
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
