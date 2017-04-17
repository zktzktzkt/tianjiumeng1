package com.yxk.tjm.tianjiumeng.utils;

import android.content.Context;

/**
 * Created by ningfei on 2017/4/13.
 */

public class UserUtil {

    public static boolean isLogin(Context context) {
        if ((boolean) SPUtil.get(context, "isLogin", false)) {
            return true;
        }
        return false;
    }

    public static void setLoginState(Context context, boolean b) {
        SPUtil.putAndApply(context, "isLogin", b);
    }

    public static void setUserId(Context context, int userId) {
        SPUtil.putAndApply(context, "userId", userId);
    }

    public static String getUserId(Context context) {
        return String.valueOf(SPUtil.get(context, "userId", -1));
    }


}
