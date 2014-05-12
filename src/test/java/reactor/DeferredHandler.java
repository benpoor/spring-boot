package reactor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.composable.Deferred;
import reactor.core.composable.Promise;
import reactor.spring.annotation.Consumer;
import reactor.spring.annotation.Selector;

/**
 * Created with IntelliJ IDEA.
 * User: benpoor2008
 * Date: 14-5-12
 * Time: 下午3:48
 * To change this template use File | Settings | File Templates.
 */
@Consumer
public class DeferredHandler {
    @Selector(value = "test", reactor = "@reactor")
    public void test(Deferred<ResponseEntity<String>, Promise<ResponseEntity<String>>> d) {
        d.accept(new ResponseEntity<String>("Hello World!", HttpStatus.OK));
    }
}
