package com.yxk.tjm.tianjiumeng.home.bean;

import java.util.List;

/**
 * Created by ningfei on 2017/4/7.
 */

public class HomeBean {


    /**
     * crclphotos : [{"goodsId":1,"goodsPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"},{"goodsId":1,"goodsPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"}]
     * newProPic : http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg
     * newPro : [{"id":1,"name":"玻璃杯","nowprice":299,"orgnprice":299,"pageNo":1,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":8,"name":"玻璃杯8","nowprice":299,"orgnprice":299,"pageNo":1,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1}]
     * hotcnnmd : [[{"categoryDecr":"天然塑造","categoryId":1,"categoryPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"},{"id":1,"nowprice":299,"orgnprice":299,"pageNo":1,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":4,"nowprice":299,"orgnprice":299,"pageNo":1,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":7,"nowprice":299,"orgnprice":299,"pageNo":1,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1}],[{"categoryDecr":"春意昂让","categoryId":2,"categoryPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"},{"id":2,"nowprice":299,"orgnprice":299,"pageNo":1,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":6,"nowprice":299,"orgnprice":299,"pageNo":1,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":8,"nowprice":299,"orgnprice":299,"pageNo":1,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1}],[{"categoryDecr":"春心荡漾","categoryId":3,"categoryPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"},{"id":9,"nowprice":299,"orgnprice":299,"pageNo":1,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":3,"nowprice":299,"orgnprice":299,"pageNo":1,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":5,"nowprice":299,"orgnprice":299,"pageNo":1,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1}]]
     * timedSale : [{"failureTime":1492823286000,"id":2,"name":"玻璃杯2","nowprice":299,"orgnprice":299,"pageNo":1,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"failureTime":1495415286000,"id":4,"name":"玻璃杯4","nowprice":299,"orgnprice":299,"pageNo":1,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1}]
     */

    private String newProPic;
    private List<CrclphotosBean> crclphotos;
    private List<NewProBean> newPro;
    private List<List<HotcnnmdBean>> hotcnnmd;
    private List<TimedSaleBean> timedSale;

    public String getNewProPic() {
        return newProPic;
    }

    public void setNewProPic(String newProPic) {
        this.newProPic = newProPic;
    }

    public List<CrclphotosBean> getCrclphotos() {
        return crclphotos;
    }

    public void setCrclphotos(List<CrclphotosBean> crclphotos) {
        this.crclphotos = crclphotos;
    }

    public List<NewProBean> getNewPro() {
        return newPro;
    }

    public void setNewPro(List<NewProBean> newPro) {
        this.newPro = newPro;
    }

    public List<List<HotcnnmdBean>> getHotcnnmd() {
        return hotcnnmd;
    }

    public void setHotcnnmd(List<List<HotcnnmdBean>> hotcnnmd) {
        this.hotcnnmd = hotcnnmd;
    }

    public List<TimedSaleBean> getTimedSale() {
        return timedSale;
    }

    public void setTimedSale(List<TimedSaleBean> timedSale) {
        this.timedSale = timedSale;
    }

    public static class CrclphotosBean {
        /**
         * goodsId : 1
         * goodsPic : http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg
         */

        private int goodsId;
        private String goodsPic;

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsPic() {
            return goodsPic;
        }

        public void setGoodsPic(String goodsPic) {
            this.goodsPic = goodsPic;
        }
    }

    public static class NewProBean {
        /**
         * id : 1
         * name : 玻璃杯
         * nowprice : 299
         * orgnprice : 299
         * pageNo : 1
         * showpic : http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg
         * typeId : 1
         */

        private int id;
        private String name;
        private int nowprice;
        private int orgnprice;
        private int pageNo;
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

    public static class HotcnnmdBean {
        /**
         * categoryDecr : 天然塑造
         * categoryId : 1
         * categoryPic : http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg
         * id : 1
         * nowprice : 299
         * orgnprice : 299
         * pageNo : 1
         * showpic : http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg
         * typeId : 1
         */

        private String categoryDecr;
        private int categoryId;
        private String categoryPic;
        private String categoryName;
        private int id;
        private int nowprice;
        private int orgnprice;
        private int pageNo;
        private String showpic;
        private int typeId;

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getCategoryDecr() {
            return categoryDecr;
        }

        public void setCategoryDecr(String categoryDecr) {
            this.categoryDecr = categoryDecr;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryPic() {
            return categoryPic;
        }

        public void setCategoryPic(String categoryPic) {
            this.categoryPic = categoryPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

    public static class TimedSaleBean {
        /**
         * failureTime : 1492823286000
         * id : 2
         * name : 玻璃杯2
         * nowprice : 299
         * orgnprice : 299
         * pageNo : 1
         * showpic : http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg
         * typeId : 1
         */

        private long failureTime;
        private int id;
        private String name;
        private int nowprice;
        private int orgnprice;
        private int pageNo;
        private String showpic;
        private int typeId;

        public long getFailureTime() {
            return failureTime;
        }

        public void setFailureTime(long failureTime) {
            this.failureTime = failureTime;
        }

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

        //倒计时相关----------------start-------------------------
        private long countdown_0;
        private long  countdown_endTime;
        private int countdown_id;

        public int getCountdown_id() {
            return countdown_id;
        }

        public void setCountdown_id(int countdown_id) {
            this.countdown_id = countdown_id;
        }

        public long getCountdown_endTime() {
            return countdown_endTime;
        }

        public void setCountdown_endTime(long countdown_endTime) {
            this.countdown_endTime = countdown_endTime;
        }

        public long getCountdown_0() {
            return countdown_0;
        }

        public void setCountdown_0(long countdown_0) {
            this.countdown_0 = countdown_0;
        }
        //倒计时相关----------------end-------------------------
    }
}
