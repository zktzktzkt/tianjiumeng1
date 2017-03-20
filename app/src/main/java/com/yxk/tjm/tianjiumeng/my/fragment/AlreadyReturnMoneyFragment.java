package com.yxk.tjm.tianjiumeng.my.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.my.bean.ReturnningMoneyBean;

import java.util.ArrayList;
import java.util.List;

public class AlreadyReturnMoneyFragment extends Fragment {

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
        recycler.setAdapter(new BaseQuickAdapter<ReturnningMoneyBean, BaseViewHolder>(R.layout.item_already_return, list) {

            @Override
            protected void convert(BaseViewHolder helper, ReturnningMoneyBean item) {
                // helper.getLayoutPosition()  //获取当前position
            }
        });
        return view;
    }


}
