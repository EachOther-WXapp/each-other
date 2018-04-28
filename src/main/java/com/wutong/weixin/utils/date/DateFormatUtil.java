package com.wutong.weixin.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author：Tom
 * Date：2017/8/14
 * Email：84048715@qq.com
 *
 * 日期格式化工具类
 */
public class DateFormatUtil {

    private static final String FORMAT_yyyy_MM_dd = "yyyy-MM-dd";

    private static final String FORMAT_yyyyMM = "yyyyMM";

    private static final String FORMAT_yyyyMMdd = "yyyyMMdd";

    private static final String FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss";

    private static final String FORMAT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    private static final String FORMAT_HH_mm_ss = "HH:mm:ss";



    /**
     * 日期格式化为字符串 （通用）
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern){
        if(date != null && pattern != null && pattern.length() > 0){
            return new SimpleDateFormat(pattern).format(date);
        }
        return null;
    }


    /**
     * 字符串转日期(通用)
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date parse(String dateStr , String pattern){
        if(dateStr != null && dateStr.length() > 0 && pattern != null && pattern.length() > 0){
            try {
                return new SimpleDateFormat(pattern).parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /*********************************************************************************************************************************/

    /**
     * 日期格式化为字符串 yyyy-MM-dd
     * @param date
     * @return
     */
    public static String format_yyyy_MM_dd(Date date){
        return format(date, FORMAT_yyyy_MM_dd);
    }

    /**
     * 日期格式化为字符串 yyyy-MM-dd
     * @return
     */
    public static String now_yyyy_MM_dd(){
        return format_yyyy_MM_dd(new Date());
    }


    /**
     * 日期格式化为字符串 yyyyMM
     * @param date
     * @return
     */
    public static String format_yyyyMM(Date date){
        return format(date, FORMAT_yyyyMM);
    }

    /**
     * 日期格式化为字符串 yyyyMM
     * @return
     */
    public static String now_yyyyMM(){
        return format_yyyyMM(new Date());
    }

    /**
     * 日期格式化为字符串 yyyyMMdd
     * @param date
     * @return
     */
    public static String format_yyyyMMdd(Date date){
        return format(date, FORMAT_yyyyMMdd);
    }

    /**
     * 日期格式化为字符串 yyyyMMdd
     * @return
     */
    public static String now_yyyyMMdd(){
        return format_yyyyMMdd(new Date());
    }

    /**
     * 日期格式化为字符串 yyyyMMddHHmmss
     * @param date
     * @return
     */
    public static String format_yyyyMMddHHmmss(Date date){
        return format(date, FORMAT_yyyyMMddHHmmss);
    }

    /**
     * 日期格式化为字符串 yyyyMMddHHmmss
     * @return
     */
    public static String now_yyyyMMddHHmmss(){
        return format_yyyyMMddHHmmss(new Date());
    }

    /**
     * 日期格式化为字符串 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String format_yyyy_MM_dd_HH_mm_ss(Date date){
        return format(date, FORMAT_yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 日期格式化为字符串 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String now_yyyy_MM_dd_HH_mm_ss(){
        return format_yyyy_MM_dd_HH_mm_ss(new Date());
    }

    /**
     * 日期格式化为字符串 HH:mm:ss
     * @param date
     * @return
     */
    public static String format_HH_mm_ss(Date date){
        return format(date, FORMAT_HH_mm_ss);
    }


    /**
     * 日期格式化为字符串 HH:mm:ss
     * @return
     */
    public static String now_HH_mm_ss(){
        return format_HH_mm_ss(new Date());
    }



    /*********************************************************************************************************************************/




    /**
     * 字符串转日期
     *
     * @param 	dateStr	yyyy-MM-dd
     * @return	Date
     */
    public static Date parse_yyyy_MM_dd(String dateStr) {
        return parse(dateStr, FORMAT_yyyy_MM_dd);
    }

    /**
     * 字符串转日期
     *
     * @param 	dateStr	yyyyMM
     * @return	Date
     */
    public static Date parse_yyyyMM(String dateStr) {
        return parse(dateStr, FORMAT_yyyyMM);
    }


    /**
     * 字符串转日期
     *
     * @param 	dateStr	yyyyMMdd
     * @return	Date
     */
    public static Date parse_yyyyMMdd(String dateStr) {
        return parse(dateStr, FORMAT_yyyyMMdd);
    }


    /**
     * 字符串转日期
     *
     * @param 	dateStr	yyyyMMddHHmmss
     * @return	Date
     */
    public static Date parse_yyyyMMddHHmmss(String dateStr) {
        return parse(dateStr, FORMAT_yyyyMMddHHmmss);
    }


    /**
     * 字符串转日期
     *
     * @param 	dateStr	yyyy-MM-dd HH:mm:ss
     * @return	Date
     */
    public static Date parse_yyyy_MM_dd_HH_mm_ss(String dateStr) {
        return parse(dateStr, FORMAT_yyyy_MM_dd_HH_mm_ss);
    }


    /**
     * 字符串转日期
     *
     * @param 	dateStr	HH:mm:ss
     * @return	Date
     */
    public static Date parse_HH_mm_ss(String dateStr) {
        return parse(dateStr, FORMAT_HH_mm_ss);
    }

}
