package com.yxk.tjm.tianjiumeng.my.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;


public class WaitGetActivity extends BaseActivity {

    private RecyclerView recycler;
    private Toolbar mToolbar;
    private List<WaitPayBean> waitPayBeanList;
    private WaitGetAdapter waitGetAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_get);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbarNavigationClick();
        recycler = (RecyclerView) findViewById(R.id.recycler);

        initData();

        recycler.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()) {
                    case R.id.btn_see:
                        Intent intent = new Intent(WaitGetActivity.this, SeeLogisticsActivity.class);
                        intent.putExtra("orderId", waitPayBeanList.get(position).getOrderId());
                        startActivity(intent);
                        break;

                    case R.id.btn_confirm:
                        new AlertDialog.Builder(WaitGetActivity.this)
                                .setTitle("确定收货？")
                                .setPositiveButton("确定",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                confirmGet(position);
                                            }
                                        }).setNegativeButton("取消", null).create()
                                .show();
                        break;
                }
            }
        });

    }

    private void initData() {
        waitGetAdapter = new WaitGetAdapter();
        recycler.setAdapter(waitGetAdapter);

        OkHttpUtils.get()
                .url(ApiConstants.MY_ORDER)
                .addParams("userId", UserUtil.getUserId(App.getAppContext()))
                .addParams("state", "3")
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

                        waitGetAdapter.setNewData(waitPayBeanList);

                    }
                });
    }

    /**
     * 确认收货
     *
     * @param position
     */
    private void confirmGet(int position) {
        OkHttpUtils.get()
                .url(ApiConstants.MY_CONFIRM_LOGISTICS)
                .addParams("orderId", waitPayBeanList.get(position).getOrderId())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jo = new JSONObject(response);
                            if (0 == (int) jo.get("success")) {
                                initData();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }


    private class WaitGetAdapter extends BaseQuickAdapter<WaitPayBean, BaseViewHolder> {

        private WaitGetAdapter() {
            super(R.layout.item_wait_get, waitPayBeanList);
        }

        @Override
        protected void convert(BaseViewHolder helper, WaitPayBean item) {
            helper.addOnClickListener(R.id.btn_see);
            helper.addOnClickListener(R.id.btn_confirm);
            Glide.with(App.getAppContext()).load(item.getGoodsShowpic()).into((ImageView) helper.getView(R.id.img_pic));
            helper.setText(R.id.tv_date, DateUtil.longToString(item.getCreateDate(), "yyyy.MM.dd"));
            helper.setText(R.id.tv_name, item.getGoodsName());
            helper.setText(R.id.tv_num, "数量：" + item.getAmount() + "个");
            helper.setText(R.id.tv_orderId, "订单号：" + item.getOrderId());
            helper.setText(R.id.tv_price, getResources().getString(R.string.RMB) + item.getSkuPrice());
        }
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
