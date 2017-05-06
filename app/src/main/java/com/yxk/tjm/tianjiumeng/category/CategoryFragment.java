package com.yxk.tjm.tianjiumeng.category;


import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.category.activity.RightHeaderListActivity;
import com.yxk.tjm.tianjiumeng.category.adapter.LeftCategoryAdapter;
import com.yxk.tjm.tianjiumeng.category.bean.CategoryBean;
import com.yxk.tjm.tianjiumeng.fragment.BaseFragment;
import com.yxk.tjm.tianjiumeng.home.activity.ProductDetailActivity;
import com.yxk.tjm.tianjiumeng.home.activity.SearchActivity;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.FullyGridLayoutManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView recycler_left;
    private RecyclerView recycler_right_header;
    private RecyclerView recycler_right_common;
    private RelativeLayout rl_search;
    private CategoryBean categoryBean;
    private RightHeaderAdapter rightHeaderAdapter;
    private RightCommonAdapter rightCommonAdapter;
    private int typeId;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_category;
    }

    @Override
    public void initView(View view) {
        recycler_left = (RecyclerView) view.findViewById(R.id.recycler_left);
        recycler_right_header = (RecyclerView) view.findViewById(R.id.recycler_right_header);
        recycler_right_common = (RecyclerView) view.findViewById(R.id.recycler_right_common);
        rl_search = (RelativeLayout) view.findViewById(R.id.rl_search);

        rl_search.setOnClickListener(this);

        //点击事件必须放在oncreate里，否则在页面的每次初始化数据后，点击监听会重复创建，导致点一下出n个界面
        recycler_right_header.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), RightHeaderListActivity.class);
                intent.putExtra("typeId", typeId);
                intent.putExtra("brandId", categoryBean.getBrandList().get(position).getBrandId());
                startActivity(intent);
            }
        });

        recycler_right_common.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("productId", "1");
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

    private void initData() {
        typeId = 1;

        OkHttpUtils.get()
                .url(ApiConstants.CATEGORY)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        categoryBean = gson.fromJson(response, CategoryBean.class);

                        setLeftList(categoryBean.getTypeList());

                        setRightHead(categoryBean.getBrandList());

                        setRightCommon(categoryBean.getProductList());
                    }
                });
    }

    /**
     * 设置左边分类列表
     */
    private void setLeftList(final List<CategoryBean.TypeListBean> typeListBeanList) {
        recycler_left.setLayoutManager(new LinearLayoutManager(getContext()));
        final LeftCategoryAdapter leftCategoryAdapter = new LeftCategoryAdapter(typeListBeanList);
        recycler_left.setAdapter(leftCategoryAdapter);

        leftCategoryAdapter.setOnItemClickListener(new LeftCategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                typeId = typeListBeanList.get(position).getTypeId();

                leftCategoryAdapter.selectPos = position;
                leftCategoryAdapter.notifyDataSetChanged();

                OkHttpUtils.get()
                        .url(ApiConstants.CATEGORY)
                        .addParams("typeId", String.valueOf(typeListBeanList.get(position).getTypeId()))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                //  LogUtil.e("response", response);
                                Gson gson = new Gson();
                                CategoryBean categoryBean = gson.fromJson(response, CategoryBean.class);

                                setRightHead(categoryBean.getBrandList());
                                setRightCommon(categoryBean.getProductList());
                            }
                        });
            }
        });
    }

    /**
     * 热门品牌
     */
    private void setRightHead(List<CategoryBean.BrandListBean> productListBeanList) {
        recycler_right_header.setLayoutManager(new FullyGridLayoutManager(getContext(), 3));
        rightHeaderAdapter = new RightHeaderAdapter(R.layout.item_right_header_category, productListBeanList);
        recycler_right_header.setAdapter(rightHeaderAdapter);
    }

    /**
     * 热门品牌
     */
    public class RightHeaderAdapter extends BaseQuickAdapter<CategoryBean.BrandListBean, BaseViewHolder> {
        public RightHeaderAdapter(int layoutResId, List<CategoryBean.BrandListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CategoryBean.BrandListBean item) {
            Glide.with(getActivity()).load(item.getBrandPic()).into((ImageView) helper.getView(R.id.img_pic));
            helper.setText(R.id.tv_name, item.getBrandName());
        }
    }

    /**
     * 热门单品
     *
     * @param productList
     */
    private void setRightCommon(List<CategoryBean.ProductListBean> productList) {
        recycler_right_common.setLayoutManager(new FullyGridLayoutManager(getContext(), 3));
        rightCommonAdapter = new RightCommonAdapter(R.layout.item_right_common_category, productList);
        recycler_right_common.setAdapter(rightCommonAdapter);
    }

    /**
     * 热门单品
     */
    public class RightCommonAdapter extends BaseQuickAdapter<CategoryBean.ProductListBean, BaseViewHolder> {
        public RightCommonAdapter(int layoutResId, List<CategoryBean.ProductListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CategoryBean.ProductListBean item) {
            Glide.with(getActivity()).load(item.getShowpic()).into((ImageView) helper.getView(R.id.img_pic));
            helper.setText(R.id.tv_name, item.getName());
            helper.setText(R.id.tv_price, getResources().getString(R.string.RMB) + item.getNowprice());
            TextView tv_original_price = helper.getView(R.id.tv_original_price);
            tv_original_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);  //添加删除线
            tv_original_price.setText(getResources().getString(R.string.RMB) + item.getOrgnprice());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_search:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                getActivity().overridePendingTransition(R.anim.enter_alpha, R.anim.out_alpha);
                break;
        }
    }
}
