package com.yxk.tjm.tianjiumeng.category.bean;

/**
 * Created by zkt on 2017/3/11.
 */

public class RightCategoryBean {
    private String name;

    private int resImage;

    public int getResImage() {
        return resImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RightCategoryBean(int resImage) {
        this.resImage = resImage;
    }


}
