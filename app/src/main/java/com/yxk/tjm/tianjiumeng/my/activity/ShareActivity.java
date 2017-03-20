package com.yxk.tjm.tianjiumeng.my.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareActivity extends BaseActivity {

    @BindView(R.id.img_qr)
    ImageView imgQr;

    Bitmap mBitmap;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
        setToolbarNavigationClick();

        mBitmap = CodeUtils.createImage("http://www.baidu.com", 400, 400, BitmapFactory.decodeResource(getResources(), R.drawable.pic_a));
        imgQr.setImageBitmap(mBitmap);
    }

    private void setToolbarNavigationClick() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
