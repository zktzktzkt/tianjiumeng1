package com.yxk.tjm.tianjiumeng.my.bean;

import java.util.List;

/**
 * Created by ningfei on 2017/3/13.
 */

public class CollectBeannn {

    /**
     * collectitem : [{"product":{"description":"天天学习，永远向上","failureTime":1492480856000,"feedbackrate":55,"feedbacktime":234,"name":"玻璃杯7","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},"collect":{"collectId":23,"goodsId":7,"userId":6}},{"product":{"description":"天天学习，永远向上","failureTime":1492480852000,"feedbackrate":55,"feedbacktime":234,"name":"玻璃杯6","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},"collect":{"collectId":22,"goodsId":6,"userId":6}},{"product":{"description":"天天学习，永远向上","failureTime":1492480847000,"feedbackrate":55,"feedbacktime":234,"name":"玻璃杯5","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},"collect":{"collectId":21,"goodsId":5,"userId":6}},{"product":{"description":"天天学习，永远向上","failureTime":1492477686000,"feedbackrate":55,"feedbacktime":234,"name":"玻璃杯4","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},"collect":{"collectId":20,"goodsId":4,"userId":6}},{"product":{"description":"天天学习，永远向上","failureTime":1492480273000,"feedbackrate":55,"feedbacktime":234,"name":"玻璃杯3","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},"collect":{"collectId":19,"goodsId":3,"userId":6}},{"product":{"description":"天天学习，永远向上","failureTime":1492477686000,"feedbackrate":55,"feedbacktime":234,"name":"玻璃杯2","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},"collect":{"collectId":18,"goodsId":2,"userId":6}},{"product":{"description":"天天学习，永远向上","failureTime":1492480859000,"feedbackrate":55,"feedbacktime":234,"name":"玻璃杯8","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},"collect":{"collectId":17,"goodsId":8,"userId":6}},{"product":{"description":"天天学习，永远向上","failureTime":1492480836000,"feedbackrate":55,"feedbacktime":345,"name":"玻璃杯","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},"collect":{"collectId":16,"goodsId":1,"userId":6}}]
     * success : false
     */

    private boolean success;
    private List<CollectitemBean> collectitem;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<CollectitemBean> getCollectitem() {
        return collectitem;
    }

    public void setCollectitem(List<CollectitemBean> collectitem) {
        this.collectitem = collectitem;
    }

    public static class CollectitemBean {
        //---------------------------------------
        public boolean isVisibleChildCb() {
            return visibleChildCb;
        }

        public void setVisibleChildCb(boolean visibleChildCb) {
            this.visibleChildCb = visibleChildCb;
        }

        private boolean visibleChildCb;

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        private boolean checked;
        //-------------------------------------

        /**
         * product : {"description":"天天学习，永远向上","failureTime":1492480856000,"feedbackrate":55,"feedbacktime":234,"name":"玻璃杯7","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1}
         * collect : {"collectId":23,"goodsId":7,"userId":6}
         */

        private ProductBean product;
        private CollectBean collect;

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public CollectBean getCollect() {
            return collect;
        }

        public void setCollect(CollectBean collect) {
            this.collect = collect;
        }

        public static class ProductBean {
            /**
             * description : 天天学习，永远向上
             * failureTime : 1492480856000
             * feedbackrate : 55
             * feedbacktime : 234
             * name : 玻璃杯7
             * nowprice : 299
             * orgnprice : 299
             * pageNo : 1
             * pageSize : 12
             * showpic : http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg
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
            private String showpic;
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

        public static class CollectBean {
            /**
             * collectId : 23
             * goodsId : 7
             * userId : 6
             */

            private int collectId;
            private int goodsId;
            private int userId;

            public int getCollectId() {
                return collectId;
            }

            public void setCollectId(int collectId) {
                this.collectId = collectId;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
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
