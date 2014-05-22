package reactor.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.spring.context.config.EnableReactor;

/**
 * Created with IntelliJ IDEA.
 * User: benpoor2008
 * Date: 14-5-8
 * Time: 上午10:51
 * To change this template use File | Settings | File Templates.
 */

@EnableReactor

@Service
public class ReactorConfig {

    @Bean(name="rootReactor")
    public Reactor rootReactor(Environment environment){
        return Reactors.reactor().env(environment).dispatcher(Environment.THREAD_POOL).get();
    }

    @Bean(name="reportReactor")
    public Reactor reportReactor(Environment environment){
        return Reactors.reactor().env(environment).get();
    }
}
