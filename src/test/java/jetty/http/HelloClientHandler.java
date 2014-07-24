package jetty.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author shensw
 * @version 1.0.0
 * @Description
 * @date 2014/7/24.14:22
 */
public class HelloClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("server say: "+msg);
    }
}
