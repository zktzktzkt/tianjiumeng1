package com.yxk.tjm.tianjiumeng.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.custom.SpecialRecommendLayout;
import com.yxk.tjm.tianjiumeng.fragment.BaseFragment;
import com.yxk.tjm.tianjiumeng.home.activity.CrystalHouseActivity;
import com.yxk.tjm.tianjiumeng.home.activity.HotStrugActivity;
import com.yxk.tjm.tianjiumeng.home.activity.MyCustomActivity;
import com.yxk.tjm.tianjiumeng.home.activity.ProductDetailActivity;
import com.yxk.tjm.tianjiumeng.home.activity.SearchActivity;
import com.yxk.tjm.tianjiumeng.home.adapter.FlashSaleAdapter;
import com.yxk.tjm.tianjiumeng.home.bean.FlashSaleBean;
import com.yxk.tjm.tianjiumeng.utils.GlideImageLoader;
import com.yxk.tjm.tianjiumeng.utils.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.special_one)
    SpecialRecommendLayout specialOne;
    @BindView(R.id.special_two)
    SpecialRecommendLayout specialTwo;
    @BindView(R.id.special_three)
    SpecialRecommendLayout specialThree;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;

    private RecyclerView mRecyclerComeMarket;
    private Banner banner;
    private RecyclerView mRrecyclerFlashSale;
    private TextView mTvCrystalHouse;
    private TextView mTvHotStrug;
    private TextView mTvMyCustom;
    private SwipeRefreshLayout swipe_refresh;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);

        banner = (Banner) view.findViewById(R.id.banner);
        mRecyclerComeMarket = (RecyclerView) view.findViewById(R.id.recycler_come_market);
        mRrecyclerFlashSale = (RecyclerView) view.findViewById(R.id.recycler_flash_sale);
        mTvCrystalHouse = (TextView) view.findViewById(R.id.tv_crystal_house);
        mTvHotStrug = (TextView) view.findViewById(R.id.tv_hot_strug);
        mTvMyCustom = (TextView) view.findViewById(R.id.tv_my_custom);
        swipe_refresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);

        mTvCrystalHouse.setOnClickListener(this);
        mTvHotStrug.setOnClickListener(this);
        mTvMyCustom.setOnClickListener(this);
        rlSearch.setOnClickListener(this);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_refresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_refresh.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.pic_350w_1);
        images.add(R.drawable.pic_350w_2);
        images.add(R.drawable.pic_350w_3);

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Accordion);
        banner.start();

        //设置特别推荐的条目点击事件
        setSpecialItemClickListener();
    }


    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<FlashSaleBean> list = new ArrayList<>();
        list.add(new FlashSaleBean(R.drawable.pic_a));
        list.add(new FlashSaleBean(R.drawable.pic_b));
        list.add(new FlashSaleBean(R.drawable.pic_c));
        list.add(new FlashSaleBean(R.drawable.pic_d));
        list.add(new FlashSaleBean(R.drawable.pic_e));
        list.add(new FlashSaleBean(R.drawable.pic_f));

        LinearLayoutManager manager = new LinearLayoutManager(App.getAppContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerComeMarket.setLayoutManager(manager);
        mRecyclerComeMarket.setAdapter(new BaseQuickAdapter<FlashSaleBean, BaseViewHolder>(R.layout.item_new_come_market, list) {
            @Override
            protected void convert(BaseViewHolder helper, FlashSaleBean item) {
                // helper.getLayoutPosition()  //获取当前position
                helper.setImageResource(R.id.image_pic, item.getResImage());
            }
        });

        mRecyclerComeMarket.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), ProductDetailActivity.class));
            }
        });

        List<FlashSaleBean> list1 = new ArrayList<>();
        list1.add(new FlashSaleBean(R.drawable.pic_f));
        list1.add(new FlashSaleBean(R.drawable.pic_e));
        list1.add(new FlashSaleBean(R.drawable.pic_b));
        list1.add(new FlashSaleBean(R.drawable.pic_a));
        list1.add(new FlashSaleBean(R.drawable.pic_c));
        list1.add(new FlashSaleBean(R.drawable.pic_d));

        LinearLayoutManager manager1 = new LinearLayoutManager(App.getAppContext());
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRrecyclerFlashSale.setLayoutManager(manager1);
        FlashSaleAdapter flashSaleAdapter = new FlashSaleAdapter();
        flashSaleAdapter.setMatchData(list1);
        mRrecyclerFlashSale.setAdapter(flashSaleAdapter);

        flashSaleAdapter.setOnItemClickListener(new FlashSaleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("flashSale", "flashSale");
                startActivity(intent);
            }
        });
    }

    private void setSpecialItemClickListener() {
        specialOne.setIvPic2(R.drawable.pic_e);
        specialOne.setIvPic2(R.drawable.pic_g);
        specialOne.setIvPic3(R.drawable.pic_c);

        specialOne.setOnItemClickListener(new SpecialRecommendLayout.OnItemClickListener() {
            @Override
            public void onItemZeroClick() {
                T.showShort(getContext(), "zero");//
            }

            @Override
            public void onItemOneClick() {
                startActivity(new Intent(getActivity(), ProductDetailActivity.class));
            }

            @Override
            public void onItemTwoClick() {
                startActivity(new Intent(getActivity(), ProductDetailActivity.class));
            }

            @Override
            public void onItemThreeClick() {
                startActivity(new Intent(getActivity(), ProductDetailActivity.class));
            }
        });


        specialTwo.setIvPic1(R.drawable.pic_d);
        specialTwo.setIvPic2(R.drawable.pic_e);
        specialTwo.setIvPic3(R.drawable.pic_f);
        specialTwo.setOnItemClickListener(new SpecialRecommendLayout.OnItemClickListener() {
            @Override
            public void onItemZeroClick() {
                T.showShort(getContext(), "zero");
            }

            @Override
            public void onItemOneClick() {
                startActivity(new Intent(getActivity(), ProductDetailActivity.class));
            }

            @Override
            public void onItemTwoClick() {
                startActivity(new Intent(getActivity(), ProductDetailActivity.class));
            }

            @Override
            public void onItemThreeClick() {
                startActivity(new Intent(getActivity(), ProductDetailActivity.class));
            }
        });


        specialThree.setIvPic1(R.drawable.pic_f);
        specialThree.setIvPic2(R.drawable.pic_g);
        specialThree.setIvPic3(R.drawable.pic_h);
        specialThree.setOnItemClickListener(new SpecialRecommendLayout.OnItemClickListener() {
            @Override
            public void onItemZeroClick() {
                T.showShort(getContext(), "zero");
            }

            @Override
            public void onItemOneClick() {
                startActivity(new Intent(getActivity(), ProductDetailActivity.class));
            }

            @Override
            public void onItemTwoClick() {
                startActivity(new Intent(getActivity(), ProductDetailActivity.class));
            }

            @Override
            public void onItemThreeClick() {
                startActivity(new Intent(getActivity(), ProductDetailActivity.class));
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_crystal_house:
                startActivity(new Intent(getActivity(), CrystalHouseActivity.class));
                break;
            case R.id.tv_hot_strug:
                startActivity(new Intent(getActivity(), HotStrugActivity.class));
                break;
            case R.id.tv_my_custom:
                startActivity(new Intent(getActivity(), MyCustomActivity.class));
                break;
            case R.id.rl_search:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                getActivity().overridePendingTransition(R.anim.enter_alpha, R.anim.out_alpha);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

}
