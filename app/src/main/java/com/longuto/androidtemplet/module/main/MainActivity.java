package com.longuto.androidtemplet.module.main;

import android.view.View;

import com.longuto.androidtemplet.R;
import com.longuto.androidtemplet.base.mvp.BaseAppCompatMvpActivity;
import com.longuto.androidtemplet.base.mvp.abs.factory.CreatePresenter;
import com.longuto.androidtemplet.widget.TestActionSheetDialog;

import butterknife.OnClick;

@CreatePresenter(RequestPresenter5.class)
public class MainActivity extends BaseAppCompatMvpActivity<RequestView5, RequestPresenter5> implements RequestView5 {

    @OnClick({R.id.tv, R.id.tv_second})
    public void function(View view) {
        switch (view.getId()) {
            case R.id.tv:
                new TestActionSheetDialog(getContext()).setData(null).show();
                break;
            case R.id.tv_second:
                TakePhotoActivity.startActivity(getContext());
                break;
            default:
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
