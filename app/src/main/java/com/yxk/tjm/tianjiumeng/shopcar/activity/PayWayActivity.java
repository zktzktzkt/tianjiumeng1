package com.yxk.tjm.tianjiumeng.shopcar.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.utils.To;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayWayActivity extends BaseActivity {

    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;
    @BindView(R.id.rl_weixin)
    RelativeLayout rlWeixin;
    @BindView(R.id.iv_alipay)
    ImageView ivAlipay;
    @BindView(R.id.rl_alipay)
    RelativeLayout rlAlipay;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_way);
        ButterKnife.bind(this);

        setToolbarNavigationClick();
    }

    @OnClick({R.id.rl_weixin, R.id.rl_alipay, R.id.btn_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_weixin:
                ivWeixin.setSelected(true);
                ivAlipay.setSelected(false);
                break;

            case R.id.rl_alipay:
                ivAlipay.setSelected(true);
                ivWeixin.setSelected(false);
                break;

            case R.id.btn_recharge:
                To.showShort(getApplicationContext(), "提交");
                break;
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
}
