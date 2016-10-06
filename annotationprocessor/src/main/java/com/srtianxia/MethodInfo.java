package com.srtianxia;

/**
 * Created by srtianxia on 2016/10/4.
 */
public class MethodInfo {
    private String mUrl;
    private String mMethodName;


    public MethodInfo(String mUrl, String mMethodName) {
        this.mUrl = mUrl;
        this.mMethodName = mMethodName;
    }


    public String getUrl() {
        return mUrl;
    }


    public String getMethodName() {
        return mMethodName;
    }
}
