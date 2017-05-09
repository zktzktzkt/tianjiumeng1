package com.yxk.tjm.tianjiumeng.my.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import com.yxk.tjm.tianjiumeng.my.activity.UpdateCustomActivity;
import com.yxk.tjm.tianjiumeng.my.bean.WaitReplyBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.DateUtil;
import com.yxk.tjm.tianjiumeng.utils.To;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

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

        recycler.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()) {
                    case R.id.btn_switch_crystal:
                        Intent intent = new Intent(getActivity(), UpdateCustomActivity.class);
                        intent.putExtra("tailorId", waitReplyBeanList.get(position).getTailorId());
                        intent.putExtra("updateCustom", "修改定制");
                        startActivity(intent);
                        break;

                    case R.id.btn_cancel:

                        new AlertDialog.Builder(getActivity())
                                .setTitle("确定要取消定制？")
                                .setPositiveButton("确定",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                cancelCustom(position);
                                            }
                                        }).setNegativeButton("取消", null).create()
                                .show();
                        break;
                }
            }
        });
        return view;
    }

    /**
     * 取消定制
     *
     * @param position
     */
    private void cancelCustom(int position) {
        OkHttpUtils.get()
                .url(ApiConstants.MY_CUSTOM_CANCEL)
                .addParams("tailorId", waitReplyBeanList.get(position).getTailorId())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jo = new JSONObject(response);
                            if ((int) jo.get("success") == 0) {
                                To.showShort(App.getAppContext(), "取消成功！");
                                initData();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void initData() {
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
                                helper.addOnClickListener(R.id.btn_switch_crystal);
                                helper.addOnClickListener(R.id.btn_cancel);
                                Glide.with(App.getAppContext()).load(item.getTailorShowPic()).into((ImageView) helper.getView(R.id.img_pic));
                                helper.setText(R.id.tv_date, DateUtil.longToString(item.getTailorCrtDate(), "yyyy.MM.dd"));
                                helper.setText(R.id.tv_name, "尺寸：" + item.getTailorSize() + "cm");
                                helper.setText(R.id.tv_texture, "材质：" + item.getTailorMaterial());
                                helper.setText(R.id.tv_num, "数量：" + item.getTailorAmount() + "个");
                                helper.setText(R.id.tv_price, item.getTailorDecr());
                            }
                        });
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}



