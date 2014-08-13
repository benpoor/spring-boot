package jetty.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;

/**
 * @author shensw
 * @version 1.0.0
 * @Description
 * @date 2014/7/24.11:32
 */
public class HelloServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext context) throws Exception{
        System.out.println("remoteServer: "+context.channel().remoteAddress()+" active");
        context.writeAndFlush("welcome to "+ InetAddress.getLocalHost().getHostName()+" service!\n");
        super.channelActive(context);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 收到消息直接打印输出
        System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);

        // 返回客户端消息 - 我已经接收到了你的消息
        ctx.writeAndFlush("Received your message !\n");
    }
}
