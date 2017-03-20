package com.yxk.tjm.tianjiumeng.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.my.bean.TJMBean;
import com.yxk.tjm.tianjiumeng.news.activity.NewsDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TJMHouseActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tjmhouse);
        ButterKnife.bind(this);
        setToolbarNavigationClick();

        List<TJMBean> list = new ArrayList<>();
        list.add(new TJMBean(R.drawable.pic_350w_1));
        list.add(new TJMBean(R.drawable.pic_350w_2));
        list.add(new TJMBean(R.drawable.pic_350w_3));
        list.add(new TJMBean(R.drawable.pic_350w_1));
        list.add(new TJMBean(R.drawable.pic_350w_2));
        list.add(new TJMBean(R.drawable.pic_350w_3));

        recycler.setAdapter(new BaseQuickAdapter<TJMBean, BaseViewHolder>(R.layout.item_tjm_house, list) {
            @Override
            protected void convert(BaseViewHolder helper, TJMBean item) {
                helper.setImageResource(R.id.img_pic, item.getImage());
            }
        });

        recycler.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(TJMHouseActivity.this, NewsDetailActivity.class));
            }
        });
    }

    private void setToolbarNavigationClick() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
