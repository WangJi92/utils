package common;

import java.io.*;

/**
 * descrption: IO工具类
 * authohr: wangji
 * date: 2017-07-14 9:22
 */
public class IOUtils {

     //默认最大的复制
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 16;

    private static final String  DEAULT_ENCODING ="utf-8";

    /**
     * @desction: 使用默认的缓冲区大小设置，从inputsream 到outputstream(复制字节流)
     * @author: wangji
     * @date: 2017/7/14
     * @param: [input, output]
     * @return: void
     */
    public static void copy(final InputStream input, final OutputStream output)throws IOException{
        copy(input,output,DEFAULT_BUFFER_SIZE);
    }

    /**
     * @desction: 复制一个流，从inputStream 到 outputStream,可以设置缓冲区的大小（复制字节流)
     * @author: wangji
     * @date: 2017/7/14
     * @param: [input, output, bufferSize]
     * @return: void
     */
    public static void copy(final InputStream input,final OutputStream output,int bufferSize) throws IOException{

        int n =0;
        final byte[] buffer = new byte[bufferSize];
        while(0 < ( n = input.read(buffer))){
            output.write(buffer,0,n);
        }
        output.flush();
    }
    /**
     * @desction: 复制字符流从Reader到Writer默认缓冲区大小（字符流）
     * @author: wangji
     * @date: 2017/7/14
     * @param: [reader, writer]
     * @return: void
     */
    public static void copy(final Reader reader, final Writer writer)throws IOException{

        copy(reader,writer,DEFAULT_BUFFER_SIZE);
    }
    /**
     * @desction: 复制字符流从Reader到Writer 自定义缓冲区大小（字符流）
     * @author: wangji
     * @date: 2017/7/14
     * @param: [reader, writer, bufferSize]
     * @return: void
     */
    public static void copy(final Reader reader,final Writer writer,int bufferSize) throws IOException{

        char[] buffer = new char[bufferSize];
        int n=0;
        while(0 < (n = reader.read(buffer))){
            writer.write(buffer,0,n);
        }
        writer.flush();
    }

    /**
     * @desction: InputStream  ==> Writer 进行复制，中间有装饰InputSreamReader
     * @author: wangji
     * @date: 2017/7/14
     * @param: [input, writer]
     * @return: void
     */
    public static  void copy(final InputStream input,final Writer writer) throws IOException{

        copy(input,writer,DEFAULT_BUFFER_SIZE);
    }
    /**
     * @desction: InputStram => InputSreamReader => Writer 字节流byte ==>char,使用自定义缓冲区的大小 使用默认的编码方式UTF_8
     * @author: wangji
     * @date: 2017/7/14
     * @param: [input, writer, bufferSize]
     * @return: void
     */
    public static void copy( final InputStream input, final Writer writer, final int bufferSize ) throws IOException{

        final InputStreamReader inputStreamReader = new InputStreamReader(input,DEAULT_ENCODING);//从inputSream => Reader
        copy(inputStreamReader,writer,bufferSize);
    }
    public static void copy( final InputStream input, final Writer writer, final int bufferSize,String enconding) throws IOException{

        final InputStreamReader inputStreamReader = new InputStreamReader(input,enconding);//从inputSream => Reader
        copy(inputStreamReader,writer,bufferSize);
    }

    /**
     * @desction: 字节流转换为字符流，toString（），String属字符范畴，构建一个Writer和一个Reader之间关联起来
     * @author: wangji
     * @date: 2017/7/14
     * @param: [input, buffSize]
     * @return: java.lang.String
     */
    public static String  toString(final InputStream input,int buffSize) throws IOException{

        final StringWriter writer = new StringWriter();//构建字符流，也可以采用其他的Writer的子类
        final InputStreamReader inputStreamReader = new InputStreamReader(input,DEAULT_ENCODING);//字节流转换为字符流 利用默认的编码方式
        copy(inputStreamReader,writer);//然后Reader 转换为Wirter（上面异步可以利用之前的）
        return writer.toString();
    }
    public static String  toString(final InputStream input) throws IOException{
        return toString(input,DEFAULT_BUFFER_SIZE);
    }

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
