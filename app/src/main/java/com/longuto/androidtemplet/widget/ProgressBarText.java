package com.longuto.androidtemplet.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.longuto.androidtemplet.R;
import com.longuto.androidtemplet.util.LogUtils;

/**
 * Author by yltang3,
 * Date on 2018/8/29.
 * PS: 字体随着ProgressBar进度滚动
 */
public class ProgressBarText extends MyCustomView {

    ProgressBar mProgressBar;    // 进度条
    TextView mTextView;  // 文本内容
    int mProgressWidth; // progressBar的长度
    float mScrollDistance;  // progressBar每一个进度的长度

    int textColor;  // 进度文本的字体颜色
    float textSize;   // 进度文本的字体大小
    Drawable progressDraw;  // progressbar的背景

    public ProgressBarText(Context context) {
        super(context);
    }

    public ProgressBarText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressBarText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAttrs(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.ProgressBarText);
        textColor = typedArray.getColor(R.styleable.ProgressBarText_textColor, Color.WHITE);
        textSize = typedArray.getDimensionPixelSize(R.styleable.ProgressBarText_textSize, 12);
        progressDraw = typedArray.getDrawable(R.styleable.ProgressBarText_progressDrawable);
        typedArray.recycle();
    }

    @Override
    public void initView(LayoutInflater inflater) {
        inflater.inflate(R.layout.layout_frame_progress_bar_text, this, true);
        mProgressBar = findViewById(R.id.progress);
        mTextView = findViewById(R.id.content);
        mTextView.setTextColor(textColor);
        mTextView.setTextSize(textSize);
        if(null != progressDraw) {
            mProgressBar.setProgressDrawable(progressDraw);
        }

        // 得到progressBar控件的宽度
        ViewTreeObserver vto2 = mProgressBar.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mProgressBar.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mProgressWidth = mProgressBar.getWidth();
                mScrollDistance = (float) ((1.0 / mProgressBar.getMax()) * mProgressWidth);
                LogUtils.mvp_frame("ProgressBarText initView()==" + mProgressWidth + ",最小的平移距离为：" +
                        mScrollDistance);
            }
        });
    }

    /**
     * 更新progressBar的进度
     */
    public void updateProgress(int progress) {
        if(progress < 0 || progress > 100) {
            LogUtils.mvp_frame("progressBar进度大小只能为0 - 100");
            return;
        }

        // 设置显示，父控件也只能设置成inVisible
        if(View.VISIBLE != getVisibility()) {
            setVisibility(View.VISIBLE);
        }
        mProgressBar.setProgress(progress);
        mTextView.setText(progress + "%");

        int w = View.MeasureSpec.makeMeasureSpec(mProgressWidth, View.MeasureSpec.AT_MOST);
        mTextView.measure(w, w);
        // 得到字体的宽度
        int tvWidth = mTextView.getMeasuredWidth();
        float currentScrollDistance = mScrollDistance * progress;
        LogUtils.mvp_frame("当前progressbar需要平移的进度是：" + currentScrollDistance + ",文字的宽度为：" + tvWidth);
        //做一个平移动画的效果
        // 这里加入条件判断
        if (currentScrollDistance - tvWidth > mTextView.getPaddingLeft() && currentScrollDistance - tvWidth > 0) {
            mTextView.setTranslationX(currentScrollDistance - tvWidth);
        }
    }
}
