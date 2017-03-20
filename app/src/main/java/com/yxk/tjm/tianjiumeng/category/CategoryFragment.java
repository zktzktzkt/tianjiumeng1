package com.yxk.tjm.tianjiumeng.category;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.category.activity.RightHeaderListActivity;
import com.yxk.tjm.tianjiumeng.category.adapter.LeftCategoryAdapter;
import com.yxk.tjm.tianjiumeng.category.bean.LeftCategoryBean;
import com.yxk.tjm.tianjiumeng.category.bean.RightCategoryBean1;
import com.yxk.tjm.tianjiumeng.home.activity.ProductDetailActivity;
import com.yxk.tjm.tianjiumeng.home.activity.SearchActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recycler_left;
    private List<LeftCategoryBean> leftList;
    private List<RightCategoryBean1> rightList;
    private List<RightCategoryBean1> rightList1;
    private RecyclerView recycler_right_header;
    private RecyclerView recycler_right_common;
    private RelativeLayout rl_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recycler_left = (RecyclerView) view.findViewById(R.id.recycler_left);
        recycler_right_header = (RecyclerView) view.findViewById(R.id.recycler_right_header);
        recycler_right_common = (RecyclerView) view.findViewById(R.id.recycler_right_common);
        rl_search = (RelativeLayout) view.findViewById(R.id.rl_search);

        rl_search.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // recycler_left.setAdapter();
        leftList = new ArrayList<>();
        leftList.add(new LeftCategoryBean("香槟杯"));
        leftList.add(new LeftCategoryBean("红酒杯"));
        leftList.add(new LeftCategoryBean("甜酒杯"));
        leftList.add(new LeftCategoryBean("白兰地杯"));
        leftList.add(new LeftCategoryBean("装饰品"));
        leftList.add(new LeftCategoryBean("其他"));

        recycler_left.setLayoutManager(new LinearLayoutManager(getContext()));
        final LeftCategoryAdapter leftCategoryAdapter = new LeftCategoryAdapter(leftList);
        recycler_left.setAdapter(leftCategoryAdapter);
        leftCategoryAdapter.setOnItemClickListener(new LeftCategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                leftCategoryAdapter.selectPos = position;
                leftCategoryAdapter.notifyDataSetChanged();
            }
        });

        rightList = new ArrayList<>();
        rightList.add(new RightCategoryBean1(R.drawable.pic_a, "白兰地"));
        rightList.add(new RightCategoryBean1(R.drawable.pic_b, "白兰地"));
        rightList.add(new RightCategoryBean1(R.drawable.pic_c, "白兰地"));
        rightList.add(new RightCategoryBean1(R.drawable.pic_e, "白兰地"));
        rightList.add(new RightCategoryBean1(R.drawable.pic_d, "白兰地"));
        rightList.add(new RightCategoryBean1(R.drawable.pic_g, "白兰地"));
        rightList.add(new RightCategoryBean1(R.drawable.pic_f, "白兰地"));
        rightList.add(new RightCategoryBean1(R.drawable.pic_c, "白兰地"));
        rightList.add(new RightCategoryBean1(R.drawable.pic_b, "白兰地"));

        recycler_right_header.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recycler_right_header.setAdapter(new BaseQuickAdapter<RightCategoryBean1, BaseViewHolder>(R.layout.item_right_header_category, rightList) {
            @Override
            protected void convert(BaseViewHolder helper, RightCategoryBean1 item) {
                // helper.getLayoutPosition()  //获取当前position
                helper.setImageResource(R.id.img_pic, item.getResImage());
                helper.setText(R.id.tv_name, item.getName());
            }
        });

        recycler_right_header.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), RightHeaderListActivity.class));
            }
        });


        rightList1 = new ArrayList<>();
        rightList1.add(new RightCategoryBean1(R.drawable.pic_a, "白兰地"));
        rightList1.add(new RightCategoryBean1(R.drawable.pic_b, "白兰地"));
        rightList1.add(new RightCategoryBean1(R.drawable.pic_c, "白兰地"));
        rightList1.add(new RightCategoryBean1(R.drawable.pic_e, "白兰地"));
        rightList1.add(new RightCategoryBean1(R.drawable.pic_d, "白兰地"));
        rightList1.add(new RightCategoryBean1(R.drawable.pic_g, "白兰地"));
        rightList1.add(new RightCategoryBean1(R.drawable.pic_f, "白兰地"));
        rightList1.add(new RightCategoryBean1(R.drawable.pic_c, "白兰地"));
        rightList1.add(new RightCategoryBean1(R.drawable.pic_b, "白兰地"));

        recycler_right_common.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recycler_right_common.setAdapter(new BaseQuickAdapter<RightCategoryBean1, BaseViewHolder>(R.layout.item_right_header_category, rightList1) {
            @Override
            protected void convert(BaseViewHolder helper, RightCategoryBean1 item) {
                // helper.getLayoutPosition()  //获取当前position
                helper.setImageResource(R.id.img_pic, item.getResImage());
                helper.setText(R.id.tv_name, item.getName());
            }
        });

        recycler_right_common.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), ProductDetailActivity.class));
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_search:
               startActivity(new Intent(getActivity(), SearchActivity.class));
                getActivity().overridePendingTransition(R.anim.enter_alpha, R.anim.out_alpha);
                break;
        }
    }
}
