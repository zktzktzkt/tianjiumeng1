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
import com.yxk.tjm.tianjiumeng.my.fragment.AlreadyReturnMoneyFragment;
import com.yxk.tjm.tianjiumeng.my.fragment.CancelReturnMoneyFragment;
import com.yxk.tjm.tianjiumeng.my.fragment.ReturnningMoneyFragment;
import com.yxk.tjm.tianjiumeng.my.fragment.WaitReturnMoneyFragment;

import java.util.ArrayList;
import java.util.List;

public class ReturnMoneyDetailActivity extends BaseActivity {

    private Toolbar mToolbar;
    private TabLayout tablayout;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_money_detail);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbarNavigationClick();
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        final String[] titles = {"待返利", "正在返", "已返利", "取消返"};

        final List<Fragment> list = new ArrayList<>();
        list.add(new WaitReturnMoneyFragment());
        list.add(new ReturnningMoneyFragment());
        list.add(new AlreadyReturnMoneyFragment());
        list.add(new CancelReturnMoneyFragment());

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
