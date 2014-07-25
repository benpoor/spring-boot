package netty.http.snoop;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import java.net.URI;

/**
 * @author shensw
 * @version 1.0.0
 * @Description
 * @date 2014/7/24.17:36
 */
public class HttpSnoopClient {
    static final String URL = System.getProperty("url", "http://127.0.0.1:8080/");

    public static void main(String[] args){
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            URI uri = new URI(URL);
            String scheme = uri.getScheme() == null? "http" : uri.getScheme();
            String host = uri.getHost() == null?"127.0.0.1":uri.getHost();
            int port = uri.getPort();
            if(port == -1){
                if("http".equalsIgnoreCase(scheme)){
                    port = 80;
                } else if("https".equalsIgnoreCase(scheme)){
                    port = 443;
                }
            }

            if (!"http".equalsIgnoreCase(scheme) && !"https".equalsIgnoreCase(scheme)) {
                System.err.println("Only HTTP(S) is supported.");
                return;
            }

            final boolean ssl = "https".equalsIgnoreCase(scheme);
            final SslContext sslCtx;
            if(ssl){
                sslCtx = SslContext.newClientContext(InsecureTrustManagerFactory.INSTANCE);
            }else {
                sslCtx = null;
            }


            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new HttpSnoopServerInitializer(sslCtx));
            Channel ch = b.connect(host, port).sync().channel();

            HttpRequest request = new DefaultFullHttpRequest(
                    HttpVersion.HTTP_1_1, HttpMethod.GET, uri.getRawPath());

            request.headers().set(HttpHeaders.Names.HOST, host);
            request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.CLOSE);
            request.headers().set(HttpHeaders.Names.ACCEPT_ENCODING, HttpHeaders.Values.GZIP);

            request.headers().set(
                    HttpHeaders.Names.COOKIE,
                    ClientCookieEncoder.encode(
                            new DefaultCookie("my-cookie", "foo"),
                            new DefaultCookie("another-cookie", "bar")));

            ch.writeAndFlush(request);
            ch.closeFuture().sync();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
           group.shutdownGracefully();
        }
    }
}
