package com.yxk.tjm.tianjiumeng.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.news.bean.NewsBean;

import java.util.List;

/**
 * Created by ningfei on 2017/3/11.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyHolder> {
    List<NewsBean> newsBeanList;

    public NewsAdapter(List<NewsBean> newsBeanList) {
        this.newsBeanList = newsBeanList;
    }

    @Override
    public NewsAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.MyHolder holder, int position) {
        NewsBean newsBean = newsBeanList.get(position);
        Glide.with(App.getAppContext()).load(newsBean.getResPicId()).into(holder.img_pic);
        holder.tv_date.setText(newsBean.getTime());
        holder.tv_title.setText(newsBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return newsBeanList == null ? 0 : newsBeanList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final TextView tv_title;
        private final TextView tv_date;
        private final ImageView img_pic;

        public MyHolder(View itemView) {
            super(itemView);

            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            img_pic = (ImageView) itemView.findViewById(R.id.img_pic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getLayoutPosition());
                }
            });
        }
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

}
