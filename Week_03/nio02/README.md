# 作业说明
## 整合你上次的httpClient/okhttp
1. 使用JDK11自带的httpClient，pom文件的java版本改为11。
2. 代码io.github.kimmking.gateway.outbound.myhttpClient下面的MyHttpOutboundHandler
3. 还没学会使用线程池，代码没有像老师的样例代码那样使用线程池，采用单线程模式。后续优化为使用线程池

## 实现过滤器
1. 代码在filter下面的MyHttpRequestFilter，添加头key为nio，值为huangzihao
2. filter的使用耦合在inbound和outbound的代码里面，后续优化解耦
