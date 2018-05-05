package com.wutong.weixin.utils.http;

import com.wutong.weixin.utils.file.FileConvertUtil;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * 文件MIMEType工具类
 * <p>
 * 文件MimeType通过后缀名判断不准确，通过输入流判断最精确
 * 文件上传：文件后缀采取用户指定后缀保存，通过流虽然能判断出MimeType，但同一MimeType文件后缀不一定相同
 * 文件下载(显示)：response给客户端的MimeType，通过文件名判断，判断不出才通过流判断
 */
public class MimeTypeUtil {



    public static void main(String[] args) throws Exception {


//        System.out.println(getType("/upload/images/20171213/1513147230549.jpeg"));
//        System.out.println(getType("20171213/1513147230549.jpeg"));
//        System.out.println(getType("20171213/1513147230549.jpg"));
//        System.out.println(getType("20171213/1513147230549.png"));
//        System.out.println(getType("20171213/1513147230549.gif"));
//        System.out.println(getType("/2.doc"));
//        System.out.println(getType("F:/2.csv"));
//        System.out.println(getType("F:/LiveUpdate.exe"));
//        System.out.println(getType("F:/1.txt"));
//        System.out.println(getType("F:/demo.jpg"));


        //String path = "D:/swagger-ui-master.zip";
        //String path = "D:/SmartPetHome1.1.0.apk";
        String path = "D:/123.txt";

       /* String mimeType = getTypeByFileName(path);
        if(mimeType == null){
            mimeType = MimeTypeUtil.getTypeByStream(path);
        }
        System.out.println(mimeType);*/

    }


    private static Logger logger = LoggerFactory.getLogger(MimeTypeUtil.class);



    /**
     * 通过文件后缀名判断MimeType，能判断的文件类型有限
     * @param filename  文件名
     * @return
     */
    public static String getTypeFromFileName(String filename){
        return URLConnection.guessContentTypeFromName(filename);
    }


    /**
     * 通过流判断文件类型，但能判断的文件类型有限，主要格式有：html,xml,jpeg,jpg,gif,png
     * @param path  文件物理路径
     * @return
     */
    public static String getTypeFromStream(String path){
        String mimeType = null;
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(path)));
            mimeType = URLConnection.guessContentTypeFromStream(inputStream);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return mimeType;
    }



    /**
     * 通过文件名后缀判断MimeType
     * 注意：经测试该方式Centos下需要给出文件全路径（而不仅仅是文件名），ubuntu和windows下只要是带后缀的文件名即可，也可能是JDK版本原因
     * 该方法核心部分涉及到jdk不开源的内容，可以在OpenJDK中查看
     * @param filename 文件名
     * @return
     */
    public static String getTypeByFileName(String filename){
        String mimeType = null;
        Path path = Paths.get(filename);
        try {
            mimeType = Files.probeContentType(path);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return mimeType;
    }



    /**
     * 通过流获取文件MimeType
     * @param path  文件物理路径
     * @return
     */
    public static String getTypeByStream(String path) {
        InputStream inputStream = FileConvertUtil.fileToInputStream(path);
        return getTypeByStream(inputStream);
    }


    /**
     * 通过流获取文件MimeType
     * @param inputStream  输入流
     * @return
     */
    public static String getTypeByStream(InputStream inputStream) {
        if(inputStream == null) return null;
        String mimeType = null;
        Tika tika = new Tika();
        try {
            mimeType = tika.detect(inputStream);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return mimeType;
    }





}
