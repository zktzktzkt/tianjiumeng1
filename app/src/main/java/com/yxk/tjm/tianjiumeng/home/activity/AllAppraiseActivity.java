package com.yxk.tjm.tianjiumeng.home.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.home.bean.AllAppraiseBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.DateUtil;
import com.yxk.tjm.tianjiumeng.utils.NumberFormatUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class AllAppraiseActivity extends BaseActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private String productId;
    private AllAppraiseAdapter allAppraiseAdapter;
    private List<AllAppraiseBean> allAppraiseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_appraise);
        ButterKnife.bind(this);
        setToolbarNavigationClick();

        productId = getIntent().getStringExtra("productId");

        initData();

 /*       allAppraiseAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        }, recycler);

        allAppraiseAdapter.setLoadMoreView(new CustomLoadMoreView());
        allAppraiseAdapter.loadMoreEnd(false);*/

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
    }

    private void initData() {
        OkHttpUtils.get()
                .url(ApiConstants.DETAIL_PAGE_All_REVIEW)
                .addParams("productId", productId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        swipeRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        allAppraiseList = new Gson().fromJson(response, new TypeToken<List<AllAppraiseBean>>() {
                        }.getType());

                        allAppraiseAdapter = new AllAppraiseAdapter(allAppraiseList);
                        recycler.setAdapter(allAppraiseAdapter);
                        swipeRefresh.setRefreshing(false);
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

    public class AllAppraiseAdapter extends BaseQuickAdapter<AllAppraiseBean, BaseViewHolder> {
        public AllAppraiseAdapter(List<AllAppraiseBean> allAppraiseList) {
            super(R.layout.item_all_appraise, allAppraiseList);
        }

        @Override
        protected void convert(BaseViewHolder helper, AllAppraiseBean item) {
            Glide.with(AllAppraiseActivity.this).load(item.getAvatar()).into((ImageView) helper.getView(R.id.img_client_head));
            helper.setText(R.id.tv_client_name, NumberFormatUtils.phoneHide(item.getPhoneNumber()));
            helper.setText(R.id.tv_client_comment, item.getReviewText());
            ((RatingBar) helper.getView(R.id.ratingbar)).setRating((float) item.getSatisfyNo());
            helper.setText(R.id.tv_id, "ID:" + item.getUserId());
            helper.setText(R.id.tv_date, DateUtil.longToString(item.getReviewTime(), "yyyy-MM-dd"));

            RecyclerView recyclerView = helper.getView(R.id.recycler_pic);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AllAppraiseActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);

            recyclerView.setAdapter(new BaseQuickAdapter<AllAppraiseBean.ReviewPicsBean, BaseViewHolder>
                    (R.layout.item_picture, item.getReviewPics()) {
                @Override
                protected void convert(BaseViewHolder helper, AllAppraiseBean.ReviewPicsBean item) {
                    Glide.with(AllAppraiseActivity.this).load(item.getReviewPic()).into((ImageView) helper.getView(R.id.img_pic));
                }
            });
        }
    }


}
