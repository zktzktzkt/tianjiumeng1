package com.yxk.tjm.tianjiumeng.my;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.fragment.BaseFragment;
import com.yxk.tjm.tianjiumeng.my.activity.CardActivity;
import com.yxk.tjm.tianjiumeng.my.activity.CollectActivity;
import com.yxk.tjm.tianjiumeng.my.activity.CommissionActivity;
import com.yxk.tjm.tianjiumeng.my.activity.CrystalDetailActivity;
import com.yxk.tjm.tianjiumeng.my.activity.CustomActivity;
import com.yxk.tjm.tianjiumeng.my.activity.MessageActivity;
import com.yxk.tjm.tianjiumeng.my.activity.MyCommissionActivity;
import com.yxk.tjm.tianjiumeng.my.activity.MyCrystalActivity;
import com.yxk.tjm.tianjiumeng.my.activity.MyInfoActivity;
import com.yxk.tjm.tianjiumeng.my.activity.MySettingActivity;
import com.yxk.tjm.tianjiumeng.my.activity.MyWaitAppraiseActivity;
import com.yxk.tjm.tianjiumeng.my.activity.RechargeActivity;
import com.yxk.tjm.tianjiumeng.my.activity.ReturnMoneyDetailActivity;
import com.yxk.tjm.tianjiumeng.my.activity.SaleAfterActivity;
import com.yxk.tjm.tianjiumeng.my.activity.ShareActivity;
import com.yxk.tjm.tianjiumeng.my.activity.TJMHouseActivity;
import com.yxk.tjm.tianjiumeng.my.activity.WaitGetActivity;
import com.yxk.tjm.tianjiumeng.my.activity.WaitPayActivity;
import com.yxk.tjm.tianjiumeng.my.activity.WaitShipActivity;
import com.yxk.tjm.tianjiumeng.my.bean.MyInfoBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {

    private TextView tv_commission_detail;
    private TextView tv_return_money_detail;
    private TextView tv_wait_pay;
    private TextView tv_wait_ship;
    private TextView tv_wait_get;
    private TextView tv_sale_after;
    private TextView tv_message;
    private TextView tv_collect;
    private TextView tv_crystal_detail;
    private TextView tv_share;
    private TextView tv_my_custom;
    private TextView tv_recharge;
    private TextView tv_card;
    private LinearLayout ll_commission;
    private LinearLayout ll_crystal;
    private TextView tv_appraise;
    private TextView tv_setting;
    private ImageView img_head;
    private TextView tv_tjm_house;
    private TextView tv_nick_name;
    private TextView tv_rakeoff;
    private TextView tv_jewel;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView(View view) {
        tv_commission_detail = (TextView) view.findViewById(R.id.tv_commission_detail);
        tv_return_money_detail = (TextView) view.findViewById(R.id.tv_return_money_detail);
        tv_wait_pay = (TextView) view.findViewById(R.id.tv_wait_pay);
        tv_wait_ship = (TextView) view.findViewById(R.id.tv_wait_ship);
        tv_wait_get = (TextView) view.findViewById(R.id.tv_wait_get);
        tv_sale_after = (TextView) view.findViewById(R.id.tv_sale_after);
        tv_message = (TextView) view.findViewById(R.id.tv_message);
        tv_collect = (TextView) view.findViewById(R.id.tv_collect);
        tv_crystal_detail = (TextView) view.findViewById(R.id.tv_crystal_detail);
        tv_share = (TextView) view.findViewById(R.id.tv_share);
        tv_my_custom = (TextView) view.findViewById(R.id.tv_my_custom);
        tv_recharge = (TextView) view.findViewById(R.id.tv_recharge);
        tv_card = (TextView) view.findViewById(R.id.tv_card);
        ll_commission = (LinearLayout) view.findViewById(R.id.ll_commission);
        ll_crystal = (LinearLayout) view.findViewById(R.id.ll_crystal);
        tv_appraise = (TextView) view.findViewById(R.id.tv_appraise);
        tv_setting = (TextView) view.findViewById(R.id.tv_setting);
        tv_tjm_house = (TextView) view.findViewById(R.id.tv_tjm_house);
        tv_nick_name = (TextView) view.findViewById(R.id.tv_nick_name);
        tv_rakeoff = (TextView) view.findViewById(R.id.tv_rakeoff);
        tv_jewel = (TextView) view.findViewById(R.id.tv_jewel);
        tv_nick_name = (TextView) view.findViewById(R.id.tv_nick_name);
        img_head = (ImageView) view.findViewById(R.id.img_head);

        tv_commission_detail.setOnClickListener(this);
        tv_return_money_detail.setOnClickListener(this);
        tv_wait_pay.setOnClickListener(this);
        tv_wait_get.setOnClickListener(this);
        tv_wait_ship.setOnClickListener(this);
        tv_sale_after.setOnClickListener(this);
        tv_message.setOnClickListener(this);
        tv_collect.setOnClickListener(this);
        tv_crystal_detail.setOnClickListener(this);
        tv_share.setOnClickListener(this);
        tv_my_custom.setOnClickListener(this);
        tv_recharge.setOnClickListener(this);
        tv_card.setOnClickListener(this);
        tv_appraise.setOnClickListener(this);
        ll_crystal.setOnClickListener(this);
        ll_commission.setOnClickListener(this);
        tv_setting.setOnClickListener(this);
        img_head.setOnClickListener(this);
        tv_tjm_house.setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public void onResume() {
        super.onResume();

        initData();

    }

    private void initData() {
        OkHttpUtils
                .get()
                .url(ApiConstants.MY_INFO)
                .addParams("userId", UserUtil.getUserId(App.getAppContext()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("MyFragment initData() :", response);
                        MyInfoBean myInfoBean = new Gson().fromJson(response, MyInfoBean.class);
                        Glide.with(App.getAppContext())
                                .load(myInfoBean.getAvatar())
                                .skipMemoryCache(true)
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .into(img_head);
                        tv_nick_name.setText(myInfoBean.getNickname());
                        tv_rakeoff.setText(myInfoBean.getRakeoff() + "");
                        tv_jewel.setText(myInfoBean.getJewel() + "");
                    }
                });
    }

    /**
     * 可见不可见状态改变时调用
     *
     * @param hidden 可见时为true
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && isResumed()) {
            initData();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_commission_detail:
                startActivity(new Intent(getActivity(), CommissionActivity.class));
                break;

            case R.id.tv_return_money_detail:
                startActivity(new Intent(getActivity(), ReturnMoneyDetailActivity.class));
                break;

            case R.id.tv_wait_pay:
                startActivity(new Intent(getActivity(), WaitPayActivity.class));
                break;

            case R.id.tv_wait_ship:
                startActivity(new Intent(getActivity(), WaitShipActivity.class));
                break;

            case R.id.tv_wait_get:
                startActivity(new Intent(getActivity(), WaitGetActivity.class));
                break;

            case R.id.tv_sale_after:
                startActivity(new Intent(getActivity(), SaleAfterActivity.class));
                break;

            case R.id.tv_message:
                startActivity(new Intent(getActivity(), MessageActivity.class));
                break;

            case R.id.tv_collect:
                startActivity(new Intent(getActivity(), CollectActivity.class));
                break;

            case R.id.tv_crystal_detail:
                startActivity(new Intent(getActivity(), CrystalDetailActivity.class));
                break;

            case R.id.tv_share:
                startActivity(new Intent(getActivity(), ShareActivity.class));
                break;

            case R.id.tv_my_custom:
                startActivity(new Intent(getActivity(), CustomActivity.class));
                break;

            case R.id.img_head:
                startActivity(new Intent(getActivity(), MyInfoActivity.class));
                break;

            case R.id.tv_recharge:
                startActivity(new Intent(getActivity(), RechargeActivity.class));
                break;

            case R.id.tv_card:
                startActivity(new Intent(getActivity(), CardActivity.class));
                break;

            case R.id.ll_commission:
                startActivity(new Intent(getActivity(), MyCommissionActivity.class));
                break;

            case R.id.ll_crystal:
                startActivity(new Intent(getActivity(), MyCrystalActivity.class));
                break;

            case R.id.tv_appraise:
                startActivity(new Intent(getActivity(), MyWaitAppraiseActivity.class));
                break;

            case R.id.tv_setting:
                startActivity(new Intent(getActivity(), MySettingActivity.class));
                break;

            case R.id.tv_tjm_house:
                startActivity(new Intent(getActivity(), TJMHouseActivity.class));
                break;
        }
    }
}
