package com.yxk.tjm.tianjiumeng.news;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.sunfusheng.marqueeview.MarqueeView;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.custom.CustomLoadMoreView;
import com.yxk.tjm.tianjiumeng.network.Url;
import com.yxk.tjm.tianjiumeng.news.activity.NewsDetailActivity;
import com.yxk.tjm.tianjiumeng.news.bean.NewsBean;
import com.yxk.tjm.tianjiumeng.utils.T;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    private MarqueeView marqueeView;
    private RecyclerView recycler;
    private NewsBean newsBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        marqueeView = (MarqueeView) view.findViewById(R.id.marqueeView);
        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        OkHttpUtils.get()
                .url(Url.NEWS)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        newsBean = gson.fromJson(response, NewsBean.class);
                        initData();
                    }
                });


    }

    private void initData() {
        List<String> publiclist = new ArrayList<>();
        for (int i = 0; i < newsBean.getPubliclist().size(); i++) {
            publiclist.add(newsBean.getPubliclist().get(i).getPublicpic());
        }

        marqueeView.startWithList(publiclist);

        recycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        NewsAdapter newsAdapter = new NewsAdapter();
        newsAdapter.setLoadMoreView(new CustomLoadMoreView());
        recycler.setAdapter(newsAdapter);
        recycler.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), NewsDetailActivity.class));
            }
        });

        newsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                T.showShort(getContext(), "LoadMoreListener");
            }
        }, recycler);

        //newsAdapter.setEnableLoadMore(true);
        // newsAdapter.loadMoreComplete();
        // newsAdapter.loadMoreFail();
        newsAdapter.loadMoreEnd(false);
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
