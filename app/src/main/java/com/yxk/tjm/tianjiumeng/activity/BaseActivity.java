package com.yxk.tjm.tianjiumeng.activity;

import android.support.v4.app.FragmentActivity;

import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.utils.StatusBarUtil;

/**
 * Created by ningfei on 2017/3/7.
 */

public class BaseActivity extends FragmentActivity {
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.statusbarColor), 0);
    }
}
