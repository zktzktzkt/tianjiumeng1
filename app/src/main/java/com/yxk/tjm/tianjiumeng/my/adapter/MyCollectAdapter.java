package com.yxk.tjm.tianjiumeng.my.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.my.bean.CollectBean;

import java.util.List;

/**
 * Created by ningfei on 2017/3/10.
 */

public class MyCollectAdapter extends RecyclerView.Adapter<MyCollectAdapter.MyHolder> {
    List<CollectBean> datas;
    CheckBox checkboxAll;
    Button btn_delete;

    public MyCollectAdapter(List<CollectBean> datas, CheckBox checkbox, Button btn_delete) {
        this.datas = datas;
        this.checkboxAll = checkbox;
        this.btn_delete = btn_delete;
        //设置全选按钮的监听
        checkAllListener();

        //删除按钮点击监听
        btnDeleteListener();
    }

    @Override
    public MyCollectAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collect, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyCollectAdapter.MyHolder holder, final int position) {
        if (datas.get(position).isVisibleChildCb()) {
            holder.checkbox_child.setVisibility(View.VISIBLE);
        } else {
            holder.checkbox_child.setVisibility(View.GONE);
        }

        if (datas.get(position).isChecked()) {
            holder.checkbox_child.setChecked(true);
        } else {
            holder.checkbox_child.setChecked(false);
        }

        holder.checkbox_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置取反状态
                datas.get(position).setChecked(!datas.get(position).isChecked());
                //设置全选按钮是否选中
                showCheckAllIsChecked();
            }
        });
    }

    /**
     * 删除按钮监听
     */
    private void btnDeleteListener() {
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem();
            }
        });
    }

    /**
     * 删除条目
     */
    private void removeItem() {
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).isChecked()) {
                datas.remove(i);
                notifyItemRemoved(i);
                i--;
                notifyItemRangeChanged(i, getItemCount());
            }
        }
    }

    /**
     * 设置列表整体的选择状态
     */
    private void listCheckboxState(boolean state) {
        for (CollectBean collectBean : datas) {
            collectBean.setChecked(state);
        }

    }

    /**
     * 全选按钮的点击监听
     */
    private void checkAllListener() {
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置全选或非全选
                checkAll_none(checkboxAll.isChecked());

                notifyDataSetChanged();
            }
        });
    }

    public void setChildCheckboxVisible(boolean visible) {
        for (CollectBean collectBean : datas) {
            if (!collectBean.isChecked()) {
                collectBean.setVisibleChildCb(visible);
            } else {
                collectBean.setVisibleChildCb(visible);
            }
        }
    }

    /**
     * 设置全选或非全选
     */
    public void checkAll_none(boolean isCheck) {
        if (isCheck) {
            listCheckboxState(true);
        } else {
            listCheckboxState(false);
        }
    }

    /**
     * 设置全选按钮是否选中
     */
    private void showCheckAllIsChecked() {
        for (CollectBean collectBean : datas) {
            if (!collectBean.isChecked()) {
                checkboxAll.setChecked(false);
                break;
            } else {
                checkboxAll.setChecked(true);
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final CheckBox checkbox_child;

        public MyHolder(View itemView) {
            super(itemView);
            checkbox_child = (CheckBox) itemView.findViewById(R.id.checkbox_child);
        }
    }
}
