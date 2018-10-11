package com.longuto.androidtemplet.util;

import android.app.Activity;

import java.lang.ref.WeakReference;

/**
 * Created by yltang3 on 2018/1/18.
 */

public class CurrentActivityManager {

    private static CurrentActivityManager sInstance = new CurrentActivityManager();

    // 采用弱引用持有 Activity ，避免造成 内存泄露
    private WeakReference<Activity> sCurrentActivityWeakRef;


    private CurrentActivityManager() {
    }

    public static CurrentActivityManager getInstance() {
        return sInstance;
    }

    public Activity getCurrentActivity() {
        Activity currentActivity = null;
        if (sCurrentActivityWeakRef != null) {
            currentActivity = sCurrentActivityWeakRef.get();
        }
        return currentActivity;
    }

    public void setCurrentActivity(Activity activity) {
        sCurrentActivityWeakRef = new WeakReference<Activity>(activity);
    }
}
