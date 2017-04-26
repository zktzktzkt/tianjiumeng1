package com.yxk.tjm.tianjiumeng.my.bean;

/**
 * Created by ningfei on 2017/4/18.
 */

public class ReturnMoneyBean {

    /**
     * endTime : 1493281855000
     * feedbackAmount : 33
     * feedbackDays : 22
     * feedbackRate : 55
     * feedbackTime : 34
     * orderId : 22344556666
     * productPrice : 123
     * rebateId : 1
     * rebateState : 1
     * showPic : http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg
     * startTime : 1492590651000
     * ttlfdbkAmount : 100
     * userId : 6
     */

    private long endTime;
    private int feedbackAmount;
    private int feedbackDays;
    private int feedbackRate;
    private int feedbackTime;
    private String orderId;
    private int productPrice;
    private int rebateId;
    private int rebateState;
    private String showPic;
    private long startTime;
    private int ttlfdbkAmount;
    private int userId;

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getFeedbackAmount() {
        return feedbackAmount;
    }

    public void setFeedbackAmount(int feedbackAmount) {
        this.feedbackAmount = feedbackAmount;
    }

    public int getFeedbackDays() {
        return feedbackDays;
    }

    public void setFeedbackDays(int feedbackDays) {
        this.feedbackDays = feedbackDays;
    }

    public int getFeedbackRate() {
        return feedbackRate;
    }

    public void setFeedbackRate(int feedbackRate) {
        this.feedbackRate = feedbackRate;
    }

    public int getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(int feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getRebateId() {
        return rebateId;
    }

    public void setRebateId(int rebateId) {
        this.rebateId = rebateId;
    }

    public int getRebateState() {
        return rebateState;
    }

    public void setRebateState(int rebateState) {
        this.rebateState = rebateState;
    }

    public String getShowPic() {
        return showPic;
    }

    public void setShowPic(String showPic) {
        this.showPic = showPic;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getTtlfdbkAmount() {
        return ttlfdbkAmount;
    }

    public void setTtlfdbkAmount(int ttlfdbkAmount) {
        this.ttlfdbkAmount = ttlfdbkAmount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
