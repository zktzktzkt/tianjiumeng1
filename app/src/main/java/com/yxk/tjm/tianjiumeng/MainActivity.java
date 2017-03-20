package com.yxk.tjm.tianjiumeng;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.category.CategoryFragment;
import com.yxk.tjm.tianjiumeng.home.HomeFragment;
import com.yxk.tjm.tianjiumeng.my.MyFragment;
import com.yxk.tjm.tianjiumeng.news.NewsFragment;
import com.yxk.tjm.tianjiumeng.shopcar.ShopCartFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    //  private ViewPager mViewPager;
    private ArrayList<Fragment> fragments;
    private RadioGroup radio_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        //  mViewPager = (ViewPager) findViewById(R.id.view_pager);
        radio_group = (RadioGroup) findViewById(R.id.radio_group);
    }

    private void initData() {
        initFragments();

        //   mViewPager.setOffscreenPageLimit(4);
        //   mViewPager.setAdapter(fragmentPagerAdapter);
        //    mViewPager.setCurrentItem(0);

        switchFragment(fragments.get(0)).commit();

        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.radio_home:
                        //mViewPager.setCurrentItem(0, false);
                        switchFragment(fragments.get(0)).commit();
                        break;

                    case R.id.radio_classify:
                        //mViewPager.setCurrentItem(1, false);
                        switchFragment(fragments.get(1)).commit();
                        break;

                    case R.id.radio_shopcar:
                        /// mViewPager.setCurrentItem(2, false);
                        switchFragment(fragments.get(2)).commit();
                        break;

                    case R.id.radio_news:
                        // mViewPager.setCurrentItem(3, false);
                        switchFragment(fragments.get(3)).commit();
                        break;

                    case R.id.radio_my:
                        // mViewPager.setCurrentItem(4, false);
                        switchFragment(fragments.get(4)).commit();
                        break;
                }
            }
        });
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CategoryFragment());
        fragments.add(new ShopCartFragment());
        fragments.add(new NewsFragment());
        fragments.add(new MyFragment());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            radio_group.getBackground().setAlpha(255);
        }
    }

    /* private FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    };*/

    private Fragment currentFragment = new Fragment();

    private FragmentTransaction switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.fragment, targetFragment, targetFragment.getClass().getName());

        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }

}