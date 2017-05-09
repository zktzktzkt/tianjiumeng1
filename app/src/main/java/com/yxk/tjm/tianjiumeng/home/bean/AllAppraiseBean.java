package com.yxk.tjm.tianjiumeng.home.bean;

import java.util.List;

/**
 * Created by ningfei on 2017/3/29.
 */

public class AllAppraiseBean {

    /**
     * avatar : http://139.199.104.48:8080/dreamSkyEverMavenWebapp/avatar/6img_head.jpg
     * goodsId : 1
     * nickname : 114努力了
     * phoneNumber : 15201168650
     * reviewPics : [{"reviewPic":"http://192.168.31.172:8080/dreamSkyEverMavenWebapp/review/1493713190126.jpg"},{"reviewPic":"http://192.168.31.172:8080/dreamSkyEverMavenWebapp/review/1493714221827.jpg"}]
     * reviewText : ，。？？
     * reviewTime : 1493714125000
     * satisfyNo : 5
     * sex : 0
     * userId : 6
     */

    private String avatar;
    private int goodsId;
    private String nickname;
    private String phoneNumber;
    private String reviewText;
    private long reviewTime;
    private int satisfyNo;
    private int sex;
    private int userId;
    private List<ReviewPicsBean> reviewPics;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public long getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(long reviewTime) {
        this.reviewTime = reviewTime;
    }

    public int getSatisfyNo() {
        return satisfyNo;
    }

    public void setSatisfyNo(int satisfyNo) {
        this.satisfyNo = satisfyNo;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<ReviewPicsBean> getReviewPics() {
        return reviewPics;
    }

    public void setReviewPics(List<ReviewPicsBean> reviewPics) {
        this.reviewPics = reviewPics;
    }

    public static class ReviewPicsBean {
        /**
         * reviewPic : http://192.168.31.172:8080/dreamSkyEverMavenWebapp/review/1493713190126.jpg
         */

        private String reviewPic;

        public String getReviewPic() {
            return reviewPic;
        }

        public void setReviewPic(String reviewPic) {
            this.reviewPic = reviewPic;
        }
    }
}
