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
import com.yxk.tjm.tianjiumeng.my.bean.WaitReplyBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.DateUtil;
import com.yxk.tjm.tianjiumeng.utils.To;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class WaitReplyFragment extends Fragment {

    private RecyclerView recycler;
    private List<WaitReplyBean> waitReplyBeanList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        recycler = (RecyclerView) view.findViewById(R.id.recycler);

        OkHttpUtils.get()
                .url(ApiConstants.MY_CUSTOM)
                .addParams("userId", UserUtil.getUserId(App.getAppContext()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        waitReplyBeanList = new Gson().fromJson(response, new TypeToken<List<WaitReplyBean>>() {
                        }.getType());

                        recycler.setAdapter(new BaseQuickAdapter<WaitReplyBean, BaseViewHolder>(R.layout.item_wait_reply, waitReplyBeanList) {

                            @Override
                            protected void convert(BaseViewHolder helper, WaitReplyBean item) {
                                Glide.with(App.getAppContext()).load(item.getTailorShowPic()).into((ImageView) helper.getView(R.id.img_pic));
                                helper.setText(R.id.tv_date, DateUtil.longToString(item.getTailorCrtDate(), "yyyy.MM.dd"));
                                helper.setText(R.id.tv_return_size, "尺寸：" + item.getTailorSize() + "cm");
                                helper.setText(R.id.tv_texture, "材质：" + item.getTailorMaterial());
                                helper.setText(R.id.tv_num, "数量" + item.getTailorAmount() + "个");
                                helper.setText(R.id.tv_detail, item.getTailorDecr());
                            }
                        });
                    }
                });


        recycler.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.btn_switch_crystal:
                        To.showShort(App.getAppContext(), "修改定制");
                        break;

                    case R.id.btn_get_money:
                        To.showShort(App.getAppContext(), "取消定制");
                        break;
                }
            }
        });
        return view;
    }
}



