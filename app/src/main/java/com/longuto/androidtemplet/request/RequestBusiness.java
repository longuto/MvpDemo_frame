package com.longuto.androidtemplet.request;

import com.longuto.androidtemplet.request.retrofit.BaseRequestBusiness;
import com.longuto.androidtemplet.request.retrofit.RetrofitRequestManager;

/**
 * @author yltang3
 * @version 1.0
 * @date 2018/3/20
 */

public class RequestBusiness extends BaseRequestBusiness {
    private RequestApiInterface requesteApiInterface;
    private static RequestBusiness mBaseRequestBusiness;

    /**
     * 单例模式，得到requestbusiness的实例
     * @return
     */
    public static synchronized RequestBusiness getInstance() {
        if (mBaseRequestBusiness == null) {
            mBaseRequestBusiness = new RequestBusiness();
        }
        return mBaseRequestBusiness;
    }

    /**
     * 得到接口API，调用相应的接口
     * @return
     */
    public RequestApiInterface getAPI() {
        //构建Retrofit
        if (requesteApiInterface == null) {
            requesteApiInterface =
                    RetrofitRequestManager.getInstance().getRetrofit().create(RequestApiInterface.class);
        }
        return requesteApiInterface;
    }
}
