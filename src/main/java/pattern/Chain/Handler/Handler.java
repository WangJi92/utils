package pattern.Chain.Handler;

/**
 * descrption: 创建责任链模式
 * authohr: wangji
 * date: 2017-09-23 9:36
 */
public abstract  class Handler {

    private Handler sucessor;//接班人

    public void setSucessor(Handler sucessor) {
        this.sucessor = sucessor;
    }
    /**
     * @desction: 1.首先执行自身的；2.然后调用责任链中的下一个继承者
     * @author: wangji
     * @date: 2017/9/23
     * @param:
     * @return:
     */
    public void execute(){
        handleProcess();
        if(sucessor != null){
            sucessor.execute();
        }
    }
    protected abstract void handleProcess();
}
