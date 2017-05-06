package com.yxk.tjm.tianjiumeng.my.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.my.bean.CollectBeannn;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by ningfei on 2017/3/10.
 */

public class MyCollectAdapter extends RecyclerView.Adapter<MyCollectAdapter.MyHolder> {
    List<CollectBeannn.CollectitemBean> datas;
    CheckBox checkboxAll;
    Button btn_delete;

    public MyCollectAdapter(List<CollectBeannn.CollectitemBean> datas, CheckBox checkbox, Button btn_delete) {
        this.datas = datas;
        Log.e("MyCollectAdapter ", "datas.size() : " + this.datas.size());
        this.checkboxAll = checkbox;
        this.btn_delete = btn_delete;
        //设置全选按钮的监听
        checkAllListener();

        //删除按钮点击监听
        btnDeleteListener();
    }

    @Override
    public MyCollectAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collect, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyCollectAdapter.MyHolder holder, final int position) {
        //-------------------------------
        if (datas.get(position).isVisibleChildCb()) {
            holder.checkbox_child.setVisibility(View.VISIBLE);
        } else {
            holder.checkbox_child.setVisibility(View.GONE);
        }

        if (datas.get(position).isChecked()) {
            holder.checkbox_child.setChecked(true);
        } else {
            holder.checkbox_child.setChecked(false);
        }
        //-----------------------------------------------

        Glide.with(App.getAppContext()).load(datas.get(position).getProduct().getShowpic()).into(holder.img_pic);
        holder.tv_title.setText(datas.get(position).getProduct().getName());
        holder.tv_price.setText(App.getAppContext().getResources().getString(R.string.RMB) + datas.get(position).getProduct().getNowprice());

        holder.checkbox_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置取反状态
                datas.get(position).setChecked(!datas.get(holder.getLayoutPosition()).isChecked());
                //设置全选按钮是否选中
                showCheckAllIsChecked();
            }
        });
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final CheckBox checkbox_child;
        private final ImageView img_pic;
        private final TextView tv_price;
        private final TextView tv_title;
        private final CheckBox cb_add_shopcart;

        public MyHolder(View itemView) {
            super(itemView);
            checkbox_child = (CheckBox) itemView.findViewById(R.id.checkbox_child);
            img_pic = (ImageView) itemView.findViewById(R.id.img_pic);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            cb_add_shopcart = (CheckBox) itemView.findViewById(R.id.cb_add_shopcart);
        }
    }

    /**
     * 删除按钮监听
     */
    private void btnDeleteListener() {
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem();
            }
        });
    }

    /**
     * 删除条目
     */
    private void removeItem() {

        JsonArray ja = new JsonArray();
        JsonObject jo = null;
        for (int i = 0, len = datas.size(); i < len; i++) { //注意写法
            if (datas.get(i).isChecked()) {
                jo = new JsonObject();
                jo.addProperty("collectId", datas.get(i).getCollect().getCollectId() + "");
                ja.add(jo);
                //-----------------------------
                notifyItemRemoved(i);
                datas.remove(i);
                len--;
                i--;
                notifyItemRangeChanged(0, getItemCount());
                //-----------------------------
            }
        }

        deleteFromNetwork(ja.toString());

    }

    /**
     * 从服务器删除
     *
     * @param s
     */
    private void deleteFromNetwork(String s) {
        Log.e("MyCollectAdapter ", "json: " + s);
        OkHttpUtils.postString()
                .content(s)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .url(ApiConstants.MY_COLLECT_DELETE)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("MyCollectAdapter ", "deleteFromNetwork() Exception: " + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("MyCollectAdapter ", "deleteFromNetwork() response: " + response);
                    }
                });
    }

    /**
     * 设置列表整体的选择状态
     */
    private void listCheckboxState(boolean state) {
        for (CollectBeannn.CollectitemBean collectBeannn : datas) {
            collectBeannn.setChecked(state);
        }

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

                notifyDataSetChanged();
            }
        });
    }

    public void setChildCheckboxVisible(boolean visible) {
        for (CollectBeannn.CollectitemBean collectBeannn : datas) {
            if (!collectBeannn.isChecked()) {
                collectBeannn.setVisibleChildCb(visible);
            } else {
                collectBeannn.setVisibleChildCb(visible);
            }
        }
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
        for (CollectBeannn.CollectitemBean collectBeannn : datas) {
            if (!collectBeannn.isChecked()) {
                checkboxAll.setChecked(false);
                break;
            } else {
                checkboxAll.setChecked(true);
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }
}
