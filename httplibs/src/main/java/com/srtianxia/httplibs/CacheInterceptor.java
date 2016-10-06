package com.srtianxia.httplibs;

/**
 * Created by srtianxia on 2016/10/3.
 * 缓存，没有完成逻辑，直接向责任链的下层传递
 */

public class CacheInterceptor implements Interceptor {

    @Override public Response intercept(Chain chain) {
        return chain.proceed(chain.request());
    }
}
