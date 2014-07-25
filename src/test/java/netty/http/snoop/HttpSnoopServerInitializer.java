package netty.http.snoop;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;


/**
 * @author shensw
 * @version 1.0.0
 * @Description
 * @date 2014/7/25.10:53
 */
public class HttpSnoopServerInitializer extends ChannelInitializer<SocketChannel> {
    private final SslContext sslContext;

    public HttpSnoopServerInitializer(SslContext sslContext){
        this.sslContext = sslContext;
    }
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        if(sslContext != null){
            p.addLast(sslContext.newHandler(ch.alloc()));
        }
        p.addLast(new HttpRequestDecoder());
        p.addLast(new HttpResponseEncoder());
        p.addLast(new HttpSnoopServerHandler());
    }
}
