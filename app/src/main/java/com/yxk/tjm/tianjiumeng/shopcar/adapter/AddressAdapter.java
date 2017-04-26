package com.yxk.tjm.tianjiumeng.shopcar.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.my.activity.AddAddressActivity;
import com.yxk.tjm.tianjiumeng.my.activity.AddressActivity;
import com.yxk.tjm.tianjiumeng.my.bean.AddressBeannn;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by ningfei on 2017/4/5.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyHolder> {
    List<AddressBeannn.AddressBean> mDatas;
    // private int selectPos = -1;
    AddressActivity activity;

    public AddressAdapter(List<AddressBeannn.AddressBean> datas, Activity activity) {
        this.mDatas = datas;
        this.activity = (AddressActivity) activity;
    }

    @Override
    public AddressAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false));
    }

    @Override
    public void onBindViewHolder(final AddressAdapter.MyHolder holder, final int position) {
        ((SwipeMenuLayout) holder.itemView).setIos(true).setLeftSwipe(true);//这句话关掉IOS阻塞式交互效果 并打开左滑

        if (mDatas.get(position).getIsDefault() == 0) {
            holder.rb_def_addr.setChecked(true);
            // selectPos = position;
        } else {
            holder.rb_def_addr.setChecked(false);
        }

        //----------------------------------------------
   /*     if (selectPos == position) {
            holder.rb_def_addr.setChecked(true);
        } else {
            holder.rb_def_addr.setChecked(false);
        }*/
        //---------------------------------------------

        holder.tv_name.setText(mDatas.get(position).getAddrName());
        holder.tv_address.setText(mDatas.get(position).getAddrProvice() + mDatas.get(position).getAddrCity() + mDatas.get(position).getAddrarea());
        holder.tv_phone.setText(mDatas.get(position).getAddrTel());

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItemFromNetwork(mDatas.get(position).getAddrId());

                mDatas.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeRemoved(0, mDatas.size());
            }
        });

        holder.rb_def_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDefaultAddress(mDatas.get(position).getAddrId());
            }
        });

        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, AddAddressActivity.class);
                intent.putExtra("addrId", mDatas.get(position).getAddrId());
                intent.putExtra("addrProvice", mDatas.get(position).getAddrProvice());
                intent.putExtra("addrCity", mDatas.get(position).getAddrCity());
                intent.putExtra("addrarea", mDatas.get(position).getAddrarea());
                intent.putExtra("addrDetail", mDatas.get(position).getAddrDetail());
                intent.putExtra("addrTel", mDatas.get(position).getAddrTel());
                intent.putExtra("addrName", mDatas.get(position).getAddrName());
                activity.startActivity(intent);
            }
        });
    }

    /**
     * 修改默认地址
     *
     * @param addrId
     */
    private void updateDefaultAddress(int addrId) {
        OkHttpUtils
                .post()
                .url(ApiConstants.MY_UPDATE_DELETE_ADDRESS)
                .addParams("addressId", addrId + "")
                .addParams("userId", UserUtil.getUserId(App.getAppContext()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("updateDefaultAddress()  Exception: ", e + "");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //selectPos = position;
                        LogUtil.e("updateDefaultAddress()  response: ", response);

                        AddressBeannn addressBeannn = new Gson().fromJson(response, AddressBeannn.class);
                        mDatas = addressBeannn.getAddress();
                        notifyDataSetChanged();
                    }
                });

    }

    /**
     * 从服务器删除条目
     *
     * @param addrId
     */
    private void deleteItemFromNetwork(int addrId) {
        LogUtil.e("AddressAdapter ", "addrId: " + addrId);
        JsonArray ja = new JsonArray();
        JsonObject jo = new JsonObject();
        jo.addProperty("addrId", addrId + "");
        ja.add(jo);

        LogUtil.e("AddressAdapter ", "ja: " + ja.toString());
        OkHttpUtils.postString()
                .content(ja.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .url(ApiConstants.MY_DELETE_ADDRESS)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("AddressAdapter ", "deleteItemFromNetwork() Exception: " + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("AddressAdapter ", "deleteItemFromNetwork() response: " + response);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final RadioButton rb_def_addr;
        private final Button btn_delete;
        private final TextView tv_name;
        private final TextView tv_phone;
        private final TextView tv_address;
        private final ImageView iv_edit;

        public MyHolder(View itemView) {
            super(itemView);
            rb_def_addr = (RadioButton) itemView.findViewById(R.id.rb_def_addr);
            btn_delete = (Button) itemView.findViewById(R.id.btn_delete);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_phone = (TextView) itemView.findViewById(R.id.tv_phone);
            tv_address = (TextView) itemView.findViewById(R.id.tv_address);
            iv_edit = (ImageView) itemView.findViewById(R.id.iv_edit);
        }
    }
}
