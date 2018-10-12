package com.longuto.androidtemplet.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.longuto.androidtemplet.util.LogUtils;

/**
 * Created by lidaofu on 2015/4/28.
 * 自定义view的基类
 */
public abstract class MyCustomView extends LinearLayout {

    private static final String TAG ="MyCustomView";

    public Context context;
    public LayoutInflater inflater;

    public MyCustomView(Context context) {
        super(context);
        init(context);
        initView(inflater);
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        setAttrs(attrs);
        initView(inflater);
    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        setAttrs(attrs);
        initView(inflater);
    }


    public void init(Context context){
        LogUtils.mvp_frame("initview");
        this.context=context;
        inflater= LayoutInflater.from(context);
    }


    public abstract void setAttrs(AttributeSet attrs);
    public abstract void initView(LayoutInflater inflater);


}
