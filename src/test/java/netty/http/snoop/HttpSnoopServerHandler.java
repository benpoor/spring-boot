package netty.http.snoop;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.*;

/**
 * @author shensw
 * @version 1.0.0
 * @Description
 * @date 2014/7/24.17:51
 */
public class HttpSnoopServerHandler extends SimpleChannelInboundHandler<Object> {

    private HttpRequest request;
    private StringBuffer buf = new StringBuffer();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof HttpRequest){
            HttpRequest request = this.request = (HttpRequest)msg;
            if(HttpHeaders.is100ContinueExpected(request)){
                send100Continue(ctx);
            }
            buf.setLength(0);
            buf.append("welcome to www server\r\n");

            buf.append("VERSION").append(request.getProtocolVersion()).append("\r\n");
            buf.append("HOSTNAME").append(request).append("\r\n");
            buf.append("REQUEST_URI").append(request.getUri()).append("\r\n");

            HttpHeaders headers = request.headers();
            if(!headers.isEmpty()){
                for(Map.Entry<String, String> m:headers){
                    String key = m.getKey();
                    String value = m.getValue();
                    buf.append("HEADER: ").append(key+"=").append(value).append("\r\n");
                }
                buf.append("\r\n");
            }

            QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.getUri());
            Map<String,List<String>> parameters = queryStringDecoder.parameters();
            if(!parameters.isEmpty()){
                for(Map.Entry<String, List<String>> m:parameters.entrySet()){
                    String key = m.getKey();
                    List<String> mValue = m.getValue();
                    for(String value:mValue){
                        buf.append("PARAM:").append(key).append("=").append(value).append("\r\n");
                    }
                }
                buf.append("\r\n");
            }
            appendDecoderResult(buf, request);
        }

        if(msg instanceof HttpContent){
            HttpContent httpContent = (HttpContent)msg;

            ByteBuf byteBuf = httpContent.content();
            if(byteBuf.isReadable()){
                buf.append("CONTENT: ");
                buf.append(byteBuf.toString(CharsetUtil.UTF_8));
                buf.append("\r\n");
                appendDecoderResult(buf, request);
            }

            if(msg instanceof LastHttpContent){
                buf.append("end of CONTENT \r\n");

                LastHttpContent lastHttpContent = (LastHttpContent)msg;
                if(!lastHttpContent.trailingHeaders().isEmpty()){
                    buf.append("\r\n");
                    for(String name : lastHttpContent.trailingHeaders().names()){
                        for(String value:lastHttpContent.trailingHeaders().getAll(name)){
                            buf.append("TRAILING HEADER: ");
                            buf.append(name).append("=").append(value).append("\r\n");
                        }
                    }
                    buf.append("\r\n");
                }
                if(writeResponse(request, ctx)){
                    ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
                }
            }
        }

    }

    private boolean writeResponse(HttpObject httpObject, ChannelHandlerContext ctx){

        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, httpObject.getDecoderResult().isSuccess()? OK : BAD_REQUEST,
                Unpooled.copiedBuffer(buf.toString(), CharsetUtil.UTF_8));
        response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");

        response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
        response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);

        String cookieString = request.headers().get(COOKIE);
        if (cookieString != null) {
            Set<Cookie> cookies = CookieDecoder.decode(cookieString);
            if (!cookies.isEmpty()) {
                // Reset the cookies if necessary.
                for (Cookie cookie: cookies) {
                    response.headers().add(SET_COOKIE, ServerCookieEncoder.encode(cookie));
                }
            }
        } else {
            // Browser sent no cookie.  Add some.
             response.headers().add(SET_COOKIE, ServerCookieEncoder.encode("key1", "value1"));
            response.headers().add(SET_COOKIE, ServerCookieEncoder.encode("key2", "value2"));
        }
        // Write the response.
        ctx.write(response);
        return true;
    }

    private static void appendDecoderResult(StringBuffer buf, HttpObject o){
        DecoderResult decoderResult = o.getDecoderResult();
        if(decoderResult.isSuccess()){
            return;
        }
        buf.append("..with decoder failure");
        buf.append(decoderResult.cause());
        buf.append("\r\n");
    }

    private static void send100Continue(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, CONTINUE);
        ctx.write(response);
    }
}
