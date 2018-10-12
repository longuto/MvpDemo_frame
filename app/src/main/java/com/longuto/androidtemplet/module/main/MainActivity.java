package com.longuto.androidtemplet.module.main;

import com.longuto.androidtemplet.R;
import com.longuto.androidtemplet.base.mvp.BaseAppCompatMvpActivity;
import com.longuto.androidtemplet.base.mvp.abs.factory.CreatePresenter;

@CreatePresenter(RequestPresenter5.class)
public class MainActivity extends BaseAppCompatMvpActivity<RequestView5, RequestPresenter5> implements RequestView5 {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
