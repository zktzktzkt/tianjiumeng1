package com.yxk.tjm.tianjiumeng.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.home.bean.CrystalHouseBean;

import java.util.List;

/**
 * Created by ningfei on 2017/3/8.
 */

public class CrystalHouseAdapter extends RecyclerView.Adapter<CrystalHouseAdapter.MyHolder> {
    List<CrystalHouseBean> crystalHouseList;

    public CrystalHouseAdapter(List<CrystalHouseBean> crystalHouseList) {
        this.crystalHouseList = crystalHouseList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(App.getAppContext()).inflate(R.layout.item_crystal_house, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tv_name.setText(crystalHouseList.get(position).getName());
        holder.tv_price.setText(App.getAppContext().getResources().getString(R.string.RMB)+crystalHouseList.get(position).getNowprice());
        Glide.with(App.getAppContext()).load(crystalHouseList.get(position).getShowpic()).into(holder.img_pic);
    }

    @Override
    public int getItemCount() {
        return crystalHouseList == null ? 0 : crystalHouseList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView img_pic;
        private final TextView tv_name;
        private final TextView tv_price;

        public MyHolder(View itemView) {
            super(itemView);
            img_pic = (ImageView) itemView.findViewById(R.id.img_pic);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(getLayoutPosition());
                    }
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
