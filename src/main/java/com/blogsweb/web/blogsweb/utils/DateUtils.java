package com.blogsweb.web.blogsweb.utils;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.platform.commons.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /** 时间格式(yyyy-MM-dd) */

    public final static String DATE_PATTERN ="yyyy-MM-dd";

    /** 时间格式(yyyy-MM-dd HH:mm:ss) */

    public final static String DATE_TIME_PATTERN ="yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date 日期
     * @return 返回yyyy-MM-dd格式日期
     */

    public static String format(Date date) {

        return format(date, DATE_PATTERN);

    }

    /**
     * 日期格式化 日期格式为:yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期
     * @return 返回yyyy-MM-dd HH:mm:ss格式日期
     */

    public static String formatLong(Date date) {

        return format(date, DATE_TIME_PATTERN);

    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date    日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 返回yyyy-MM-dd格式日期
     */

    public static String format(Date date, String pattern) {

        if (date != null) {

            SimpleDateFormat df = new SimpleDateFormat(pattern);

            return df.format(date);

        }

        return null;

    }

    /**
     * 字符串转换成日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */

    public static Date stringToDate(String strDate, String pattern) {

        if (StringUtils.isBlank(strDate)) {

            return null;

        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);

        return fmt.parseLocalDateTime(strDate).toDate();

    }

    /**
     * 根据周数，获取开始日期、结束日期
     *
     * @param week 周期  0本周，-1上周，-2上上周，1下周，2下下周
     * @return 返回date[0]开始日期、date[1]结束日期
     */

    public static Date[] getWeekStartAndEnd(int week) {

        DateTime dateTime = new DateTime();

        LocalDate date = new LocalDate(dateTime.plusWeeks(week));

        date = date.dayOfWeek().withMinimumValue();

        Date beginDate = date.toDate();

        Date endDate = date.plusDays(6).toDate();

        return new Date[]{beginDate, endDate};

    }

    /**
     * 对日期的【秒】进行加/减
     *
     * @param date    日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */

    public static Date addDateSeconds(Date date, int seconds) {

        DateTime dateTime = new DateTime(date);

        return dateTime.plusSeconds(seconds).toDate();

    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date    日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */

    public static Date addDateMinutes(Date date, int minutes) {

        DateTime dateTime = new DateTime(date);

        return dateTime.plusMinutes(minutes).toDate();

    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date  日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */

    public static Date addDateHours(Date date, int hours) {

        DateTime dateTime = new DateTime(date);

        return dateTime.plusHours(hours).toDate();

    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */

    public static Date addDateDays(Date date, int days) {

        DateTime dateTime = new DateTime(date);

        return dateTime.plusDays(days).toDate();

    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date  日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */

    public static Date addDateWeeks(Date date, int weeks) {

        DateTime dateTime = new DateTime(date);

        return dateTime.plusWeeks(weeks).toDate();

    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date   日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */

    public static Date addDateMonths(Date date, int months) {

        DateTime dateTime = new DateTime(date);

        return dateTime.plusMonths(months).toDate();

    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date  日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */

    public static Date addDateYears(Date date, int years) {

        DateTime dateTime = new DateTime(date);

        return dateTime.plusYears(years).toDate();

    }

    /**
     * 日期相减得到天数
     *
     * @param beforeDate
     * @param afterDate
     * @return afterDate - beforeDate
     */
    public static int subtractDate(Date beforeDate, Date afterDate) {
        DateTime beforeDateTime = new DateTime(beforeDate);
        DateTime afterDateTime = new DateTime(afterDate);

        return Days.daysBetween(beforeDateTime, afterDateTime).getDays();
    }

    /**
     * 两个日期相减小时数
     * @param beforeDate 被减日期
     * @param afterDate 日期
     * @return afterDate - beforeDate
     */
    public static int subtractHours(Date beforeDate, Date afterDate) {
        DateTime beforeDateTime = new DateTime(beforeDate);
        DateTime afterDateTime = new DateTime(afterDate);

        return Hours.hoursBetween(beforeDateTime, afterDateTime).getHours();
    }

    /**
     * 两个日期相减分钟数
     * @param beforeDate 被减日期
     * @param afterDate 日期
     * @return afterDate - beforeDate
     */
    public static int subtractMinutes(Date beforeDate, Date afterDate) {
        DateTime beforeDateTime = new DateTime(beforeDate);
        DateTime afterDateTime = new DateTime(afterDate);

        return Minutes.minutesBetween(beforeDateTime, afterDateTime).getMinutes();
    }

    /**
     * 将日期 yyyy-MM-dd HH:mm:ss 转为 yyyy-MM-dd 00:00:00
     * @param date 日期
     * @return 格式化之后的日期 yyyy-MM-dd 00:00:00
     */
    public static Date formatShortDate(Date date) {
        DateTime dateTime = new DateTime(date);
        String tempDate = dateTime.toString("yyyy-MM-dd HH:mm:ss");
        return DateUtils.stringToDate(tempDate, DateUtils.DATE_PATTERN);
    }

}
