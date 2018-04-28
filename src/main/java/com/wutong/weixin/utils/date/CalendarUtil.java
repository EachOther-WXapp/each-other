package com.wutong.weixin.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日历工具类
 */
public class CalendarUtil {

    private static Calendar gregorianCalendar = null;

    static {
        gregorianCalendar = new GregorianCalendar();
    }


    /**
     * 获取日期所在星期的星期一日期
     *
     * @param date 指定日期
     * @return date
     */
    public static Date getFirstDayOfWeek(Date date) {
        if (date == null) return null;
        gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek());
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当前星期的星期一日期
     *
     * @return date
     */
    public static Date getFirstDayOfWeek() {
        return getFirstDayOfWeek(new Date());
    }


    /**
     * 获取日期所在星期的星期日日期
     *
     * @param date 指定日期
     * @return date
     */
    public static Date getLastDayOfWeek(Date date) {
        if (date == null) return null;
        gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek() + 6);
        return gregorianCalendar.getTime();
    }


    /**
     * 获取当前星期的星期日日期
     *
     * @return date
     */
    public static Date getLastDayOfWeek() {
        return getLastDayOfWeek(new Date());
    }


    /**
     * 获取指定月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        if (date == null) return null;
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当前月的第一天
     *
     * @return date
     */
    public static Date getFirstDayOfMonth() {
        return getFirstDayOfMonth(new Date());
    }


    /**
     * 获取指定月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        if (date == null) return null;
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        gregorianCalendar.add(Calendar.MONTH, 1);
        gregorianCalendar.add(Calendar.DAY_OF_MONTH, -1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当前月的最后一天
     *
     * @return
     */
    public static Date getLastDayOfMonth() {
        return getLastDayOfMonth(new Date());
    }

    /**
     * 获取日期前一天
     *
     * @param date
     * @return date
     */
    public static Date getDayBefore(Date date) {
        if (date == null) return null;
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(Calendar.DATE);
        gregorianCalendar.set(Calendar.DATE, day - 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取日期后一天
     *
     * @param date
     * @return date
     */
    public static Date getDayAfter(Date date) {
        if (date == null) return null;
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(Calendar.DATE);
        gregorianCalendar.set(Calendar.DATE, day + 1);
        return gregorianCalendar.getTime();
    }


    /**
     * 获取当月天数
     *
     * @return
     */
    public static int getNowMonthDay() {
        Calendar d = Calendar.getInstance();
        return d.getActualMaximum(Calendar.DATE);
    }


    /**
     * 获取时间段的每一天
     *
     * @param startDate 开始日期
     * @param endDate   结算日期
     * @return 日期列表
     */
    public static List<Date> getEveryDay(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        // 格式化日期(yy-MM-dd)
        startDate = DateFormatUtil.parse_yyyy_MM_dd(DateFormatUtil.format_yyyy_MM_dd(startDate));
        endDate = DateFormatUtil.parse_yyyy_MM_dd(DateFormatUtil.format_yyyy_MM_dd(endDate));
        List<Date> dates = new ArrayList<>();
        gregorianCalendar.setTime(startDate);
        dates.add(gregorianCalendar.getTime());
        while (gregorianCalendar.getTime().compareTo(endDate) < 0) {
            // 加1天
            gregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
            dates.add(gregorianCalendar.getTime());
        }
        return dates;
    }


    /**
     * 获取提前多少个月
     *
     * @param monty
     * @return
     */
    public static Date getFirstMonth(int monty) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -monty);
        return c.getTime();
    }


    /**
     * 计算时间差(天数)
     * 不满一天算一整天
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 天数
     */
    public static int calculateDay(Date startTime, Date endTime) {
        return (int) Math.ceil((endTime.getTime() - startTime.getTime()) / (1000 * 60 * 60 * 24.0));
    }

    /**
     * 计算时间差(分钟)
     * 不足一分钟舍弃
     *
     * @param startTime
     * @param endTime
     * @return 分钟
     */
    public static int calculateDate(Date startTime, Date endTime) {
        return (int) Math.floor((endTime.getTime() - startTime.getTime()) / (1000 * 60.0));
    }

    /**
     * 获取当前粗糙时间(精确到秒)
     *
     * @return
     */
    public static Date getNowCoarseDate() {
        return new Date(System.currentTimeMillis() / 1000 * 1000);
    }

    /**
     * 获取当前粗糙时间(精确到天)
     *
     * @return
     */
    public static Date getCoarseDate(Date date) {
        return getDate(date, null);
    }

    private static Date getDate(Date date, TimeZone timeZone) {
        Calendar calendar;
        if (timeZone == null) {
            calendar = Calendar.getInstance();
        } else {
            calendar = Calendar.getInstance(timeZone);
        }
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当前粗糙时间(精确到天),特定时区
     *
     * @return
     */
    public static Date getCoarseDate(Date date, TimeZone timeZone) {
        return getDate(date, timeZone);
    }


    /**
     * 开通会员时间计算
     *
     * @param startDate 开始时间
     * @param monthNum  开通月数
     * @return
     */
    public static Date addMonth(Date startDate, int monthNum) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + monthNum);

        int delta = calendar.get(Calendar.MONTH) - month;
        if (delta < 0) {
            delta = 12 - Math.abs(delta);
        }
        if (delta > monthNum % 12) {
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        }

        //计算
        Calendar d = Calendar.getInstance();
        d.setTime(calendar.getTime());
        int updateDay = d.getActualMaximum(Calendar.DATE);
        if (day > updateDay) {
            day = updateDay;
        }
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * 年份定位，根据传入的日期参数定位日期的年、月、日，可获得指定日期的前后N年
     *
     * @param date   Date 指定的日期
     * @param amount 定位值，前传负数，后传正数
     * @return Date 定位后的日期
     */
    public static Date yearOrientation(Date date, int amount) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.YEAR, amount);
        return ca.getTime();
    }

    /**
     * 获取传入时间年份的第一天日期
     *
     * @param date 时间
     * @return Date
     */
    public static Date getYearFirst(Date date) {

        Calendar currCal = Calendar.getInstance();
        currCal.setTime(date);
        int currentYear = currCal.get(Calendar.YEAR);
        currCal.clear();
        currCal.set(Calendar.YEAR, currentYear);
        Date currYearFirst = currCal.getTime();
        return currYearFirst;
    }

    /**
     * 月份定位，根据传入的日期参数定位日期的年、月、日，可获得指定日期的前后N月
     *
     * @param date   Date 指定的日期
     * @param amount 定位值，前传负数，后传正数
     * @return Date 定位后的日期
     */
    public static Date monthOrientation(Date date, int amount) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH, amount);
        return ca.getTime();
    }

    /**
     * 日定位，根据传入的日期参数定位日期的年、月、日，可获得指定日期的前后N天
     *
     * @param date   Date 指定的日期
     * @param amount 定位值，前传负数，后传正数
     * @return Date 定位后的日期
     */
    public static Date dayOrientation(Date date, int amount) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DATE, amount);
        return ca.getTime();
    }

    /**
     * 时定位，根据传入的日期参数定位日期的小时、分钟，可获得指定日期的前后N小时
     *
     * @param date   Date 指定的日期
     * @param amount 定位值，前传负数，后传正数
     * @return Date 定位后的日期
     */
    public static Date hourOrientation(Date date, int amount) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.HOUR, amount);
        return ca.getTime();
    }

    /**
     * 分定位，根据传入的日期参数定位日期的小时、分钟，可获得指定日期的前后N分钟
     *
     * @param date   Date 指定的日期
     * @param amount 定位值，前传负数，后传正数
     * @return Date 定位后的日期
     */
    public static Date minuteOrientation(Date date, int amount) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MINUTE, amount);
        return ca.getTime();
    }

    /**
     * 秒定位，根据传入的日期参数定位日期的小时、分钟，可获得指定日期的前后N秒
     *
     * @param date   Date 指定的日期
     * @param amount 定位值，前传负数，后传正数
     * @return Date 定位后的日期
     */
    public static Date secondOrientation(Date date, int amount) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.SECOND, amount);
        return ca.getTime();
    }

    /**
     * 根据生日，计算年龄
     *
     * @param date     生日
     * @param onlyYear true则精确到年，否则精确到月
     * @return 年龄，最多精确到月，不足一年返回月数，不足一月返回天数
     */
    public static String calculateAge(Date date, boolean onlyYear) {
        Date untilDate = new Date();
        int months = monthsElapses(date, untilDate);
        int years = months / 12;
        String age = "";
        if (years > 0) {
            age = years + "岁";
            if (onlyYear) {
                return age;
            }
        }
        if (months > 0) {
            int restMonths = months % 12;
            if (restMonths > 0) {
                return age + restMonths + "月";
            }
            return age;
        }
        return calculateDay(date, untilDate) + "天";
    }

    /**
     * 根据生日，计算年龄
     *
     * @param date 生日
     * @return 年龄，精确到月，不足一月返回天数
     */
    public static String calculateAge(Date date) {
        return calculateAge(date, false);
    }

    /**
     * 根据生日，计算年龄
     *
     * @param date 生日
     * @return 年龄，精确到月（year:年 month:月 day:天），不足一月则结果中包含天数
     */
    public static Map<String, Integer> calculateAgeMap(Date date) {
        Map<String, Integer> ageMap = new HashMap<>();
        Date untilDate = new Date();
        int months = monthsElapses(date, untilDate);
        ageMap.put("year", months / 12);
        ageMap.put("month", months % 12);
        if (ageMap.get("year") == 0 && ageMap.get("month") == 0) {
            ageMap.put("day", calculateDay(date, untilDate));
        }
        return ageMap;
    }

    public static boolean isLeapYear(Date date) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return year % 400 == 0 || (year % 4 == 0 && year % 100 > 0);
    }

    /**
     * 计算给定两个日期之间相差的月份数。
     * 比如：2017-01-01与2017-01-31之间相差0月；2017-01-01与2017-02-01之间相差1月
     *
     * @param startDate 起始日期
     * @param untilDate 结束日期
     * @return 给定两个日期之间相差的月份数
     */
    public static int monthsElapses(Date startDate, Date untilDate) throws IllegalArgumentException {
        startDate = getCoarseDate(startDate);
        if (startDate.after(untilDate)) throw new IllegalArgumentException("结束日期必须大于起始日期");
        Calendar start = Calendar.getInstance();
        Calendar until = Calendar.getInstance();
        start.setTime(startDate);
        until.setTime(untilDate);
        int months = (until.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12 + until.get(Calendar.MONTH) - start.get(Calendar.MONTH);
        int untilDay = until.get(Calendar.DAY_OF_MONTH);
        // 结束日期天数早于起始日期且不是月的最后一天
        if (untilDay < start.get(Calendar.DAY_OF_MONTH) && untilDay < until.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            months--;
        }
        return months;
    }

    /**
     * 计算给定两个日期之间相差的年数。
     * 比如：2016-02-01与2017-01-31之间相差0年；2016-02-01与2017-02-01之间相差1年
     *
     * @param startDate 起始日期
     * @param untilDate 结束日期
     * @return 给定两个日期之间相差的年数
     */
    public static int yearsElapses(Date startDate, Date untilDate) throws IllegalArgumentException {
        startDate = getCoarseDate(startDate);
        if (startDate.after(untilDate)) throw new IllegalArgumentException("结束日期必须大于起始日期");
        Calendar start = Calendar.getInstance();
        Calendar until = Calendar.getInstance();
        start.setTime(startDate);
        until.setTime(untilDate);
        int years = until.get(Calendar.YEAR) - start.get(Calendar.YEAR);
        int startMonth = start.get(Calendar.MONTH);
        int months = until.get(Calendar.MONTH) - startMonth;
        if (months > 0) {
            return years;
        }
        int startDay = start.get(Calendar.DAY_OF_MONTH);
        int days = until.get(Calendar.DAY_OF_MONTH) - startDay;
        // 同月份：
        // 1，结束日期天数不早于起始日期
        // 2，结束日期天数早于起始日期，起始日期正好是2-29（闰年中多出的那一天）且结束日期正好是2月28
        if (months == 0 && (days >= 0 || startMonth == 1 && startDay == 29 && days == -1)) {
            return years;
        }
        return years - 1;
    }

    /**
     * @param date 时间
     * @param year 要定位的年份
     * @return 根据传入的时间, 根据当前时间, 计算年份相加/减后的时间,精确到天
     */
    public static Date calculateDateForYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int yearInt = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setTimeInMillis(System.currentTimeMillis());//当前时间
        int todayYear = calendar.get(Calendar.YEAR);
        int nextYear = todayYear + year;
        calendar.set(nextYear, month, day);
        return calendar.getTime();
    }

    /**
     * 格式化时间
     *
     * @param date   时间
     * @param format 格式
     * @return 格式后的字符串
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 根据依据时间, 把formatTime的年月日与standardTime保持一致
     * @param standardTime 依据的时间
     * @param formatTime 要同步的时间
     */
    public static Date setSameDay(Date standardTime, Date formatTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(standardTime);
        Calendar formatCal = Calendar.getInstance();
        formatCal.setTime(formatTime);
        formatCal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        return formatCal.getTime();
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = "2018-10-12 10:22:22";
        Date date = new Date();
        Date format = sdf.parse(dateStr);
        System.out.println(setSameDay(date, format));

    }

}
