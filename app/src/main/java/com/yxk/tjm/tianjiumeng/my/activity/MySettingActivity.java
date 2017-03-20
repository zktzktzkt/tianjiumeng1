package com.yxk.tjm.tianjiumeng.my.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MySettingActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_cache)
    TextView tvCache;
    @BindView(R.id.relative_clean_cache)
    RelativeLayout relativeCleanCache;
    @BindView(R.id.rl_question)
    RelativeLayout rlQuestion;
    @BindView(R.id.rl_quit)
    RelativeLayout rlQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);
        ButterKnife.bind(this);

        setToolbarNavigationClick();
    }

    private void setToolbarNavigationClick() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @OnClick({R.id.relative_clean_cache, R.id.rl_question, R.id.rl_quit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_clean_cache:
                break;
            case R.id.rl_question:
                break;
            case R.id.rl_quit:
                break;
        }
    }
}
