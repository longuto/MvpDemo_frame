package com.longuto.androidtemplet.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.longuto.androidtemplet.R;
import com.longuto.androidtemplet.util.ActivityManager;
import com.longuto.androidtemplet.util.AppManager;
import com.longuto.androidtemplet.util.LogUtils;
import com.longuto.androidtemplet.widget.CusProgressDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Author by yltang,
 * Date on 2018/10/11.
 * PS: AppCompatActivity基类
 */
public abstract class BaseSupportActivity extends SupportActivity implements IBaseView {

    private LinearLayout rootLayout;    // titleBar
    private TextView title; // 标题文本
    private ImageView back; // 返回按钮
    private CusProgressDialog mProgressDialog;  // 加载等待对话框

    /**
     * 设置标题
     * @param msg
     */
    protected void setTitle(String msg) {
        if (title != null) {
            title.setText(msg);
        }
    }

    /**
     * sometime you want to define back event
     */
    protected void setBackBtn() {
        if (back != null) {
            back.setVisibility(View.VISIBLE);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    close();
                }
            });
        }else {
            LogUtils.mvp_frame_e("back is null ,please check out");
        }

    }

    protected void setBackClickListener(View.OnClickListener listener) {
        if (back != null) {
            back.setVisibility(View.VISIBLE);
            back.setOnClickListener(listener);
        }else {
            LogUtils.mvp_frame_e("back is null ,please check out");
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        // 这句很关键，注意是调用父类的方法，重写这个方法
        super.setContentView(R.layout.activity_base);
        setContentView(getLayoutId());
        ActivityManager.getInstance().addActivity(this);
        ButterKnife.bind(this);
        mProgressDialog = new CusProgressDialog(this);
    }

    /**
     * 获取界面布局id
     * @return
     */
    protected abstract int getLayoutId();

    // 初始化toolbar
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        if (getSupportActionBar() != null) {
            // Enable the Up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }
        back = (ImageView) findViewById(R.id.img_back);
        title = (TextView) findViewById(R.id.title);
    }

    @Override
    public void setContentView(int layoutResID) {
        if(isShowToolbar()) {
            setContentView(View.inflate(this, layoutResID, null));
            initToolbar();
        } else {
            super.setContentView(layoutResID);
        }
    }

    /**
     * 是否显示toolbar，
     * @return
     */
    protected boolean isShowToolbar() {
        return false;
    }

    @Override
    public void setContentView(View view) {
        rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (rootLayout == null) return;
        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);
        if (null != mProgressDialog) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    @Override
    public void showProgress(String content) {
        if (mProgressDialog != null) {
            if (!TextUtils.isEmpty(content)) {
                mProgressDialog.setProgressText(content);
            }
            mProgressDialog.show();
        }
    }

    @Override
    public void showProgress() {
        if(mProgressDialog != null) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.hide();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void close() {
        finish();
    }

    /**
     * 当前Activity是否存在
     * @return
     */
    protected boolean isActExist() {
        if (this == null || this.isFinishing()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onPermissionSuccess() {

    }

    @Override
    public void onPermissionFail() {

    }

    // -----------------请求权限相关-------------------- //

    protected String[] permissions = {};//需要请求的权限
    protected String[] refuseTips = {};//拒绝请求后的对话框提示

    private int permissionPosition = 0;//当前请求的权限
    private static final int REQUEST_TO_SETTING = 0;//跳转到系统设置权限页面

    AlertDialog.Builder permissionDialogBuilder;

    protected void setPermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onPermissionSuccess();
        } else {
            List<String> pTemp = new ArrayList<>();
            List<String> tTemp = new ArrayList<>();
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                if (ContextCompat.checkSelfPermission(this, permission) !=
                        PackageManager.PERMISSION_GRANTED) {
                    pTemp.add(permission);
                    tTemp.add(refuseTips[i]);
                }
            }

            permissions = pTemp.toArray(new String[pTemp.size()]);
            refuseTips = tTemp.toArray(new String[tTemp.size()]);

            requestPermissions(0);
        }
    }

    private void requestPermissions(int index) {
        if (permissions.length > 0 && index >= 0 && index < permissions.length) {
            ActivityCompat.requestPermissions(this, new String[]{permissions[index]}, index);
        } else if (permissions.length == 0 || index >= permissions.length) {
            onPermissionSuccess();
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, String[] p, int[] grantResults) {
        if (grantResults != null && grantResults.length > 0) {
            permissionPosition = requestCode;
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                if (requestCode < refuseTips.length) {
                    permissionDialogBuilder = new AlertDialog.Builder(this);
                    permissionDialogBuilder.setTitle("权限设置");
                    permissionDialogBuilder.setMessage(refuseTips[requestCode]);
                    permissionDialogBuilder.setPositiveButton("去设置", new DialogInterface
                            .OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AppManager.showInstalledAppDetails(BaseSupportActivity.this,
                                    getPackageName(), REQUEST_TO_SETTING);
                        }
                    });
                    permissionDialogBuilder.setNegativeButton("取消", new DialogInterface
                            .OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            onPermissionFail();
                        }
                    });
                    permissionDialogBuilder.show();
                } else {
                    LogUtils.mvp_frame_e("请求权限的说明文本偏少");
                }
            } else {
                nextRequestPermissions();
            }
        }
    }

    /**
     * 请求数组里的下一个权限
     */
    private void nextRequestPermissions() {
        int nextRequest = permissionPosition + 1;
        requestPermissions(nextRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_TO_SETTING == requestCode) {
            if (permissionPosition < permissions.length) {
                if (ContextCompat.checkSelfPermission(this, permissions[permissionPosition]) !=
                        PackageManager.PERMISSION_GRANTED) {
                    onPermissionFail();
                } else {
                    nextRequestPermissions();
                }
            }
        }
    }

    /**------------ 点击空白处隐藏软键盘 ------------*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }
}
