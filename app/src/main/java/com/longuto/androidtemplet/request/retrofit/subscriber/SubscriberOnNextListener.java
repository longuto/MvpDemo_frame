package com.longuto.androidtemplet.request.retrofit.subscriber;

import com.longuto.androidtemplet.util.UiUtils;

/**
 * Created by yltang3 on 16/3/10.
 */
public abstract class SubscriberOnNextListener<T> {
    protected abstract void onNext(T t);

    protected void onError(String message) {
        UiUtils.showFancyToast(message);
    }
}
