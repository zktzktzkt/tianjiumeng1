package com.yxk.tjm.tianjiumeng.network;

/**
 * Created by ningfei on 2017/4/7.
 */

public class ApiConstants {

    public static final String BASE_URL = "http://192.168.31.172:8080/dreamSkyEverMavenWebapp";

    /**
     * 登录
     */
    public static final String LOGIN = BASE_URL + "/login";

    /**
     * 注册
     */
    public static final String REGIST = BASE_URL + "/registe";

    /**
     * 忘记密码
     */
    public static final String FORGET_PWD = BASE_URL + "/updatePassword";

    /**
     * 首页查询
     */
    public static final String HOME = BASE_URL + "/qryhome";

    /**
     * 分类
     */
    public static final String CATEGORY = BASE_URL + "/qryproductType";

    /**
     * 资讯
     */
    public static final String NEWS = BASE_URL + "/qryactinfo";

     /**
     * 资讯->分页
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

    /**
     * 详情页->收藏
     */
    public static final String DETAIL_PAGE_COLLECT = BASE_URL + "/insertcollect";

    /**
     * 购物车
     */
    public static final String SHOPCAR = BASE_URL + "/BuyCart/getItem";

    /**
     * 购物车->修改数量
     */
    public static final String SHOPCAR_UPDATE = BASE_URL + "/BuyCart/updateItem";

    /**
     * 加入购物车
     */
    public static final String DETAIL_ADD_SHOPCAR = BASE_URL + "/BuyCart/insertItem";

    /**
     * 从购物车删除
     */
    public static final String SHOPCAR_DELETE = BASE_URL + "/BuyCart/deleteItem";

    /**
     * 我的 -> 查询个人信息
     */
    public static final String MY_INFO = BASE_URL + "/qrymycenter";

    /**
     * 我的 -> 查询个人信息
     */
    public static final String MY_HEAD_UPDATE = BASE_URL + "/uploadUserImage";

    /**
     * 我的 -> 我的收藏
     */
    public static final String MY_COLLECT = BASE_URL + "/qrycollect";

    /**
     * 我的 -> 我的收藏 -> 删除
     */
    public static final String MY_COLLECT_DELETE = BASE_URL + "/deletecollect";

    /**
     * 我的 -> 个人资料 -> 更新个人信息
     */
    public static final String MY_UPDATE_INFO = BASE_URL + "/updatemyInfo";

    /**
     * 我的 -> 个人资料 -> 添加收货地址
     */
    public static final String MY_INSERT_ADDRESS = BASE_URL + "/insertaddr";

    /**
     * 我的 -> 个人资料 -> 查询收货地址
     */
    public static final String MY_QUERY_ADDRESS = BASE_URL + "/qryaddr";

}
