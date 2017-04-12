package com.yxk.tjm.tianjiumeng.my.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.utils.To;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImmediateAppraiseActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ratingbar)
    RatingBar ratingbar;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immediate_appraise);
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

    @OnClick(R.id.btn_submit)
    public void onClick() {
        To.showShort(this, "提交");
    }
}
