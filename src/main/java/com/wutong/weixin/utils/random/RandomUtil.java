package com.wutong.weixin.utils.random;


import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;
import java.util.UUID;

/**
 */
public class RandomUtil {

    /**
     * 生成纯数字随机数
     *
     * @param length 位数
     * @return
     * @see RandomStringUtils
     */
    public static String createDigitCode(int length) {

        if (length < 1) return "";

        // 生成随机数
        Random random = new Random();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < length; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();

        //也可使用Commons lang3提供的RandomStringUtils测试性能比自定义的稍微差点，不过可以忽略
        //return RandomStringUtils.randomNumeric(length);

    }


    /**
     * 随机字符串生成 字母（大小写）和数字
     *
     * @param length 生成字符串的长度
     * @return
     */
    public static String createRandomString(int length) {
        return createRandomString(length, null);
    }


    /**
     * 随机字符串生成 字母（大小写）和数字
     *
     * @param length    生成字符串的长度
     * @param upperCase 是否大写  默认null大小写都存在
     * @return
     */
    public static String createRandomString(int length, Boolean upperCase) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (upperCase != null) {
            if (upperCase) {
                base = base.toUpperCase();
            } else {
                base = base.toLowerCase();
            }
        }
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    public static void main(String[] args) {

        /*System.out.println("--------------------------------  自定义随机数  --------------------------------");
        long startTime = System.currentTimeMillis();
        System.out.println("startTime: " + startTime);
        for (int i = 0; i < 10000; i++) {
            createDigitCode(10000);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("endTime: " + endTime);
        System.out.println("total: " + (endTime - startTime));//1407
        System.out.println("--------------------------------  自定义随机数  --------------------------------");*/

        //System.out.println();

        /*System.out.println("--------------------------------  Commons提供随机数  --------------------------------");
        long startTime = System.currentTimeMillis();
        System.out.println("startTime: " + startTime);
        for(int i = 0; i < 10000; i++) {
            RandomStringUtils.randomNumeric(10000);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("endTime: " + endTime);
        System.out.println("total: " + (endTime - startTime));//14313
        System.out.println("--------------------------------  Commons提供随机数  --------------------------------");*/

        System.out.println(RandomStringUtils.random(100));
        System.out.println(RandomStringUtils.random(100, true, true));
        System.out.println(RandomStringUtils.randomNumeric(100));
        System.out.println(RandomStringUtils.randomAlphabetic(100));
        System.out.println(RandomStringUtils.randomAlphanumeric(100));
        System.out.println(RandomStringUtils.randomAscii(100));
        System.out.println(RandomStringUtils.random(100, "hello"));
        System.out.println(RandomStringUtils.random(100, "%$#"));
        /*for (int i = 0; i < 100; i++) {
            String no = (System.currentTimeMillis() - 936841632612L + System.currentTimeMillis()) + RandomUtil.createDigitCode(3);
            long id = Long.parseLong(no);
            System.out.println(id);
        }*/
        //2085795426382070
        //2085795464456240
        //2085795479834990

        System.out.println(getUUID());

    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.replace("-", "");
    }

}
