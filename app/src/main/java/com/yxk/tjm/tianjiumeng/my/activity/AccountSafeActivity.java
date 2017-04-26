package com.yxk.tjm.tianjiumeng.my.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;

public class AccountSafeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_safe);
        App.getActivityManager().pushActivity(this);

    }
}
