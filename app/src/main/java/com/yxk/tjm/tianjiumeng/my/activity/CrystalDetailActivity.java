package com.yxk.tjm.tianjiumeng.my.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.my.bean.CrystalDetailBean;
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

public class CrystalDetailActivity extends BaseActivity {
    private static final String TAG = "CrystalDetailActivity ";

    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private List<CrystalDetailBean> crystalDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_pay);
        ButterKnife.bind(this);
        setToolbarNavigationClick();

        tvToolbar.setText("水晶钻明细");

        OkHttpUtils.get()
                .url(ApiConstants.MY_JEWEL_DETAIL)
                .addParams("userId", UserUtil.getUserId(getApplicationContext()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e(TAG, "水晶钻明细 Exception: " + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e(TAG, "水晶钻明细:" + response);

                        crystalDetailList = new Gson().fromJson(response, new TypeToken<List<CrystalDetailBean>>() {
                        }.getType());

                        recycler.setAdapter(new BaseQuickAdapter<CrystalDetailBean, BaseViewHolder>(R.layout.item_crystal_detail, crystalDetailList) {
                            @Override
                            protected void convert(BaseViewHolder helper, CrystalDetailBean item) {
                                helper.setText(R.id.tv_monthday, DateUtil.longToString(item.getCreatetime(), "yyyy-MM-dd"));
                                helper.setText(R.id.tv_hourMinute, DateUtil.longToString(item.getCreatetime(), "HH:mm"));
                                helper.setText(R.id.tv_price, item.getDecr());
                                helper.setText(R.id.tv_money, item.getAmount());
                            }
                        });
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
