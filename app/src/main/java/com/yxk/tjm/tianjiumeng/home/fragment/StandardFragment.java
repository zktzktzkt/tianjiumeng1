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
import com.yxk.tjm.tianjiumeng.home.bean.ProductInnerSuitBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.ScreenUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;


/**
 * A simple {@link Fragment} subclass.
 */
public class StandardFragment extends Fragment {

    @BindView(R.id.recycler_recommond)
    RecyclerView recyclerRecommond;
    private ImageView image;
    private ProductInnerSuitBean productInnerSuitBean;
    private String productId;

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
                .url(ApiConstants.DETAIL_PAGE_STANDARD)
                .addParams("productId", productId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        // Log.e(TAG, "onActivityCreated() response " + response);
                        Gson gson = new Gson();
                        productInnerSuitBean = gson.fromJson(response, ProductInnerSuitBean.class);

                        setImagePic();

                        initRecommendForYouRecycler();
                    }
                });
    }

    private void setImagePic() {
        Glide.with(getActivity())
                .load(productInnerSuitBean.getSuitPics())
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
                        Glide.with(App.getAppContext()).load(productInnerSuitBean.getSuitPics()).asBitmap().into(image);
                    }
                });
    }


    private void initRecommendForYouRecycler() {
        recyclerRecommond.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerRecommond.setAdapter(new RecommendForYouAdapter(R.layout.item_recommend, productInnerSuitBean.getCnnmdForYou()));
        recyclerRecommond.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("productId", 1 + ""); // TODO: 2017/4/11 id以后更换
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    public class RecommendForYouAdapter extends BaseQuickAdapter<ProductInnerSuitBean.CnnmdForYouBean, BaseViewHolder> {
        public RecommendForYouAdapter(int layoutResId, List<ProductInnerSuitBean.CnnmdForYouBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ProductInnerSuitBean.CnnmdForYouBean item) {
            Glide.with(getActivity()).load(item.getShowpic()).into((ImageView) helper.getView(R.id.img_pic));
            helper.setText(R.id.tv_name, item.getName());
            helper.setText(R.id.tv_price, getResources().getString(R.string.RMB) + item.getNowprice());
            helper.setText(R.id.tv_original_price, getResources().getString(R.string.RMB) + item.getOrgnprice());
        }
    }
}
