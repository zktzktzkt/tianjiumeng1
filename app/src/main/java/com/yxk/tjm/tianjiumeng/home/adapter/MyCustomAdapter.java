package com.yxk.tjm.tianjiumeng.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;

import java.util.ArrayList;

/**
 * Created by ningfei on 2017/3/8.
 */

public class MyCustomAdapter extends RecyclerView.Adapter {
    ArrayList<String> mPathList;
    private static final int ADD = 0;
    private static final int NORMAL = 1;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        if (viewType == MyCustomAdapter.ADD) {
            return new AddViewHolder(from.inflate(R.layout.item_add, parent, false));
        } else {
            return new MyHolder(from.inflate(R.layout.item_picture, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof AddViewHolder) {
            ((AddViewHolder) holder).img_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });

        } else if (holder instanceof MyHolder) {
            Glide.with(App.getAppContext())
                    .load(mPathList.get(position))
                    .override(300, 300)
                    .into(((MyHolder) holder).img_pic);
        }
    }

    @Override
    public int getItemCount() {
        return mPathList == null ? 1 : mPathList.size() + 1;
    }

    public void setMatchData(ArrayList<String> pathList) {
        this.mPathList = pathList;
    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() - 1 ? MyCustomAdapter.ADD : MyCustomAdapter.NORMAL;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public ImageView img_pic;

        public MyHolder(View itemView) {
            super(itemView);
            img_pic = (ImageView) itemView.findViewById(R.id.img_pic);
        }
    }

    public class AddViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_add;

        public AddViewHolder(View itemView) {
            super(itemView);

            img_add = (ImageView) itemView.findViewById(R.id.img_add);
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
