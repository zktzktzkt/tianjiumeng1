package com.yxk.tjm.tianjiumeng.home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.home.bean.SearchBean;
import com.yxk.tjm.tianjiumeng.utils.To;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ningfei on 2017/3/17.
 */

public class SearchActivity extends BaseActivity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        App.getActivityManager().pushActivity(this);

        ButterKnife.bind(this);

        final List<SearchBean> list = new ArrayList<>();
        list.add(new SearchBean("五粮液"));
        list.add(new SearchBean("开酒器"));
        list.add(new SearchBean("高脚杯"));
        list.add(new SearchBean("盛酒器皿"));
        list.add(new SearchBean("开酒器"));
        list.add(new SearchBean("烟灰缸"));
        list.add(new SearchBean("甜酒杯"));
        list.add(new SearchBean("白兰地杯"));
        list.add(new SearchBean("装饰品"));

        recycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        recycler.setAdapter(new BaseQuickAdapter<SearchBean, BaseViewHolder>(R.layout.item_search_hot, list) {
            @Override
            protected void convert(BaseViewHolder helper, SearchBean item) {
                helper.setText(R.id.tv_text, item.getItem());
                helper.addOnClickListener(R.id.tv_text);
            }
        });

        recycler.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_text:
                        To.showShort(getApplicationContext(), list.get(position).getItem());
                        break;
                }
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
        overridePendingTransition(R.anim.enter_alpha, R.anim.out_alpha);
    }
}
