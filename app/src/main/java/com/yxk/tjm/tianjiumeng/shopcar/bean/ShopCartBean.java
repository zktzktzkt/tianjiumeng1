package com.yxk.tjm.tianjiumeng.shopcar.bean;

import java.util.List;

/**
 * Created by ningfei on 2017/3/10.
 */

public class ShopCartBean {

    private List<BuyitemBean> buyitem;

    public List<BuyitemBean> getBuyitem() {
        return buyitem;
    }

    public void setBuyitem(List<BuyitemBean> buyitem) {
        this.buyitem = buyitem;
    }

    public static class BuyitemBean {
        /**
         * product : {"description":"天天学习，永远向上","failureTime":1492480836000,"feedbackrate":55,"feedbacktime":345,"name":"玻璃杯","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"typeId":1}
         * buyCart : {"buyCartId":2,"goodsAccant":1,"goodsHW":"3x4","goodsId":1,"goodsPrice":299,"userId":1}
         */

        //---------------------------------------------
        private boolean isChecked = true;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        private int number = 1;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }
        //-----------------------------------------------

        private ProductBean product;
        private BuyCartBean buyCart;

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public BuyCartBean getBuyCart() {
            return buyCart;
        }

        public void setBuyCart(BuyCartBean buyCart) {
            this.buyCart = buyCart;
        }

        public static class ProductBean {
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
            private String showpic;
            private int nowprice;
            private int orgnprice;
            private int pageNo;
            private int pageSize;
            private int typeId;

            public String getShowpic() {
                return showpic;
            }

            public void setShowpic(String showpic) {
                this.showpic = showpic;
            }

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

        public static class BuyCartBean {
            /**
             * buyCartId : 2
             * goodsAccant : 1
             * goodsHW : 3x4
             * goodsId : 1
             * goodsPrice : 299
             * userId : 1
             */

            private int buyCartId;
            private int goodsAccant;
            private String goodsHW;
            private int goodsId;
            private int goodsPrice;
            private int userId;

            public int getBuyCartId() {
                return buyCartId;
            }

            public void setBuyCartId(int buyCartId) {
                this.buyCartId = buyCartId;
            }

            public int getGoodsAccant() {
                return goodsAccant;
            }

            public void setGoodsAccant(int goodsAccant) {
                this.goodsAccant = goodsAccant;
            }

            public String getGoodsHW() {
                return goodsHW;
            }

            public void setGoodsHW(String goodsHW) {
                this.goodsHW = goodsHW;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public int getGoodsPrice() {
                return goodsPrice;
            }

            public void setGoodsPrice(int goodsPrice) {
                this.goodsPrice = goodsPrice;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }
    }

}
