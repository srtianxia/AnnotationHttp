# AnnotationHttp
compile time http request library

编译时注解的网络请求库，现在只完成了雏形，仅供学习，因为最近看了`okhttp`的源码，所以请求是仿照`okhttp`的**责任链模式**封装的`HttpURLConnection`，负责缓存的链节暂时是一个空实现直接向下传递。至于编译时注解可以看[这篇文章](http://srtianxia.github.io/2016/10/02/%E6%B3%A8%E8%A7%A3/)


## 使用方式
#### 定义接口
```java
public interface HttpApi {
    @GET("http://www.jianshu.com/")
    void jianshu(Callback callback);

    @GET("http://www.zhihu.com/")
    void zhihu(Callback callback);
}
```

#### 使用
```java
private HttpApi mApi = ApiFactory.create(HttpApi.class);

mApi.zhihu(new Callback() {
                    @Override public void onResponse(Response response) {
                        log(response.string());
                    }


                    @Override public void onFailure() {

                    }
                });
```

目前只提供了string类型的数据格式，自行解析吧。

#### apt生成的代码样式

```java

// Generated code from AnnotationHttp. Do not modify!

import com.srtianxia.httplibs.Callback;
import com.srtianxia.httplibs.HttpClient;
import com.srtianxia.httplibs.Request;

public class HttpApi_Proxy implements com.srtianxia.annotationhttp.HttpApi {
    private HttpClient mClient = new HttpClient();


    @Override public void jianshu(Callback callback) {
        mClient.newCall(new Request.Builder().url("http://www.jianshu.com/").build())
            .enqueue(callback);
    }


    @Override public void zhihu(Callback callback) {
        mClient.newCall(new Request.Builder().url("http://www.zhihu.com/").build())
            .enqueue(callback);
    }

}
```