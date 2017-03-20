package com.yxk.tjm.tianjiumeng.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yxk.tjm.tianjiumeng.R;

/**
 * Created by ningfei on 2017/3/10.
 */

public class MyToolbar extends Toolbar {

    public static final String EDIT_TAG = "EDIT_TAG";
    public static final String OK_TAG = "OK_TAG";

    private TextView tv_edit;
    private TextView tv_title;
    private String title;

    public MyToolbar(Context context) {
        this(context, null);
    }


    public MyToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_toolbar, this);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_edit = (TextView) findViewById(R.id.tv_edit);

        initData();
    }

    public void setTitle(String title) {
        tv_title.setText(title);
    }

    private void initData() {
        tv_edit.setTag(EDIT_TAG);

        tv_edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                switch ((String) tv_edit.getTag()) {
                    case EDIT_TAG:
                        onEditClickListener.onEditClik(EDIT_TAG);
                        tv_edit.setTag(OK_TAG);
                        tv_edit.setText("完成");
                        break;

                    case OK_TAG:
                        onEditClickListener.onEditClik(OK_TAG);
                        tv_edit.setTag(EDIT_TAG);
                        tv_edit.setText("编辑");
                        break;
                }
            }
        });
    }


    OnEditClickListener onEditClickListener;

    public void setOnEditClickListener(OnEditClickListener onEditClickListener) {
        this.onEditClickListener = onEditClickListener;
    }

    public interface OnEditClickListener {
        void onEditClik(String tag);
    }


}
