package com.longuto.androidtemplet.module.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.longuto.androidtemplet.base.mvp.presenter.BaseMvpPresenter;

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
            Log.e("perfect-mvp","RequestPresenter5  onCreatePersenter 测试  = " + savedState.getString("test2") );
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("perfect-mvp","RequestPresenter5  onSaveInstanceState 测试 " );
        outState.putString("test2","test_save2");
    }

    @Override
    public void onDestroyPersenter() {
        super.onDestroyPersenter();
    }
}
