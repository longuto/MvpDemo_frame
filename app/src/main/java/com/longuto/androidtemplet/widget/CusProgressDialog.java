
package com.longuto.androidtemplet.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.TextView;

import com.longuto.androidtemplet.R;

/**
 * Created by lidaofu on 2015/8/15.
 * eamil lidaofu_zlx@163.com
 */
public class CusProgressDialog extends Dialog {

    private TextView txt_progress;
    private Context context;

    public CusProgressDialog(Context context) {
        super(context);
        initView(context);
    }


    public CusProgressDialog(Context context, int theme) {
        super(context, theme);
        initView(context);
    }

    protected CusProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;

        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置无标题
        getWindow().setBackgroundDrawable(new ColorDrawable());//设置无背景边框
        this.setCancelable(false);
        setContentView(R.layout.progress_dialog);
        txt_progress = (TextView) findViewById(R.id.txt_progress);
    }

    public void setProgressText(String text) {
        txt_progress.setText(text);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            dismiss();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}