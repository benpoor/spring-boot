package reactor;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import reactor.core.composable.Promise;
import reactor.function.Consumer;

/**
 * Created with IntelliJ IDEA.
 * User: benpoor2008
 * Date: 14-5-12
 * Time: 下午3:54
 * To change this template use File | Settings | File Templates.
 */
public class PromiseHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return Promise.class.isAssignableFrom(returnType.getParameterType());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handleReturnValue(Object returnValue,
                                  final MethodParameter returnType,
                                  final ModelAndViewContainer mavContainer,
                                  final NativeWebRequest webRequest) throws Exception {
        final DeferredResult<Object> deferredResult = new DeferredResult<Object>();
        ((Promise)returnValue)
                .onSuccess(new Consumer() {
                    @Override
                    public void accept(Object o) {
                        deferredResult.setResult(o);
                    }
                })
                .onError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) {
                        deferredResult.setErrorResult(t);
                    }
                });

        WebAsyncUtils.getAsyncManager(webRequest)
                .startDeferredResultProcessing(deferredResult, mavContainer);
    }

}
