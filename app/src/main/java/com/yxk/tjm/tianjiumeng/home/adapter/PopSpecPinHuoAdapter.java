package com.yxk.tjm.tianjiumeng.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.home.bean.HotStrugDetailBean;

import java.util.List;

/**
 * Created by ningfei on 2017/3/9.
 */

public class PopSpecPinHuoAdapter<T> extends RecyclerView.Adapter<PopSpecPinHuoAdapter.MyHolder> {
    private List<HotStrugDetailBean.HWsBean> mDatas;

    private int lastPos = -1;

    @Override
    public PopSpecPinHuoAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pop_spec, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final PopSpecPinHuoAdapter.MyHolder holder, final int position) {
        holder.tv_spec.setText(mDatas.get(position).getGoodsHeight() + "x" + mDatas.get(position).getGoodsWide());

        if (mDatas.get(position).isSelected()) {
            holder.tv_spec.setSelected(true);
            holder.tv_spec.setTextColor(App.getAppContext().getResources().getColor(R.color.white));
        } else {
            holder.tv_spec.setSelected(false);
            holder.tv_spec.setTextColor(App.getAppContext().getResources().getColor(R.color.tabBg));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void setMatchData(List<T> datas) {
        this.mDatas = (List<HotStrugDetailBean.HWsBean>) datas;
    }

    public int getLastPos() {
        return lastPos;
    }

    public void setLastPos(int lastPos) {
        this.lastPos = lastPos;
    }


    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public final TextView tv_spec;

        public MyHolder(View itemView) {
            super(itemView);
            tv_spec = (TextView) itemView.findViewById(R.id.tv_spec);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getLayoutPosition());
                }
            });
        }
    }
}
