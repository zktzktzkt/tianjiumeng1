package com.yxk.tjm.tianjiumeng.my.bean;

import java.util.List;

/**
 * Created by ningfei on 2017/3/18.
 */

public class TJMBean {

    private List<DreamhousesBean> dreamhouses;

    public List<DreamhousesBean> getDreamhouses() {
        return dreamhouses;
    }

    public void setDreamhouses(List<DreamhousesBean> dreamhouses) {
        this.dreamhouses = dreamhouses;
    }

    public static class DreamhousesBean {
        /**
         * houseId : 5
         * houseName : 北京东城区旗舰店
         * housePic : http://192.168.31.172:8080/dreamSkyEverMavenWebapp/images/tu6.png
         * manager : 赵经理
         * managerPhone : 13838383838
         */

        private int houseId;
        private String houseName;
        private String housePic;
        private String manager;
        private String managerPhone;

        public int getHouseId() {
            return houseId;
        }

        public void setHouseId(int houseId) {
            this.houseId = houseId;
        }

        public String getHouseName() {
            return houseName;
        }

        public void setHouseName(String houseName) {
            this.houseName = houseName;
        }

        public String getHousePic() {
            return housePic;
        }

        public void setHousePic(String housePic) {
            this.housePic = housePic;
        }

        public String getManager() {
            return manager;
        }

        public void setManager(String manager) {
            this.manager = manager;
        }

        public String getManagerPhone() {
            return managerPhone;
        }

        public void setManagerPhone(String managerPhone) {
            this.managerPhone = managerPhone;
        }
    }
}
