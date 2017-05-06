package com.yxk.tjm.tianjiumeng.home.bean;

/**
 * Created by ningfei on 2017/5/5.
 */

public class SearchListBean {

    /**
     * id : 1
     * name : 玻璃杯
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
