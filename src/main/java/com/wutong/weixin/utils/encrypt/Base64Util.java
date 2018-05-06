package com.wutong.weixin.utils.encrypt;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 *
 * Base64编码解码
 */
public class Base64Util {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * BASE64解码
     *
     * @param src
     * @return
     */
    public static String decrypt(String src){

        if (src == null) {
            return null;
        }
        if (src.length() == 0) {
            return "";
        }
        byte[] bytes;
        try {
            bytes = Base64.getDecoder().decode(src.getBytes(DEFAULT_CHARSET));
        } catch (Exception e) {
            bytes = new byte[0];
        }
        return new String(bytes, DEFAULT_CHARSET);
    }

    /**
     * BASE64编码
     *
     * @param src
     * @return
     */
    public static String encrypt(String src){

        if (src == null) {
            return null;
        }
        if (src.length() == 0) {
            return "";
        }
        return Base64.getEncoder().encodeToString(src.getBytes(DEFAULT_CHARSET));
    }


    public static void main(String[] args) {
        String  str = "18890380383:88888888";
        System.out.println(encrypt(str));
    }























}
