package com.yxk.tjm.tianjiumeng.my.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.my.bean.LogistBean;
import com.yxk.tjm.tianjiumeng.my.bean.TrackBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class SeeLogisticsActivity extends BaseActivity {

    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.tv_company_no)
    TextView tvCompanyNo;
    @BindView(R.id.tv_company_phone)
    TextView tvCompanyPhone;
    @BindView(R.id.tv_state)
    TextView tvState;
    private String company;
    private String company_code;
    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_logistics);
        ButterKnife.bind(this);

        orderId = getIntent().getStringExtra("orderId");

        initData();

        setToolbarNavigationClick();
    }

    private void initData() {
        OkHttpUtils.get()
                .url(ApiConstants.MY_QRY_LOGISTICS)
                .addParams("orderId", orderId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogistBean logistBean = new Gson().fromJson(response, LogistBean.class);
                        company = logistBean.getDeliverCode();
                        company_code = logistBean.getDeliverNo();

                        tvCompany.setText(company);
                        tvCompanyNo.setText(company_code);

                        /**请求数据获取物流公司和单号*/
                        getCompanyAndCode();
                    }
                });
    }

    private void getCompanyAndCode() {
        KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
        try {
            api.getOrderTracesByJson(company, company_code); //快递公司缩写，快递单号
        } catch (Exception e) {
            e.printStackTrace();
        }
        api.SetResultCallListener(new KdniaoTrackQueryAPI.ResultCallListener() {
            @Override
            public void result(TrackBean bean) {
                if (bean.getTraces().size() == 0 || bean.getTraces() == null) {
                    tvState.setText("未查询到信息");
                }
                recycler.setAdapter(new MyAdapter(bean));
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

    private class MyAdapter extends RecyclerView.Adapter<MyHolder> {
        TrackBean trackBean;

        public MyAdapter(TrackBean trackBean) {
            this.trackBean = trackBean;
            Collections.reverse(this.trackBean.getTraces());
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.item_track, null);

            MyHolder holder = new MyHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            if (position == 0) {
                holder.mListitem_doing.setVisibility(View.VISIBLE);
                holder.mListitem_done.setVisibility(View.GONE);
                holder.tv_accept_time.setTextColor(Color.BLUE);
                holder.tv_accept_station.setTextColor(Color.BLUE);
                holder.tv_accept_time.setText(trackBean.getTraces().get(position).getAcceptTime());
                holder.tv_accept_station.setText(trackBean.getTraces().get(position).getAcceptStation());
            } else {
                holder.mListitem_doing.setVisibility(View.GONE);
                holder.mListitem_done.setVisibility(View.VISIBLE);
                holder.tv_accept_time.setTextColor(Color.BLACK);
                holder.tv_accept_station.setTextColor(Color.BLACK);
                holder.tv_accept_time.setText(trackBean.getTraces().get(position).getAcceptTime());
                holder.tv_accept_station.setText(trackBean.getTraces().get(position).getAcceptStation());
            }
        }

        @Override
        public int getItemCount() {
            return MyAdapter.this.trackBean.getTraces().size();
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private RelativeLayout mListitem_doing;
        private RelativeLayout mListitem_done;
        private TextView tv_accept_time;
        private TextView tv_accept_station;

        public MyHolder(View itemView) {
            super(itemView);
            mListitem_doing = (RelativeLayout) itemView.findViewById(R.id.listitem_doing);
            mListitem_done = (RelativeLayout) itemView.findViewById(R.id.listitem_done);
            tv_accept_time = (TextView) itemView.findViewById(R.id.tv_accept_time);
            tv_accept_station = (TextView) itemView.findViewById(R.id.tv_accept_station);

        }
    }
}
