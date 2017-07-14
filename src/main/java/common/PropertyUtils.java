package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * descrption: 当前文件作为读取配置文件属性的利器
 * authohr: wangji
 * date: 2017-07-14 9:12
 */
public class PropertyUtils {

    /**
     * @desction: 从URL中读取配置文件的信息
     * @author: wangji
     * @date: 2017/7/14
     * @param: [url]
     * @return: java.util.Properties
     */
    public static Properties loadProperties(final URL url) throws IOException{

        if(url == null){
            throw new NullPointerException("url");
        }
        return loadProperties(url.openStream());
    }



    /**
     * @desction: 从文件中读取配置信息
     * @author: wangji
     * @date: 2017/7/14
     * @param: [file]
     * @return: java.util.Properties
     * @throws NullPointerException,IOException
     */
    public static Properties loadProperties(final File file) throws IOException{

        if(file == null){
            throw  new NullPointerException("file");
        }
        return loadProperties(new FileInputStream(file));
    }



    /**
     * @desction: 从流中读取配置的属性
     * @author: wangji
     * @date: 2017/7/14
     * @param: [is]
     * @return: java.util.Properties
     */
    public static Properties loadProperties(final InputStream is) throws  IOException {

        InputStream in = is;
        try {
            final Properties properties = new Properties();
            if (in != null) {
                properties.load(in);
                in.close();
                in = null;//gc
            }
            return properties;
        } finally {
            IOUtils.close(in);
        }
    }
}
