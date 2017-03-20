package com.yxk.tjm.tianjiumeng.home.bean;

/**
 * Created by zkt on 2017/3/9.
 */

public class PopSpecBean {
    public boolean isSelected;
    public String specStr;

    public PopSpecBean(String specStr) {
        this.specStr = specStr;
    }

    public String getSpecStr() {
        return specStr;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
