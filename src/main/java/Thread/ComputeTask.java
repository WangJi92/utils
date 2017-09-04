package Thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * descrption: 实现Callable接口将线程执行后的结果返回
 * authohr: wangji
 * date: 2017-09-01 15:21
 */
@Slf4j
public class ComputeTask implements Callable<Integer> {
    private Integer result = 0;
    private String taskName = "";

    public ComputeTask(Integer iniResult, String taskName){
        result = iniResult;
        this.taskName = taskName;
    }
    public Integer call() throws Exception {
          return todoWork();
    }
    public Integer todoWork() {
        log.info("子线程计算任务: "+taskName+" 开始执行!");
        for (int i = 0; i < 100; i++) {
            result =i+result;
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
           log.error("线程被中断",e);
        }
        log.info("子线程计算任务: "+taskName+" 执行完成! 结果："+result);
        return result;
    }
    public String getTaskName(){
        return this.taskName;
    }
}
