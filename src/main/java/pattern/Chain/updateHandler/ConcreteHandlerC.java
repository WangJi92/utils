package pattern.Chain.updateHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * descrption:
 * authohr: wangji
 * date: 2017-09-23 10:30
 */
@Slf4j
public class ConcreteHandlerC extends ChainHandler {
    protected void handleProcess() {
    log.info("handle by ConcreteHandlerC");
    }
}
