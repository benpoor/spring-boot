package reactor.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.composable.Composable;
import reactor.core.composable.Deferred;
import reactor.core.composable.Promise;
import reactor.function.Function;
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
    @Selector(value = "test", reactor = "@rootReactor")
    public void test(Deferred<ResponseEntity<String>, Promise<ResponseEntity<String>>> d) {
        System.out.println("************调用***********"+d);
        try{
            System.out.println("************sleep 10秒***********");
            Thread.sleep(10000);
        } catch (Exception e){
            e.printStackTrace();
        }
        d.accept(new ResponseEntity<String>("Hello World!", HttpStatus.OK));
        System.out.println("************结束***********"+d);
    }
}
