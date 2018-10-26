package com.longuto.androidtemplet.util;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by yltang3 on 2018/1/15.
 */
public class ConfigUtil {

    private static InputStream input;

    private volatile Properties configuration = new Properties();

    /**
     * 单例模式
     */
    private static ConfigUtil config;
    public static ConfigUtil getInstance() {
        if(config == null) {
            config = new ConfigUtil();
        }
        return config;
    }

    //构造函数内完成了加载配置文件
    private ConfigUtil() {
    }

    /**
     * 设置Raw的id获取流文件
     */
    public void setRawId(int id) {
        try {
            input = UiUtils.getContext().getResources().openRawResource(id);
//        input = this.getClass().getResourceAsStream(path);
            this.configuration.clear();
            this.configuration.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFilePath(String dir, String name) {
        try {
            File file = createFile(dir, name);
            if(null == file) {
                LogUtils.mvp_frame_e("创建配置文件失败，结束配置流程");
                return;
            }
            input = new FileInputStream(file);
            //        input = this.getClass().getResourceAsStream(path);
            this.configuration.clear();
            this.configuration.load(input);
        } catch (IOException e) {
            LogUtils.mvp_frame_e(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 创建file文件
     */
    private File createFile(String dir, String name) {
        try {
            File dirFile = new File(Environment.getExternalStorageDirectory(), dir);
            if(!dirFile.exists()) {
                if(dirFile.mkdirs()) {
                    LogUtils.mvp_frame_e("创建文件目录成功");
                } else {
                    LogUtils.mvp_frame_e("创建文件目录失败");
                    return null;
                }
            }
            LogUtils.mvp_frame_e("配置文件目录已存在");
            File file = new File(dirFile, name);
            if(!file.exists()) {
                if(file.createNewFile()) {
                    LogUtils.mvp_frame_e("创建配置文件成功");
                } else {
                    LogUtils.mvp_frame_e("创建配置文件失败");
                    return null;
                }
            }
            LogUtils.mvp_frame_e("创建配置文件已存在");
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public String getStringValue(String key) {
        String value = null;
        try {
            value = new String(this.configuration.getProperty(key).getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            value = null;
        }
        return value;
    }

    public String getStringValue(String key, String defaultValue) {
        String value = null;
        try {
            value = new String(this.configuration.getProperty(key).getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            value = null;
        }

        if (value == null) {
            return defaultValue;
        } else {
            return value;
        }
    }

    public void setConfiguration(String key, String value)
    {
        this.configuration.setProperty(key, value);
    }
}
