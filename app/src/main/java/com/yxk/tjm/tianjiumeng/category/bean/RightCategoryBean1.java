package com.yxk.tjm.tianjiumeng.category.bean;

/**
 * Created by ningfei on 2017/3/16.
 */

public class RightCategoryBean1 {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResImage() {
        return resImage;
    }

    public void setResImage(int resImage) {
        this.resImage = resImage;
    }

    public RightCategoryBean1(int resImage, String name) {
        this.name = name;
        this.resImage = resImage;
    }

    private String name;

    private int resImage;
}
