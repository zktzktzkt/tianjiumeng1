package com.yxk.tjm.tianjiumeng.home.adapter;

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
import com.yxk.tjm.tianjiumeng.home.bean.ProductInnerDetailBean;

import java.util.List;

/**
 * Created by ningfei on 2017/3/9.
 */

public class RecommendForYouAdapter extends RecyclerView.Adapter<RecommendForYouAdapter.MyHolder> {
    List<ProductInnerDetailBean.CnnmdForYouBean> mDatas;

    @Override
    public RecommendForYouAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecommendForYouAdapter.MyHolder holder, int position) {
        Glide.with(App.getAppContext()).load(mDatas.get(position).getShowpic()).into(holder.img_pic);
        holder.tv_original_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tv_name.setText(mDatas.get(position).getName());
        holder.tv_price.setText(App.getAppContext().getResources().getString(R.string.RMB) + mDatas.get(position).getNowprice());
        holder.tv_original_price.setText(App.getAppContext().getResources().getString(R.string.RMB) + mDatas.get(position).getOrgnprice());
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }


    public void setMatchData(List<ProductInnerDetailBean.CnnmdForYouBean> datas) {
        this.mDatas = datas;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView img_pic;
        private final TextView tv_original_price;
        private final TextView tv_name;
        private final TextView tv_price;

        public MyHolder(View itemView) {
            super(itemView);
            img_pic = (ImageView) itemView.findViewById(R.id.img_pic);
            tv_original_price = (TextView) itemView.findViewById(R.id.tv_original_price);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getLayoutPosition());
                }
            });
        }
    }
}
