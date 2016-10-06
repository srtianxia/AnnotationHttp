package com.srtianxia.httplibs;

/**
 * Created by srtianxia on 2016/10/3.
 */

public class Response {
    private String response;

    public Response(String response) {
        this.response = response;
    }

    public String string() {
        return response;
    }
}
