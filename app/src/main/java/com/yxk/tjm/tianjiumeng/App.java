package com.yxk.tjm.tianjiumeng;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by ningfei on 2017/3/7.
 */

public class App extends Application {
    private static Context mContext;
    private static ActivityManager activityManager = null; // activity管理类

    public static Context getAppContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        activityManager = getManagerInstance(); // 获得实例


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public static ActivityManager getActivityManager() {
        return activityManager;
    }


    public ActivityManager getManagerInstance() {
        if (activityManager != null) {
            return activityManager;
        } else {
            return activityManager = new ActivityManager();
        }
    }

    /**
     * activity管理类**********************************************
     */
    public class ActivityManager {
        /**
         * 接收activity的Stack
         */
        private Stack<Activity> activityStack = null;

        private ActivityManager() {
        }

        /**
         * 将activity移出栈
         *
         * @param activity
         */
        public void popActivity(Activity activity) {
            if (activity != null) {
                activityStack.remove(activity);
            }
        }

        /**
         * 结束指定activity
         *
         * @param activity
         */
        public void endActivity(Activity activity) {
            if (activity != null) {
                activity.finish();
                activityStack.remove(activity);
                activity = null;
            }
        }

        /**
         * 获得当前的activity(即最上层)
         *
         * @return
         */
        public Activity currentActivity() {
            Activity activity = null;
            if (!activityStack.empty())
                activity = activityStack.lastElement();
            return activity;
        }

        /**
         * 将activity推入栈内
         *
         * @param activity
         */
        public void pushActivity(Activity activity) {
            if (activityStack == null) {
                activityStack = new Stack<Activity>();
            }
            activityStack.add(activity);
        }

        /**
         * 弹出除cls外的所有activity
         *
         * @param cls
         */
        public void popAllActivityExceptOne(Class<? extends Activity> cls) {
            while (true) {
                Activity activity = currentActivity();
                if (activity == null) {
                    break;
                }
                if (activity.getClass().equals(cls)) {
                    break;
                }
                popActivity(activity);
            }
        }

        /**
         * 结束除cls之外的所有activity,执行结果都会清空Stack
         *
         * @param cls
         */
        public void finishAllActivityExceptOne(Class<? extends Activity> cls) {
            while (!activityStack.empty()) {
                Activity activity = currentActivity();
                if (activity.getClass().equals(cls)) {
                    popActivity(activity);
                } else {
                    endActivity(activity);
                }
            }
        }

        /**
         * 结束所有activity
         */
        public void finishAllActivity() {
            while (!activityStack.empty()) {
                Activity activity = currentActivity();
                endActivity(activity);
            }
        }
    }
}


