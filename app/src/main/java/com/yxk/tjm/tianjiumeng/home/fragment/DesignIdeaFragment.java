package com.yxk.tjm.tianjiumeng.home.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.home.activity.ProductDetailActivity;
import com.yxk.tjm.tianjiumeng.home.bean.ProductInnerDesignBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.ScreenUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;


/**
 * 商品详情 -> 设计理念
 */
public class DesignIdeaFragment extends Fragment {

    @BindView(R.id.recycler_recommond)
    RecyclerView recyclerRecommond;
    private ImageView image;
    private String productId;
    private ProductInnerDesignBean productInnerDesignBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_design_idea, container, false);

        productId = getArguments().getString("productId");

        initView(view);
        ButterKnife.bind(this, view);
        return view;
    }

    private void initView(View view) {
        image = (ImageView) view.findViewById(R.id.image);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        OkHttpUtils.get()
                .url(ApiConstants.DETAIL_PAGE_DESIGN)
                .addParams("productId", productId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("DesignIdeaFragment ", "onActivityCreated() response " + response);
                        Gson gson = new Gson();
                        productInnerDesignBean = gson.fromJson(response, ProductInnerDesignBean.class);

                        setImagePic();

                    }
                });
    }

    private void setImagePic() {
        if (productInnerDesignBean.getDesignPics() != null) {
            Glide.with(getActivity())
                    .load(productInnerDesignBean.getDesignPics().getGoodsPic())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            int imageWidth = resource.getWidth();
                            int imageHeight = resource.getHeight();
                            int height = ScreenUtils.getScreenWidth(App.getAppContext()) * imageHeight / imageWidth;
                            ViewGroup.LayoutParams para = image.getLayoutParams();
                            if (para != null) {
                                para.height = height;
                                image.setLayoutParams(para);
                            }
                            Glide.with(App.getAppContext()).load(productInnerDesignBean.getDesignPics().getGoodsPic()).asBitmap().into(image);
                        }
                    });
        }

        //加载列表（需要在设置完图片后在设置，否则可能出现goospic空指针）
        initRecommendForYouRecycler();

    }

    private void initRecommendForYouRecycler() {
        recyclerRecommond.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerRecommond.setAdapter(new RecommendForYouAdapter(R.layout.item_recommend, productInnerDesignBean.getCnnmdForYou()));
        recyclerRecommond.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("productId", productId); // TODO: 2017/4/11 id以后更换
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    public class RecommendForYouAdapter extends BaseQuickAdapter<ProductInnerDesignBean.CnnmdForYouBean, BaseViewHolder> {
        public RecommendForYouAdapter(int layoutResId, List<ProductInnerDesignBean.CnnmdForYouBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ProductInnerDesignBean.CnnmdForYouBean item) {
            Glide.with(getActivity()).load(item.getShowpic()).into((ImageView) helper.getView(R.id.img_pic));
            helper.setText(R.id.tv_name, item.getName());
            helper.setText(R.id.tv_price, getResources().getString(R.string.RMB) + item.getNowprice());
            helper.setText(R.id.tv_original_price, getResources().getString(R.string.RMB) + item.getOrgnprice());
        }
    }
}
