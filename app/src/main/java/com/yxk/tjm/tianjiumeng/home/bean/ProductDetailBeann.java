package com.yxk.tjm.tianjiumeng.home.bean;

import java.util.List;

/**
 * Created by ningfei on 2017/4/11.
 */

public class ProductDetailBeann {

    /**
     * HWs : [{"goodsHeight":3,"goodsWide":4},{"goodsHeight":3,"goodsWide":3},{"goodsHeight":3,"goodsWide":4},{"goodsHeight":3,"goodsWide":5},{"goodsHeight":3,"goodsWide":6},{"goodsHeight":3,"goodsWide":7},{"goodsHeight":3,"goodsWide":8}]
     * productDetail : {"description":"天天学习，永远向上","feedbackrate":55,"feedbacktime":345,"name":"玻璃杯","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"typeId":1}
     * crclphotos : [{"goodsPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"},{"goodsPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"},{"goodsPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"}]
     * detailPics : [{"goodsPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"},{"goodsPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"},{"goodsPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"}]
     * reviewPics : [{"reviewPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"},{"reviewPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"},{"reviewPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"}]
     * tatalStore : 1400
     * reviewVO : {"goodsId":1,"phoneNumber":"15011213780","realname":"燕华","replyText":"哈哈","reviewText":"哈哈","reviewTime":1491960908000,"satisfyNo":4,"sex":1,"userId":1}
     * cnnmdForYou : [{"id":9,"name":"玻璃杯9","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":1,"name":"玻璃杯","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":2,"name":"玻璃杯2","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":3,"name":"玻璃杯3","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":4,"name":"玻璃杯4","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":5,"name":"玻璃杯5","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":6,"name":"玻璃杯6","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":7,"name":"玻璃杯7","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1}]
     */

    private ProductDetailBean productDetail;
    private int tatalStore;
    private ReviewVOBean reviewVO;
    private List<HWsBean> HWs;
    private List<CrclphotosBean> crclphotos;
    private List<DetailPicsBean> detailPics;
    private List<ReviewPicsBean> reviewPics;
    private List<CnnmdForYouBean> cnnmdForYou;

    public ProductDetailBean getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetailBean productDetail) {
        this.productDetail = productDetail;
    }

    public int getTatalStore() {
        return tatalStore;
    }

    public void setTatalStore(int tatalStore) {
        this.tatalStore = tatalStore;
    }

    public ReviewVOBean getReviewVO() {
        return reviewVO;
    }

    public void setReviewVO(ReviewVOBean reviewVO) {
        this.reviewVO = reviewVO;
    }

    public List<HWsBean> getHWs() {
        return HWs;
    }

    public void setHWs(List<HWsBean> HWs) {
        this.HWs = HWs;
    }

    public List<CrclphotosBean> getCrclphotos() {
        return crclphotos;
    }

    public void setCrclphotos(List<CrclphotosBean> crclphotos) {
        this.crclphotos = crclphotos;
    }

    public List<DetailPicsBean> getDetailPics() {
        return detailPics;
    }

    public void setDetailPics(List<DetailPicsBean> detailPics) {
        this.detailPics = detailPics;
    }

    public List<ReviewPicsBean> getReviewPics() {
        return reviewPics;
    }

    public void setReviewPics(List<ReviewPicsBean> reviewPics) {
        this.reviewPics = reviewPics;
    }

    public List<CnnmdForYouBean> getCnnmdForYou() {
        return cnnmdForYou;
    }

    public void setCnnmdForYou(List<CnnmdForYouBean> cnnmdForYou) {
        this.cnnmdForYou = cnnmdForYou;
    }

    public static class ProductDetailBean {
        /**
         * description : 天天学习，永远向上
         * feedbackrate : 55
         * feedbacktime : 345
         * name : 玻璃杯
         * nowprice : 299
         * orgnprice : 299
         * pageNo : 1
         * pageSize : 12
         * typeId : 1
         */

        private String description;
        private int feedbackrate;
        private int feedbacktime;
        private String name;
        private int nowprice;
        private int orgnprice;
        private int pageNo;
        private int pageSize;
        private int typeId;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getFeedbackrate() {
            return feedbackrate;
        }

        public void setFeedbackrate(int feedbackrate) {
            this.feedbackrate = feedbackrate;
        }

        public int getFeedbacktime() {
            return feedbacktime;
        }

        public void setFeedbacktime(int feedbacktime) {
            this.feedbacktime = feedbacktime;
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

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }
    }

    public static class ReviewVOBean {
        /**
         * goodsId : 1
         * phoneNumber : 15011213780
         * realname : 燕华
         * replyText : 哈哈
         * reviewText : 哈哈
         * reviewTime : 1491960908000
         * satisfyNo : 4
         * sex : 1
         * userId : 1
         */

        private int goodsId;
        private String phoneNumber;
        private String realname;
        private String replyText;
        private String reviewText;
        private long reviewTime;
        private int satisfyNo;
        private int sex;
        private int userId;

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
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

    public static class HWsBean {
        /**
         * goodsHeight : 3
         * goodsWide : 4
         */

        private int goodsHeight;
        private int goodsWide;

        public boolean isSelected;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public int getGoodsHeight() {
            return goodsHeight;
        }

        public void setGoodsHeight(int goodsHeight) {
            this.goodsHeight = goodsHeight;
        }

        public int getGoodsWide() {
            return goodsWide;
        }

        public void setGoodsWide(int goodsWide) {
            this.goodsWide = goodsWide;
        }
    }

    public static class CrclphotosBean {
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

    public static class CnnmdForYouBean {
        /**
         * id : 9
         * name : 玻璃杯9
         * nowprice : 299
         * orgnprice : 299
         * pageNo : 1
         * pageSize : 12
         * showpic : http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg
         * typeId : 1
         */

        private int id;
        private String name;
        private int nowprice;
        private int orgnprice;
        private int pageNo;
        private int pageSize;
        private String showpic;
        private int typeId;

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

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }
    }
}
