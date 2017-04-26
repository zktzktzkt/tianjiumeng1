package com.yxk.tjm.tianjiumeng.my.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.my.bean.ReturnMoneyBean;
import com.yxk.tjm.tianjiumeng.utils.DateUtil;

import java.util.List;

public class WaitReturnMoneyAdapter extends RecyclerView.Adapter<WaitReturnMoneyAdapter.ViewHolder> {
    List<ReturnMoneyBean> waitReturnList;

    public WaitReturnMoneyAdapter(List<ReturnMoneyBean> waitReturnList) {
        this.waitReturnList = waitReturnList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wait_return, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Glide.with(App.getAppContext()).load(waitReturnList.get(position).getShowPic()).into(holder.img_pic);

        holder.tv_date.setText(DateUtil.longToString(waitReturnList.get(position).getStartTime(), "yyyy-MM-dd"));
        holder.tv_return_percent.setText("返利比例：" + waitReturnList.get(position).getFeedbackRate() + "%");
        holder.tv_return_cycle.setText("返利周期：" + waitReturnList.get(position).getFeedbackTime() + "天");
        holder.tv_orderId.setText("订单号：" + waitReturnList.get(position).getOrderId());
        holder.tv_price.setText("¥" + waitReturnList.get(position).getProductPrice());
    }

    @Override
    public int getItemCount() {
        return waitReturnList == null ? 0 : waitReturnList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_return_percent;
        private final TextView tv_return_cycle;
        private final TextView tv_price;
        private final TextView tv_orderId;
        private final TextView tv_date;
        private final ImageView img_pic;

        public ViewHolder(View view) {
            super(view);
            tv_return_percent = (TextView) itemView.findViewById(R.id.tv_return_size);
            tv_return_cycle = (TextView) itemView.findViewById(R.id.tv_return_cycle);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_orderId = (TextView) itemView.findViewById(R.id.tv_orderId);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            img_pic = (ImageView) itemView.findViewById(R.id.img_pic);
        }
    }
}
