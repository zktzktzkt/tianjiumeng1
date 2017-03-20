package com.yxk.tjm.tianjiumeng.shopcar.bean;

/**
 * Created by ningfei on 2017/3/10.
 */

public class ShopCartBean {

    public ShopCartBean(int picUrl, String title, String price) {
        this.picUrl = picUrl;
        this.title = title;
        this.price = price;
    }

    private int picUrl;
    private String title;
    private String price;
    private boolean isChecked = true;
    private int number = 1;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(int picUrl) {
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
