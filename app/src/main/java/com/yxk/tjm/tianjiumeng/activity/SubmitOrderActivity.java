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

import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.my.activity.AddressActivity;
import com.yxk.tjm.tianjiumeng.shopcar.activity.PayWayActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.cb_cystal)
    CheckBox cbCystal;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);

        setToolbarNavigationClick();
    }

    private void setToolbarNavigationClick() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @OnClick({R.id.rl_person_info, R.id.btn_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_person_info:
                startActivity(new Intent(SubmitOrderActivity.this, AddressActivity.class));
                break;
            case R.id.btn_submit:
                startActivity(new Intent(SubmitOrderActivity.this, PayWayActivity.class));
                break;
        }
    }
}
