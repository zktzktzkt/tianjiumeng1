package com.yxk.tjm.tianjiumeng.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.home.activity.ProductDetailActivity;
import com.yxk.tjm.tianjiumeng.my.bean.ReturnningMoneyBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yxk.tjm.tianjiumeng.R.id.btn_get_money;
import static com.yxk.tjm.tianjiumeng.R.id.img_pic;

public class MyWaitAppraiseActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appraise);
        ButterKnife.bind(this);
        setToolbarNavigationClick();

        List<ReturnningMoneyBean> list = new ArrayList();
        list.add(new ReturnningMoneyBean());
        list.add(new ReturnningMoneyBean());
        list.add(new ReturnningMoneyBean());
        list.add(new ReturnningMoneyBean());
        list.add(new ReturnningMoneyBean());
        list.add(new ReturnningMoneyBean());
        list.add(new ReturnningMoneyBean());

        recycler.setAdapter(new BaseQuickAdapter<ReturnningMoneyBean, BaseViewHolder>(R.layout.item_wait_appraise, list) {

            @Override
            protected void convert(BaseViewHolder helper, ReturnningMoneyBean item) {
                // helper.getLayoutPosition()  //获取当前position
                helper.addOnClickListener(btn_get_money)
                        .addOnClickListener(img_pic);
            }
        });

        recycler.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.img_pic:
                        startActivity(new Intent(MyWaitAppraiseActivity.this, ProductDetailActivity.class));
                        break;
                    case R.id.btn_get_money:
                        startActivity(new Intent(MyWaitAppraiseActivity.this, ImmediateAppraiseActivity.class));
                        break;
                }
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
