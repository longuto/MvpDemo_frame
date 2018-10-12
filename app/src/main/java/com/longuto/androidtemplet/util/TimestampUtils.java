package com.longuto.androidtemplet.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取当前时间的工具类
 */
public class TimestampUtils {

    /**
     * 返回当前的时间戳
     * @return
     */
    public static String getCurrentTimestamp() {
        Date date = new Date();// 创建一个时间对象，获取到当前的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置时间显示格式
        return sdf.format(date);
    }
}
