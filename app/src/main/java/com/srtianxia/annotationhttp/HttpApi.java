package com.srtianxia.annotationhttp;

import com.srtianxia.GET;
import com.srtianxia.httplibs.Callback;

/**
 * Created by srtianxia on 2016/10/4.
 */

public interface HttpApi {
    @GET("http://www.jianshu.com/")
    void jianshu(Callback callback);

    @GET("http://www.zhihu.com/")
    void zhihu(Callback callback);
}
