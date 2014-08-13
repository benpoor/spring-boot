package netty.http.snoop;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author shensw
 * @version 1.0.0
 * @Description
 * @date 2014/7/25.11:10
 */
public class HttpSnoopClientHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if(msg instanceof HttpResponse){
            HttpResponse response = (HttpResponse) msg;
            System.err.println("STATUS: " + response.getStatus());
            System.err.println("VERSION: " + response.getProtocolVersion());
            System.err.println();
            if(!response.headers().isEmpty()){
                for(String name :response.headers().names()){
                    for(String value:response.headers().getAll(name)){
                        System.err.println("HEADER: " + name + " = " + value);
                    }
                }
                System.err.println();
            }
            if(HttpHeaders.isTransferEncodingChunked(response)){
                System.err.println("CHUNKED CONTENT {");
            } else {
                System.err.println("CONTENT {");
            }
        }

        if(msg instanceof HttpContent){
            HttpContent content = (HttpContent)msg;
            System.err.print(content.content().toString(CharsetUtil.UTF_8));
            System.err.flush();

            if(content instanceof LastHttpContent){
                System.err.println("} END OF CONTENT");
                ctx.close();
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
