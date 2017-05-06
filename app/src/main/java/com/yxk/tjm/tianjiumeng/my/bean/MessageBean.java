package com.yxk.tjm.tianjiumeng.my.bean;

/**
 * Created by ningfei on 2017/5/6.
 */

public class MessageBean {

    /**
     * replyText : 哈哈
     * replyTime : 1494040005000
     * satisfyNo : 0
     */

    private String replyText;
    private long replyTime;
    private int satisfyNo;

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    public long getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(long replyTime) {
        this.replyTime = replyTime;
    }

    public int getSatisfyNo() {
        return satisfyNo;
    }

    public void setSatisfyNo(int satisfyNo) {
        this.satisfyNo = satisfyNo;
    }
}
