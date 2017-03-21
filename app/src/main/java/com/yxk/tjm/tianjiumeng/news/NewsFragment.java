package com.yxk.tjm.tianjiumeng.news;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.sunfusheng.marqueeview.MarqueeView;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.custom.CustomLoadMoreView;
import com.yxk.tjm.tianjiumeng.news.activity.NewsDetailActivity;
import com.yxk.tjm.tianjiumeng.news.bean.NewsBean;
import com.yxk.tjm.tianjiumeng.utils.T;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    private MarqueeView marqueeView;
    private RecyclerView recycler;
    private List<NewsBean> newsBeanList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        marqueeView = (MarqueeView) view.findViewById(R.id.marqueeView);
        recycler = (RecyclerView) view.findViewById(R.id.recycler);

        initData();
        return view;
    }

    private void initData() {
        newsBeanList = new ArrayList<>();
        newsBeanList.add(new NewsBean(R.drawable.pic_news_1, "一年一度的元宵节在北京玉渊潭公园举行一年一度的元宵节在北京玉渊潭公园举行一年一度的元宵节在北京玉渊潭公园举行", "2016-11-4"));
        newsBeanList.add(new NewsBean(R.drawable.pic_news_2, "一年一度的元宵节在北京玉渊潭公园举行", "2016-11-4"));
        newsBeanList.add(new NewsBean(R.drawable.pic_news_2, "一年一度的元宵节在北京玉渊潭公园举行", "2016-11-4"));
        newsBeanList.add(new NewsBean(R.drawable.pic_news_2, "一年一度的元宵节在北京玉渊潭公园举行一年一度的元宵节在北京玉渊潭公园举行一年一度的元宵节在北京玉渊潭公园举行", "2016-11-4"));
        newsBeanList.add(new NewsBean(R.drawable.pic_news_1, "一年一度的元宵节在北京玉渊潭公园举行", "2016-11-4"));
        newsBeanList.add(new NewsBean(R.drawable.pic_news_1, "一年一度的元宵节在北京玉渊潭公园举行一年一度的元宵节在北京玉渊潭公园举行一年一度的元宵节在北京玉渊潭公园举行", "2016-11-4"));
        newsBeanList.add(new NewsBean(R.drawable.pic_news_1, "一年一度的元宵节在北京玉渊潭公园举行", "2016-11-4"));

        List<String> marqueeList = new ArrayList<>();
        marqueeList.add("一年一度的元宵节在北京玉渊潭公园举行1");
        marqueeList.add("一年一度的元宵节在北京玉渊潭公园举行2");
        marqueeList.add("一年一度的元宵节在北京玉渊潭公园举行3");
        marqueeList.add("一年一度的元宵节在北京玉渊潭公园举行4");

        marqueeView.startWithList(marqueeList);

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

    public class NewsAdapter extends BaseQuickAdapter<NewsBean, BaseViewHolder> {

        public NewsAdapter() {
            super(R.layout.item_news, newsBeanList);
        }

        @Override
        protected void convert(BaseViewHolder helper, NewsBean item) {
            helper.setText(R.id.tv_title, item.getTitle());
            helper.setImageResource(R.id.img_pic, item.getResPicId());
        }
    }

}
