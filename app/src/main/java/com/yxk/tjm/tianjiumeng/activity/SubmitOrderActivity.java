package com.yxk.tjm.tianjiumeng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.my.activity.AddressActivity;
import com.yxk.tjm.tianjiumeng.my.activity.CardActivity;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.shopcar.bean.ShopcartSerialize;
import com.yxk.tjm.tianjiumeng.shopcar.bean.SubmitOrderBean;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.To;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

public class SubmitOrderActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_person)
    TextView tvPerson;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.img_pic)
    ImageView imgPic;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_freight)
    TextView tvFreight;
    @BindView(R.id.tv_discount_coupon)
    TextView tvDiscountCoupon;
    @BindView(R.id.tv_cystal)
    TextView tv_cystal;
    @BindView(R.id.cb_invoice)
    CheckBox cbInvoice;
    @BindView(R.id.tv_all_price)
    TextView tvAllPrice;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.rl_person_info)
    RelativeLayout rlPersonInfo;
    @BindView(R.id.heji)
    TextView heji;
    //多个商品的时候显示件数
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    //多个商品的时候显示的布局
    @BindView(R.id.rl_amount)
    RelativeLayout rlAmount;
    //单个商品时候显示的布局
    @BindView(R.id.rl_single)
    RelativeLayout rlSingle;
    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;
    @BindView(R.id.rl_weixin)
    RelativeLayout rlWeixin;
    @BindView(R.id.iv_alipay)
    ImageView ivAlipay;
    @BindView(R.id.rl_alipay)
    RelativeLayout rlAlipay;
    @BindView(R.id.rl_crystal)
    RelativeLayout rlCrystal;
    @BindView(R.id.rl_coupon)
    RelativeLayout rlCoupon;
    private int amount;
    private double totalPrice;
    private SubmitOrderBean submitOrderBean;
    private List<ShopcartSerialize> shopcartList;
    private static final int COUPON = 1;
    private String couponId_result = "";
    private String downPrice_result;
    private String paymentWay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);

        amount = getIntent().getIntExtra("amount", -1);
        totalPrice = getIntent().getDoubleExtra("totalPrice", -1);
        shopcartList = (List<ShopcartSerialize>) getIntent().getSerializableExtra("shopcartList");

        tvAllPrice.setText(totalPrice + "元");

        btnSubmit.setText("提交(" + amount + ")");

        tvDiscountCoupon.setText("使用优惠券");

        setToolbarNavigationClick();

    }

    @Override
    protected void onResume() {
        super.onResume();

        initData();
    }

    private void initData() {
        OkHttpUtils.get()
                .url(ApiConstants.SHOPCAR_SETTLE)
                .addParams("userId", UserUtil.getUserId(getApplicationContext()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        submitOrderBean = new Gson().fromJson(response, SubmitOrderBean.class);
                        tvPerson.setText("收货人：" + submitOrderBean.getDefAddress().getAddrName());
                        tvPhone.setText("电话：" + submitOrderBean.getDefAddress().getAddrTel());
                        tvAddress.setText(submitOrderBean.getDefAddress().getAddrProvice() + submitOrderBean.getDefAddress().getAddrCity() + submitOrderBean.getDefAddress().getAddrarea());

                        tv_cystal.setText(submitOrderBean.getJewel() + "个");

                        if (submitOrderBean.getCoupon().size() != 0 || submitOrderBean.getCoupon() != null) {
                            /*tvDiscountCoupon.setText(getResources().getString(R.string.RMB) + submitOrderBean.getCoupon().get(0).getDownPrice());
                            couponId_result = submitOrderBean.getCoupon().get(0).getCouponId() + "";*/
                            //  tvDiscountCoupon.setText("使用优惠券");
                        } else {
                            tvDiscountCoupon.setText("无可用优惠券");
                            couponId_result = "";
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

    @OnClick({R.id.rl_person_info, R.id.btn_submit, R.id.rl_weixin, R.id.rl_alipay, R.id.rl_crystal, R.id.rl_coupon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_person_info:
                startActivity(new Intent(SubmitOrderActivity.this, AddressActivity.class));
                break;

            case R.id.rl_coupon:
                Intent intent = new Intent(SubmitOrderActivity.this, CardActivity.class);
                intent.putExtra("submitOrderEnter", "submitOrderEnter");
                startActivityForResult(intent, COUPON);
                break;

            case R.id.btn_submit:
                submit();
                break;

            case R.id.rl_weixin:
                ivWeixin.setSelected(true);
                ivAlipay.setSelected(false);
                tv_cystal.setSelected(false);
                paymentWay = "1";
                break;

            case R.id.rl_alipay:
                ivAlipay.setSelected(true);
                ivWeixin.setSelected(false);
                tv_cystal.setSelected(false);
                paymentWay = "2";
                break;

            case R.id.rl_crystal:
                tv_cystal.setSelected(true);
                ivAlipay.setSelected(false);
                ivWeixin.setSelected(false);
                paymentWay = "3";
                break;
        }
    }

    private void submit() {
        if (!ivWeixin.isSelected() && !ivAlipay.isSelected() && !tv_cystal.isSelected()) {
            To.showShort(getApplicationContext(), "请选择支付方式");
        } else {
            JsonObject jo = new JsonObject();
            jo.addProperty("address", submitOrderBean.getDefAddress().getAddrProvice() + submitOrderBean.getDefAddress().getAddrCity() + submitOrderBean.getDefAddress().getAddrarea());
            jo.addProperty("addrName", submitOrderBean.getDefAddress().getAddrName());
            jo.addProperty("addrTel", submitOrderBean.getDefAddress().getAddrTel());
            jo.addProperty("isGroup", "0");  //0是普通商品  // 1是拼伙商品
            jo.addProperty("jewelAmount", submitOrderBean.getJewel() + "");
            jo.addProperty("couponId", couponId_result);
            jo.addProperty("paymentWay", paymentWay);

            JsonArray ja = new JsonArray();
            for (int i = 0; i < shopcartList.size(); i++) {
                JsonObject jo1 = new JsonObject();
                jo1.addProperty("buyCartId", shopcartList.get(i).getBuyCartId());
                jo1.addProperty("goodsAccant", shopcartList.get(i).getGoodsAccant());
                ja.add(jo1);
            }
            jo.add("buycartls", ja);

            LogUtil.e("hahahahahahahah  ", jo.toString());

            OkHttpUtils.postString()
                    .url(ApiConstants.SHOPCAR_SUBMIT)
                    .content(jo.toString())
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogUtil.e("提交订单response", e + "");
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            LogUtil.e("提交订单response", response);
                        }
                    });


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COUPON && resultCode == 1) {

            couponId_result = data.getStringExtra("couponId");
            downPrice_result = data.getStringExtra("downPrice");

            tvDiscountCoupon.setText(getResources().getString(R.string.RMB) + downPrice_result);

            LogUtil.e("couponId_result", couponId_result);
        }

    }
}
