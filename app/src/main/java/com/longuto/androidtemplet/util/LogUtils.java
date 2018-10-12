package com.longuto.androidtemplet.util;

import android.util.Log;

import com.longuto.androidtemplet.BuildConfig;
import com.longuto.androidtemplet.app.G;

/**
 * 日志打印类,调试时将isDebug设为true,正式上线时将isDebug设为false
 * Created by longuto on 2016/10/27.
 */
public class LogUtils {
	private static boolean isDebug = BuildConfig.DEBUG;	// 是否处于debug模式

	public static void mvp_frame(String msg) {
		if(isDebug) {
			Log.i(G.TAG, msg);
		}
	}

	public static void mvp_frame_i(String msg) {
		if(isDebug) {
			Log.i(G.TAG, msg);
		}
	}

	public static void mvp_frame_v(String msg) {
		if(isDebug) {
			Log.v(G.TAG, msg);
		}
	}

	public static void mvp_frame_w(String msg) {
		if(isDebug) {
			Log.w(G.TAG, msg);
		}
	}

	public static void mvp_frame_e(String msg) {
		if(isDebug) {
			Log.e(G.TAG, msg);
		}
	}

	private LogUtils(){}	//私有构造方法

    public static void db(String s) {

	}
}
