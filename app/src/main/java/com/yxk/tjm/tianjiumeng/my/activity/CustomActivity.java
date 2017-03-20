package com.yxk.tjm.tianjiumeng.my.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.my.fragment.PaySurplusCustomFragment;
import com.yxk.tjm.tianjiumeng.my.fragment.SaleAfterCustomFragment;
import com.yxk.tjm.tianjiumeng.my.fragment.WaitGetCustomFragment;
import com.yxk.tjm.tianjiumeng.my.fragment.WaitPayMoneyCustomFragment;
import com.yxk.tjm.tianjiumeng.my.fragment.WaitReplyFragment;

import java.util.ArrayList;
import java.util.List;

public class CustomActivity extends BaseActivity {
    private Toolbar mToolbar;
    private TabLayout tablayout;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbarNavigationClick();
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        final String[] titles = {"待回复", "待付款", "待收货", "待尾款", "售后"};

        final List<Fragment> list = new ArrayList<>();
        list.add(new WaitReplyFragment());
        list.add(new WaitPayMoneyCustomFragment());
        list.add(new WaitGetCustomFragment());
        list.add(new PaySurplusCustomFragment());
        list.add(new SaleAfterCustomFragment());

        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        tablayout.setupWithViewPager(viewpager);
    }

    private void setToolbarNavigationClick() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}

