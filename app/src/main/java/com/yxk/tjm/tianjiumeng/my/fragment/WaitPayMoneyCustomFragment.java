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
import com.yxk.tjm.tianjiumeng.my.bean.WaitPayBean;
import com.yxk.tjm.tianjiumeng.utils.To;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitPayMoneyCustomFragment extends Fragment {


    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wait_pay_money_custom, container, false);
        ButterKnife.bind(this, view);

        List<WaitPayBean> list = new ArrayList<>();
        list.add(new WaitPayBean());
        list.add(new WaitPayBean());
        list.add(new WaitPayBean());
        list.add(new WaitPayBean());
        list.add(new WaitPayBean());
        list.add(new WaitPayBean());

        recycler.setAdapter(new BaseQuickAdapter<WaitPayBean, BaseViewHolder>(R.layout.item_wait_pay_custom, list) {
            @Override
            protected void convert(BaseViewHolder helper, WaitPayBean item) {
                helper.addOnClickListener(R.id.btn_see)
                        .addOnClickListener(R.id.btn_pay);
            }
        });

        recycler.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.btn_see:
                        To.showShort(App.getAppContext(), "查看合同");
                        break;

                    case R.id.btn_pay:
                        To.showShort(App.getAppContext(), "立即付款");
                        break;
                }
            }
        });

        return view;
    }

}
