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
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.my.bean.WaitPayMoneyCustomBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.DateUtil;
import com.yxk.tjm.tianjiumeng.utils.To;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitGetCustomFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    private List<WaitPayMoneyCustomBean> waitPayMoneyCustomList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wait_pay_money_custom, container, false);
        ButterKnife.bind(this, view);

        OkHttpUtils.get()
                .url(ApiConstants.MY_CUSTOM)
                .addParams("userId", UserUtil.getUserId(App.getAppContext()))
                .addParams("tailorState", "2")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        waitPayMoneyCustomList = new Gson().fromJson(response, new TypeToken<List<WaitPayMoneyCustomBean>>() {
                        }.getType());

                        recycler.setAdapter(new BaseQuickAdapter<WaitPayMoneyCustomBean, BaseViewHolder>(R.layout.item_wait_get_custom, waitPayMoneyCustomList) {

                            @Override
                            protected void convert(BaseViewHolder helper, WaitPayMoneyCustomBean item) {
                                Glide.with(App.getAppContext()).load(item.getTailorShowPic()).into((ImageView) helper.getView(R.id.img_pic));
                                helper.setText(R.id.tv_date, DateUtil.longToString(item.getCreateDate(), "yyyy.MM.dd"));
                                helper.setText(R.id.tv_return_size, "尺寸：" + item.getTailorSize() + "cm");
                                helper.setText(R.id.tv_texture, "材质：" + item.getTailorMaterial());
                                helper.setText(R.id.tv_num, "数量" + item.getTailorAmount() + "个");
                                helper.setText(R.id.tv_detail, item.getTailorDecr());
                                helper.setText(R.id.tv_orderId, "订单号：" + item.getOrderId());
                            }
                        });
                    }
                });

        recycler.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.btn_see:
                        To.showShort(App.getAppContext(), "查看物流");
                        break;

                    case R.id.btn_pay:
                        To.showShort(App.getAppContext(), "确认收货");
                        break;
                }
            }
        });

        return view;
    }

}
