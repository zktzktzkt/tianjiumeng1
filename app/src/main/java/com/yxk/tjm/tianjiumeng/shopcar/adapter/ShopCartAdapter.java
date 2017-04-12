package com.yxk.tjm.tianjiumeng.shopcar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.custom.AmountView;
import com.yxk.tjm.tianjiumeng.shopcar.bean.ShopCartBean;

import java.util.List;

/**
 * Created by ningfei on 2017/3/10.
 */

public class ShopCartAdapter extends RecyclerView.Adapter<ShopCartAdapter.MyHolder> {
    List<ShopCartBean.BuyitemBean> datas;
    CheckBox checkboxAll;
    TextView tv_all_price;
    Button btn_account;
    CheckBox checkbox_edit;
    Button btn_delete;

    public ShopCartAdapter(List<ShopCartBean.BuyitemBean> datas, CheckBox checkbox, TextView tv_all_price, Button btn_account, CheckBox checkbox_edit, Button btn_delete) {
        this.datas = datas;
        this.checkboxAll = checkbox;
        this.tv_all_price = tv_all_price;
        this.btn_account = btn_account;
        this.checkboxAll.setChecked(true);
        this.checkbox_edit = checkbox_edit;
        this.btn_delete = btn_delete;
        //设置全选按钮的监听
        checkAllListener();

        //删除状态下的checkbox监听
        deleteStateCheckboxListener();

        //删除按钮点击监听
        btnDeleteListener();

        //设置总价格
        showTotalPrice();
        //设置选中的数量
        showCheckedCount();
    }



    @Override
    public ShopCartAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopcart, parent, false);
        return new MyHolder(view);
    }


    @Override
    public void onBindViewHolder(final ShopCartAdapter.MyHolder holder, final int position) {
        holder.itemView.setTag(position);
        holder.tv_title.setText(datas.get(position).getProduct().getName());
        Glide.with(App.getAppContext()).load(datas.get(position).getProduct().getShowpic()).into(holder.img_pic);
        holder.checkbox_child.setChecked(datas.get(position).isChecked());
        holder.tv_price.setText("¥ " + datas.get(position).getProduct().getNowprice());
        holder.amount_view.etAmount.setText(datas.get(position).getBuyCart().getGoodsAccant() + "");
       // holder.amount_view.etAmount.setTag(position);

        holder.amount_view.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                if ((Integer) holder.itemView.getTag() == position) {
                    datas.get(position).setNumber(amount);
                    showTotalPrice();
                }
            }
        });

        holder.checkbox_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置取反状态
                datas.get(position).setChecked(!datas.get(position).isChecked());
                //计算总价格
                showTotalPrice();
                //计算结算的数量
                showCheckedCount();
                //设置全选按钮是否选中
                showCheckAllIsChecked();
            }
        });
    }

    public void showTotalPrice() {
        tv_all_price.setText("" + getTotalPrice());
    }

    public void showCheckedCount() {
        btn_account.setText("结算(" + computeCheckedCount() + ")");
    }

    /**
     * 删除按钮监听
     */
    private void btnDeleteListener() {
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem();
                // TODO: 2017/4/5 结算数量为0的时候显示另一个界面
                if(computeCheckedCount() <= 0){

                }
            }
        });
    }

    /**
     * 删除条目
     */
    private void removeItem() {
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).isChecked()) {
                datas.remove(i);
                notifyItemRemoved(i);
                i--;
                notifyItemRangeChanged(i, getItemCount());
            }
        }
    }

    /**
     * 删除状态下的列表条目的状态
     */
    private void deleteStateCheckboxListener() {
        checkbox_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkbox_edit.isChecked()) {
                    listCheckboxState(true);
                } else {
                    listCheckboxState(false);
                }
                notifyDataSetChanged();
            }
        });
    }

    /**
     * 设置列表整体的选择状态
     */
    private void listCheckboxState(boolean state) {
        for (ShopCartBean.BuyitemBean shopCartBean : datas) {
            shopCartBean.setChecked(state);
        }
    }

    /**
     * 计算结算的数量
     */
    private int computeCheckedCount() {
        int count = 0;
        if (datas != null && datas.size() > 0) {
            for (ShopCartBean.BuyitemBean shopCartBean : datas) {
                if (shopCartBean.isChecked()) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 计算总价格
     *
     * @return
     */
    private double getTotalPrice() {
        double totalPrice = 0.0;
        if (datas != null && datas.size() > 0) {
            for (ShopCartBean.BuyitemBean shopCartBean : datas) {
                if (shopCartBean.isChecked()) {
                    totalPrice += Double.valueOf(shopCartBean.getNumber()) * Double.valueOf(shopCartBean.getProduct().getNowprice());
                }
            }
        }
        return totalPrice;
    }

    /**
     * 全选按钮的点击监听
     */
    private void checkAllListener() {
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置全选或非全选
                checkAll_none(checkboxAll.isChecked());
                //合计
                showTotalPrice();
                //结算的数量
                showCheckedCount();

                notifyDataSetChanged();
            }
        });
    }

    /**
     * 设置全选或非全选
     */
    public void checkAll_none(boolean isCheck) {
        if (isCheck) {
            listCheckboxState(true);
        } else {
            listCheckboxState(false);
        }
    }

    /**
     * 设置全选按钮是否选中
     */
    private void showCheckAllIsChecked() {
        for (ShopCartBean.BuyitemBean shopCartBean : datas) {
            if (!shopCartBean.isChecked()) {
                checkboxAll.setChecked(false);
                checkbox_edit.setChecked(false);
                break;
            } else {
                checkboxAll.setChecked(true);
                checkbox_edit.setChecked(true);
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final CheckBox checkbox_child;
        private final ImageView img_pic;
        private final TextView tv_title;
        private final TextView tv_price;
        private final AmountView amount_view;

        public MyHolder(View itemView) {
            super(itemView);
            checkbox_child = (CheckBox) itemView.findViewById(R.id.checkbox_child);
            img_pic = (ImageView) itemView.findViewById(R.id.img_pic);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            amount_view = (AmountView) itemView.findViewById(R.id.amount_view);
        }
    }
}
