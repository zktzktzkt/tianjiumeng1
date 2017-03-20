package com.yxk.tjm.tianjiumeng.my.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.my.bean.WaitPayBean;

import java.util.ArrayList;
import java.util.List;


public class WaitShipActivity extends BaseActivity {

    private RecyclerView recycler;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_ship);
        List<WaitPayBean> list = new ArrayList<>();
        list.add(new WaitPayBean());
        list.add(new WaitPayBean());
        list.add(new WaitPayBean());
        list.add(new WaitPayBean());
        list.add(new WaitPayBean());
        list.add(new WaitPayBean());

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbarNavigationClick();

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setAdapter(new BaseQuickAdapter<WaitPayBean, BaseViewHolder>(R.layout.item_wait_ship, list) {
            @Override
            protected void convert(BaseViewHolder helper, WaitPayBean item) {

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
