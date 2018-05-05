package com.wutong.weixin.utils.file;

import org.springframework.util.Base64Utils;

import java.io.*;

/**
 * 文件工具类
 */
public class FileConvertUtil {

    /**
     * 将文件转化成Base64字符串
     *
     * @param filePath 文件路径
     * @return
     */
    public static String fileToBase64(String filePath) {
        InputStream inputStream = fileToInputStream(filePath);
        return inputStreamToBase64(inputStream);
    }

    /**
     * 将base64编码字符串转化成文件
     *
     * @param savePath   保存路径
     * @param fileBase64 文件base64编码
     * @return
     */
    public static boolean base64ToFile(String savePath, String fileBase64) {
        byte[] bytes = base64ToByte(fileBase64);
        return byteToFile(savePath, bytes);
    }



    /**
     * 将文件转化成输入流
     *
     * @param filePath 文件路径
     * @return
     */
    public static InputStream fileToInputStream(String filePath) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }



    /**
     * 将输入流转化base64编码
     *
     * @param inputStream 输入流
     * @return
     */
    public static String inputStreamToBase64(InputStream inputStream) {
        if(inputStream == null) return null;
        byte[] bytes = null;
        try {
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        // Base64编码
        return Base64Utils.encodeToString(bytes);
    }



    /**
     * 将base64编码字符串转化字节数组
     *
     * @param base64 base64编码
     * @return
     */
    public static byte[] base64ToByte(String base64) {
        if (base64 == null) return new byte[0];
        // Base64解码
        byte[] bytes = Base64Utils.decodeFromString(base64);
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {//调整异常数据
                bytes[i] += 256;
            }
        }
        return bytes;
    }


    /**
     * 将字节数组转化成文件
     *
     * @param savePath 保存路径
     * @param bytes 字节数组
     * @return
     */
    public static boolean byteToFile(String savePath, byte[] bytes) {
        if(bytes == null || bytes.length == 0) return false;
        OutputStream out = null;
        boolean result = false;
        try {
            // 生成文件
            out = new FileOutputStream(savePath);
            out.write(bytes);
            out.flush();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }




    public static void main(String[] args) {
        String savePath = "E:\\bakimage";
        File file = new File(savePath);
        File[] files = file.listFiles();
        savePath = files[0].getAbsolutePath();
        System.out.println(fileToBase64(savePath));

    }

}