package com.longuto.androidtemplet.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.support.multidex.MultiDex;

import com.longuto.androidtemplet.util.CurrentActivityManager;
import com.longuto.androidtemplet.util.MyCrashHandler;

/**
 * Created by yltang3 on 2017/3/20.
 */
public class MyApplication extends Application {

    private static Context context;  //上下文
    private static int mainThreadId; //主线程id

    public static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        context = getApplicationContext();
        mainThreadId = Process.myTid();
        initAbility();    // 初始化相关操作
    }

    /**返回上下文*/
    public static Context getContext() {
        return context;
    }

    /**获取主线程id*/
    public static int getUiTid() {
        return mainThreadId;
    }

    /**------------------------ 初始化初始值相关配置 -------------------------*/

    /** 初始化功能 */
    private void initAbility() {
        registerActivityLife();
        MyCrashHandler.getInstance().register(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 注册Activity生命周期
     */
    private void registerActivityLife() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                CurrentActivityManager.getInstance().setCurrentActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
}
