package com.longuto.androidtemplet.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.List;

public class AppManager {

    //获取本机已经安装信息列表
    public static List<PackageInfo> getInstallApkList(Context context) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.PERMISSION_GRANTED);

        if (packages != null && packages.size() > 0) {
            return packages;
        } else {
            return new ArrayList<>();
        }
    }

    //卸载APP
    public static void startUninstall(Context context, final String pkg) {
        Uri packageURI = Uri.parse("package:" + pkg);
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        context.startActivity(uninstallIntent);
    }

    private static final String SCHEME = "package";

    //调用系统InstalledAppDetails界面所需的Extra名称(用于Android 2.1及之前版本)
    private static final String APP_PKG_NAME_21 = "com.android.settings.ApplicationPkgName";

    //调用系统InstalledAppDetails界面所需的Extra名称(用于Android 2.2)
    private static final String APP_PKG_NAME_22 = "pkg";

    //InstalledAppDetails所在包名
    private static final String APP_DETAILS_PACKAGE_NAME = "com.android.settings";

    //InstalledAppDetails类名
    private static final String APP_DETAILS_CLASS_NAME = "com.android.settings.InstalledAppDetails";

    /**
     * 调用系统InstalledAppDetails界面显示已安装应用程序的详细信息。 对于Android 2.3（Api Level
     * 9）以上，使用SDK提供的接口； 2.3以下，使用非公开的接口（查看InstalledAppDetails源码）。
     *
     * @param packageName 应用程序的包名
     */
    public static void showInstalledAppDetails(Context context, String packageName, int... requestCode) {
        Intent intent = new Intent();
        final int apiLevel = Build.VERSION.SDK_INT;
        if (apiLevel >= Build.VERSION_CODES.GINGERBREAD) { // 2.3（ApiLevel 9）以上，使用SDK提供的接口
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts(SCHEME, packageName, null);
            intent.setData(uri);
        } else {
            // 2.3以下，使用非公开的接口（查看InstalledAppDetails源码）
            // 2.2和2.1中，InstalledAppDetails使用的APP_PKG_NAME不同。
            final String appPkgName = (apiLevel == 8 ? APP_PKG_NAME_22 : APP_PKG_NAME_21);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName(APP_DETAILS_PACKAGE_NAME, APP_DETAILS_CLASS_NAME);
            intent.putExtra(appPkgName, packageName);
        }

        if (requestCode.length > 0) {
            ((Activity) context).startActivityForResult(intent, requestCode[0]);
        } else {
            context.startActivity(intent);
        }

    }

    /**
     * 获取当前apk的版本号
     */
    public static int getVersionCode(Context mContext) {
        if (mContext != null) {
            try {
                return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException ignored) {
            }
        }
        return 0;
    }

    /**
     * 获取当前apk的版本名称
     * @param mContext
     * @return
     */
    public static String getVersionName(Context mContext) {
        if (mContext != null) {
            try {
                return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException ignored) {
            }
        }

        return "";
    }

    /**
     * 获取设备信息
     * @return
     */
    public static String getDeviceInfo() {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("DEVICE INFORMATION").append('\n');
            sb.append("Board: ").append(Build.BOARD).append('\n');
            sb.append("BOOTLOADER: ").append(Build.BOOTLOADER).append('\n');
            sb.append("BRAND: ").append(Build.BRAND).append('\n');
            sb.append("DEVICE: ").append(Build.DEVICE).append('\n');
            sb.append("DISPLAY: ").append(Build.DISPLAY).append('\n');
            sb.append("FINGERPRINT: ").append(Build.FINGERPRINT).append('\n');
            sb.append("HARDWARE: ").append(Build.HARDWARE).append('\n');
            sb.append("HOST: ").append(Build.HOST).append('\n');
            sb.append("ID: ").append(Build.ID).append('\n');
            sb.append("MANUFACTURER: ").append(Build.MANUFACTURER).append('\n');
            sb.append("PRODUCT: ").append(Build.PRODUCT).append('\n');
            sb.append("TAGS: ").append(Build.TAGS).append('\n');
            sb.append("TYPE: ").append(Build.TYPE).append('\n');
            sb.append("USER: ").append(Build.USER).append('\n');
        } catch (Exception e) {
            e.toString();
        }
        return sb.toString();
    }
}
