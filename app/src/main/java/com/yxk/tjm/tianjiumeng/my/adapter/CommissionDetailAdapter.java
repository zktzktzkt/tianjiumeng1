package com.yxk.tjm.tianjiumeng.my.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.my.bean.CommissionBean;
import com.yxk.tjm.tianjiumeng.utils.DateUtil;

import java.util.List;

/**
 * Created by zkt on 2017/3/12.
 */

public class CommissionDetailAdapter extends RecyclerView.Adapter<CommissionDetailAdapter.MyHolder> {
    List<CommissionBean> commissionList;
    public CommissionDetailAdapter(List<CommissionBean> commissionList) {
        this.commissionList = commissionList;
    }

    @Override
    public CommissionDetailAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commission, parent, false));
    }

    @Override
    public void onBindViewHolder(CommissionDetailAdapter.MyHolder holder, int position) {
        holder.tv_monthday.setText(DateUtil.longToString(commissionList.get(position).getCreatetime(), "yyyy-MM-dd"));
        holder.tv_hourMinute.setText(DateUtil.longToString(commissionList.get(position).getCreatetime(), "HH:mm"));
        holder.tv_detail.setText(commissionList.get(position).getDecr());
        holder.tv_money.setText(commissionList.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return commissionList == null ? 0 : commissionList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final TextView tv_monthday;
        private final TextView tv_hourMinute;
        private final TextView tv_detail;
        private final TextView tv_money;

        public MyHolder(View itemView) {
            super(itemView);

            tv_monthday = (TextView) itemView.findViewById(R.id.tv_monthday);
            tv_hourMinute = (TextView) itemView.findViewById(R.id.tv_hourMinute);
            tv_detail = (TextView) itemView.findViewById(R.id.tv_detail);
            tv_money = (TextView) itemView.findViewById(R.id.tv_money);
        }
    }
}
