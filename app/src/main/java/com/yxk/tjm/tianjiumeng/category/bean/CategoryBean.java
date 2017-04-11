package com.yxk.tjm.tianjiumeng.category.bean;

import java.util.List;

/**
 * Created by ningfei on 2017/4/8.
 */

public class CategoryBean {

    private List<BrandListBean> BrandList;
    private List<ProductListBean> ProductList;
    private List<TypeListBean> TypeList;

    public List<BrandListBean> getBrandList() {
        return BrandList;
    }

    public void setBrandList(List<BrandListBean> BrandList) {
        this.BrandList = BrandList;
    }

    public List<ProductListBean> getProductList() {
        return ProductList;
    }

    public void setProductList(List<ProductListBean> ProductList) {
        this.ProductList = ProductList;
    }

    public List<TypeListBean> getTypeList() {
        return TypeList;
    }

    public void setTypeList(List<TypeListBean> TypeList) {
        this.TypeList = TypeList;
    }

    public static class BrandListBean {
        /**
         * brandId : 1
         * brandName : 青苹果
         * brandPic : http://img14.360buyimg.com/n1/jfs/t2671/40/3738529602/91694/6d433474/5796ff32N124294ab.jpg
         */

        private int brandId;
        private String brandName;
        private String brandPic;

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getBrandPic() {
            return brandPic;
        }

        public void setBrandPic(String brandPic) {
            this.brandPic = brandPic;
        }
    }

    public static class ProductListBean {
        /**
         * id : 10
         * name : 玻璃杯10
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

    public static class TypeListBean {
        /**
         * typeId : 1
         * typeName : 红酒杯
         */

        private int typeId;
        private String typeName;

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
    }
}
