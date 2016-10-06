package com.srtianxia.httplibs;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by srtianxia on 2016/10/3.
 */

final class RealCall implements Call {
    private HttpClient mHttpClient;
    private Request mOriginalRequest;


    protected RealCall(HttpClient httpClient, Request request) {
        this.mHttpClient = httpClient;
        this.mOriginalRequest = request;
    }


    @Override public void enqueue(Callback responseCallback) {
        mHttpClient.dispatcher().enqueue(new AsyncCall(responseCallback));
    }


    final class AsyncCall implements Runnable {
        private final Callback responseCallback;

        public AsyncCall(Callback responseCallback) {
            this.responseCallback = responseCallback;
        }

        @Override public void run() {
            Response response = getResponseWithInterceptorChain();
            responseCallback.onResponse(response);
        }
    }

    private Response getResponseWithInterceptorChain() {
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new CacheInterceptor());
        interceptors.add(new NetInterceptor());
        Interceptor.Chain chain = new RealInterceptorChain(interceptors, 0, mOriginalRequest);
        return chain.proceed(mOriginalRequest);
    }
}
