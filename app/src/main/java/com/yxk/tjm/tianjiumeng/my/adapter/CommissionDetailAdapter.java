package com.yxk.tjm.tianjiumeng.my.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxk.tjm.tianjiumeng.R;

/**
 * Created by zkt on 2017/3/12.
 */

public class CommissionDetailAdapter extends RecyclerView.Adapter<CommissionDetailAdapter.MyHolder> {
    @Override
    public CommissionDetailAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commission, parent, false));
    }

    @Override
    public void onBindViewHolder(CommissionDetailAdapter.MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public MyHolder(View itemView) {
            super(itemView);
        }
    }
}
