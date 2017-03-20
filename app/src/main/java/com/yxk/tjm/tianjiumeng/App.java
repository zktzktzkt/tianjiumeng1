package com.yxk.tjm.tianjiumeng;

import android.app.Application;
import android.content.Context;

/**
 * Created by ningfei on 2017/3/7.
 */

public class App extends Application {
    private static Context mContext;

    public static Context getAppContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
