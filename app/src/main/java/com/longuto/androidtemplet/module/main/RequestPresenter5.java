package com.longuto.androidtemplet.module.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.longuto.androidtemplet.base.mvp.abs.presenter.BaseMvpPresenter;
import com.longuto.androidtemplet.util.LogUtils;

/**
 * @author 刘镓旗
 * @date 2017/11/17
 * @description
 */
public class RequestPresenter5 extends BaseMvpPresenter<RequestView5> {

    @Override
    public void onCreatePersenter(@Nullable Bundle savedState) {
        super.onCreatePersenter(savedState);
        if(savedState != null){
//            LogUtils.mvp_frame("RequestPresenter5  onCreatePersenter 测试  = " + savedState.getString("test2") );
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.mvp_frame("RequestPresenter5  onSaveInstanceState 测试 " );
//        outState.putString("test2","test_save2");
    }

    @Override
    public void onDestroyPersenter() {
        super.onDestroyPersenter();
    }
}
