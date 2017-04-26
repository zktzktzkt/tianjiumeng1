package com.yxk.tjm.tianjiumeng.my.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.my.adapter.CommissionDetailAdapter;
import com.yxk.tjm.tianjiumeng.my.bean.CommissionBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class CommissionActivity extends BaseActivity {

    private RecyclerView recycler;
    private Toolbar mToolbar;
    private List<CommissionBean> commissionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commission);
        App.getActivityManager().pushActivity(this);

        recycler = (RecyclerView) findViewById(R.id.recycler);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbarNavigationClick();

        OkHttpUtils.get()
                .url(ApiConstants.MY_RAKEOFF_DETAIL)
                .addParams("userId", UserUtil.getUserId(getApplicationContext()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        commissionList = new Gson().fromJson(response, new TypeToken<List<CommissionBean>>() {
                        }.getType());

                        recycler.setLayoutManager(new LinearLayoutManager(CommissionActivity.this));
                        recycler.setAdapter(new CommissionDetailAdapter(commissionList));
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
