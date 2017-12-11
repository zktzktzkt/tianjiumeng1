package com.yxk.tjm.tianjiumeng.home.adapter;

import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.home.bean.HomeBean;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.iwgang.countdownview.CountdownView;
import cn.iwgang.countdownview.DynamicConfig;

/**
 * Created by ningfei on 2017/3/20.
 */

public class FlashSaleAdapter extends RecyclerView.Adapter<FlashSaleAdapter.MyHolder> {
    private final SparseArray<MyHolder> mCountdownVHList;  //5.
    private Handler mHandler = new Handler();
    private boolean isCancel = true;
    private Timer mTimer;
    List<HomeBean.TimedSaleBean> list;
    HomeBean.TimedSaleBean flashSaleBean;
    private final DynamicConfig.Builder builder;
    private DynamicConfig build;
    private final int RED = 0;
    private final int WHITE = 1;

    public FlashSaleAdapter() {
        this.mCountdownVHList = new SparseArray<>();

        builder = new DynamicConfig.Builder();
    }

    private DynamicConfig getDynamicConfigState(int state) {
        if (state == RED) {
            builder.setTimeTextColor(App.getAppContext().getResources().getColor(R.color.red_ff0000));
        } else if (state == WHITE) {
            builder.setTimeTextColor(App.getAppContext().getResources().getColor(R.color.white));
        }

        if (build != null) {
            return this.build;
        } else {
            return builder.build();
        }
    }

    @Override
    public FlashSaleAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flash_sale, parent, false));
    }

    @Override
    public void onBindViewHolder(FlashSaleAdapter.MyHolder holder, int position) {
        flashSaleBean = list.get(position);

        //============（固定用法）==================
        //000
        flashSaleBean.setCountdown_id(position);
        //001
        flashSaleBean.setCountdown_0(System.currentTimeMillis());
        flashSaleBean.setCountdown_endTime(System.currentTimeMillis() + (flashSaleBean.getFailureTime() - System.currentTimeMillis()));
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
        //===================END=====================

        holder.tv_original_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);  //添加删除线
        Glide.with(App.getAppContext()).load(list.get(position).getShowpic()).into(holder.image_pic);
        holder.tv_title.setText(flashSaleBean.getName());
        holder.tv_price.setText("￥" + flashSaleBean.getNowprice());
        holder.tv_original_price.setText("￥" + flashSaleBean.getOrgnprice());

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setMatchData(List<HomeBean.TimedSaleBean> list) {
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
        HomeBean.TimedSaleBean mItemInfo;
        private final ImageView image_pic;
        private final TextView tv_title;
        private final TextView tv_price;
        private final TextView tv_original_price;

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
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_original_price = (TextView) itemView.findViewById(R.id.tv_original_price);
        }

        /**
         * （原）绑定数据
         *
         * @param itemInfo
         */
        public void bindData(HomeBean.TimedSaleBean itemInfo) {  //1.
            mItemInfo = itemInfo;

            if (itemInfo.getCountdown_0() > 0) {
                refreshTime(System.currentTimeMillis());//
            } else {
                mCvCountdownView.allShowZero();
            }
        }

        /**
         * （原）更新时间
         */
        public void refreshTime(long curTimeMillis) {   //2.
            if (null == mItemInfo || mItemInfo.getCountdown_0() <= 0)
                return;
            mCvCountdownView.updateShow(mItemInfo.getCountdown_endTime() - curTimeMillis);
        }

        /**
         * （原）
         *
         * @return
         */
        public HomeBean.TimedSaleBean getBean() {  //3.
            return mItemInfo;
        }
    }

    //================================================================================

    /**
     * （原）
     *
     * @param holder
     */
    @Override
    public void onViewRecycled(FlashSaleAdapter.MyHolder holder) {  //6.
        super.onViewRecycled(holder);

        HomeBean.TimedSaleBean flashSaleBean = holder.getBean();
        if (null != flashSaleBean && flashSaleBean.getCountdown_0() > 0) {
            mCountdownVHList.remove(flashSaleBean.getCountdown_id());
        }
    }

    /**
     * （原）开始倒计时
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
     * （原）取消倒计时
     */
    public void cancelRefreshTime() {
        isCancel = true;
        if (null != mTimer) {
            mTimer.cancel();
        }
        mHandler.removeCallbacks(mRefreshTimeRunnable);
    }


    /**
     * （原）每隔10毫秒执行一遍该Runnable，执行间隔在Timer设置
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
                        if (flashSaleBean.getCountdown_endTime() - System.currentTimeMillis() < (long) (1 * 60 * 60 * 1000)) {
                            myHolder.mCvCountdownView.dynamicShow(getDynamicConfigState(RED));
                        } else {
                            myHolder.mCvCountdownView.dynamicShow(getDynamicConfigState(WHITE));
                        }

                        myHolder.refreshTime(currentTime);
                    }

                }
            }
        }
    };

}
