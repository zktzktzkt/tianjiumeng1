package com.yxk.tjm.tianjiumeng.home.adapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.home.bean.FlashSaleBean;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.iwgang.countdownview.CountdownView;

/**
 * Created by ningfei on 2017/3/20.
 */

public class FlashSaleAdapter extends RecyclerView.Adapter<FlashSaleAdapter.MyHolder> {
    private final SparseArray<MyHolder> mCountdownVHList;  //5.
    private Handler mHandler = new Handler();
    private boolean isCancel = true;
    private Timer mTimer;
    List<FlashSaleBean> list;
    FlashSaleBean flashSaleBean;

    public FlashSaleAdapter() {
        this.mCountdownVHList = new SparseArray<>();
    }

    @Override
    public FlashSaleAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flash_sale, parent, false));
    }

    @Override
    public void onBindViewHolder(FlashSaleAdapter.MyHolder holder, int position) {
        Glide.with(App.getAppContext()).load(list.get(position).getResImage()).into(holder.image_pic);

        //-----------------------------------
        flashSaleBean = list.get(position);
        //000
        flashSaleBean.setCountdown_id(position);
        //001
        flashSaleBean.setCountdown_0(System.currentTimeMillis());
        flashSaleBean.setCountdown_endTime(System.currentTimeMillis() + 120 * 60 * 60 * 1000);
        //002
        holder.bindData(flashSaleBean);  //4. 需要Countdown_endTime和countdown_0
        //003 处理倒计时
        if (flashSaleBean.getCountdown_0() > 0) {   //5.
            synchronized (mCountdownVHList) {
                mCountdownVHList.put(position, holder);
            }
        }
        //004
        startRefreshTime();  //需要mCountdownVHList
        //-------------------------------------

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setMatchData(List<FlashSaleBean> list) {
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    //===============================================================================================

    public class MyHolder extends RecyclerView.ViewHolder {

        private final CountdownView mCvCountdownView;
        FlashSaleBean mItemInfo;
        private final ImageView image_pic;

        public MyHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getLayoutPosition());
                }
            });
            mCvCountdownView = (CountdownView) itemView.findViewById(R.id.cv_countdownView);
            image_pic = (ImageView) itemView.findViewById(R.id.image_pic);
        }

        /**
         * 绑定数据
         *
         * @param itemInfo
         */
        public void bindData(FlashSaleBean itemInfo) {  //1.
            mItemInfo = itemInfo;

            if (itemInfo.getCountdown_0() > 0) {
                refreshTime(System.currentTimeMillis());//
            } else {
                mCvCountdownView.allShowZero();
            }
        }

        /**
         * 更新时间
         */
        public void refreshTime(long curTimeMillis) {   //2.
            if (null == mItemInfo || mItemInfo.getCountdown_0() <= 0)
                return;
            mCvCountdownView.updateShow(mItemInfo.getCountdown_endTime() - curTimeMillis);
        }

        public FlashSaleBean getBean() {  //3.
            return mItemInfo;
        }
    }

    //================================================================================
    @Override
    public void onViewRecycled(FlashSaleAdapter.MyHolder holder) {  //6.
        super.onViewRecycled(holder);

        FlashSaleBean flashSaleBean = holder.getBean();
        if (null != flashSaleBean && flashSaleBean.getCountdown_0() > 0) {
            mCountdownVHList.remove(flashSaleBean.getCountdown_id());
        }
    }

    /**
     * 开始倒计时
     */
    public void startRefreshTime() {
        if (!isCancel)
            return;

        if (null != mTimer) {
            mTimer.cancel();
        }

        isCancel = false;
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.post(mRefreshTimeRunnable);
            }
        }, 0, 10);
    }

    /**
     * 每隔10毫秒执行一遍该Runnable，执行间隔在Timer设置
     */
    private Runnable mRefreshTimeRunnable = new Runnable() {   //7.
        @Override
        public void run() {
            if (mCountdownVHList.size() == 0)
                return;

            synchronized (mCountdownVHList) {
                long currentTime = System.currentTimeMillis();
                int key;
                for (int i = 0; i < mCountdownVHList.size(); i++) {
                    key = mCountdownVHList.keyAt(i);
                    MyHolder myHolder = mCountdownVHList.get(key);
                    // TODO: 判断是否结束
                    if (currentTime >= myHolder.getBean().getCountdown_endTime()) {
                        // 倒计时结束
                        myHolder.getBean().setCountdown_0(0);
                        mCountdownVHList.remove(key);

                        //notifyDataSetChanged();
                    } else {
                        myHolder.refreshTime(currentTime);
                    }

                }
            }
        }
    };

}
