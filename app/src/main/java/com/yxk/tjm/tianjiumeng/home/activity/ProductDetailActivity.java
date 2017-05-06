package com.yxk.tjm.tianjiumeng.home.activity;

import android.animation.ObjectAnimator;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.otto.Subscribe;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.custom.AutoHeightViewPager;
import com.yxk.tjm.tianjiumeng.custom.BottomPop;
import com.yxk.tjm.tianjiumeng.custom.CustomPopWindow;
import com.yxk.tjm.tianjiumeng.custom.MyNestedScrollView;
import com.yxk.tjm.tianjiumeng.event.BusProvider;
import com.yxk.tjm.tianjiumeng.event.EventOne;
import com.yxk.tjm.tianjiumeng.home.bean.ProductDetailBeannn;
import com.yxk.tjm.tianjiumeng.home.fragment.DesignIdeaFragment;
import com.yxk.tjm.tianjiumeng.home.fragment.ProductDetailFragment;
import com.yxk.tjm.tianjiumeng.home.fragment.StandardFragment;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.GlideImageLoader;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.To;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.iwgang.countdownview.CountdownView;
import cn.iwgang.countdownview.DynamicConfig;
import okhttp3.Call;

/**
 * 商品详情页
 */
public class ProductDetailActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "ProductDetailActivity ";
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
    private RelativeLayout mRelativeProductSpec;
    private TextView mTvCollect;
    private TextView mTvAddShopcart;
    private Button mBtnBuy;
    private RelativeLayout relative_bottom;
    private TextView tv_original_price;
    private TextView tv_money_day;
    private TextView tv_money_percent;
    private CustomPopWindow customPopWindow;
    private LayoutInflater layoutInflater;
    private View rebateView;
    private TextView tv_content;
    private TextView tv_rebate_info;
    private TextView ms_price;
    private TextView tv_total_store;
    private CountdownView cv_countdownView;
    private String flashSale;
    private String productId;
    private final int RED = 0;
    private final int WHITE = 1;
    private DynamicConfig.Builder builder;
    private DynamicConfig build;
    private ProductDetailBeannn productDetailBean;
    private SwipeRefreshLayout swipe_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        App.getActivityManager().pushActivity(this);
        BusProvider.getInstance().register(this);//订阅事件

        layoutInflater = LayoutInflater.from(this);

        flashSale = getIntent().getStringExtra("flashSale");
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

        initView();

   /*     if (TextUtils.isEmpty(flashSale)) {
            cv_countdownView.setVisibility(View.GONE);
        } else {
            ms_price.setVisibility(View.VISIBLE);
            cv_countdownView.start(5 * 60 * 1000);
            cv_countdownView.setVisibility(View.VISIBLE);
        }
        */
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
        mMyNestedScroll = (MyNestedScrollView) findViewById(R.id.my_nested_scroll);
        mBanner = (Banner) findViewById(R.id.banner);
        mRelativeProductSpec = (RelativeLayout) findViewById(R.id.relative_product_spec);
        mTvCollect = (TextView) findViewById(R.id.tv_collect);
        mTvAddShopcart = (TextView) findViewById(R.id.tv_add_shopcart);
        mBtnBuy = (Button) findViewById(R.id.btn_buy);
        relative_bottom = (RelativeLayout) findViewById(R.id.relative_bottom);
        tv_original_price = (TextView) findViewById(R.id.tv_original_price);
        tv_money_day = (TextView) findViewById(R.id.tv_money_day);
        tv_money_percent = (TextView) findViewById(R.id.tv_money_percent);
        tv_rebate_info = (TextView) findViewById(R.id.tv_rebate_info);
        ms_price = (TextView) findViewById(R.id.tv_ms_price);
        tv_total_store = (TextView) findViewById(R.id.tv_total_store);
        cv_countdownView = (CountdownView) findViewById(R.id.cv_countdownView);
        swipe_refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        setToolbarNavigationClick();
        mRelativeProductSpec.setOnClickListener(this);
        mTvCollect.setOnClickListener(this);
        mTvAddShopcart.setOnClickListener(this);
        mBtnBuy.setOnClickListener(this);
        tv_money_day.setOnClickListener(this);
        tv_money_percent.setOnClickListener(this);

        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });

        tv_original_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);  //添加删除线

    }

    private void initData() {
        //判断是不是限时特卖，如果是，就显示倒计时
        if (TextUtils.isEmpty(flashSale)) {
            ms_price.setVisibility(View.GONE);
            cv_countdownView.setVisibility(View.GONE);
            setData(0);
        } else {
            ms_price.setVisibility(View.VISIBLE);
            cv_countdownView.setVisibility(View.VISIBLE);
            setData(1);
        }

        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * 设置数据 大于0代表是有倒计时的
     */
    private void setData(final int isnew) {
        Log.e(TAG, "isnew : " + isnew);

        OkHttpUtils.get()
                .url(ApiConstants.DETAIL_PAGE)
                .addParams("productId", productId)
                .addParams("isnew", String.valueOf(isnew))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e(TAG, "initData() Exception:" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e(TAG, "initData() response:" + response);
                        Gson gson = new Gson();
                        productDetailBean = gson.fromJson(response, ProductDetailBeannn.class);

                        initBanner();

                        setProductInfo();

                        if (isnew > 0) {
                            long failureTime = productDetailBean.getProductDetail().getFailureTime();

                            cv_countdownView.start(failureTime - System.currentTimeMillis());
                            setCountdownView(failureTime);
                        }

                        swipe_refresh.setRefreshing(false);

                    }
                });
    }

    /**
     * 设置商品的数据
     */
    private void setProductInfo() {
        mTvTitle.setText(productDetailBean.getProductDetail().getName());
        mTvIntroduce.setText(productDetailBean.getProductDetail().getDescription());
        mTvPrice.setText(productDetailBean.getProductDetail().getNowprice() + "");
        tv_original_price.setText("参考价￥" + productDetailBean.getProductDetail().getOrgnprice());
        tv_money_percent.setText("返利比例：" + productDetailBean.getProductDetail().getFeedbackrate() + "%");
        tv_money_day.setText("返利周期：" + productDetailBean.getProductDetail().getFeedbacktime() + "天");
        tv_total_store.setText("库存：" + productDetailBean.getTatalStore());
    }

    /**
     * 设置如果是限时特卖点进去的倒计时
     */
    private void setCountdownView(final long failureTime) {
        builder = new DynamicConfig.Builder();

        cv_countdownView.setOnCountdownIntervalListener(50, new CountdownView.OnCountdownIntervalListener() {
            @Override
            public void onInterval(CountdownView cv, long remainTime) {
                if (failureTime < System.currentTimeMillis() + (long) (60 * 60 * 1000)) {
                    cv_countdownView.dynamicShow(getDynamicConfigState(RED));
                } else {
                    cv_countdownView.dynamicShow(getDynamicConfigState(WHITE));
                }
            }
        });
        cv_countdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                finish();
            }
        });
    }


    /**
     * 获取设置倒计时的单例
     *
     * @param state
     * @return
     */
    private DynamicConfig getDynamicConfigState(int state) {
        if (state == RED) {
            builder.setTimeTextColor(App.getAppContext().getResources().getColor(R.color.red_ff0000));
        } else if (state == WHITE) {
            builder.setTimeTextColor(App.getAppContext().getResources().getColor(R.color.white));
        }

        if (build != null) {
            return this.build;
        } else {
            return builder.build();
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
    protected void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);//注销订阅
        cv_countdownView.stop();
    }

    /**
     * 设置轮播图
     */
    private void initBanner() {
        List<String> images = new ArrayList<>();
        if (productDetailBean != null && productDetailBean.getCrclphotos().size() > 0) {
            for (int i = 0; i < productDetailBean.getCrclphotos().size(); i++) {
                images.add(productDetailBean.getCrclphotos().get(i).getGoodsPic());
            }
        }

        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(images);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Accordion);
        mBanner.start();//
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        int tabTop = mTabLayout.getTop();

    }

    public static final String PERCENT = "1";
    public static final String DAY = "2";
    public static String COMMON = "3";

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_product_spec:
              /*  BottomDialog dialog = new BottomDialog(ProductDetailActivity.this);
                dialog.show();*/
                showBottomPopupWindow();
                break;

            case R.id.btn_buy:
                BottomPop.COMMON = BottomPop.BUY;
                showBottomPopupWindow();
                break;

            case R.id.tv_collect:
                if (UserUtil.isLogin(getApplicationContext())) {
                    //收藏
                    collect();
                } else {
                    To.showShort(ProductDetailActivity.this, "请先登录！");
                }
                break;

            case R.id.tv_add_shopcart:
                BottomPop.COMMON = BottomPop.ADD_SHOP_CART;
                showBottomPopupWindow();
                break;

            case R.id.tv_money_percent:
                COMMON = PERCENT;
                judgeRebateVisible();
                break;

            case R.id.tv_money_day:
                COMMON = DAY;
                judgeRebateVisible();
                break;
        }
    }

    /**
     * 收藏
     */
    private void collect() {
        // TODO: 2017/4/14
        OkHttpUtils.post()
                .url(ApiConstants.DETAIL_PAGE_COLLECT)
                .addParams("goodsId", productId)
                .addParams("userId", UserUtil.getUserId(getApplicationContext()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e(TAG, "collect() response:" + response);
                        try {
                            JSONObject jo = new JSONObject(response);
                            if ((boolean) jo.get("success")) {
                                To.showShort(getApplicationContext(), "收藏成功");
                            } else {
                                To.showShort(getApplicationContext(), "已收藏");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    /**
     * 判断返利的信息是否是显示状态
     */
    private void judgeRebateVisible() {
        if (COMMON.equals(DAY)) {
            if (tv_rebate_info.getVisibility() == View.VISIBLE &&
                    getResources().getString(R.string.rebate_day).equals(tv_rebate_info.getText().toString())) {
                tv_rebate_info.setVisibility(View.GONE);
            } else {
                tv_rebate_info.setText(getResources().getString(R.string.rebate_day));
                tv_rebate_info.setVisibility(View.VISIBLE);
                ObjectAnimator.ofFloat(tv_rebate_info, "scaleY", 0, 1).setDuration(150).start();
            }

        } else if (COMMON.equals(PERCENT)) {
            if (tv_rebate_info.getVisibility() == View.VISIBLE &&
                    getResources().getString(R.string.rebate_percent).equals(tv_rebate_info.getText().toString())) {
                tv_rebate_info.setVisibility(View.GONE);
            } else {
                tv_rebate_info.setText(getResources().getString(R.string.rebate_percent));
                tv_rebate_info.setVisibility(View.VISIBLE);
                ObjectAnimator.ofFloat(tv_rebate_info, "scaleY", 0, 1).setDuration(150).start();
            }
        }
    }

    private void showBottomPopupWindow() {
        BottomPop bottomPop = new BottomPop(this, productDetailBean.getHWs(), productId, productDetailBean.getProductDetail().getNowprice());
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

    @Subscribe
    public void EventOtto(EventOne eventOne) {
        switch (eventOne.getEvent()) {
            case "结束当前页面":
                BusProvider.getInstance().post(new EventOne("切换到购物车"));
                finish();
                break;
        }
    }


}
