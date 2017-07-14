package common;

import java.io.IOException;
import java.io.InputStream;

/**
 * descrption: IO工具类
 * authohr: wangji
 * date: 2017-07-14 9:22
 */
public class IOUtils {


    /**
     * @desction: 关闭异常，关闭失败不做任何处理
     * @author: wangji
     * @date: 2017/7/14
     * @param: [in]
     * @return: void
     */
    public  static  void close (InputStream in){
        if(in == null){
            return;
        }
        try {
            in.close();
        } catch (IOException e) {
           //关闭异常不做任务处理
        }
    }
}
