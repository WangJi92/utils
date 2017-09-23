package pattern.Chain.updateHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * descrption:
 * authohr: wangji
 * date: 2017-09-23 10:29
 */
@Slf4j
public class ConcreteHandlerB extends ChainHandler {
    protected void handleProcess() {
        log.info("handle by ConcreteHandlerB");
    }
}
