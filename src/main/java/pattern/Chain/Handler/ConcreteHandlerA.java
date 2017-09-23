package pattern.Chain.Handler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteHandlerA extends Handler {
    @Override
    protected void handleProcess() {
        log.info("handle by ConcreteHandlerA");
    }
}
