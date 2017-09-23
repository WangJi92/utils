package pattern.Chain.Handler;

/**
 * descrption: 创建和调用责任链
 * authohr: wangji
 * date: 2017-09-23 9:45
 */
public class Client {
    public static void main(String[] args) {
        ConcreteHandlerA ConcreteHandlerA = new ConcreteHandlerA();
        ConcreteHandlerB ConcreteHandlerB = new ConcreteHandlerB();
        ConcreteHandlerC ConcreteHandlerC = new ConcreteHandlerC();
        //A->B-C
        ConcreteHandlerA.setSucessor(ConcreteHandlerB);
        ConcreteHandlerB.setSucessor(ConcreteHandlerC);
        ConcreteHandlerA.execute();
        //这里比较的麻烦，每次都是需要依次的处理一个个后继

    }
}
