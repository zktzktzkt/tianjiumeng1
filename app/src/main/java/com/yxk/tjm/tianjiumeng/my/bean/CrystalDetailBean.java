package com.yxk.tjm.tianjiumeng.my.bean;

/**
 * Created by ningfei on 2017/4/18.
 */

public class CrystalDetailBean {

    /**
     * amount : -24444
     * createtime : 1492496665000
     * decr : 消费
     * jewelId : 2
     * userId : 6
     */

    private String amount;
    private long createtime;
    private String decr;
    private int jewelId;
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

    public int getJewelId() {
        return jewelId;
    }

    public void setJewelId(int jewelId) {
        this.jewelId = jewelId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
