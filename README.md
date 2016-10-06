# AnnotationHttp
compile time http request library

编译时注解的网络请求库，现在只有一个雏形，因为最近看了okhttp的源码，所以请求是仿照okhttp的**责任链模式**封装的`HttpURLConnection`，负责缓存的链节暂时是一个空实现直接向下传递。至于编译时注解可以看[这篇文章](http://srtianxia.github.io/2016/10/02/%E6%B3%A8%E8%A7%A3/)
