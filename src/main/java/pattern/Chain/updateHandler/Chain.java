package pattern.Chain.updateHandler;

import java.util.List;

/**
 * descrption: 责任链模式的升级处理，不要每次都去处理后继者
 * http://blog.csdn.net/justloveyou_/article/details/68491121
 * 这个博客自己手动写责任链模式有点类似
 * authohr: wangji
 * date: 2017-09-23 9:54
 */
public class Chain {
    private List<ChainHandler> handlers;

    private int index = 0;

    public Chain(List<ChainHandler> handlers) {
        this.handlers = handlers;
    }

    public void proceed(){
        if(index >= handlers.size()){
            return ;
        }
        handlers.get(index++).execute(this);
    }
}
