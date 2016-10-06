package com.srtianxia.httplibs;

/**
 * Created by srtianxia on 2016/10/3.
 */

public interface Callback {
    void onResponse(Response response);

    void onFailure();
}
