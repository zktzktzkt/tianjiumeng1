package com.yxk.tjm.tianjiumeng.home.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by zkt on 2017/3/7.
 */

public class NewComeMarketItem implements MultiItemEntity {
    public static final int FIRST = 1;
    public static final int OTHER = 2;
    private int itemType;
    private int resImage;

    public NewComeMarketItem(int itemType, int resImage) {
        this.itemType = itemType;
        this.resImage = resImage;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
