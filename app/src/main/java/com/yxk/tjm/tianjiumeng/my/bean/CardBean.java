package com.yxk.tjm.tianjiumeng.my.bean;

import java.util.List;

/**
 * Created by ningfei on 2017/4/18.
 */

public class CardBean {

    /**
     * seccess : 1
     * coupon : [{"couponId":3,"downPrice":10,"finalTime":1493089827000,"fullBuyPrice":199,"startTime":1492571423000,"userId":6},{"couponId":2,"downPrice":10,"finalTime":1493349003000,"fullBuyPrice":199,"startTime":1492571397000,"userId":6},{"couponId":1,"downPrice":10,"finalTime":1493521755000,"fullBuyPrice":199,"startTime":1492484949000,"userId":6}]
     */

    private int seccess;
    private List<CouponBean> coupon;

    public int getSeccess() {
        return seccess;
    }

    public void setSeccess(int seccess) {
        this.seccess = seccess;
    }

    public List<CouponBean> getCoupon() {
        return coupon;
    }

    public void setCoupon(List<CouponBean> coupon) {
        this.coupon = coupon;
    }

    public static class CouponBean {
        /**
         * couponId : 3
         * downPrice : 10
         * finalTime : 1493089827000
         * fullBuyPrice : 199
         * startTime : 1492571423000
         * userId : 6
         */

        private int couponId;
        private int downPrice;
        private long finalTime;
        private int fullBuyPrice;
        private long startTime;
        private int userId;

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }

        public int getDownPrice() {
            return downPrice;
        }

        public void setDownPrice(int downPrice) {
            this.downPrice = downPrice;
        }

        public long getFinalTime() {
            return finalTime;
        }

        public void setFinalTime(long finalTime) {
            this.finalTime = finalTime;
        }

        public int getFullBuyPrice() {
            return fullBuyPrice;
        }

        public void setFullBuyPrice(int fullBuyPrice) {
            this.fullBuyPrice = fullBuyPrice;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
