package com.srtianxia.httplibs;

/**
 * Created by srtianxia on 2016/10/3.
 */

public interface Call {

    void enqueue(Callback responseCallback);

    interface Factory {
        Call newCall(Request request);
    }
}
