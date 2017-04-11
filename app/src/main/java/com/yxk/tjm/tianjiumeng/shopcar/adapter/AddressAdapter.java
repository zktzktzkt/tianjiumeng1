package com.yxk.tjm.tianjiumeng.shopcar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.shopcar.bean.AddressBean;

import java.util.List;

/**
 * Created by ningfei on 2017/4/5.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyHolder> {
    List<AddressBean> mDatas;
    private int selectPos = -1;

    public AddressAdapter(List<AddressBean> datas) {
        this.mDatas = datas;
    }

    @Override
    public AddressAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false));
    }

    @Override
    public void onBindViewHolder(final AddressAdapter.MyHolder holder, final int position) {
        ((SwipeMenuLayout) holder.itemView).setIos(true).setLeftSwipe(true);//这句话关掉IOS阻塞式交互效果 并打开左滑

        if (selectPos == position) {
            holder.rb_def_addr.setChecked(true);
        } else {
            holder.rb_def_addr.setChecked(false);
        }

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatas.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

        holder.rb_def_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPos = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final RadioButton rb_def_addr;
        private final Button btn_delete;

        public MyHolder(View itemView) {
            super(itemView);
            rb_def_addr = (RadioButton) itemView.findViewById(R.id.rb_def_addr);
            btn_delete = (Button) itemView.findViewById(R.id.btn_delete);
        }
    }
}
