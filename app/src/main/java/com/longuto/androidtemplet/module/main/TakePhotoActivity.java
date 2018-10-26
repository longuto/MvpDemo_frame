package com.longuto.androidtemplet.module.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.longuto.androidtemplet.R;
import com.longuto.androidtemplet.app.takephoto.TakeOrPickPhotoManager;
import com.longuto.androidtemplet.base.BaseAppCompatActivity;

import butterknife.OnClick;

/**
 * Author by yltang,
 * Date on 2018/10/26.
 * PS: takephoto的Activity.
 */
public class TakePhotoActivity extends BaseAppCompatActivity implements TakePhoto.TakeResultListener, InvokeListener {

    /**
     * 启动Act
     * @param context
     */
    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, TakePhotoActivity.class));
    }

    @OnClick({R.id.btn_take, R.id.btn_album})
    public void functions(View view) {
        switch (view.getId()) {
            case R.id.btn_take: // 拍照
                takeForPhoto(true);
                break;
            case R.id.btn_album:    // 相册
                takeForPhoto(false);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_takephoto;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void takeSuccess(TResult result) {

    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this);
    }

    // 拍照，获取图片
    private void takeForPhoto(boolean takephoto) {
        if(null == mTakeOrPickPhotoManager) {
            mTakeOrPickPhotoManager = new TakeOrPickPhotoManager(getTakePhoto());
        }
        //选择图片
        mTakeOrPickPhotoManager.takeOrPickPhoto(takephoto);
    }

    public TakeOrPickPhotoManager mTakeOrPickPhotoManager;
    TakePhoto takePhoto;
    InvokeParam invokeParam;

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    private TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this)
                    .bind(new TakePhotoImpl(this, this));

        }
        return takePhoto;
    }
}
