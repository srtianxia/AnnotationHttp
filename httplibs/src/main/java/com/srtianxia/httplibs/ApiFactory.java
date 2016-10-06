package com.srtianxia.httplibs;

/**
 * Created by srtianxia on 2016/10/3.
 */

public class ApiFactory {
    private static final String PROXY_NAME = "_Proxy";


    public static <T> T create(Class<T> clazz) {
        String name = clazz.getSimpleName() + PROXY_NAME;
        T obj = null;
        try {
            obj = (T) Class.forName(name).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) obj;
    }
}
