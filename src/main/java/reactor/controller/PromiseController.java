package reactor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.composable.Deferred;
import reactor.core.composable.Promise;
import reactor.core.composable.spec.Promises;
import reactor.event.Event;

/**
 * Created with IntelliJ IDEA.
 * User: benpoor2008
 * Date: 14-5-12
 * Time: 下午3:41
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class PromiseController {
    @Autowired
    Environment env;
    @Autowired
    @Qualifier("rootReactor")
    Reactor reactor;

    @RequestMapping("/promise")
    public Promise<ResponseEntity<String>> get() {
        Deferred<ResponseEntity<String>, Promise<ResponseEntity<String>>> d = Promises.<ResponseEntity<String>>defer(env);

        reactor.notify("test", Event.wrap(d));

        return d.compose();
    }

}

