package com.yxk.tjm.tianjiumeng.shopcar.bean;

import java.io.Serializable;

/**
 * Created by ningfei on 2017/5/8.
 */

public class ShopcartSerialize implements Serializable {
    String buyCartId;
    String goodsAccant;

    public ShopcartSerialize(String buyCartId, String goodsAccant) {
        this.buyCartId = buyCartId;
        this.goodsAccant = goodsAccant;
    }

    public String getBuyCartId() {
        return buyCartId;
    }

    public void setBuyCartId(String buyCartId) {
        this.buyCartId = buyCartId;
    }

    public String getGoodsAccant() {
        return goodsAccant;
    }

    public void setGoodsAccant(String goodsAccant) {
        this.goodsAccant = goodsAccant;
    }
}
