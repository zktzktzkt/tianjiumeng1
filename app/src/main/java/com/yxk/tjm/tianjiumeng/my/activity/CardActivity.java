package com.yxk.tjm.tianjiumeng.my.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.my.bean.CardBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.DateUtil;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class CardActivity extends BaseActivity {
    private static final String TAG = "CardActivity ";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private CardBean cardBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        App.getActivityManager().pushActivity(this);

        ButterKnife.bind(this);

        OkHttpUtils.get()
                .url(ApiConstants.MY_COUPON)
                .addParams("userId", UserUtil.getUserId(getApplicationContext()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e(TAG, "卡券包数据：Exception: " + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e(TAG, "卡券包数据：" + response);
                        cardBean = new Gson().fromJson(response, CardBean.class);

                        recycler.setAdapter(new BaseQuickAdapter<CardBean.CouponBean, BaseViewHolder>(R.layout.item_card, cardBean.getCoupon()) {
                            @Override
                            protected void convert(BaseViewHolder helper, CardBean.CouponBean item) {
                                helper.setText(R.id.tv_price, item.getDownPrice()+"");
                                helper.setText(R.id.tv_time, DateUtil.longToString(item.getStartTime(), "yyyy-MM-dd")
                                        + "至" + DateUtil.longToString(item.getFinalTime(), "yyyy-MM-dd"));
                                helper.setText(R.id.tv_condition, "满" + item.getFullBuyPrice() + "使用");
                            }
                        });
                    }
                });


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


}
