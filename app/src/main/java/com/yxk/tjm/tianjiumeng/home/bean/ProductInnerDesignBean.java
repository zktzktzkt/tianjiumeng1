package com.yxk.tjm.tianjiumeng.home.bean;

import java.util.List;

/**
 * Created by ningfei on 2017/4/11.
 */

public class ProductInnerDesignBean {

    /**
     * designPics : {"goodsPic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg"}
     * cnnmdForYou : [{"id":9,"name":"玻璃杯9","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":1,"name":"玻璃杯","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":2,"name":"玻璃杯2","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":3,"name":"玻璃杯3","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":4,"name":"玻璃杯4","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":5,"name":"玻璃杯5","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":6,"name":"玻璃杯6","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1},{"id":7,"name":"玻璃杯7","nowprice":299,"orgnprice":299,"pageNo":1,"pageSize":12,"showpic":"http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg","typeId":1}]
     */

    private DesignPicsBean designPics;
    private List<CnnmdForYouBean> cnnmdForYou;

    public DesignPicsBean getDesignPics() {
        return designPics;
    }

    public void setDesignPics(DesignPicsBean designPics) {
        this.designPics = designPics;
    }

    public List<CnnmdForYouBean> getCnnmdForYou() {
        return cnnmdForYou;
    }

    public void setCnnmdForYou(List<CnnmdForYouBean> cnnmdForYou) {
        this.cnnmdForYou = cnnmdForYou;
    }

    public static class DesignPicsBean {
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
