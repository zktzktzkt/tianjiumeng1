package com.yxk.tjm.tianjiumeng.network;

/**
 * Created by ningfei on 2017/4/7.
 */

public class ApiConstants {

    /**
     * 本地服务器
     */
    public static final String BASE_URL = "http://192.168.31.172:8080/dreamSkyEverMavenWebapp";

    /**
     * 正式服务器
     */
  //    public static final String BASE_URL = "http://139.199.104.48:8080/dreamSkyEverMavenWebapp";

    /**
     * 登录
     */
    public static final String LOGIN = BASE_URL + "/login";

    /**
     * 注册
     */
    public static final String REGIST = BASE_URL + "/registe";

    /**
     * 判断用户是否注册过
     */
    public static final String CHECK_USER = BASE_URL + "/isregiste";

    /**
     * 注册 -> 发送验证码
     */
    public static final String SEND_VERIFI_CODE = BASE_URL + "/sendVcode";

    /**
     * 忘记密码
     */
    public static final String FORGET_PWD = BASE_URL + "/updatePassword";

    /**
     * 首页
     */
    public static final String HOME = BASE_URL + "/qryhome";

    /**
     * 首页->水晶之屋
     */
    public static final String HOME_CRYSTAL = BASE_URL + "/qryproductlist";

    /**
     * 首页->拼伙团
     */
    public static final String HOME_PINHUO = BASE_URL + "/qryPinHuoprols";

    /**
     * 首页->私人定制->材质
     */
    public static final String HOME_CUSTOM_TEXTRUE = BASE_URL + "/tailormaterials";

    /**
     * 首页->私人定制-> 上传图片
     */
    public static final String HOME_SUBMIT_IMG = BASE_URL + "/uploadtailorPic";
    /**
     * 首页->私人定制-> 提交
     */
    public static final String HOME_CUSTOM_SUBMIT = BASE_URL + "/uploadtailorInfo";

    /**
     * 首页->拼伙团 ->拼伙详情
     */
    public static final String HOME_PINHUO_DETAIL = BASE_URL + "/qryPinHuoDetail";

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

    /**
     * 我的 -> 个人资料 -> 修改收货地址
     */
    public static final String MY_MODIFI_ADDRESS = BASE_URL + "/updateaddr";

    /**
     * 我的 -> 个人资料 -> 删除收货地址
     */
    public static final String MY_DELETE_ADDRESS = BASE_URL + "/deleteaddr";

    /**
     * 我的 -> 个人资料 -> 修改默认地址
     */
    public static final String MY_UPDATE_DELETE_ADDRESS = BASE_URL + "/updateaddrDef";

    /**
     * 我的 -> 我的订单
     */
    public static final String MY_ORDER = BASE_URL + "/qryorder";

    /**
     * 我的 -> 我的订单
     */
    public static final String MY_ORDER_CANCEL = BASE_URL + "/cancelorder";

    /**
     * 我的 -> 卡券包
     */
    public static final String MY_COUPON = BASE_URL + "/qryCoupon";

    /**
     * 我的 -> 天久梦之家
     */
    public static final String MY_TJM_HOUSE = BASE_URL + "/qrydreamhouse";

    /**
     * 我的 -> 佣金明细
     */
    public static final String MY_RAKEOFF_DETAIL = BASE_URL + "/qryrakeoffDetail";

    /**
     * 我的 -> 水晶钻明细
     */
    public static final String MY_JEWEL_DETAIL = BASE_URL + "/qryjewelDetail";

    /**
     * 我的 -> 返利明细
     */
    public static final String MY_REBATE_DETAIL = BASE_URL + "/qryrebateDetail";

    /**
     * 我的 -> 私人定制
     */
    public static final String MY_CUSTOM = BASE_URL + "/qrytailor";

}
