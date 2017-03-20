package com.yxk.tjm.tianjiumeng.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.home.adapter.CrystalHouseAdapter;

public class CrystalHouseActivity extends BaseActivity {

    private RecyclerView mRecycler;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crystal_house);

        mRecycler = (RecyclerView) findViewById(R.id.xrecycler);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbarNavigationClick();

        LinearLayoutManager layoutManager = new GridLayoutManager(this, 3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(layoutManager);
        CrystalHouseAdapter crystalHouseAdapter = new CrystalHouseAdapter();
        mRecycler.setAdapter(crystalHouseAdapter);
        crystalHouseAdapter.setOnItemClickListener(new CrystalHouseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(CrystalHouseActivity.this, ProductDetailActivity.class));
            }
        });
    }

    private void setToolbarNavigationClick() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
