package com.yxk.tjm.tianjiumeng.my.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.my.bean.UpdataCustomBean;

import java.util.List;

/**
 * Created by ningfei on 2017/3/8.
 */

public class UpdateCustomAdapter extends RecyclerView.Adapter<UpdateCustomAdapter.MyHolder> {
    List<UpdataCustomBean.TailorPicsBean> mDatas;

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        return new MyHolder(from.inflate(R.layout.item_picture, parent, false));

    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Glide.with(App.getAppContext())
                .load(mDatas.get(position).getTailorPicUrl())
                .override(350, 350)
                .into(holder.img_pic);
    }


    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void setMatchData(List<UpdataCustomBean.TailorPicsBean> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        public ImageView img_pic;

        public MyHolder(View itemView) {
            super(itemView);
            img_pic = (ImageView) itemView.findViewById(R.id.img_pic);
        }
    }


    onItemClickListener onItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }
}
