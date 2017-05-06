package com.yxk.tjm.tianjiumeng.my.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.my.bean.ReturnMoneyBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.DateUtil;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

public class CancelReturnMoneyFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        OkHttpUtils.get()
                .url(ApiConstants.MY_REBATE_DETAIL)
                .addParams("userId", UserUtil.getUserId(App.getAppContext()))
                .addParams("rebateState", "4")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        List<ReturnMoneyBean> cancelReturnList = new Gson().fromJson(response, new TypeToken<List<ReturnMoneyBean>>() {
                        }.getType());

                        recycler.setAdapter(new BaseQuickAdapter<ReturnMoneyBean, BaseViewHolder>(R.layout.item_cancel_return, cancelReturnList) {
                            @Override
                            protected void convert(BaseViewHolder helper, ReturnMoneyBean item) {
                                Glide.with(App.getAppContext()).load(item.getShowPic()).into((ImageView) helper.getView(R.id.img_pic));

                                helper.setText(R.id.tv_date, DateUtil.longToString(item.getStartTime(), "yyyy-MM-dd"));
                                helper.setText(R.id.tv_return_size, "返利比例：" + item.getFeedbackRate() + "%");
                                helper.setText(R.id.tv_return_cycle, "返利周期：" + item.getFeedbackTime() + "天");
                                helper.setText(R.id.tv_orderId, "订单号：" + item.getOrderId());
                                helper.setText(R.id.tv_price, getResources().getString(R.string.RMB) + item.getProductPrice());
                            }
                        });
                    }
                });


        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
