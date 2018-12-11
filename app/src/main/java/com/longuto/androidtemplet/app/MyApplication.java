package com.longuto.androidtemplet.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.support.multidex.MultiDex;

import com.longuto.androidtemplet.BuildConfig;
import com.longuto.androidtemplet.util.CurrentActivityManager;
import com.longuto.androidtemplet.util.MyCrashHandler;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

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
        initFragmentation();
        MyCrashHandler.getInstance().register(this);
    }

    /**
     * 初始化Fragmentation
     */
    private void initFragmentation() {
        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG) // 实际场景建议.debug(BuildConfig.DEBUG)
                /**
                 * 可以获取到{@link me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning}
                 * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                 */
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
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
