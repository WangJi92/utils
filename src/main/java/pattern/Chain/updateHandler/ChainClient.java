package pattern.Chain.updateHandler;

import java.util.Arrays;
import java.util.List;

/**
 * descrption:升级版Chain模式，不需要为每一个具体的实现确定一个下一继承者
 * authohr: wangji
 * date: 2017-09-23 10:32
 */
public class ChainClient {
    public static void main(String[] args) {
        //这里的处理也是一样的，只是不需要为每一个设置下一继承者，在确定List的时候就通过自身的下标确定了。
        List<ChainHandler> handlers = Arrays.asList(
                new ConcreteHandlerA(),
                new ConcreteHandlerB(),
                new ConcreteHandlerC()
        );
        Chain chain = new Chain(handlers);
        chain.proceed();
    }
}
