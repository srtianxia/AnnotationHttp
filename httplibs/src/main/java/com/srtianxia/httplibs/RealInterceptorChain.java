package com.srtianxia.httplibs;

import java.util.List;

/**
 * Created by srtianxia on 2016/10/3.
 */

public class RealInterceptorChain implements Interceptor.Chain {
    private final List<Interceptor> mInterceptors;
    private final int mIndex;
    private final Request request;


    public RealInterceptorChain(List<Interceptor> interceptors, int index, Request request) {
        this.mInterceptors = interceptors;
        this.mIndex = index;
        this.request = request;
    }


    @Override public Response proceed(Request request) {
        RealInterceptorChain next = new RealInterceptorChain(mInterceptors, mIndex + 1, request);
        Interceptor interceptor = mInterceptors.get(mIndex);
        Response response = interceptor.intercept(next);
        return response;
    }


    @Override public Request request() {
        return request;
    }
}
