package com.yxk.tjm.tianjiumeng.network;

/**
 * Created by ningfei on 2017/4/7.
 */

public class Url {

    public static final String BASE_URL = "http://192.168.31.172:8080/dreamSkyEverMavenWebapp";

    /**
     * 首页查询
     */
    public static final String HOME = BASE_URL + "/qryhome";

    /**
     * 分类
     */
    public static final String CATEGORY = BASE_URL + "/qryproductType";

    /**
     * 资讯查询
     */
    public static final String NEWS = BASE_URL + "/qryactinfo";

     /**
     * 资讯查询->分页
     */
    public static final String NEWS_PAGE = BASE_URL + "/qryactinfoByNo";

    /**
     * 详情页
     */
    public static final String DETAIL_PAGE = BASE_URL + "/qryproduct";

    /**
     * 详情页->商品详情
     */
    public static final String DETAIL_PAGE_DETAIL = BASE_URL + "/qryproductDetail";

    /**
     * 详情页->设计理念
     */
    public static final String DETAIL_PAGE_DESIGN = BASE_URL + "/qryproductDesign";

    /**
     * 详情页->商品规格
     */
    public static final String DETAIL_PAGE_STANDARD = BASE_URL + "/qryproductSuit";
}
