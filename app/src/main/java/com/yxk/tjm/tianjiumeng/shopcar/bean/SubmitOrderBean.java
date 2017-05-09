package com.yxk.tjm.tianjiumeng.shopcar.bean;

import java.util.List;

/**
 * Created by ningfei on 2017/5/8.
 */

public class SubmitOrderBean {

    /**
     * jewel : 20000
     * Coupon : [{"couponId":3,"downPrice":10,"finalTime":1493089827000,"fullBuyPrice":199,"startTime":1492571423000,"userId":6},{"couponId":2,"downPrice":10,"finalTime":1493349003000,"fullBuyPrice":199,"startTime":1492571397000,"userId":6},{"couponId":1,"downPrice":10,"finalTime":1493521755000,"fullBuyPrice":199,"startTime":1492484949000,"userId":6}]
     * DefAddress : {"addrCity":"北京市","addrDetail":"，。？","addrId":24,"addrName":"1层阿里","addrProvice":"北京市","addrTel":"15201168650","addrarea":"昌平区","isDefault":0,"userId":6}
     */

    private int jewel;
    private DefAddressBean DefAddress;
    private List<CouponBean> Coupon;

    public int getJewel() {
        return jewel;
    }

    public void setJewel(int jewel) {
        this.jewel = jewel;
    }

    public DefAddressBean getDefAddress() {
        return DefAddress;
    }

    public void setDefAddress(DefAddressBean DefAddress) {
        this.DefAddress = DefAddress;
    }

    public List<CouponBean> getCoupon() {
        return Coupon;
    }

    public void setCoupon(List<CouponBean> Coupon) {
        this.Coupon = Coupon;
    }

    public static class DefAddressBean {
        /**
         * addrCity : 北京市
         * addrDetail : ，。？
         * addrId : 24
         * addrName : 1层阿里
         * addrProvice : 北京市
         * addrTel : 15201168650
         * addrarea : 昌平区
         * isDefault : 0
         * userId : 6
         */

        private String addrCity;
        private String addrDetail;
        private int addrId;
        private String addrName;
        private String addrProvice;
        private String addrTel;
        private String addrarea;
        private int isDefault;
        private int userId;

        public String getAddrCity() {
            return addrCity;
        }

        public void setAddrCity(String addrCity) {
            this.addrCity = addrCity;
        }

        public String getAddrDetail() {
            return addrDetail;
        }

        public void setAddrDetail(String addrDetail) {
            this.addrDetail = addrDetail;
        }

        public int getAddrId() {
            return addrId;
        }

        public void setAddrId(int addrId) {
            this.addrId = addrId;
        }

        public String getAddrName() {
            return addrName;
        }

        public void setAddrName(String addrName) {
            this.addrName = addrName;
        }

        public String getAddrProvice() {
            return addrProvice;
        }

        public void setAddrProvice(String addrProvice) {
            this.addrProvice = addrProvice;
        }

        public String getAddrTel() {
            return addrTel;
        }

        public void setAddrTel(String addrTel) {
            this.addrTel = addrTel;
        }

        public String getAddrarea() {
            return addrarea;
        }

        public void setAddrarea(String addrarea) {
            this.addrarea = addrarea;
        }

        public int getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
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
