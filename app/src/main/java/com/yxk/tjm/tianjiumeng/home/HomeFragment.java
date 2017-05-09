package com.yxk.tjm.tianjiumeng.home;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
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
import com.yxk.tjm.tianjiumeng.home.bean.HomeBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.GlideImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.special_one)
    SpecialRecommendLayout specialOne;
    @BindView(R.id.special_two)
    SpecialRecommendLayout specialTwo;
    @BindView(R.id.special_three)
    SpecialRecommendLayout specialThree;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;

    private static final String TAG = "HomeFragment";

    private RecyclerView mRecyclerComeMarket;
    private Banner banner;
    private RecyclerView mRrecyclerFlashSale;
    private TextView mTvCrystalHouse;
    private TextView mTvHotStrug;
    private TextView mTvMyCustom;
    private SwipeRefreshLayout swipe_refresh;
    private HomeBean homeBean;
    private ImageView iv_new_big_pic;
    private View view_cover;

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
        iv_new_big_pic = (ImageView) view.findViewById(R.id.iv_new_big_pic);
        view_cover = view.findViewById(R.id.view_cover);

        mTvCrystalHouse.setOnClickListener(this);
        mTvHotStrug.setOnClickListener(this);
        mTvMyCustom.setOnClickListener(this);
        rlSearch.setOnClickListener(this);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });

        //特别推荐点击
        setSpecialClickListener();

        //新品上市点击
        mRecyclerComeMarket.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("productId", String.valueOf(homeBean.getNewPro().get(position).getId()));//String.valueOf(homeBean.getNewPro().get(position).getId())
                startActivity(intent);
            }
        });

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initData();
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
            initData();
        }
    }

    /**
     * 设置轮播图
     */
    private void setBanner() {
        List<String> imageList = new ArrayList<>();
        for (int i = 0; i < homeBean.getCrclphotos().size(); i++) {
            imageList.add(homeBean.getCrclphotos().get(i).getGoodsPic());
        }
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imageList);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Accordion);
        banner.start();

    }

    /**
     * 初始化所有数据
     */
    private void initData() {
        swipe_refresh.setRefreshing(true);
        view_cover.setVisibility(View.VISIBLE);
        OkHttpUtils.get()
                .url(ApiConstants.HOME)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(App.getAppContext(), "网络错误:" + e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "onActivityCreated 首页查询" + response);
                        Gson gson = new Gson();
                        homeBean = gson.fromJson(response, HomeBean.class);

                        //设置轮播图
                        setBanner();

                        //设置新品上市
                        setComeMarket();

                        //设置限时特卖
                        setFlashSale();

                        //设置特别推荐
                        setSpecial();

                        view_cover.setVisibility(View.GONE);
                        swipe_refresh.setRefreshing(false);
                    }
                });
    }

    /**
     * 设置新品上市
     */
    private void setComeMarket() {
        Glide.with(getActivity()).load(homeBean.getNewProPic()).into(iv_new_big_pic);

        LinearLayoutManager manager = new LinearLayoutManager(App.getAppContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerComeMarket.setLayoutManager(manager);
        mRecyclerComeMarket.setAdapter(new BaseQuickAdapter<HomeBean.NewProBean, BaseViewHolder>(R.layout.item_new_come_market, homeBean.getNewPro()) {
            @Override
            protected void convert(BaseViewHolder helper, HomeBean.NewProBean item) {
                TextView tv_original_price = helper.getView(R.id.tv_original_price);
                TextView tv_title = helper.getView(R.id.tv_title);
                TextView tv_price = helper.getView(R.id.tv_price);

                tv_original_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);  //添加删除线
                Glide.with(getActivity()).load(item.getShowpic()).into((ImageView) helper.getView(R.id.image_pic));
                tv_title.setText(item.getName());
                tv_price.setText("￥" + item.getNowprice());
                tv_original_price.setText("￥" + item.getOrgnprice());
            }
        });

    }

    /**
     * 设置限时特卖
     */
    private void setFlashSale() {
        LinearLayoutManager manager1 = new LinearLayoutManager(App.getAppContext());
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRrecyclerFlashSale.setLayoutManager(manager1);
        FlashSaleAdapter flashSaleAdapter = new FlashSaleAdapter();
        flashSaleAdapter.setMatchData(homeBean.getTimedSale());
        mRrecyclerFlashSale.setAdapter(flashSaleAdapter);

        flashSaleAdapter.setOnItemClickListener(new FlashSaleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("flashSale", "flashSale");
                intent.putExtra("productId", String.valueOf(homeBean.getNewPro().get(position).getId()));
                startActivity(intent);
            }
        });
    }

    /**
     * 设置特别推荐
     */
    private void setSpecial() {
        /**
         * 第一行
         * */
        if (homeBean.getHotcnnmd() != null && homeBean.getHotcnnmd().size() >= 1) {
            specialOne.setVisibility(View.VISIBLE);
            specialTwo.setVisibility(View.GONE);
            specialThree.setVisibility(View.GONE);

            if (homeBean.getHotcnnmd().get(0).size() >= 1 && homeBean.getHotcnnmd().get(0) != null) {
                specialOne.setZeroData(homeBean.getHotcnnmd().get(0).get(0).getCategoryName(),
                        homeBean.getHotcnnmd().get(0).get(0).getCategoryPic(),
                        homeBean.getHotcnnmd().get(0).get(0).getCategoryDecr());
            }
            if (homeBean.getHotcnnmd().get(0).size() >= 2 && homeBean.getHotcnnmd().get(0) != null) {
                specialOne.setIvPic1(homeBean.getHotcnnmd().get(0).get(1).getShowpic(),
                        "￥" + homeBean.getHotcnnmd().get(0).get(1).getNowprice());
            }

            if (homeBean.getHotcnnmd().get(0).size() >= 3 && homeBean.getHotcnnmd().get(0) != null) {
                specialOne.setIvPic2(homeBean.getHotcnnmd().get(0).get(2).getShowpic(),
                        "￥" + homeBean.getHotcnnmd().get(0).get(2).getNowprice());
            }

            if (homeBean.getHotcnnmd().get(0).size() >= 4 && homeBean.getHotcnnmd().get(0) != null) {
                specialOne.setIvPic3(homeBean.getHotcnnmd().get(0).get(3).getShowpic(),
                        "￥" + homeBean.getHotcnnmd().get(0).get(3).getNowprice());
            }
        }


        /**
         * 第二行
         * */
        if (homeBean.getHotcnnmd() != null && homeBean.getHotcnnmd().size() >= 2) {
            specialOne.setVisibility(View.VISIBLE);
            specialTwo.setVisibility(View.VISIBLE);
            specialThree.setVisibility(View.GONE);

            if (homeBean.getHotcnnmd().get(1).size() >= 1 && homeBean.getHotcnnmd().get(1) != null) {
                specialTwo.setZeroData(homeBean.getHotcnnmd().get(1).get(0).getCategoryName(),
                        homeBean.getHotcnnmd().get(1).get(0).getCategoryPic(),
                        homeBean.getHotcnnmd().get(1).get(0).getCategoryDecr());
            }
            if (homeBean.getHotcnnmd().get(1).size() >= 2 && homeBean.getHotcnnmd().get(1) != null) {
                specialTwo.setIvPic1(homeBean.getHotcnnmd().get(1).get(1).getShowpic(),
                        "￥" + homeBean.getHotcnnmd().get(1).get(1).getNowprice());
            }

            if (homeBean.getHotcnnmd().get(1).size() >= 3 && homeBean.getHotcnnmd().get(1) != null) {
                specialTwo.setIvPic2(homeBean.getHotcnnmd().get(1).get(2).getShowpic(),
                        "￥" + homeBean.getHotcnnmd().get(1).get(2).getNowprice());
            }

            if (homeBean.getHotcnnmd().get(1).size() >= 4 && homeBean.getHotcnnmd().get(1) != null) {
                specialTwo.setIvPic3(homeBean.getHotcnnmd().get(1).get(3).getShowpic(),
                        "￥" + homeBean.getHotcnnmd().get(1).get(3).getNowprice());
            }

        }


        /**
         * 第三行
         * */
        if (homeBean.getHotcnnmd() != null && homeBean.getHotcnnmd().size() >= 3) {
            specialOne.setVisibility(View.VISIBLE);
            specialTwo.setVisibility(View.VISIBLE);
            specialThree.setVisibility(View.VISIBLE);

            if (homeBean.getHotcnnmd().get(2).size() >= 1 && homeBean.getHotcnnmd().get(2) != null) {
                specialThree.setZeroData(homeBean.getHotcnnmd().get(2).get(0).getCategoryName(),
                        homeBean.getHotcnnmd().get(2).get(0).getCategoryPic(),
                        homeBean.getHotcnnmd().get(2).get(0).getCategoryDecr());
            }
            if (homeBean.getHotcnnmd().get(2).size() >= 2 && homeBean.getHotcnnmd().get(2) != null) {
                specialThree.setIvPic1(homeBean.getHotcnnmd().get(2).get(1).getShowpic(),
                        "￥" + homeBean.getHotcnnmd().get(2).get(1).getNowprice());
            }
            if (homeBean.getHotcnnmd().get(2).size() >= 3 && homeBean.getHotcnnmd().get(2) != null) {
                specialThree.setIvPic2(homeBean.getHotcnnmd().get(2).get(2).getShowpic(),
                        "￥" + homeBean.getHotcnnmd().get(2).get(2).getNowprice());
            }
            if (homeBean.getHotcnnmd().get(2).size() >= 4 && homeBean.getHotcnnmd().get(2) != null) {
                specialThree.setIvPic3(homeBean.getHotcnnmd().get(2).get(3).getShowpic(),
                        "￥" + homeBean.getHotcnnmd().get(2).get(3).getNowprice());
            }

        }

    }

    /**
     * 设置特别推荐条目点击事件
     */
    private void setSpecialClickListener() {

        /**
         * 第一行
         */
        specialOne.setOnItemClickListener(new SpecialRecommendLayout.OnItemClickListener() {
            @Override
            public void onItemZeroClick() {
                // To.showShort(getContext(), "zero");//
            }

            @Override
            public void onItemOneClick() {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("productId", homeBean.getHotcnnmd().get(0).get(1).getId()+"");// TODO: 2017/4/17  正常是getId()
                startActivity(intent);
            }

            @Override
            public void onItemTwoClick() {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("productId", homeBean.getHotcnnmd().get(0).get(2).getId()+"");// TODO: 2017/4/17  正常是getId()
                startActivity(intent);
            }

            @Override
            public void onItemThreeClick() {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("productId", homeBean.getHotcnnmd().get(0).get(3).getId()+"");// TODO: 2017/4/17  正常是getId()
                startActivity(intent);
            }
        });

        /**
         * 第二行
         */
        specialTwo.setOnItemClickListener(new SpecialRecommendLayout.OnItemClickListener() {
            @Override
            public void onItemZeroClick() {
                //  To.showShort(getContext(), "zero");
            }

            @Override
            public void onItemOneClick() {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("productId", homeBean.getHotcnnmd().get(1).get(1).getId()+"");// TODO: 2017/4/17  正常是getId()
                startActivity(intent);
            }

            @Override
            public void onItemTwoClick() {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("productId", homeBean.getHotcnnmd().get(1).get(2).getId()+"");// TODO: 2017/4/17  正常是getId()
                startActivity(intent);
            }

            @Override
            public void onItemThreeClick() {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("productId", homeBean.getHotcnnmd().get(1).get(3).getId()+"");// TODO: 2017/4/17  正常是getId()
                startActivity(intent);
            }
        });

        /**
         * 第三行
         */
        specialThree.setOnItemClickListener(new SpecialRecommendLayout.OnItemClickListener() {
            @Override
            public void onItemZeroClick() {
                //  To.showShort(getContext(), "zero");
            }

            @Override
            public void onItemOneClick() {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("productId", homeBean.getHotcnnmd().get(2).get(1).getId()+"");// TODO: 2017/4/17  正常是getId()
                startActivity(intent);
            }

            @Override
            public void onItemTwoClick() {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("productId", homeBean.getHotcnnmd().get(2).get(2).getId()+"");// TODO: 2017/4/17  正常是getId()
                startActivity(intent);
            }

            @Override
            public void onItemThreeClick() {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("productId", homeBean.getHotcnnmd().get(2).get(3).getId()+"");// TODO: 2017/4/17  正常是getId()
                startActivity(intent);
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

}
