package com.srtianxia.httplibs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by srtianxia on 2016/10/3.
 */

public class NetInterceptor implements Interceptor {
    private HttpURLConnection mHttpURLConnection;
    private static final String REQUEST_METHOD_GET = "GET";

    @Override public Response intercept(Chain chain) {
        try {
            mHttpURLConnection = (HttpURLConnection) new URL(chain.request().url()).openConnection();
            mHttpURLConnection.setRequestMethod(REQUEST_METHOD_GET);
            mHttpURLConnection.setReadTimeout(5000);
            mHttpURLConnection.setConnectTimeout(10000);

            InputStream is = mHttpURLConnection.getInputStream();
            String response = getStringFromInputStream(is);
            return new Response(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getStringFromInputStream(InputStream is)
        throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        is.close();
        String state = os.toString();
        os.close();
        return state;
    }
}
