package com.yxk.tjm.tianjiumeng.news.bean;

import java.util.List;

/**
 * Created by ningfei on 2017/3/11.
 */

public class NewsBean {


    private List<NewslistBean> newslist;
    private List<PubliclistBean> publiclist;

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public List<PubliclistBean> getPubliclist() {
        return publiclist;
    }

    public void setPubliclist(List<PubliclistBean> publiclist) {
        this.publiclist = publiclist;
    }

    public static class NewslistBean {
        /**
         * infoDate : 1491276153000
         * infoDetail : https://isux.tencent.com/wp-content/uploads/2015/03/20150313125355600.jpg
         * infoId : 10
         * infoShowPic : http://www.cnaca.org/files/41631020.jpg
         * infoTopic : 证法撒旦法
         * pageNo : 1
         * pageSize : 6
         */

        private long infoDate;
        private String infoDetail;
        private int infoId;
        private String infoShowPic;
        private String infoTopic;
        private int pageNo;
        private int pageSize;

        public long getInfoDate() {
            return infoDate;
        }

        public void setInfoDate(long infoDate) {
            this.infoDate = infoDate;
        }

        public String getInfoDetail() {
            return infoDetail;
        }

        public void setInfoDetail(String infoDetail) {
            this.infoDetail = infoDetail;
        }

        public int getInfoId() {
            return infoId;
        }

        public void setInfoId(int infoId) {
            this.infoId = infoId;
        }

        public String getInfoShowPic() {
            return infoShowPic;
        }

        public void setInfoShowPic(String infoShowPic) {
            this.infoShowPic = infoShowPic;
        }

        public String getInfoTopic() {
            return infoTopic;
        }

        public void setInfoTopic(String infoTopic) {
            this.infoTopic = infoTopic;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }

    public static class PubliclistBean {
        /**
         * id : 1
         * publicpic : 一年一度的劳动节在北京天坛举行
         */

        private int id;
        private String publicpic;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPublicpic() {
            return publicpic;
        }

        public void setPublicpic(String publicpic) {
            this.publicpic = publicpic;
        }
    }
}
