package com.yxk.tjm.tianjiumeng.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.my.bean.AddressBeannn;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.shopcar.adapter.AddressAdapter;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AddressActivity extends BaseActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_add_address)
    Button btnAddAddress;
    private AddressBeannn addressBeannn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        setToolbarNavigationClick();

    }

    @Override
    protected void onResume() {
        super.onResume();

        initData();
    }

    private void initData() {

        OkHttpUtils.get()
                .url(ApiConstants.MY_QUERY_ADDRESS)
                .addParams("userId", UserUtil.getUserId(App.getAppContext()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("AddressActivity ","initData() Exception:"+e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("AddressActivity ","initData() response:"+response);

                        addressBeannn = new Gson().fromJson(response, AddressBeannn.class);
                        recycler = (RecyclerView) findViewById(R.id.recycler);
                        recycler.setAdapter(new AddressAdapter(addressBeannn.getAddress(), AddressActivity.this));
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

    @OnClick(R.id.btn_add_address)
    public void onClick() {
        startActivity(new Intent(this, AddAddressActivity.class));
    }
}
