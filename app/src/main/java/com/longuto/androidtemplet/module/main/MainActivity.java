package com.longuto.androidtemplet.module.main;

import android.view.View;

import com.longuto.androidtemplet.R;
import com.longuto.androidtemplet.base.mvp.BaseAppCompatMvpActivity;
import com.longuto.androidtemplet.base.mvp.abs.factory.CreatePresenter;
import com.longuto.androidtemplet.widget.TestActionSheetDialog;

import butterknife.OnClick;

@CreatePresenter(RequestPresenter5.class)
public class MainActivity extends BaseAppCompatMvpActivity<RequestView5, RequestPresenter5> implements RequestView5 {

    @OnClick(R.id.tv)
    public void function(View view) {
        new TestActionSheetDialog(getContext()).setData(null).show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
