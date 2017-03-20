package com.yxk.tjm.tianjiumeng.category.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.category.bean.RightCategoryBean;

import java.util.List;

/**
 * Created by zkt on 2017/3/12.
 */

public class RightCommonAdapter extends RecyclerView.Adapter<RightCommonAdapter.MyHolder> {
    List<RightCategoryBean> rightList;

    public RightCommonAdapter(List<RightCategoryBean> rightList) {
        this.rightList = rightList;
    }

    @Override
    public RightCommonAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_right_header_category, parent, false));
    }

    @Override
    public void onBindViewHolder(RightCommonAdapter.MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return rightList == null ? 0 : rightList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public MyHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getLayoutPosition());
                }
            });
        }
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
