package com.yxk.tjm.tianjiumeng;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.activity.LoginActivity;
import com.yxk.tjm.tianjiumeng.category.CategoryFragment;
import com.yxk.tjm.tianjiumeng.event.BusProvider;
import com.yxk.tjm.tianjiumeng.event.EventOne;
import com.yxk.tjm.tianjiumeng.home.HomeFragment;
import com.yxk.tjm.tianjiumeng.my.MyFragment;
import com.yxk.tjm.tianjiumeng.news.NewsFragment;
import com.yxk.tjm.tianjiumeng.shopcar.ShopCartFragment;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ArrayList<Fragment> fragments;
    private RadioGroup radio_group;
    private static final int SHOPCART_NOT_LOGIN = 10;
    private static final int MY_NOT_LOGIN = 20;
    private RadioButton radio_home;
    private RadioButton radio_my;
    private RadioButton radio_shopcar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getActivityManager().pushActivity(this);
        BusProvider.getInstance().register(this);
        initView();
        initData();
    }

    private void initView() {
        radio_group = (RadioGroup) findViewById(R.id.radio_group);
        radio_home = (RadioButton) findViewById(R.id.radio_home);
        radio_my = (RadioButton) findViewById(R.id.radio_my);
        radio_shopcar = (RadioButton) findViewById(R.id.radio_shopcar);
    }

    private void initData() {
        initFragments();

        switchFragment(fragments.get(0)).commitAllowingStateLoss();

        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.radio_home:
                        //mViewPager.setCurrentItem(0, false);
                        switchFragment(fragments.get(0)).commitAllowingStateLoss();
                        break;

                    case R.id.radio_classify:
                        //mViewPager.setCurrentItem(1, false);
                        switchFragment(fragments.get(1)).commitAllowingStateLoss();
                        break;

                    case R.id.radio_shopcar:
                        /// mViewPager.setCurrentItem(2, false);
                        if (UserUtil.isLogin(getApplicationContext())) {
                            switchFragment(fragments.get(2)).commitAllowingStateLoss();
                        } else {
                            startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), SHOPCART_NOT_LOGIN);
                        }
                        break;

                    case R.id.radio_news:
                        // mViewPager.setCurrentItem(3, false);
                        switchFragment(fragments.get(3)).commitAllowingStateLoss();
                        break;

                    case R.id.radio_my:
                        // mViewPager.setCurrentItem(4, false);
                        if (UserUtil.isLogin(getApplicationContext())) {
                            switchFragment(fragments.get(4)).commitAllowingStateLoss();
                        } else {
                            startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), MY_NOT_LOGIN);
                        }
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SHOPCART_NOT_LOGIN:
                switchFragment(fragments.get(0)).commit();
                break;

            case MY_NOT_LOGIN:
                switchFragment(fragments.get(0)).commit();
                break;
        }
        radio_home.setChecked(true);
    }

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

    private long mExitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {//
                // 如果两次按键时间间隔大于2000毫秒，则不退出
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();// 更新mExitTime
            } else {
                App.getActivityManager().finishAllActivity();
                //  System.exit(0);// 否则退出程序
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        BusProvider.getInstance().unregister(this);
    }


    @Subscribe
    public void EventOtto(EventOne eventOne) {
        switch (eventOne.getEvent()) {
            case "切换到购物车":
                radio_shopcar.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        radio_shopcar.setChecked(true);
                    }
                }, 50);
                break;
        }
    }
}