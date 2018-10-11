package com.longuto.androidtemplet.app;

import java.io.File;

/**
 * Author by yltang,
 * Date on 2018/10/11.
 * PS: 全局静态常量工具类
 */
public class G {
    
    public static String TAG = "mvp_frame";   // 项目的TAG

    // 本地文件存储目录 - 前缀为sdcard/Download
    public static String MVP_FRAME_ROOT = "mvp_frame" + File.separator + "Android";
    public static final String CACHE = "cache"; // 缓存
    public static final String DATABASES = "databases"; // 数据库
    public static final String CRASHLOG = "CrashLog"; // 崩溃日志
}
