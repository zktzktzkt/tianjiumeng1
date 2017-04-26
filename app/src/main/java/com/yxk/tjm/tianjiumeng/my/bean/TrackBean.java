package com.yxk.tjm.tianjiumeng.my.bean;

import java.util.List;

/**
 * Created by ningfei on 2017/4/26.
 */

public class TrackBean {
    /**
     * EBusinessID : 1266422
     * ShipperCode : YTO
     * Success : true
     * LogisticCode : 12345678
     * State : 2
     * Traces : [{"AcceptTime":"2015-12-17 11:26:01","AcceptStation":"【已揽收_日本合作公司】 海外收入"},{"AcceptTime":"2016-06-21 23:31:06","AcceptStation":"【广东省阳江市公司】 派件人: 林显凡 派件中 派件员电话0662-2839878"},{"AcceptTime":"2016-07-03 11:19:06","AcceptStation":"快件已到达福特北路258号妈妈驿站派件/自提,请及时取件,如有疑问请联系13262916689"},{"AcceptTime":"2016-07-17 13:39:35","AcceptStation":"快件已到达长安南街22号妈妈驿站派件/自提,请及时取件,如有疑问请联系18924109590"},{"AcceptTime":"2016-07-26 20:46:01","AcceptStation":"快件已到达浦南伟斌沙场办公室招牌对面巷口妈妈驿站派件/自提,请及时取件,如有疑问请联系13923540626"},{"AcceptTime":"2016-07-30 14:24:51","AcceptStation":"快件已到达中心幼儿园向南第四家拿圆通妈妈驿站派件/自提,请及时取件,如有疑问请联系18005113328"},{"AcceptTime":"2016-07-31 08:21:41","AcceptStation":"快件已到达阳光鑫城联想电脑店妈妈驿站派件/自提,请及时取件,如有疑问请联系18972669694"},{"AcceptTime":"2016-08-16 12:28:16","AcceptStation":"快件已到达海滴韵小区南门嘎豆童装圆通速递妈妈驿站派件/自提,请及时取件,如有疑问请联系15354330932"},{"AcceptTime":"2016-08-20 09:50:07","AcceptStation":"快件已到达好又鲜超市圆通驿站如需送货上门妈妈驿站派件/自提,请及时取件,如有疑问请联系18522001961"},{"AcceptTime":"2016-08-30 09:35:32","AcceptStation":"快件已到达三湾公园对面老城北汽车站内妈妈驿站派件/自提,请及时取件,如有疑问请联系15727698086"},{"AcceptTime":"2016-09-02 13:59:34","AcceptStation":"快件已到达宏远厂邮局往北100米处妈妈驿站派件/自提,请及时取件,如有疑问请联系13759716219"},{"AcceptTime":"2016-09-02 16:18:23","AcceptStation":"【山东省德州市宁津县公司】 取件人: 陈绪康 已收件"},{"AcceptTime":"2016-09-06 12:01:33","AcceptStation":"快件已到达恒大绿州五号楼二单元102妈妈驿站派件/自提,请及时取件,如有疑问请联系15032617387"},{"AcceptTime":"2016-09-10 17:28:03","AcceptStation":"快件已到达西苑一期秀身内衣店领取圆通快递妈妈驿站派件/自提,请及时取件,如有疑问请联系17304773019"},{"AcceptTime":"2016-09-17 08:41:49","AcceptStation":"快件已到达信科楼1-102圆通速递妈妈驿站派件/自提,请及时取件,如有疑问请联系15538302551"},{"AcceptTime":"2016-09-18 14:09:27","AcceptStation":"快件已到达天邻风景北门482号妈妈驿站派件/自提,请及时取件,如有疑问请联系18623471555"}]
     */

    private String EBusinessID;
    private String ShipperCode;
    private boolean Success;
    private String LogisticCode;
    private String State;
    /**
     * AcceptTime : 2015-12-17 11:26:01
     * AcceptStation : 【已揽收_日本合作公司】 海外收入
     */

    private List<TracesBean> Traces;

    public String getEBusinessID() {
        return EBusinessID;
    }

    public void setEBusinessID(String EBusinessID) {
        this.EBusinessID = EBusinessID;
    }

    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String ShipperCode) {
        this.ShipperCode = ShipperCode;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getLogisticCode() {
        return LogisticCode;
    }

    public void setLogisticCode(String LogisticCode) {
        this.LogisticCode = LogisticCode;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public List<TracesBean> getTraces() {
        return  Traces;
    }

    public void setTraces(List<TracesBean> Traces) {
        this.Traces = Traces;
    }

    public static class TracesBean {
        private String AcceptTime;
        private String AcceptStation;

        public String getAcceptTime() {
            return AcceptTime;
        }

        public void setAcceptTime(String AcceptTime) {
            this.AcceptTime = AcceptTime;
        }

        public String getAcceptStation() {
            return AcceptStation;
        }

        public void setAcceptStation(String AcceptStation) {
            this.AcceptStation = AcceptStation;
        }
    }
}
