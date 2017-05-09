package com.yxk.tjm.tianjiumeng.home.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
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

import com.google.gson.Gson;
import com.squareup.otto.Subscribe;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.activity.SubmitOrderActivity;
import com.yxk.tjm.tianjiumeng.custom.AutoHeightViewPager;
import com.yxk.tjm.tianjiumeng.custom.BottomPopPinHuo;
import com.yxk.tjm.tianjiumeng.custom.MyNestedScrollView;
import com.yxk.tjm.tianjiumeng.event.BusProvider;
import com.yxk.tjm.tianjiumeng.event.EventOne;
import com.yxk.tjm.tianjiumeng.home.bean.HotStrugDetailBean;
import com.yxk.tjm.tianjiumeng.home.fragment.DesignIdeaFragment;
import com.yxk.tjm.tianjiumeng.home.fragment.ProductDetailFragment;
import com.yxk.tjm.tianjiumeng.home.fragment.StandardFragment;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.GlideImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class HotStrugDetailActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvTitle;
    private TextView mTvIntroduce;
    private TextView tv_tatalStore;
    private TextView tv_original_price;
    private TextView mTvPrice;
    private TextView tv_add_shopcart;
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
    private List<String> images;
    private String productId;
    private HotStrugDetailBean hotStrugDetailBean;
    private SwipeRefreshLayout swipe_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_strug_detail);
        App.getActivityManager().pushActivity(this);
        BusProvider.getInstance().register(this);//订阅事件

        productId = getIntent().getStringExtra("productId");

        fragments = new ArrayList<>();

        Bundle bundle = new Bundle();
        bundle.putString("productId", String.valueOf(productId));

        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
        productDetailFragment.setArguments(bundle);

        DesignIdeaFragment designIdeaFragment = new DesignIdeaFragment();
        designIdeaFragment.setArguments(bundle);

        StandardFragment standardFragment = new StandardFragment();
        standardFragment.setArguments(bundle);

        fragments.add(productDetailFragment);
        fragments.add(designIdeaFragment);
        fragments.add(standardFragment);


        titles = new ArrayList<>();
        titles.add("商品详情");
        titles.add("设计理念");
        titles.add("商品规格");

        images = new ArrayList<>();

        initView();
        initData();
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvIntroduce = (TextView) findViewById(R.id.tv_introduce);
        tv_tatalStore = (TextView) findViewById(R.id.tv_tatalStore);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        tv_add_shopcart = (TextView) findViewById(R.id.tv_add_shopcart);
        tv_original_price = (TextView) findViewById(R.id.tv_original_price);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (AutoHeightViewPager) findViewById(R.id.view_pager);
        mImgPicture = (ImageView) findViewById(R.id.img_picture);
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbarNavigationClick();
        mMyNestedScroll = (MyNestedScrollView) findViewById(R.id.my_nested_scroll);
        mBanner = (Banner) findViewById(R.id.banner);
        btn_buy = (Button) findViewById(R.id.btn_buy);
        swipe_refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        tv_original_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);  //添加删除线

        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });

        btn_buy.setOnClickListener(this);
        tv_add_shopcart.setOnClickListener(this);
    }

    private void initData() {

        OkHttpUtils.get()
                .url(ApiConstants.HOME_PINHUO_DETAIL)
                .addParams("productId", productId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        hotStrugDetailBean = new Gson().fromJson(response, HotStrugDetailBean.class);
                        mTvTitle.setText(hotStrugDetailBean.getProductDetail().getName());
                        mTvIntroduce.setText(hotStrugDetailBean.getProductDetail().getDescription());
                        tv_tatalStore.setText("库存：" + hotStrugDetailBean.getTatalStore());
                        mTvPrice.setText(getResources().getString(R.string.RMB) + hotStrugDetailBean.getProductDetail().getNowprice());

                        images.clear();
                        for (int i = 0; i < hotStrugDetailBean.getCrclphotos().size(); i++) {
                            images.add(hotStrugDetailBean.getCrclphotos().get(i).getGoodsPic());
                        }
                        initBanner(images);

                        swipe_refresh.setRefreshing(false);
                    }
                });

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

    private void initBanner(List<String> images) {
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
                BottomPopPinHuo.COMMON = BottomPopPinHuo.BUY;
                showBottomPopupWindow();
                break;

            case R.id.tv_add_shopcart:
                BottomPopPinHuo.COMMON = BottomPopPinHuo.ADD_SHOP_CART;
                showBottomPopupWindow();
                break;
        }
    }

    private void showBottomPopupWindow() {
        BottomPopPinHuo bottomPop = new BottomPopPinHuo(this, hotStrugDetailBean.getHWs(), productId, hotStrugDetailBean.getProductDetail().getNowprice());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);//注销订阅
    }

    @Subscribe
    public void EventOtto(EventOne eventOne) {
        switch (eventOne.getEvent()) {
            case "跳转提交订单页面":
                Intent intent = new Intent(HotStrugDetailActivity.this, SubmitOrderActivity.class);
                intent.putExtra("amount", 1);
                intent.putExtra("totalPrice", Integer.parseInt(eventOne.getAmount()) * (double) hotStrugDetailBean.getProductDetail().getNowprice());
                startActivity(intent);
                break;
        }
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
