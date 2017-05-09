package com.yxk.tjm.tianjiumeng.my.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.my.bean.WaitPayBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.DateUtil;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class SaleAfterActivity extends BaseActivity {

    private RecyclerView recycler;
    private Toolbar mToolbar;
    private List<WaitPayBean> waitPayBeanList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_after);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbarNavigationClick();
        recycler = (RecyclerView) findViewById(R.id.recycler);

        OkHttpUtils.get()
                .url(ApiConstants.MY_ORDER)
                .addParams("userId", UserUtil.getUserId(App.getAppContext()))
                .addParams("state", "6")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("WaitPayActivity response:", response);
                        waitPayBeanList = new Gson().fromJson(response, new TypeToken<List<WaitPayBean>>() {
                        }.getType());

                        recycler.setAdapter(new BaseQuickAdapter<WaitPayBean, BaseViewHolder>(R.layout.item_sale_after, waitPayBeanList) {
                            @Override
                            protected void convert(BaseViewHolder helper, WaitPayBean item) {
                                Glide.with(App.getAppContext()).load(item.getGoodsShowpic()).into((ImageView) helper.getView(R.id.img_pic));
                                helper.setText(R.id.tv_date, DateUtil.longToString(item.getCreateDate(), "yyyy.MM.dd"));
                                helper.setText(R.id.tv_orderId, "订单号：" + item.getOrderId());
                                helper.setText(R.id.tv_name, item.getGoodsName());
                                helper.setText(R.id.tv_num, "数量：" + item.getAmount() + "个");
                                helper.setText(R.id.tv_price, getResources().getString(R.string.RMB) + item.getSkuPrice());
                            }
                        });
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