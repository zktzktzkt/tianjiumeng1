package com.yxk.tjm.tianjiumeng.my.bean;

import java.util.List;

/**
 * Created by ningfei on 2017/4/5.
 */

public class AddressBeannn {


    private List<AddressBean> address;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    private boolean isSelect;

    public List<AddressBean> getAddress() {
        return address;
    }

    public void setAddress(List<AddressBean> address) {
        this.address = address;
    }

    public static class AddressBean {
        /**
         * addrCity : 为fee
         * addrDetail : 供热管网
         * addrId : 2
         * addrName : 各让个人
         * addrProvice : few发送
         * addrTel : 1234567
         * addrarea : 大伙儿个
         * userId : 6
         */

        private String addrCity;
        private String addrDetail;
        private int addrId;
        private String addrName;
        private String addrProvice;
        private String addrTel;
        private String addrarea;
        private int userId;
        private int isDefault;

        public int getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }

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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
