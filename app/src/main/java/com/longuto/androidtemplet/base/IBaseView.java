package com.longuto.androidtemplet.base;

import android.content.Context;

/**
 * Created by yltang3 on 2017/11/16.
 *
 * 基类界面数据
 */

public interface IBaseView {

    /**
     * 显示文本进度
     * @param text
     */
    void showProgress(String text);

    /**
     * 显示进度
     */
    void showProgress();

    /**
     * 隐藏进度
     */
    void hideProgress();

    /**
     * 获取当前上下文对象
     * @return
     */
    Context getContext();

    /**
     * 结束当前页面
     */
    void close();

    /**
     * 请求权限成功后的回调
     */
    void onPermissionSuccess();

    /**
     * 请求权限失败后的回调
     */
    void onPermissionFail();
}
