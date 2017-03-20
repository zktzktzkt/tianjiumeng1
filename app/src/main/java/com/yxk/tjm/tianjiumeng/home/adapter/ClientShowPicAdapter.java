package com.yxk.tjm.tianjiumeng.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;

import java.util.List;

/**
 * Created by ningfei on 2017/3/9.
 */

public class ClientShowPicAdapter extends RecyclerView.Adapter<ClientShowPicAdapter.MyHolder> {
    //List<String> paths;
    // TODO: 2017/3/9 为了测试用这个res id，正式情况用string的路径
    List<Integer> paths;

    @Override
    public ClientShowPicAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(ClientShowPicAdapter.MyHolder holder, int position) {
        Glide.with(App.getAppContext()).load(paths.get(position)).into(holder.img_pic);
    }

    @Override
    public int getItemCount() {
        return paths == null ? 0 : paths.size();
    }

   /* public void setMatchData(List<String> paths) {
        this.paths = paths;
    }*/

    public void setMatchData(List<Integer> paths) {
        this.paths = paths;
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView img_pic;

        public MyHolder(View itemView) {
            super(itemView);
            img_pic = (ImageView) itemView.findViewById(R.id.img_pic);
        }
    }
}
