package com.longuto.androidtemplet.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.longuto.androidtemplet.R;

import butterknife.BindView;

/**
 * Author by yltang,
 * Date on 2018/10/13.
 * PS: RecyclerView含有下拉刷新，上拉加载更多的基类
 */
public class BaseRecyclerSupportActivity extends BaseSupportActivity {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefresh;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycler;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRecyerView();
    }

    // 初始化Recy和Refresh
    private void initRecyerView() {

    }
}
