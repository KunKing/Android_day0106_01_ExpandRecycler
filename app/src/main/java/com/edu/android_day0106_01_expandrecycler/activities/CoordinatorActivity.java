package com.edu.android_day0106_01_expandrecycler.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.edu.android_day0106_01_expandrecycler.R;
import com.edu.android_day0106_01_expandrecycler.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class CoordinatorActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private List<String> list;
    private FloatingActionButton floatBtn;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.format("第%02d条数据", i));
        }
        ItemAdapter adapter = new ItemAdapter(this, list);
        recyclerView.setAdapter(adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_bar);
        setSupportActionBar(toolbar);

        floatBtn = (FloatingActionButton) findViewById(R.id.floatBtn);

        floatBtn.setOnClickListener(this);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLay);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public void onClick(View v) {
       /* Intent intent = new Intent(this,TextInputActivity.class);
        startActivity(intent);*/
        Snackbar.make(coordinatorLayout,"点击事件",Snackbar.LENGTH_LONG).show();
    }
}
