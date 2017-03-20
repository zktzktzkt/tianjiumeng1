package com.yxk.tjm.tianjiumeng.my.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.my.bean.ReturnningMoneyBean;
import com.yxk.tjm.tianjiumeng.utils.T;

import java.util.ArrayList;
import java.util.List;

public class WaitReplyFragment extends Fragment {

    private RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        List<ReturnningMoneyBean> list = new ArrayList();
        list.add(new ReturnningMoneyBean());
        list.add(new ReturnningMoneyBean());
        list.add(new ReturnningMoneyBean());
        list.add(new ReturnningMoneyBean());
        list.add(new ReturnningMoneyBean());
        list.add(new ReturnningMoneyBean());
        list.add(new ReturnningMoneyBean());

        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        recycler.setAdapter(new BaseQuickAdapter<ReturnningMoneyBean, BaseViewHolder>(R.layout.item_wait_reply, list) {

            @Override
            protected void convert(BaseViewHolder helper, ReturnningMoneyBean item) {
                // helper.getLayoutPosition()  //获取当前position
                helper.addOnClickListener(R.id.btn_get_money)
                        .addOnClickListener(R.id.btn_switch_crystal);
            }
        });

        recycler.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.btn_switch_crystal:
                        T.showShort(App.getAppContext(), "修改定制");
                        break;

                    case R.id.btn_get_money:
                        T.showShort(App.getAppContext(), "取消定制");
                        break;
                }
            }
        });
        return view;
    }
}



