package com.yxk.tjm.tianjiumeng.my.bean;

/**
 * Created by zkt on 2017/3/12.
 */

public class CommissionBean {
    public CommissionBean(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    private String item;
}
