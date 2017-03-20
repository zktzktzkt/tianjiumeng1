package com.yxk.tjm.tianjiumeng.home.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.custom.AutoHeightViewPager;
import com.yxk.tjm.tianjiumeng.custom.BottomPop;
import com.yxk.tjm.tianjiumeng.custom.MyNestedScrollView;
import com.yxk.tjm.tianjiumeng.home.adapter.RecommendForYouAdapter;
import com.yxk.tjm.tianjiumeng.home.fragment.ProductDetailFragment;
import com.yxk.tjm.tianjiumeng.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotStrugDetailActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvTitle;
    private TextView mTvIntroduce;
    private TextView mTvPrice;
    private TabLayout mTabLayout;
    private AutoHeightViewPager mViewPager;
    private ImageView mImgPicture;
    private RecyclerView mRecycler;
    private List<Fragment> fragments;
    private List<String> titles;
    private Toolbar mToolbar;
    private MyNestedScrollView mMyNestedScroll;
    private Banner mBanner;
    private Button btn_buy;
    private List<Integer> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_strug_detail);

        fragments = new ArrayList<>();
        fragments.add(new ProductDetailFragment());
        fragments.add(new ProductDetailFragment());
        fragments.add(new ProductDetailFragment());

        titles = new ArrayList<>();
        titles.add("商品详情");
        titles.add("设计理念");
        titles.add("商品规格");

        images = new ArrayList<>();
        images.add(R.drawable.pic_e);
        images.add(R.drawable.pic_a);
        images.add(R.drawable.pic_b);
        images.add(R.drawable.pic_c);

        initView();
        initData();
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvIntroduce = (TextView) findViewById(R.id.tv_introduce);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (AutoHeightViewPager) findViewById(R.id.view_pager);
        mImgPicture = (ImageView) findViewById(R.id.img_picture);
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbarNavigationClick();
        mMyNestedScroll = (MyNestedScrollView) findViewById(R.id.my_nested_scroll);
        mBanner = (Banner) findViewById(R.id.banner);
        btn_buy = (Button) findViewById(R.id.btn_buy);

        btn_buy.setOnClickListener(this);
    }

    private void initData() {
        initBanner();

        initRecommendForYouRecycler();
        mViewPager.setAdapter(fragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setToolbarNavigationClick() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initRecommendForYouRecycler() {
        mRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        RecommendForYouAdapter recommendForYouAdapter = new RecommendForYouAdapter();
        recommendForYouAdapter.setMatchData(Arrays.asList(R.drawable.pic_a, R.drawable.pic_a, R.drawable.pic_a, R.drawable.pic_a, R.drawable.pic_a, R.drawable.pic_a, R.drawable.pic_a));
        mRecycler.setAdapter(recommendForYouAdapter);
    }

    private void initBanner() {
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(images);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Accordion);
        mBanner.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMyNestedScroll.setTitleAndHead(mToolbar, mBanner);
        mToolbar.getBackground().setAlpha(0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mToolbar.getBackground().setAlpha(255);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_buy:
                showBottomPopupWindow();
                break;
        }
    }

    private void showBottomPopupWindow() {
        BottomPop bottomPop = new BottomPop(this);
        //      设置Popupwindow显示位置（从底部弹出）
        bottomPop.showAtLocation(findViewById(R.id.my_nested_scroll), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.alpha = 0.7f;
        window.setAttributes(wl);

        bottomPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Window window = getWindow();
                WindowManager.LayoutParams wl = window.getAttributes();
                wl.alpha = 1f;   //这句就是设置窗口里崆件的透明度的．0.0全透明．1.0不透明．
                window.setAttributes(wl);
            }
        });
    }

    private FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    };

}
