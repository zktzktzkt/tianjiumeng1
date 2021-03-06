package com.yxk.tjm.tianjiumeng.shopcar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.SubmitOrderActivity;
import com.yxk.tjm.tianjiumeng.custom.MyToolbar;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.shopcar.adapter.ShopCartAdapter;
import com.yxk.tjm.tianjiumeng.shopcar.bean.ShopCartBean;
import com.yxk.tjm.tianjiumeng.shopcar.bean.ShopcartSerialize;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


public class ShopCartFragment extends Fragment {

    private RecyclerView recycler;
    private CheckBox checkbox;
    private TextView tv_all_price;
    private Button btn_account;
    private Button btn_delete;
    private MyToolbar mToolbar;
    private RelativeLayout relative_edit;
    private RelativeLayout relative;
    private ShopCartAdapter shopCartAdapter;
    private CheckBox checkbox_edit;
    public ShopCartBean shopCartBean;
    public TextView tv_shopcart_null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_cart, container, false);
        mToolbar = (MyToolbar) view.findViewById(R.id.toolbar);
        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        checkbox = (CheckBox) view.findViewById(R.id.checkbox);
        tv_all_price = (TextView) view.findViewById(R.id.tv_all_price);
        btn_account = (Button) view.findViewById(R.id.btn_account);
        btn_delete = (Button) view.findViewById(R.id.btn_delete);
        relative_edit = (RelativeLayout) view.findViewById(R.id.relative_edit);
        relative = (RelativeLayout) view.findViewById(R.id.relative);
        checkbox_edit = (CheckBox) view.findViewById(R.id.checkbox_edit);
        tv_shopcart_null = (TextView) view.findViewById(R.id.tv_shopcart_null);

        LogUtil.e("ShopCartFragment ", "onCreatView");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();

        btnAccountListener();

        LogUtil.e("ShopCartFragment ", "onActivityCreated");
    }

    private void btnAccountListener() {
        btn_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ShopcartSerialize> list = new ArrayList<ShopcartSerialize>();
                for (int i = 0; i < shopCartBean.getBuyitem().size(); i++) {
                    list.add(new ShopcartSerialize(shopCartBean.getBuyitem().get(i).getBuyCart().getBuyCartId() + "",
                            shopCartBean.getBuyitem().get(i).getBuyCart().getGoodsAccant() + ""));
                }

                Intent intent = new Intent(getActivity(), SubmitOrderActivity.class);
                intent.putExtra("amount", shopCartAdapter.computeCheckedCount());
                intent.putExtra("totalPrice", shopCartAdapter.getTotalPrice());
                intent.putExtra("shopcartList", (Serializable) list);
                getActivity().startActivity(intent);
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
            LogUtil.e("ShopCartFragment ", "onHiddenChanged");
            initData();

            btnAccountListener();
        }
    }

    private void initData() {
        LogUtil.e("userId", UserUtil.getUserId(getContext()));
        OkHttpUtils
                .get()
                .url(ApiConstants.SHOPCAR)
                .addParams("userId", UserUtil.getUserId(getContext()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("ShopCartFragment ", "e：" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("ShopCartFragment ", "initData response:" + response);
                        Gson gson = new Gson();
                        shopCartBean = gson.fromJson(response, ShopCartBean.class);
                        if (shopCartBean.getBuyitem().size() <= 0) {
                            tv_shopcart_null.setVisibility(View.VISIBLE);
                            relative.setVisibility(View.GONE);
                            mToolbar.setEditVisible(false);
                        } else {
                            tv_shopcart_null.setVisibility(View.GONE);
                            relative.setVisibility(View.VISIBLE);
                            mToolbar.setEditVisible(true);

                            initRecyclerView(shopCartBean.getBuyitem());
                            toolbarEditClickListener();
                        }

                    }
                });

    }

    private void initRecyclerView(List<ShopCartBean.BuyitemBean> list) {
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        shopCartAdapter = new ShopCartAdapter(list, checkbox, tv_all_price, btn_account, checkbox_edit, btn_delete, tv_shopcart_null, mToolbar);
        recycler.setAdapter(shopCartAdapter);
    }

    private void toolbarEditClickListener() {
        mToolbar.setOnEditClickListener(new MyToolbar.OnEditClickListener() {
            @Override
            public void onEditClik(String tag) {
                switch (tag) {
                    //点击编辑
                    case MyToolbar.EDIT_TAG:
                        relative.setVisibility(View.GONE);
                        relative_edit.setVisibility(View.VISIBLE);
                        listCheckboxState(false, ShopCartFragment.this.shopCartBean.getBuyitem());
                        checkbox_edit.setChecked(false);
                        shopCartAdapter.notifyDataSetChanged();
                        break;

                    //点击完成
                    case MyToolbar.OK_TAG:
                        relative.setVisibility(View.VISIBLE);
                        relative_edit.setVisibility(View.GONE);
                        listCheckboxState(true, ShopCartFragment.this.shopCartBean.getBuyitem());
                        checkbox.setChecked(true);
                        shopCartAdapter.notifyDataSetChanged();
                        shopCartAdapter.showTotalPrice();
                        shopCartAdapter.showCheckedCount();
                        break;
                }
            }
        });
    }

    private void listCheckboxState(boolean state, List<ShopCartBean.BuyitemBean> buyitem) {
        for (ShopCartBean.BuyitemBean shopCartBean : buyitem) {
            shopCartBean.setChecked(state);
        }
    }

}
