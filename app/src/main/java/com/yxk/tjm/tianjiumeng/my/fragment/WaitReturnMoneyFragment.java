package com.yxk.tjm.tianjiumeng.my.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.my.adapter.WaitReturnMoneyAdapter;
import com.yxk.tjm.tianjiumeng.my.bean.ReturnMoneyBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

public class WaitReturnMoneyFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    private List<ReturnMoneyBean> waitReturnList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        OkHttpUtils.get()
                .url(ApiConstants.MY_REBATE_DETAIL)
                .addParams("userId", UserUtil.getUserId(App.getAppContext()))
                .addParams("rebateState", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        waitReturnList = new Gson().fromJson(response, new TypeToken<List<ReturnMoneyBean>>() {
                        }.getType());
                        recycler.setAdapter(new WaitReturnMoneyAdapter(waitReturnList));
                    }
                });

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
