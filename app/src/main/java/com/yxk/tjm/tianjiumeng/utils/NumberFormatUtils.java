package com.yxk.tjm.tianjiumeng.utils;

import android.text.TextUtils;

/**
 * Created by Administrator on 2016/11/12 0012.
 */

public class NumberFormatUtils {

    public static String phoneHide(String pNumber){
        if(!TextUtils.isEmpty(pNumber) && pNumber.length() == 11){
            boolean result=pNumber.matches("[0-9]+");
            if(result){
                StringBuilder sb  =new StringBuilder();
                for (int i = 0; i < pNumber.length(); i++) {
                    char c = pNumber.charAt(i);
                    if (i >= 3 && i <= 6) {
                        sb.append('*');
                    } else {
                        sb.append(c);
                    }
                }
                return  sb.toString();
            }else {
                return pNumber;
            }
        }
        return pNumber;
    }

}
