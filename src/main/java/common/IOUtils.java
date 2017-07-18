package common;

import java.io.*;
import java.nio.channels.Channel;

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


  //InputStream => byte[]
    /**
     * @desction: 将inputStream转换为 byte[]数组，借助于ByteArrayOutputSream，然后InputStram 向outputSream复制
     * @author: wangji
     * @date: 2017/7/17
     * @param: [input]
     * @return: byte[]
     */
    public static byte[] toByteArrays(final  InputStream input)throws IOException{

       return toByteArrays(input,DEFAULT_BUFFER_SIZE);
    }
    public static byte[] toByteArrays(final  InputStream input,int bufferSize) throws IOException{
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        //这里的ByteArrayOutputSream 和ByteArrayInputSream 就是byte[] 和流之间的装饰类
        //类似的还有CharArrayReader 和 CharArrayWriter 只不过是一个是对于字节流一个是对于字符流之间的处理
        //将输入流复制到输出流然后在处理，将内部的byte[]数据输出
        copy(input,output,bufferSize);
        return output.toByteArray();
    }

   //Reader  ==> * (inputSream,String)


    //reader = > inputStream

    public static  void copy(final Reader reader,final OutputStream output)throws IOException{
        //字符流向字节流转换
        copy(reader,output,DEFAULT_BUFFER_SIZE);
    }
    public static  void copy(final Reader reader,final OutputStream output,int bufferSize)throws IOException{
        //字符流向字节流转换,需要借助中间转换，将outputSream 转为 outputSreamWriter
        final OutputStreamWriter outputStreamWriter  = new OutputStreamWriter(output,DEAULT_ENCODING);
        copy(reader,outputStreamWriter);
        outputStreamWriter.flush();

    }

    //reader（Char[]） =>String
    public static String toString(Reader reader) throws IOException {
        return toString(reader, DEFAULT_BUFFER_SIZE);
    }

    //reader =>StringWriter(复制) =>String
    public static String toString(Reader reader, int bufferSize) throws IOException {
        final StringWriter writer = new StringWriter();//和其他的有异曲同工值妙
        //通过中间进行转换
        copy(reader, writer, bufferSize);
        return writer.toString();
    }

    //reader => byte[](字符流转换为字节流)
    public  static byte[] toByteArrays(final Reader reader)throws IOException{
        return toByteArrays(reader,DEFAULT_BUFFER_SIZE);
    }
    public  static byte[] toByteArrays(final Reader reader,int bufferSize)throws IOException{
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();//生成字节流 和fileInputStream 意义类似
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
        //Reader -> OutPutSreamWriter(ByteArrayOutputSream);
        copy(reader,outputStreamWriter,bufferSize);
        outputStreamWriter.flush();
        return byteArrayOutputStream.toByteArray();
    }


    //String === *

    //String ===>Outputstream
    public static void copy(final String  inputString,final OutputStream output) throws IOException{
      copy(inputString,output);
    }
    public static void copy(final String  inputString,final OutputStream output,int bufferSize) throws IOException{
        StringReader reader = new StringReader(inputString);
        //包装一下子,String =>Reader
        //包装一下子 Outputstream => OutputStreamWriter
        //虽然包装了，但是最后的实际写入还是在OutputSream中的
        final  OutputStreamWriter outputStreamWriter = new OutputStreamWriter(output);
        copy(reader,outputStreamWriter,bufferSize);
        outputStreamWriter.flush();
    }


    //String =>byte[]

    public static byte[] toByteArrays(final String string) throws IOException{
        return string.getBytes();
    }
    public static byte[] toByteArrays(final String string,int bufferSize) throws IOException {
        final StringReader stringReader = new StringReader(string);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
        copy(stringReader,outputStreamWriter,bufferSize);
        return byteArrayOutputStream.toByteArray();
    }

    //byte[] => *

    //byte ->Writer

    /**
     * 1.byte首先转换为字节流
     * 2.字节流转为字符流Reader
     * 3.字符流Reader复制到Writer
     * @param inputByteArray
     * @param writer
     * @throws IOException
     */
    public static void copy(final byte[] inputByteArray, final Writer writer) throws IOException {
        copy(inputByteArray, writer, DEFAULT_BUFFER_SIZE);
    }

    public static void copy(final byte[] inputByteArray, final Writer writer, int bufferSize) throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputByteArray);
        final InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream);
        copy(inputStreamReader, writer, bufferSize);
    }

    //byte[]=> outputstream
    public static void copy(final  byte[] inputByteArray,final OutputStream outputStream)throws IOException{
        outputStream.write(inputByteArray);
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

    public static void close(Channel channel) {
        if (channel == null) {
            return;
        }

        try {
            channel.close();
        } catch (IOException ex) {
            // ignore
        }
    }

    public static void close(OutputStream outputStream) {
        if (outputStream == null) {
            return;
        }

        try {
            outputStream.close();
        } catch (IOException ex) {
            // ignore
        }
    }

    public static void close(Reader reader) {
        if (reader == null) {
            return;
        }

        try {
            reader.close();
        } catch (IOException ex) {
            // ignore
        }
    }

    public static void close(Writer writer) {
        if (writer == null) {
            return;
        }

        try {
            writer.close();
        } catch (IOException ex) {
            // ignore
        }
    }
}
