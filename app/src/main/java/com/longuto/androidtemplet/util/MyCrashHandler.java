/*
**        DroidPlugin Project
**
** Copyright(c) 2015 Andy Zhang <zhangyong232@gmail.com>
**
** This file is part of DroidPlugin.
**
** DroidPlugin is free software: you can redistribute it and/or
** modify it under the terms of the GNU Lesser General Public
** License as published by the Free Software Foundation, either
** version 3 of the License, or (at your option) any later version.
**
** DroidPlugin is distributed in the hope that it will be useful,
** but WITHOUT ANY WARRANTY; without even the implied warranty of
** MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
** Lesser General Public License for more details.
**
** You should have received a copy of the GNU Lesser General Public
** License along with DroidPlugin.  If not, see <http://www.gnu.org/licenses/lgpl.txt>
**
**/

package com.longuto.androidtemplet.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import com.longuto.androidtemplet.BuildConfig;

import java.io.File;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MyCrashHandler implements UncaughtExceptionHandler {

    private static final String TAG = "MyCrashHandler";

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT1 = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final MyCrashHandler sMyCrashHandler = new MyCrashHandler();


    private UncaughtExceptionHandler mOldHandler;

    private Context mContext;


    public static MyCrashHandler getInstance() {
        return sMyCrashHandler;
    }

    public void register(Context context) {
        if (context != null) {
            mOldHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (mOldHandler != this) {
                Thread.setDefaultUncaughtExceptionHandler(this);
            }
            mContext = context;
        }
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        LogUtils.mvp_frame("uncaught" + ex.toString());
        if (BuildConfig.DEBUG) {//debug时采用默认处理方式
//            mOldHandler.uncaughtException(thread, ex);
            saveLogToFile(ex);
        } else {
            saveLogToFile(ex);
        }
    }

    /**
     * 出现未知异常时退出程序,不要出现默认的提示黑框
     *
     * @return
     */
    private boolean handlerException() {
        ActivityManager.getInstance().AppExit();
        return true;
    }


    private void saveLogToFile(Throwable ex) {
        PrintWriter writer = null;
        try {
            File fileRoot = StorageUtils.getExternalCrashlogDir(UiUtils.getContext());
            if(null == fileRoot) {
                return;
            }
            Date date = new Date();
            String dateStr = SIMPLE_DATE_FORMAT1.format(date);
            File file = new File(fileRoot, String.format("CrashLog_%s_%s.log", dateStr, android.os.Process.myPid()));
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (file.exists()) {
                file.delete();
            }

            writer = new PrintWriter(file);
            StringBuffer dbContentSb = new StringBuffer();

            writer.println("Date:" + SIMPLE_DATE_FORMAT.format(date));
            dbContentSb.append("Date:" + SIMPLE_DATE_FORMAT.format(date) + "\r\n");
            String packageName = mContext.getPackageName();
            writer.println("AppPkgName:" + packageName);
            dbContentSb.append("AppPkgName:" + packageName + "\r\n");
            try {
                PackageInfo packageInfo = mContext.getPackageManager().getPackageInfo(packageName, 0);
                writer.println("VersionCode:" + packageInfo.versionCode);
                dbContentSb.append("VersionCode:" + packageInfo.versionCode + "\r\n");
                writer.println("VersionName:" + packageInfo.versionName);
                dbContentSb.append("VersionName:" + packageInfo.versionName + "\r\n");
                writer.println("Debug:" + (0 != (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE)));
                dbContentSb.append("Debug:" + (0 != (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE)) + "\r\n");
            } catch (Exception e) {
                writer.println("VersionCode:-1");
                dbContentSb.append("VersionCode:-1" + "\r\n");
                writer.println("VersionName:null");
                dbContentSb.append("VersionName:null" + "\r\n");
                writer.println("Debug:Unkown");
                dbContentSb.append("Debug:Unkown" + "\r\n");
            }
            try {
                writer.println("deviceInfo-->" + AppManager.getDeviceInfo());
                dbContentSb.append("\r\n").append("deviceInfo-->" + AppManager.getDeviceInfo() + "\r\n");
            } catch (Exception e) {
            }
            ex.printStackTrace(writer);
            dbContentSb.append(getThrowStackcontent(ex));
            if(!BuildConfig.DEBUG) {
                insertDb(dbContentSb.toString());
            }
        } catch (Throwable e) {
            LogUtils.mvp_frame_e("记录uncaughtException" + e.toString());
        } finally {
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            } catch (Exception e) {
            }
            //未知异常时,退出程序
            handlerException();
        }
    }

    // 获取Throwable的内容
    private String getThrowStackcontent(Throwable ex) {
        StackTraceElement[] stackElements = ex.getStackTrace();//通过Throwable获得堆栈信息
        StringBuffer sb = new StringBuffer();
        sb.append(ex.toString()).append("\r\n");
        if (stackElements != null) {
            for (int i = 0; i < stackElements.length; i++) {
                sb.append("\tat ").append(stackElements[i].getClassName() + ".").append(stackElements[i].getMethodName())
                        .append("(").append(stackElements[i].getFileName()).append(":")
                        .append(stackElements[i].getLineNumber()).append(")").append("\r\n");
            }
        }
        return sb.toString();
    }

    // 插入崩溃日志
    private void insertDb(String content) {

    }
}
