package com.yxk.tjm.tianjiumeng.home.bean;

import java.util.List;

/**
 * Created by ningfei on 2017/4/11.
 */

public class ProductInnerDetailBean {


    /**
     * detailPics : {"goodsPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"}
     * reviewPics : [{"reviewPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"}]
     * reviewVO : [{"avatar":"http://139.199.104.48:8080/dreamSkyEverMavenWebapp/avatar/6img_head.jpg","goodsId":6,"nickname":"114努力了","phoneNumber":"15201168650","realname":"复式公寓","replyText":"哈哈","reviewText":"，？！","reviewTime":1493710988000,"satisfyNo":3,"sex":0,"userId":6}]
     * cnnmdForYou : [{"id":9,"name":"玻璃杯9","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"},{"id":1,"name":"玻璃杯","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"},{"id":2,"name":"玻璃杯2","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"},{"id":3,"name":"玻璃杯3","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"},{"id":4,"name":"玻璃杯4","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"},{"id":5,"name":"玻璃杯5","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"},{"id":6,"name":"高脚杯6","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"},{"id":7,"name":"玻璃杯7","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"}]
     */

    private DetailPicsBean detailPics;
    private List<ReviewPicsBean> reviewPics;
    private List<ReviewVOBean> reviewVO;
    private List<CnnmdForYouBean> cnnmdForYou;

    public DetailPicsBean getDetailPics() {
        return detailPics;
    }

    public void setDetailPics(DetailPicsBean detailPics) {
        this.detailPics = detailPics;
    }

    public List<ReviewPicsBean> getReviewPics() {
        return reviewPics;
    }

    public void setReviewPics(List<ReviewPicsBean> reviewPics) {
        this.reviewPics = reviewPics;
    }

    public List<ReviewVOBean> getReviewVO() {
        return reviewVO;
    }

    public void setReviewVO(List<ReviewVOBean> reviewVO) {
        this.reviewVO = reviewVO;
    }

    public List<CnnmdForYouBean> getCnnmdForYou() {
        return cnnmdForYou;
    }

    public void setCnnmdForYou(List<CnnmdForYouBean> cnnmdForYou) {
        this.cnnmdForYou = cnnmdForYou;
    }

    public static class DetailPicsBean {
        /**
         * goodsPic : http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg
         */

        private String goodsPic;

        public String getGoodsPic() {
            return goodsPic;
        }

        public void setGoodsPic(String goodsPic) {
            this.goodsPic = goodsPic;
        }
    }

    public static class ReviewPicsBean {
        /**
         * reviewPic : http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg
         */

        private String reviewPic;

        public String getReviewPic() {
            return reviewPic;
        }

        public void setReviewPic(String reviewPic) {
            this.reviewPic = reviewPic;
        }
    }

    public static class ReviewVOBean {
        /**
         * avatar : http://139.199.104.48:8080/dreamSkyEverMavenWebapp/avatar/6img_head.jpg
         * goodsId : 6
         * nickname : 114努力了
         * phoneNumber : 15201168650
         * realname : 复式公寓
         * replyText : 哈哈
         * reviewText : ，？！
         * reviewTime : 1493710988000
         * satisfyNo : 3
         * sex : 0
         * userId : 6
         */

        private String avatar;
        private int goodsId;
        private String nickname;
        private String phoneNumber;
        private String realname;
        private String replyText;
        private String reviewText;
        private long reviewTime;
        private int satisfyNo;
        private int sex;
        private int userId;

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

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getReplyText() {
            return replyText;
        }

        public void setReplyText(String replyText) {
            this.replyText = replyText;
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
    }

    public static class CnnmdForYouBean {
        /**
         * id : 9
         * name : 玻璃杯9
         * nowprice : 299
         * orgnprice : 299
         * pageNo : 1
         * pageSize : 12
         * showpic : http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg
         */

        private int id;
        private String name;
        private int nowprice;
        private int orgnprice;
        private int pageNo;
        private int pageSize;
        private String showpic;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNowprice() {
            return nowprice;
        }

        public void setNowprice(int nowprice) {
            this.nowprice = nowprice;
        }

        public int getOrgnprice() {
            return orgnprice;
        }

        public void setOrgnprice(int orgnprice) {
            this.orgnprice = orgnprice;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public String getShowpic() {
            return showpic;
        }

        public void setShowpic(String showpic) {
            this.showpic = showpic;
        }
    }
}
