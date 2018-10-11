/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.longuto.androidtemplet.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.longuto.androidtemplet.app.G;

import java.io.File;
import java.io.IOException;

import static android.os.Environment.MEDIA_MOUNTED;

public class StorageUtils {

    private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";

    private StorageUtils() {
    }

    /**
     * Returns application cache directory. Cache directory will be created on SD card
     * <i>("/Android/data/[app_package_name]/cache")</i> if card is mounted and app has appropriate permission. Else -
     * Android defines cache directory on device's file system.
     *
     * @param context Application context
     * @return Cache {@link File directory}
     */
    public static File getCacheDirectory(Context context) {
        File appCacheDir = null;
        if (MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) {
            appCacheDir = getExternalCacheDir(context);
        }
        if (appCacheDir == null) {
            appCacheDir = context.getCacheDir();
        }
        if (appCacheDir == null) {
            LogUtils.mvp_frame_e("Can't define system cache directory! The app should be re-installed.");
        }
        return appCacheDir;
    }

    /**
     * 本地缓存位置
     * @param context
     * @return
     */
    private static File getExternalCacheDir(Context context) {
        File dataDir = new File(Environment.getExternalStoragePublicDirectory(Environment
                .DIRECTORY_DOWNLOADS), G.MVP_FRAME_ROOT);
        File appCacheDir = new File(new File(dataDir, context.getPackageName()), G.CACHE);
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                LogUtils.mvp_frame_e("Unable to create external cache directory");
                return null;
            }
            try {
                new File(appCacheDir, ".nomedia").createNewFile();
            } catch (IOException e) {
                LogUtils.mvp_frame_e("Can't create \".nomedia\" file in application external cache directory");
            }
        }
        return appCacheDir;
    }

    private static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
        return perm == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 本地数据库文件位置
     * @param context
     * @return
     */
    public static File getExternalDatabasesDir(Context context) {
        if (MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) {
            File dataDir = new File(Environment.getExternalStoragePublicDirectory(Environment
                    .DIRECTORY_DOWNLOADS), G.MVP_FRAME_ROOT);
            File appCacheDir = new File(new File(dataDir, context.getPackageName()), G.DATABASES);
            if (!appCacheDir.exists()) {
                if (!appCacheDir.mkdirs()) {
                    LogUtils.mvp_frame_e("Unable to create external cache directory");
                    return null;
                }
                try {
                    new File(appCacheDir, ".nomedia").createNewFile();
                } catch (IOException e) {
                    LogUtils.mvp_frame_e("Can't create \".nomedia\" file in application external cache directory");
                }
            }
            return appCacheDir;
        } else {
            return null;
        }
    }

    /**
     * 本地崩溃日志位置
     * @param context
     * @return
     */
    public static File getExternalCrashlogDir(Context context) {
        if (MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) {
            File dataDir = new File(Environment.getExternalStoragePublicDirectory(Environment
                    .DIRECTORY_DOWNLOADS), G.MVP_FRAME_ROOT);
            File appCacheDir = new File(new File(dataDir, context.getPackageName()), G.CRASHLOG);
            if (!appCacheDir.exists()) {
                if (!appCacheDir.mkdirs()) {
                    LogUtils.mvp_frame_e("Unable to create external cache directory");
                    return null;
                }
                try {
                    new File(appCacheDir, ".nomedia").createNewFile();
                } catch (IOException e) {
                    LogUtils.mvp_frame_e("Can't create \".nomedia\" file in application external cache directory");
                }
            }
            return appCacheDir;
        } else {
            return null;
        }
    }
}
