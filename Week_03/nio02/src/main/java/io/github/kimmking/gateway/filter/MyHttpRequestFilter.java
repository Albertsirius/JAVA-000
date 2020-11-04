package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * <p>实现HttpRequestFilter接口，请求头添加nio：huangzihao
 *
 * @author AlbertSirius
 * @since 2020/11/4
 */
public class MyHttpRequestFilter implements HttpRequestFilter{

    public final static String KEY = "nio";

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set(KEY, "huangzihao");
    }
}
