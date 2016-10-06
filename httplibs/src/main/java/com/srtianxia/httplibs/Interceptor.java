package com.srtianxia.httplibs;

/**
 * Created by srtianxia on 2016/10/3.
 */

public interface Interceptor {
    Response intercept(Chain chain);

    interface Chain {
        Response proceed(Request request);

        Request request();
    }
}
