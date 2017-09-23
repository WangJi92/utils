package pattern.Chain.Handler;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteHandlerC extends Handler {

    protected void handleProcess() {
        log.info("handle by ConcreteHandlerC");
    }
}
