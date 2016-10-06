package com.srtianxia.httplibs;

/**
 * Created by srtianxia on 2016/10/3.
 */

public class Request {
    private String mUrl;

    public Request(Builder builder) {
        this.mUrl = builder.mUrl;
    }

    public String url() {
        return mUrl;
    }

    public static class Builder {
        private String mUrl;


        public Builder() {

        }

        public Builder url(String url) {
            this.mUrl = url;
            return this;
        }

        public Request build() {
            return new Request(this);
        }
    }
}
