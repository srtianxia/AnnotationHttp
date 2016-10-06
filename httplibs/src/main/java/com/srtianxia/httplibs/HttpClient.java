package com.srtianxia.httplibs;

/**
 * Created by srtianxia on 2016/10/3.
 */

public class HttpClient implements Call.Factory {
    private Dispatcher mDispatcher;


    public HttpClient() {
        this(new Builder());
    }


    protected Dispatcher dispatcher() {
        return mDispatcher;
    }


    public HttpClient(Builder builder) {
        this.mDispatcher = builder.dispatcher;
    }


    @Override public Call newCall(Request request) {
        return new RealCall(this, request);
    }


    public static final class Builder {
        Dispatcher dispatcher;

        public Builder() {
            dispatcher = new Dispatcher();
        }
    }
}
