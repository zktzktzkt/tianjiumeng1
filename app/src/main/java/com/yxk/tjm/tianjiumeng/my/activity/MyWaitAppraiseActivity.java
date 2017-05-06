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
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class MyWaitAppraiseActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private List<WaitPayBean> waitPayBeanList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appraise);
        ButterKnife.bind(this);
        setToolbarNavigationClick();

        OkHttpUtils.get()
                .url(ApiConstants.MY_ORDER)
                .addParams("userId", UserUtil.getUserId(App.getAppContext()))
                .addParams("state", "4")
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

                        recycler.setAdapter(new BaseQuickAdapter<WaitPayBean, BaseViewHolder>(R.layout.item_wait_appraise, waitPayBeanList) {
                            @Override
                            protected void convert(BaseViewHolder helper, WaitPayBean item) {
                                // helper.addOnClickListener(R.id.img_pic);
                                helper.addOnClickListener(R.id.btn_appraise);
                                Glide.with(App.getAppContext()).load(item.getGoodsShowpic()).into((ImageView) helper.getView(R.id.img_pic));
                                helper.setText(R.id.tv_date, DateUtil.longToString(item.getCreateDate(), "yyyy.MM.dd"));
                                helper.setText(R.id.tv_orderId, "订单号：" + item.getOrderId());
                                helper.setText(R.id.tv_return_size, "尺寸：" + item.getSize() + "cm");
                                helper.setText(R.id.tv_num, "数量：" + item.getAmount() + "个");
                                helper.setText(R.id.tv_price, getResources().getString(R.string.RMB) + item.getSkuPrice());
                            }
                        });
                    }
                });

        recycler.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.img_pic:
                        //startActivity(new Intent(MyWaitAppraiseActivity.this, ProductDetailActivity.class));
                        break;
                    case R.id.btn_appraise:
                        Intent intent = new Intent(MyWaitAppraiseActivity.this, ImmediateAppraiseActivity.class);
                        intent.putExtra("goodsId", waitPayBeanList.get(position).getGoodsId());
                        intent.putExtra("orderId", waitPayBeanList.get(position).getOrderId());
                        startActivity(intent);
                        break;
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
}
