package pattern.Chain.updateHandler;

/**
 * descrption:通过传入Chain中保存了ChainHanler的List，然后遍历，通过下标标识。由于有Chain的对象，可以调用其方法
 * authohr: wangji
 * date: 2017-09-23 9:55
 */
public abstract class ChainHandler {

    public void execute(Chain chain){
        handleProcess();
        chain.proceed();
    }

    protected abstract void handleProcess();
}