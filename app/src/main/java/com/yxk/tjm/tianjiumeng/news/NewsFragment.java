package com.yxk.tjm.tianjiumeng.news;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.sunfusheng.marqueeview.MarqueeView;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.custom.CustomLoadMoreView;
import com.yxk.tjm.tianjiumeng.fragment.BaseFragment;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.news.activity.NewsDetailActivity;
import com.yxk.tjm.tianjiumeng.news.bean.NewsBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment {

    private MarqueeView marqueeView;
    private RecyclerView recycler;
    private NewsBean newsBean;
    private NewsAdapter newsAdapter;
    private int pageCount = 2;


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView(View view) {
        marqueeView = (MarqueeView) view.findViewById(R.id.marqueeView);
        recycler = (RecyclerView) view.findViewById(R.id.recycler);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setData();

        recycler.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), NewsDetailActivity.class));
            }
        });


    }

    /**
     * 可见不可见状态改变时调用
     *
     * @param hidden 可见时为true
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && isResumed()) {
            setData();
        } else {
            marqueeView.stopFlipping();
        }
    }

    /**
     * 设置数据
     */
    private void setData() {
        recycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        OkHttpUtils.get()
                .url(ApiConstants.NEWS_PAGE)
                .addParams("pageNo", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        newsAdapter.loadMoreFail();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        newsBean = gson.fromJson(response, NewsBean.class);

                        List<String> publiclist = new ArrayList<>();

                        for (int i = 0; i < newsBean.getPubliclist().size(); i++) {
                            publiclist.add(newsBean.getPubliclist().get(i).getPublicpic());
                        }

                        marqueeView.startWithList(publiclist);

                        newsAdapter = new NewsAdapter();
                        recycler.setAdapter(newsAdapter);
                        newsAdapter.setLoadMoreView(new CustomLoadMoreView());
                        newsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                            @Override
                            public void onLoadMoreRequested() {
                                loadMore(pageCount);
                            }
                        }, recycler);
                    }
                });
    }

    /**
     * 加载更多
     *
     * @param pageNo
     */
    private void loadMore(int pageNo) {
        OkHttpUtils.get()
                .url(ApiConstants.NEWS_PAGE)
                .addParams("pageNo", pageNo + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        newsAdapter.loadMoreFail();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        NewsBean newsBean = new Gson().fromJson(response, NewsBean.class);
                        try {
                            JSONObject jo = new JSONObject(response);
                            if ("[]".equals(jo.get("newslist").toString())) {
                                newsAdapter.loadMoreEnd(false);
                            } else {
                                newsAdapter.addData(newsBean.getNewslist());
                                pageCount++;
                                newsAdapter.loadMoreComplete();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        marqueeView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        marqueeView.stopFlipping();
    }

    public class NewsAdapter extends BaseQuickAdapter<NewsBean.NewslistBean, BaseViewHolder> {

        public NewsAdapter() {
            super(R.layout.item_news, newsBean.getNewslist());
        }

        @Override
        protected void convert(BaseViewHolder helper, NewsBean.NewslistBean item) {
            helper.setText(R.id.tv_title, item.getInfoTopic());
            Glide.with(getActivity()).load(item.getInfoShowPic()).into((ImageView) helper.getView(R.id.img_pic));
            String time = DateUtils.formatDateTime(getContext(), item.getInfoDate(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR);
            helper.setText(R.id.tv_date, time);
        }
    }

}
