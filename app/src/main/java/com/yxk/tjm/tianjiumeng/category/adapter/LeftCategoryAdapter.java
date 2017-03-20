package com.yxk.tjm.tianjiumeng.category.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.category.bean.LeftCategoryBean;

import java.util.List;

/**
 * Created by zkt on 2017/3/11.
 */

public class LeftCategoryAdapter extends RecyclerView.Adapter<LeftCategoryAdapter.MyHolder> {
    List<LeftCategoryBean> leftList;
    public int selectPos = 0;

    public LeftCategoryAdapter(List<LeftCategoryBean> leftList) {
        this.leftList = leftList;
    }

    @Override
    public LeftCategoryAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_left_category, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(LeftCategoryAdapter.MyHolder holder, int position) {
        holder.tv_left_item.setText(leftList.get(position).getLeftItem());

        if (selectPos == position) {
            holder.tv_left_item.setSelected(true);
            holder.linear_left_item.setBackgroundColor(App.getAppContext().getResources().getColor(R.color.gray_common));
        } else {
            holder.tv_left_item.setSelected(false);
            holder.linear_left_item.setBackgroundColor(App.getAppContext().getResources().getColor(R.color.background));
        }

    }

    @Override
    public int getItemCount() {
        return leftList == null ? 0 : leftList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final TextView tv_left_item;
        private final LinearLayout linear_left_item;

        public MyHolder(View itemView) {
            super(itemView);
            tv_left_item = (TextView) itemView.findViewById(R.id.tv_left_item);
            linear_left_item = (LinearLayout) itemView.findViewById(R.id.linear_left_item);

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
