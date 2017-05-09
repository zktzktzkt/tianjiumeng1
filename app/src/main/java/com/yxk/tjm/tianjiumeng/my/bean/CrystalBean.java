package com.yxk.tjm.tianjiumeng.my.bean;

/**
 * Created by ningfei on 2017/5/9.
 */

public class CrystalBean {

    /**
     * jeweltranable : 8000
     * income : 501
     * jewelTranRules : 上月收入500水晶钻以下         只能转化40%为佣金
     上月收入501-2000水晶钻以下         只能转化40%为佣金
     上月收入2000水晶钻以上         只能转化40%为佣金

     */

    private String jeweltranable;
    private String income;
    private String jewelTranRules;

    public String getJeweltranable() {
        return jeweltranable;
    }

    public void setJeweltranable(String jeweltranable) {
        this.jeweltranable = jeweltranable;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getJewelTranRules() {
        return jewelTranRules;
    }

    public void setJewelTranRules(String jewelTranRules) {
        this.jewelTranRules = jewelTranRules;
    }
}
