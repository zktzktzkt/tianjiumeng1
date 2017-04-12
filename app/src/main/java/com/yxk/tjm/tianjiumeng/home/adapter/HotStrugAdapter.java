package com.yxk.tjm.tianjiumeng.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.utils.To;

/**
 * Created by ningfei on 2017/3/8.
 */

public class HotStrugAdapter extends RecyclerView.Adapter<HotStrugAdapter.MyHolder> {
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(App.getAppContext()).inflate(R.layout.item_hot_strug, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                To.showShort(App.getAppContext(), "算我一份");
            }
        });

        holder.btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemButtonClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final Button btn_buy;

        public MyHolder(View itemView) {
            super(itemView);
            btn_buy = (Button) itemView.findViewById(R.id.btn_buy);

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

        void onItemButtonClick(int position);
    }
}
