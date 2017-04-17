package com.yxk.tjm.tianjiumeng.home.bean;

import java.util.List;

/**
 * Created by ningfei on 2017/4/11.
 */

public class ProductDetailBeannn {

    /**
     * HWs : [{"goodsHeight":3,"goodsWide":4},{"goodsHeight":3,"goodsWide":3},{"goodsHeight":3,"goodsWide":4},{"goodsHeight":3,"goodsWide":5},{"goodsHeight":3,"goodsWide":6},{"goodsHeight":3,"goodsWide":7},{"goodsHeight":3,"goodsWide":8}]
     * productDetail : {"description":"天天学习，永远向上","failureTime":1492480836000,"feedbackrate":55,"feedbacktime":345,"name":"玻璃杯","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"typeId":1}
     * crclphotos : [{"goodsPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"},{"goodsPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"},{"goodsPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"}]
     * tatalStore : 1400
     */

    private ProductDetailBean productDetail;
    private int tatalStore;
    private List<HWsBean> HWs;
    private List<CrclphotosBean> crclphotos;

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

    public static class ProductDetailBean {
        /**
         * description : 天天学习，永远向上
         * failureTime : 1492480836000
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
        private long failureTime;
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

        public long getFailureTime() {
            return failureTime;
        }

        public void setFailureTime(long failureTime) {
            this.failureTime = failureTime;
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
}
