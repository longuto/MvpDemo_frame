package com.longuto.androidtemplet.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.longuto.androidtemplet.base.BaseAppCompatActivity;
import com.longuto.androidtemplet.base.mvp.abs.factory.PresenterMvpFactory;
import com.longuto.androidtemplet.base.mvp.abs.factory.PresenterMvpFactoryImpl;
import com.longuto.androidtemplet.base.mvp.abs.presenter.BaseMvpPresenter;
import com.longuto.androidtemplet.base.mvp.abs.proxy.BaseMvpProxy;
import com.longuto.androidtemplet.base.mvp.abs.proxy.PresenterProxyInterface;
import com.longuto.androidtemplet.base.mvp.abs.view.BaseMvpView;

/**
 * Author by yltang,
 * Date on 2018/10/12.
 * PS: 1、继承Mvp基类，需要 @CreatePresenter 注解， 且 <V extends BaseMvpView, P extends BaseMvpPresenter<V>>
 *     implements (V extends BaseMvpView)
 *     2、是继承BaseAppCompatActivity重写写一份AbstractMvpActivity方法好，还是继承AbstractMvpActivity
 *     重写一份BaseAppCompatActivity方法， 哪一种效果好，看扩展
 */
public abstract class BaseAppCompatMvpActivity<V extends BaseMvpView, P
        extends BaseMvpPresenter<V>> extends BaseAppCompatActivity implements PresenterProxyInterface<V,P> {

    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private BaseMvpProxy<V,P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V,P>createFactory(getClass()));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("perfect-mvp","V onCreate");
        Log.e("perfect-mvp","V onCreate mProxy = " + mProxy);
        Log.e("perfect-mvp","V onCreate this = " + this.hashCode());
        if(savedInstanceState != null){
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("perfect-mvp","V onResume");
        mProxy.onResume((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("perfect-mvp","V onDestroy = " + isChangingConfigurations());
        mProxy.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("perfect-mvp","V onSaveInstanceState");
        outState.putBundle(PRESENTER_SAVE_KEY,mProxy.onSaveInstanceState());
    }

    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        Log.e("perfect-mvp","V setPresenterFactory");
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        Log.e("perfect-mvp","V getPresenterFactory");
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getMvpPresenter() {
        Log.e("perfect-mvp","V getMvpPresenter");
        return mProxy.getMvpPresenter();
    }
}
