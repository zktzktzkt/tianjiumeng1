package com.yxk.tjm.tianjiumeng.my.bean;

import java.util.List;

/**
 * Created by ningfei on 2017/5/3.
 */

public class UpdataCustomBean {

    /**
     * tailorAmount : 1
     * tailorDecr : 哈哈
     * tailorMaterial : 玻璃
     * tailorPics : [{"tailorPicUrl":"http://192.168.31.172:8080/dreamSkyEverMavenWebapp/tailor/1492682616614.jpg"}]
     * tailorSize : x3x2
     */

    private String tailorAmount;
    private String tailorDecr;
    private String tailorMaterial;
    private String tailorSize;
    private List<TailorPicsBean> tailorPics;

    public String getTailorAmount() {
        return tailorAmount;
    }

    public void setTailorAmount(String tailorAmount) {
        this.tailorAmount = tailorAmount;
    }

    public String getTailorDecr() {
        return tailorDecr;
    }

    public void setTailorDecr(String tailorDecr) {
        this.tailorDecr = tailorDecr;
    }

    public String getTailorMaterial() {
        return tailorMaterial;
    }

    public void setTailorMaterial(String tailorMaterial) {
        this.tailorMaterial = tailorMaterial;
    }

    public String getTailorSize() {
        return tailorSize;
    }

    public void setTailorSize(String tailorSize) {
        this.tailorSize = tailorSize;
    }

    public List<TailorPicsBean> getTailorPics() {
        return tailorPics;
    }

    public void setTailorPics(List<TailorPicsBean> tailorPics) {
        this.tailorPics = tailorPics;
    }

    public static class TailorPicsBean {
        /**
         * tailorPicUrl : http://192.168.31.172:8080/dreamSkyEverMavenWebapp/tailor/1492682616614.jpg
         */

        private String tailorPicUrl;

        public String getTailorPicUrl() {
            return tailorPicUrl;
        }

        public void setTailorPicUrl(String tailorPicUrl) {
            this.tailorPicUrl = tailorPicUrl;
        }
    }
}
