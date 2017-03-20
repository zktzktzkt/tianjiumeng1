package com.yxk.tjm.tianjiumeng.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyInfoActivity extends BaseActivity {

    @BindView(R.id.relative_address)
    RelativeLayout relativeAddress;
    @BindView(R.id.relative_head)
    RelativeLayout relativeHead;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        ButterKnife.bind(this);

        setToolbarNavigationClick();
    }


    @OnClick({R.id.relative_head, R.id.relative_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_head:
                break;
            case R.id.relative_address:
                startActivity(new Intent(this, AddressActivity.class));
                break;
        }
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
