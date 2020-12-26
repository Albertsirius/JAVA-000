package huangzihao.homework.rpcfx.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import huangzihao.homework.rpcfx.api.RpcfxRequest;
import huangzihao.homework.rpcfx.api.RpcfxResponse;
import huangzihao.homework.rpcfx.api.RpxfxException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public final class Rpcfx {

    static {
        ParserConfig.getGlobalInstance().addAccept("huangzihao.homework.rpcfx");
    }

    public static <T> T create(final Class<T> serviceClass, final String url) {

        // 0. 替换动态代理 -> AOP
        // 使用cglib.Enhancer实现
        //return (T) Proxy.newProxyInstance(Rpcfx.class.getClassLoader(), new Class[]{serviceClass}, new RpcfxInvocationHandler(serviceClass, url));
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new RpcfxInvocationHandler(serviceClass, url));
        enhancer.setSuperclass(serviceClass);
        return (T) enhancer.create();


    }

    //RpcfxInvocationHandler的改为实现MethodInterceptor接口
    public static class /*RpcfxInvocationHandler implements InvocationHandler*/ RpcfxInvocationHandler implements MethodInterceptor {

        public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

        private final Class<?> serviceClass;
        private final String url;
        public <T> RpcfxInvocationHandler(Class<T> serviceClass, String url) {
            this.serviceClass = serviceClass;
            this.url = url;
        }

        // 可以尝试，自己去写对象序列化，二进制还是文本的，，，rpcfx是xml自定义序列化、反序列化，json: code.google.com/p/rpcfx
        // int byte char float double long bool
        // [], data class

        @Override
        public Object /*invoke(Object proxy, Method method, Object[] params)*/intercept(Object proxy, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
            RpcfxRequest request = new RpcfxRequest();
            request.setServiceClass(this.serviceClass.getName());
            request.setMethod(method.getName());
            request.setParams(params);

            RpcfxResponse response = post(request, url);

            // 这里判断response.status，处理异常
            // 考虑封装一个全局的RpcfxException
            if(!response.isStatus()){
                throw new RpxfxException(RpxfxException.PRC_INVOKE_ERROR);
            }
            return JSON.parse(response.getResult().toString());
        }

        private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
            String reqJson = JSON.toJSONString(req);
            System.out.println("req json: "+reqJson);

            // 1.可以复用client
            // 2.尝试使用httpclient或者netty client

            //huangzihao 使用java 11 自带的httpclient
            /*OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(JSONTYPE, reqJson))
                    .build();
            String respJson = client.newCall(request).execute().body().string();*/
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(reqJson))
                    .uri(URI.create(url))
                    .setHeader("Content-Type","application/json; charset=utf-8")
                    .build();
            try {
                HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                String respJson = httpResponse.body();
                System.out.println("resp json: "+respJson);
                return JSON.parseObject(respJson, RpcfxResponse.class);
            } catch (InterruptedException e) {
                RpcfxResponse response = new RpcfxResponse();
                response.setStatus(false);
                response.setException(e);
                return response;
            }
            /*System.out.println("resp json: "+respJson);
            return JSON.parseObject(respJson, RpcfxResponse.class);*/

        }
    }
}
