package com.longuto.androidtemplet.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.longuto.androidtemplet.base.BaseSupportActivity;
import com.longuto.androidtemplet.base.mvp.abs.factory.PresenterMvpFactory;
import com.longuto.androidtemplet.base.mvp.abs.factory.PresenterMvpFactoryImpl;
import com.longuto.androidtemplet.base.mvp.abs.presenter.BaseMvpPresenter;
import com.longuto.androidtemplet.base.mvp.abs.proxy.BaseMvpProxy;
import com.longuto.androidtemplet.base.mvp.abs.proxy.PresenterProxyInterface;
import com.longuto.androidtemplet.base.mvp.abs.view.BaseMvpView;
import com.longuto.androidtemplet.util.LogUtils;

/**
 * Author by yltang,
 * Date on 2018/10/12.
 * PS: 1、继承Mvp基类，需要 @CreatePresenter 注解， 且 <V extends BaseMvpView, P extends BaseMvpPresenter<V>>
 *     implements (V extends BaseMvpView)
 *     2、是继承BaseAppCompatActivity重写写一份AbstractMvpActivity方法好，还是继承AbstractMvpActivity
 *     重写一份BaseAppCompatActivity方法， 哪一种效果好，看扩展
 */
public abstract class BaseSupportMvpActivity<V extends BaseMvpView, P
        extends BaseMvpPresenter<V>> extends BaseSupportActivity implements PresenterProxyInterface<V,P> {

    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private BaseMvpProxy<V,P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V,P>createFactory(getClass()));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.mvp_frame("V onCreate");
        LogUtils.mvp_frame("V onCreate mProxy = " + mProxy);
        LogUtils.mvp_frame("V onCreate this = " + this.hashCode());
        if(savedInstanceState != null){
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.mvp_frame("V onResume");
        mProxy.onResume((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.mvp_frame("V onDestroy = " + isChangingConfigurations());
        mProxy.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.mvp_frame("V onSaveInstanceState");
        outState.putBundle(PRESENTER_SAVE_KEY,mProxy.onSaveInstanceState());
    }

    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        LogUtils.mvp_frame("V setPresenterFactory");
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        LogUtils.mvp_frame("V getPresenterFactory");
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getMvpPresenter() {
        LogUtils.mvp_frame("V getMvpPresenter");
        return mProxy.getMvpPresenter();
    }
}
