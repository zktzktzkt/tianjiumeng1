package com.yxk.tjm.tianjiumeng.my.bean;

/**
 * Created by zkt on 2017/3/12.
 */

public class CommissionBean {

    /**
     * amount : +2000
     * createtime : 1492410326000
     * decr : 收入
     * rakeoffId : 2
     * userId : 6
     */

    private String amount;
    private long createtime;
    private String decr;
    private int rakeoffId;
    private int userId;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public String getDecr() {
        return decr;
    }

    public void setDecr(String decr) {
        this.decr = decr;
    }

    public int getRakeoffId() {
        return rakeoffId;
    }

    public void setRakeoffId(int rakeoffId) {
        this.rakeoffId = rakeoffId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
