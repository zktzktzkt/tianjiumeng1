package com.yxk.tjm.tianjiumeng.my.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.KeyBoardUtil;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.To;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;


/**
 * 添加收货地址
 */
public class AddAddressActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.et_receiver)
    EditText etReceiver;
    @BindView(R.id.et_tel)
    EditText etTel;
    @BindView(R.id.et_detail_addr)
    EditText etDetailAddr;
    @BindView(R.id.btn_add_address)
    Button btnAddAddress;
    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;

    /**wheel设置-> 省*/
    private String province;
    /**wheel设置-> 市*/
    private String city;
    /**wheel设置-> 区*/
    private String area;

    private int addrId;
    private String addrProvice;
    private String addrCity;
    private String addrarea;
    private String addrDetail;
    private String addrTel;
    private String addrName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        App.getActivityManager().pushActivity(this);

        ButterKnife.bind(this);

        addrId = getIntent().getIntExtra("addrId", -1);
        addrProvice = getIntent().getStringExtra("addrProvice");
        addrCity = getIntent().getStringExtra("addrCity");
        addrarea = getIntent().getStringExtra("addrarea");
        addrTel = getIntent().getStringExtra("addrTel");
        addrDetail = getIntent().getStringExtra("addrDetail");
        addrName = getIntent().getStringExtra("addrName");

        tvArea.setOnClickListener(this);
        setToolbarNavigationClick();

        if (addrId != -1) {
            etReceiver.setText(addrName);
            etTel.setText(addrTel);
            tvArea.setText(addrProvice + " " + addrCity + " " + addrarea);
            etDetailAddr.setText(addrDetail);

            tvToolbar.setText("修改地址");
            btnAddAddress.setText("确认修改");

        }

    }

    /**
     * 从修改收货地址传进来
     */
    private void setmodifiInitAddr() {
        String receiver = etReceiver.getText().toString().trim();
        if (TextUtils.isEmpty(receiver)) {
            To.showShort(AddAddressActivity.this, "收货人不能为空！");
            return;
        }

        String tel = etTel.getText().toString().trim();
        if (TextUtils.isEmpty(tel)) {
            To.showShort(AddAddressActivity.this, "电话不能为空！");
            return;
        }

        String address = tvArea.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            To.showShort(AddAddressActivity.this, "省市区不能为空");
            return;
        }

        String detailAddr = etDetailAddr.getText().toString().trim();
        if (TextUtils.isEmpty(detailAddr)) {
            To.showShort(AddAddressActivity.this, "详细地址不能为空！");
            return;
        }
        JsonObject jo = new JsonObject();
        jo.addProperty("addrId", addrId);
        jo.addProperty("addrProvice", province);
        jo.addProperty("addrCity", city);
        jo.addProperty("addrarea", area);
        jo.addProperty("addrTel", tel);
        jo.addProperty("addrDetail", detailAddr);
        jo.addProperty("addrName", receiver);
        jo.addProperty("userId", Integer.parseInt(UserUtil.getUserId(App.getAppContext())));

        OkHttpUtils.postString()
                .content(jo.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .url(ApiConstants.MY_MODIFI_ADDRESS)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("AddAddressActivity ", "setmodifiInitAddr() Exception: " + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("AddAddressActivity ", "setmodifiInitAddr() response: " + response);
                        try {
                            JSONObject jo = new JSONObject(response);
                            if(0 == (int)jo.get("success")){
                                To.showShort(AddAddressActivity.this, "修改成功");
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }


    private void submit() {
        String receiver = etReceiver.getText().toString().trim();
        if (TextUtils.isEmpty(receiver)) {
            To.showShort(AddAddressActivity.this, "收货人不能为空！");
            return;
        }

        String tel = etTel.getText().toString().trim();
        if (TextUtils.isEmpty(tel)) {
            To.showShort(AddAddressActivity.this, "电话不能为空！");
            return;
        }

        String address = tvArea.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            To.showShort(AddAddressActivity.this, "省市区不能为空");
            return;
        }

        String detailAddr = etDetailAddr.getText().toString().trim();
        if (TextUtils.isEmpty(detailAddr)) {
            To.showShort(AddAddressActivity.this, "详细地址不能为空！");
            return;
        }

        try {
            JSONObject jo = new JSONObject();
            jo.put("userId", Integer.parseInt(UserUtil.getUserId(App.getAppContext())));
            jo.put("addrProvice", province);
            jo.put("addrCity", city);
            jo.put("addrarea", area);
            jo.put("addrTel", tel);
            jo.put("addrName", receiver);
            jo.put("addrDetail", detailAddr);

            LogUtil.e("jo1 :  ", jo.toString());
            OkHttpUtils.postString()
                    .url(ApiConstants.MY_INSERT_ADDRESS)
                    .content(jo.toString())
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogUtil.e("AddAddressActivity ", "initData() Exception: " + e);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            LogUtil.e("AddAddressActivity ", "initData() response: " + response);
                            try {
                                JSONObject jo = new JSONObject(response);
                                if(0 == (int)jo.get("success")){
                                    To.showShort(AddAddressActivity.this, "添加成功");
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            finish();
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void setToolbarNavigationClick() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @OnClick({R.id.btn_add_address, R.id.tv_area})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_address:
                if(addrId != -1){
                    setmodifiInitAddr();
                }else {
                    submit();
                }
                break;
            case R.id.tv_area:
                KeyBoardUtil.closeKeybord(etReceiver, AddAddressActivity.this);
                KeyBoardUtil.closeKeybord(etTel, AddAddressActivity.this);
                KeyBoardUtil.closeKeybord(etDetailAddr, AddAddressActivity.this);
                //设置省市区
                setArea();
                break;
        }
    }

    /**
     * 设置省市区
     */
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

                province = citySelected[0];
                city = citySelected[1];
                area = citySelected[2];
            }

            @Override
            public void onCancel() {
                Toast.makeText(AddAddressActivity.this, "已取消", Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.btn_add_address)
    public void onViewClicked() {
    }
}
