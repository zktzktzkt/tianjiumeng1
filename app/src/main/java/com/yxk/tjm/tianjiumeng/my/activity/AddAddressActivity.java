package com.yxk.tjm.tianjiumeng.my.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.To;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AddAddressActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_add_address)
    Button btnAddAddress;
    @BindView(R.id.tv_area)
    TextView tvArea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
        tvArea.setOnClickListener(this);
        setToolbarNavigationClick();

        initData();
    }

    private void initData() {
        JsonObject jo = new JsonObject();
        jo.addProperty("userId", UserUtil.getUserId(App.getAppContext()));
        jo.addProperty("addrProvice", UserUtil.getUserId(App.getAppContext()));
        jo.addProperty("addrCity", UserUtil.getUserId(App.getAppContext()));
        jo.addProperty("addrarea", UserUtil.getUserId(App.getAppContext()));
        jo.addProperty("addrTel", UserUtil.getUserId(App.getAppContext()));
        jo.addProperty("addrName", UserUtil.getUserId(App.getAppContext()));
        jo.addProperty("addrDetail", UserUtil.getUserId(App.getAppContext()));

        JsonObject jo1 = new JsonObject();
        jo1.add("address", jo);

        OkHttpUtils.get()
                .url(ApiConstants.MY_INSERT_ADDRESS)
                .addParams("address", jo1.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

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
        To.showShort(getApplicationContext(), "保存");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_area:
                setArea();
                break;
        }
    }

    private void setArea() {
        CityPicker cityPicker = new CityPicker.Builder(AddAddressActivity.this).textSize(20)
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .province("北京市")
                .city("北京市")
                .district("昌平区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(false)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .build();

        cityPicker.show();
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                tvArea.setText(citySelected[0] + " " + citySelected[1] + " "
                        + citySelected[2]);
            }

            @Override
            public void onCancel() {
                android.widget.Toast.makeText(AddAddressActivity.this, "已取消", android.widget.Toast.LENGTH_LONG).show();
            }
        });
    }
}
