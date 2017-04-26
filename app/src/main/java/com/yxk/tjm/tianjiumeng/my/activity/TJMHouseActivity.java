package com.yxk.tjm.tianjiumeng.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.my.bean.TJMBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.news.activity.NewsDetailActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class TJMHouseActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private TJMBean tjmBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tjmhouse);
        ButterKnife.bind(this);
        setToolbarNavigationClick();

        OkHttpUtils.get()
                .url(ApiConstants.MY_TJM_HOUSE)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        tjmBean = new Gson().fromJson(response, TJMBean.class);

                        recycler.setAdapter(new BaseQuickAdapter<TJMBean.DreamhousesBean, BaseViewHolder>(R.layout.item_tjm_house, tjmBean.getDreamhouses()) {
                            @Override
                            protected void convert(BaseViewHolder helper, TJMBean.DreamhousesBean item) {
                                Glide.with(TJMHouseActivity.this).load(item.getHousePic()).into((ImageView) helper.getView(R.id.img_pic));
                                helper.setText(R.id.tv_address, item.getHouseName());
                                helper.setText(R.id.tv_person_info, item.getManager() + ":" + item.getManagerPhone());
                            }
                        });
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
