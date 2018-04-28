package com.wutong.weixin.utils.encrypt;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * 哈希算法工具类
 * MD5、SHA1、SHA224、SHA256、SHA384、SHA512
 * HmacMD5、HmacSHA1、HmacSHA224、HmacSHA256、HmacSHA384、HmacSHA512
 */
public class HashUtil {


    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private static final String KEY_MD5 = "MD5";
    private static final String KEY_SHA1 = "SHA1";
    private static final String KEY_SHA224 = "SHA-224";
    private static final String KEY_SHA256 = "SHA-256";
    private static final String KEY_SHA384 = "SHA-384";
    private static final String KEY_SHA512 = "SHA-512";

    private static final String KEY_HMAC_MD5 = "HmacMD5";
    private static final String KEY_HMAC_SHA1 = "HmacSHA1";
    private static final String KEY_HMAC_SHA224 = "HmacSHA224";
    private static final String KEY_HMAC_SHA256 = "HmacSHA256";
    private static final String KEY_HMAC_SHA384 = "HmacSHA384";
    private static final String KEY_HMAC_SHA512 = "HmacSHA512";


    /**
     * 消息摘要
     * @param data  原始数据
     * @param algorithm 加密算法
     * @return
     */
    private static String messageDigest(byte[] data, String algorithm){
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(data);
            return ByteHexUtil.byteToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 文件消息摘要
     * @param file  文件
     * @param algorithm 加密算法
     * @return
     */
    private static String messageDigest(File file, String algorithm){
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            InputStream inputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            inputStream.close();
            return ByteHexUtil.byteToHex(md.digest());
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }



    /**
     * MD5加密
     * @param file  文件
     * @return
     */
    public static String md5(File file){
        return messageDigest(file, KEY_MD5);
    }


    /**
     * SHA1加密
     *
     * @param file  文件
     * @return
     */
    public static String sha1(File file){
        return messageDigest(file, KEY_SHA1);
    }



    /**
     * MD5加密
     *
     * @param data  原始数据
     * @return
     */
    public static String md5(String data){
        return md5(data.getBytes(DEFAULT_CHARSET));
    }

    /**
     * MD5加密
     * @param data  原始数据
     * @return
     */
    public static String md5(byte[] data){
        return messageDigest(data, KEY_MD5);
    }

    /**
     * SHA1加密
     *
     * @param data  原始数据
     * @return
     */
    public static String sha1(String data){
        return sha1(data.getBytes(DEFAULT_CHARSET));
    }

    /**
     * SHA1加密
     *
     * @param data  原始数据
     * @return
     */
    public static String sha1(byte[] data){
        return messageDigest(data, KEY_SHA1);
    }


    /**
     * SHA224加密
     *
     * @param data  原始数据
     * @return
     */
    public static String sha224(String data){
        return sha224(data.getBytes(DEFAULT_CHARSET));
    }

    /**
     * SHA224加密
     *
     * @param data  原始数据
     * @return
     */
    public static String sha224(byte[] data){
        return messageDigest(data, KEY_SHA224);
    }


    /**
     * SHA256加密
     *
     * @param data  原始数据
     * @return
     */
    public static String sha256(String data){
        return sha256(data.getBytes(DEFAULT_CHARSET));
    }

    /**
     * SHA256加密
     *
     * @param data  原始数据
     * @return
     */
    public static String sha256(byte[] data){
        return messageDigest(data, KEY_SHA256);
    }


    /**
     * SHA384加密
     *
     * @param data  原始数据
     * @return
     */
    public static String sha384(String data){
        return sha384(data.getBytes(DEFAULT_CHARSET));
    }

    /**
     * SHA384加密
     *
     * @param data  原始数据
     * @return
     */
    public static String sha384(byte[] data){
        return messageDigest(data, KEY_SHA384);
    }


    /**
     * SHA512加密
     *
     * @param data  原始数据
     * @return
     */
    public static String sha512(String data){
        return sha512(data.getBytes(DEFAULT_CHARSET));
    }

    /**
     * SHA256加密
     *
     * @param data  原始数据
     * @return
     */
    public static String sha512(byte[] data){
        return messageDigest(data, KEY_SHA512);
    }




    /**
     * 生成密钥
     *
     * @param algorithm 算法
     * @param seed 种子
     * @return
     */
    private static String generateKey(String algorithm, String seed){
        SecureRandom secureRandom;
        if (seed == null) {
            secureRandom = new SecureRandom();
        } else {
            secureRandom = new SecureRandom(seed.getBytes(DEFAULT_CHARSET));
        }
        try {
            //指定算法的密钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
            keyGenerator.init(secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            return ByteHexUtil.byteToHex(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成HmacMD5算法的密钥
     * @return
     */
    public static String generateHmacMD5Key(){
        return generateHmacMD5Key(null);
    }

    /**
     * 生成HmacMD5算法的密钥
     * @param seed  种子
     * @return
     */
    public static String generateHmacMD5Key(String seed){
        return generateKey(KEY_HMAC_MD5, seed);
    }


    /**
     * 生成HmacSHA1算法的密钥
     * @return
     */
    public static String generateHmacSHA1Key(){
        return generateHmacSHA1Key(null);
    }

    /**
     * 生成HmacSHA1算法的密钥
     * @param seed  种子
     * @return
     */
    public static String generateHmacSHA1Key(String seed){
        return generateKey(KEY_HMAC_SHA1, seed);
    }



    /**
     * 生成HmacSHA224算法的密钥
     * @return
     */
    public static String generateHmacSHA224Key(){
        return generateHmacSHA224Key(null);
    }

    /**
     * 生成HmacSHA224算法的密钥
     * @param seed  种子
     * @return
     */
    public static String generateHmacSHA224Key(String seed){
        return generateKey(KEY_HMAC_SHA224, seed);
    }


    /**
     * 生成HmacSHA256算法的密钥
     * @return
     */
    public static String generateHmacSHA256Key(){
        return generateHmacSHA256Key(null);
    }

    /**
     * 生成HmacSHA256算法的密钥
     * @param seed  种子
     * @return
     */
    public static String generateHmacSHA256Key(String seed){
        return generateKey(KEY_HMAC_SHA256, seed);
    }


    /**
     * 生成HmacSHA384算法的密钥
     * @return
     */
    public static String generateHmacSHA384Key(){
        return generateHmacSHA384Key(null);
    }

    /**
     * 生成HmacSHA384算法的密钥
     * @param seed  种子
     * @return
     */
    public static String generateHmacSHA384Key(String seed){
        return generateKey(KEY_HMAC_SHA384, seed);
    }


    /**
     * 生成HmacSHA512算法的密钥
     * @return
     */
    public static String generateHmacSHA512Key(){
        return generateHmacSHA512Key(null);
    }

    /**
     * 生成HmacSHA512算法的密钥
     * @param seed  种子
     * @return
     */
    public static String generateHmacSHA512Key(String seed){
        return generateKey(KEY_HMAC_SHA512, seed);
    }






    /**
     * HMAC加密
     * @param data  原始数据
     * @param algorithm 加密算法
     * @param key   加密密钥
     * @return
     */
    private static String hmac(byte[] data, String algorithm, String key){
        try {
            SecretKey secretKey = new SecretKeySpec(key.getBytes(DEFAULT_CHARSET), algorithm);
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            return ByteHexUtil.byteToHex(mac.doFinal(data));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * HmacMD5加密
     *
     * @param data  原始数据
     * @param key   加密密钥
     * @return
     */
    public static String hmacMD5(String data, String key){
        return hmacMD5(data.getBytes(DEFAULT_CHARSET), key);
    }

    /**
     * HmacMD5加密
     *
     * @param data  原始数据
     * @param key   加密密钥
     * @return
     */
    public static String hmacMD5(byte[] data, String key){
        return hmac(data, KEY_HMAC_MD5, key);
    }


    /**
     * HmacSHA1加密
     *
     * @param data  原始数据
     * @param key   加密密钥
     * @return
     */
    public static String hmacSHA1(String data, String key){
        return hmacSHA1(data.getBytes(DEFAULT_CHARSET), key);
    }

    /**
     * HmacSHA1加密
     *
     * @param data  原始数据
     * @param key   加密密钥
     * @return
     */
    public static String hmacSHA1(byte[] data, String key){
        return hmac(data, KEY_HMAC_SHA1, key);
    }


    /**
     * HmacSHA224加密
     *
     * @param data  原始数据
     * @param key   加密密钥
     * @return
     */
    public static String hmacSHA224(String data, String key){
        return hmacSHA224(data.getBytes(DEFAULT_CHARSET), key);
    }

    /**
     * HmacSHA224加密
     *
     * @param data  原始数据
     * @param key   加密密钥
     * @return
     */
    public static String hmacSHA224(byte[] data, String key){
        return hmac(data, KEY_HMAC_SHA224, key);
    }


    /**
     * HmacSHA256加密
     *
     * @param data  原始数据
     * @param key   加密密钥
     * @return
     */
    public static String hmacSHA256(String data, String key){
        return hmacSHA256(data.getBytes(DEFAULT_CHARSET), key);
    }

    /**
     * HmacSHA256加密
     *
     * @param data  原始数据
     * @param key   加密密钥
     * @return
     */
    public static String hmacSHA256(byte[] data, String key){
        return hmac(data, KEY_HMAC_SHA256, key);
    }

    /**
     * HmacSHA384加密
     *
     * @param data  原始数据
     * @param key   加密密钥
     * @return
     */
    public static String hmacSHA384(String data, String key){
        return hmacSHA384(data.getBytes(DEFAULT_CHARSET), key);
    }

    /**
     * HmacSHA384加密
     *
     * @param data  原始数据
     * @param key   加密密钥
     * @return
     */
    public static String hmacSHA384(byte[] data, String key){
        return hmac(data, KEY_HMAC_SHA384, key);
    }

    /**
     * HmacSHA512加密
     *
     * @param data  原始数据
     * @param key   加密密钥
     * @return
     */
    public static String hmacSHA512(String data, String key){
        return hmacSHA512(data.getBytes(DEFAULT_CHARSET), key);
    }

    /**
     * HmacSHA512加密
     *
     * @param data  原始数据
     * @param key   加密密钥
     * @return
     */
    public static String hmacSHA512(byte[] data, String key){
        return hmac(data, KEY_HMAC_SHA512, key);
    }


    public static void main(String[] args) {

        String password = "abc12345678";

        System.out.println("原始串：" + password);

        String md5 = md5(password);
        System.out.println(md5.length() + "位  md5 = " + md5);//32位  9a361ed860ec2617da5af72079594a21

        String sha1 = sha1(password);
        System.out.println(sha1.length() + "位  sha1 = " + sha1);//40位  62d0f59b1df3634412cceb717635b4f701e812f3

        String sha224 = sha224(password);
        System.out.println(sha224.length() + "位  sha224 = " + sha224);//56位  3315dc6c6f80898dc9f4ee38ea34c2f2e8c3570f21671587e8bfa640

        String sha256 = sha256(password);
        System.out.println(sha256.length() + "位  sha256 = " + sha256);//64位  e06787aebebb1ce726260447b036fd36289a7e7133f61b1def05f18734300b1a

        String sha384 = sha384(password);
        System.out.println(sha384.length() + "位  sha384 = " + sha384);//96位  4b82823dedbfe88b95f5851ddcb665a8b20766fac2e2613d92b3e3ad0d66c1ca227ce3ef92d1d5b53e89d4a04a6d6f4c

        String sha512 = sha512(password);
        System.out.println(sha512.length() + "位  sha512 = " + sha512);//128位  f2d5c881bc7aea0069201581130965e169c63eb32cb7175ab4b8316d4e80ba615f44860f17802e4f00e413f2ebdc219ac065b14db590da794775e4558e19cfed

        String hmacMD5Key = generateHmacMD5Key();
        System.out.println(hmacMD5Key.length() + "位  hmacMD5Key = " + hmacMD5Key);//128位  5c271f27b7a0afb62999fe00ddb448a322bf6eb65ee75af093490c9410ec7549c3c0120b04ed437a7c259ac2ad864897eca2f01c9b52f36055052ec1c402e39a

        String hmacMD5 = hmacMD5(password, hmacMD5Key);
        System.out.println(hmacMD5.length() + "位  hmacMD5 = " + hmacMD5);//32位  3729e37f5a12f5c6122974c5dd43ecb6


        String hmacSHA1Key = generateHmacSHA1Key();
        System.out.println(hmacSHA1Key.length() + "位  hmacSHA1Key = " + hmacSHA1Key);//128位  27acc304d6cf8d84e3ef6af1c4379b5301943a2876cb4a2f577c9b4c868540996ad667a0c57f4660207b4c2cfba4fa20265f2f4e628d1c95aa721780b289d103

        String hmacSHA1 = hmacSHA1(password, hmacSHA1Key);
        System.out.println(hmacSHA1.length() + "位  hmacSHA1 = " + hmacSHA1);//40位  58835b91877a26144f7665e24f620623392459b5


        String hmacSHA224Key = generateHmacSHA224Key();
        System.out.println(hmacSHA224Key.length() + "位  hmacSHA224Key = " + hmacSHA224Key);//56位  bd328d52c17e4626dd6c559c10c9153bc60caca82c3ed0ed1da2b22a

        String hmacSHA224 = hmacSHA224(password, hmacSHA224Key);
        System.out.println(hmacSHA224.length() + "位  hmacSHA224 = " + hmacSHA224);//56位  86bd2d75cccc9a5ced06afb7b620a23534066616492df53ef0c5508d

        String hmacSHA256Key = generateHmacSHA256Key();
        System.out.println(hmacSHA256Key.length() + "位  hmacSHA256Key = " + hmacSHA256Key);//64位  489ae96994587a9b87643bef81b74896b5b242aeccc9f611aab2659204669462

        String hmacSHA256 = hmacSHA256(password, hmacSHA256Key);
        System.out.println(hmacSHA256.length() + "位  hmacSHA256 = " + hmacSHA256);//64位  91319cd55f0ade82f6864bacba0ac0f08ef163f29f0f7e487b39550e8b03fb53

        String hmacSHA384Key = generateHmacSHA384Key();
        System.out.println(hmacSHA384Key.length() + "位  hmacSHA384Key = " + hmacSHA384Key);//96位  69714150272d92c8dca6bd26fe543baacf0298e42c2b1d36f1e7c38cb5e8a8505bce43cc892983e77931a3440d2c2220

        String hmacSHA384 = hmacSHA384(password, hmacSHA384Key);
        System.out.println(hmacSHA384.length() + "位  hmacSHA384 = " + hmacSHA384);//96位  29b26d5643d3f5eeffa0dd538413c43331ab4d97c5f5fb8659b53df5f2070430987c87b9eda3df5bf8f7361526323d37

        String hmacSHA512Key = generateHmacSHA512Key();
        System.out.println(hmacSHA512Key.length() + "位  hmacSHA512Key = " + hmacSHA512Key);//128位  5e95bf590181d651c0b639b7e8c982ced2d7ef673ba6bacd4111deb8c1eaf3d109e9ff5bd421937a6ccce3a8304d4b6af75f77f28beb8e29004aa446873fb898

        String hmacSHA512 = hmacSHA512(password, hmacSHA512Key);
        System.out.println(hmacSHA512.length() + "位  hmacSHA512 = " + hmacSHA512);//128位  31465923294bb154a430c7904d0f53e51a772c54d362162f370c410021dd3c9ecc1cfb1070abc4f68126fec28312c1c25199c3f071449f2e8808939d0f977f75





    }





}
