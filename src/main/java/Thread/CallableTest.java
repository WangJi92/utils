package Thread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * descrption: 测试执行线程的返回值
 * authohr: wangji
 * date: 2017-09-01 15:27
 */
@Slf4j
public class CallableTest {

    public static void main(String[] args) {
        //1. create a thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //2 create a list of Callable
        List<Future<Integer>> returnFutureList = new ArrayList<Future<Integer>>();
        for(int i=0;i<20;i++){
            //ComputeTask implement callbale interface
             ComputeTask callableItem = new ComputeTask(30+i,"work"+i);
             Future<Integer> future = executorService.submit(callableItem);
             returnFutureList.add(future);
        }
        log.info("提交完了任务，可以把其他的事情做完了，再来轮询获取线程返回的结果的信息");
        for(int i=0;i<returnFutureList.size();i++){
            Future<Integer> future =null;
            try {
                 future = returnFutureList.get(i);
                 //依次阻塞一直等待获取到结果的信息
                 Integer taskReturnValue = future.get();
            } catch (InterruptedException e) {
                log.error("当前线程中断",e);
                Thread.currentThread().interrupt();//重新声明线程中断了
                if(future !=null){
                    future.cancel(true);
                }

            } catch (ExecutionException e) {
                log.error("执行线程异常",e);
                if(future !=null){
                    future.cancel(true);
                }
            }
        }

    }
}
