package com.yxk.tjm.tianjiumeng.category.adapter;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.category.bean.CategoryBean;

import java.util.List;

/**
 * Created by zkt on 2017/3/12.
 */

public class RightCommonAdapter extends RecyclerView.Adapter<RightCommonAdapter.MyHolder> {
    List<CategoryBean.ProductListBean> rightList;

    public RightCommonAdapter(List<CategoryBean.ProductListBean> rightList) {
        this.rightList = rightList;
    }

    @Override
    public RightCommonAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_right_common_category, parent, false));
    }

    @Override
    public void onBindViewHolder(RightCommonAdapter.MyHolder holder, int position) {
        Glide.with(App.getAppContext()).load(rightList.get(position).getShowpic()).into(holder.img_pic);
        holder.tv_name.setText(rightList.get(position).getName());
        holder.tv_price.setText(App.getAppContext().getResources().getString(R.string.RMB) + rightList.get(position).getNowprice());
        holder.tv_original_price.setText(App.getAppContext().getResources().getString(R.string.RMB) + rightList.get(position).getOrgnprice());
        holder.tv_original_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);  //添加删除线
    }

    public void setData(List<CategoryBean.ProductListBean> rightList){
        this.rightList = rightList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return rightList == null ? 0 : rightList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final TextView tv_name;
        private final TextView tv_price;
        private final TextView tv_original_price;
        private final ImageView img_pic;

        public MyHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getLayoutPosition());
                }
            });

            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_original_price = (TextView) itemView.findViewById(R.id.tv_original_price);
            img_pic = (ImageView) itemView.findViewById(R.id.img_pic);

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
