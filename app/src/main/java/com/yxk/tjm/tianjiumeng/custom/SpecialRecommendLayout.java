package com.yxk.tjm.tianjiumeng.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ningfei on 2017/3/8.
 */

public class SpecialRecommendLayout extends RelativeLayout {
    @BindView(R.id.iv_pic_1)
    ImageView ivPic1;
    @BindView(R.id.iv_pic_2)
    ImageView ivPic2;
    @BindView(R.id.iv_pic_3)
    ImageView ivPic3;

    public SpecialRecommendLayout(Context context) {
        this(context, null);
    }

    public SpecialRecommendLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpecialRecommendLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_special_recommend, this);
        ButterKnife.bind(this, view);

    }

    @OnClick({R.id.ll_0, R.id.iv_pic_1, R.id.iv_pic_2, R.id.iv_pic_3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_0:
                onItemClickListener.onItemZeroClick();
                break;
            case R.id.iv_pic_1:
                onItemClickListener.onItemOneClick();
                break;
            case R.id.iv_pic_2:
                onItemClickListener.onItemTwoClick();
                break;
            case R.id.iv_pic_3:
                onItemClickListener.onItemThreeClick();
                break;
        }
    }

    public void setIvPic1(int resid) {
        Glide.with(App.getAppContext()).load(resid).into(ivPic1);
    }


    public void setIvPic2(int resid) {
        Glide.with(App.getAppContext()).load(resid).into(ivPic2);
    }


    public void setIvPic3(int resid) {
        Glide.with(App.getAppContext()).load(resid).into(ivPic3);
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onItemZeroClick();

        void onItemOneClick();

        void onItemTwoClick();

        void onItemThreeClick();
    }
}
