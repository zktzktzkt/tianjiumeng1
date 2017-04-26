package com.yxk.tjm.tianjiumeng.my.activity;

import android.os.Bundle;

import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;

public class ClientFeedbackActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_feedback);
        App.getActivityManager().pushActivity(this);

    }
}
