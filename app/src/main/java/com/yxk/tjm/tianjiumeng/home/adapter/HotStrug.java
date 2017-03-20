package com.yxk.tjm.tianjiumeng.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxk.tjm.tianjiumeng.R;

/**
 * Created by ningfei on 2017/3/8.
 */

public class HotStrug extends RecyclerView.Adapter<HotStrug.MyHolder> {
    @Override
    public HotStrug.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot_strug, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(HotStrug.MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public MyHolder(View itemView) {
            super(itemView);
        }
    }
}
