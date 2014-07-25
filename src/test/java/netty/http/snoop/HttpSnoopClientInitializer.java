package netty.http.snoop;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.ssl.SslContext;


/**
 * @author shensw
 * @version 1.0.0
 * @Description
 * @date 2014/7/25.11:09
 */
public class HttpSnoopClientInitializer extends ChannelInitializer<SocketChannel> {
    private final SslContext sslCtx;
    public  HttpSnoopClientInitializer(SslContext sslCtx){
        this.sslCtx = sslCtx;
    }
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        if(sslCtx != null){
            p.addLast(sslCtx.newHandler(ch.alloc()));
        }
        p.addLast(new HttpClientCodec());

        p.addLast(new HttpContentDecompressor());

        p.addLast(new HttpSnoopClientHandler());
    }
}
