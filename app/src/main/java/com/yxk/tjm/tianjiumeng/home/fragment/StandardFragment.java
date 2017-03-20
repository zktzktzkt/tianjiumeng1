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
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.home.activity.ProductDetailActivity;
import com.yxk.tjm.tianjiumeng.home.adapter.RecommendForYouAdapter;
import com.yxk.tjm.tianjiumeng.utils.ScreenUtils;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class StandardFragment extends Fragment {

    @BindView(R.id.recycler_recommond)
    RecyclerView recyclerRecommond;
    private ImageView image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_design_idea, container, false);
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

        setImagePic();
        initRecommendForYouRecycler();
    }

    private void setImagePic() {
        Glide.with(getActivity())
                .load(R.drawable.guige)
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
                        Glide.with(App.getAppContext()).load(R.drawable.guige).asBitmap().override(400, 400).into(image);
                    }
                });
    }

    private void initRecommendForYouRecycler() {
        recyclerRecommond.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        RecommendForYouAdapter recommendForYouAdapter = new RecommendForYouAdapter();
        recommendForYouAdapter.setMatchData(Arrays.asList(R.drawable.pic_a, R.drawable.pic_a, R.drawable.pic_a, R.drawable.pic_a, R.drawable.pic_a, R.drawable.pic_a, R.drawable.pic_a));
        recyclerRecommond.setAdapter(recommendForYouAdapter);
        recommendForYouAdapter.setOnItemClickListener(new RecommendForYouAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(getActivity(), ProductDetailActivity.class));
                getActivity().finish();
            }
        });
    }
}
