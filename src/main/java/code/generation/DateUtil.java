package code.generation;

/**
 * @Author: wyh
 * @Date: 2018/7/16 18:08
 * @Description: price-center
 */


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 */
public class DateUtil {

    public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String YMDHM = "yyyy-MM-dd HH:mm";
    public static final String YMDH = "yyyy-MM-dd HH";
    public static final String YMD = "yyyy-MM-dd";
    public static final String YM = "yyyy-MM";
    public static final String MD = "MM-dd";
    public static final String MDH = "MM-dd HH";
    public static final String Y = "yyyy";
    public static final String M = "MM";
    public static final String D = "dd";
    public static final String H = "HH";
    public static final String BRANCH = "MM_dd_HH_mm_ss";
    public static final String YMDHMSU = "yyyyMMddHHmmss";
    public static final String YMDHMSS = "yyyyMMddHHmmssSSS";
    public static final String YMDU = "yyyyMMdd";
    public static final String YMDHMST = "yyyy-MM-dd'T'HH:mm:ss";


    /**
     * 转换到字符串
     */
    final public static Long curTimestamp() {

        return new Date().getTime();
    }


    /**
     * 转换到字符串
     */
    final public static String format(Date date, String type) {
        if (date == null)
            return "";
        return new SimpleDateFormat(type).format(date);
    }

    /**
     * 转换到字符串
     */
    final public static String nowFormat(String type) {
        return new SimpleDateFormat(type).format(new Date());
    }

    /**
     * 时间加 减
     * @param date 日期
     * @param field Calendar.XXX
     * @param num 变化数值
     * @return
     */
    final public static Date dateAdd(Date date, final int field, int num) {
        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, num);
        return cal.getTime();
    }

    /**
     * 时间加 减
     * @param field Calendar.XXX
     * @param num 变化数值
     * @return
     */
    final public static Date nowAdd(final int field, int num) {
        Calendar cal = Calendar.getInstance();
        cal.add(field, num);
        return cal.getTime();
    }




    /**
     * 将毫秒转成当前时间DATE类型
     *
     * @param millis
     * @return
     */
    public static Date getDate(long millis) {
        if (millis == 0L) {
            return null;
        }
        return new Date(millis);
    }

    /**
     * 将Str类型的Date转成Date
     *
     * @param dateStr
     * @param patten
     * @return
     */
    public static Date parse(String dateStr, String patten) {
//        if (StringUtils.isEmpty(dateStr) || StringUtils.isEmpty(patten)) {
//            return null;
//        }
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        try {
            return sdf.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }



    /**
     * 获取一个月的第一天
     *
     * @param month YYYYMM
     * @return YYYY-MM-DD
     */
    public static Date getMonthFirstDay(int month) {
        String dateStr = String.valueOf(month);
        if (dateStr.length() != 6) {
            throw new RuntimeException("month format not YYYYMM, can't get month");
        }
        String yearStr = dateStr.substring(0, 4);
        String monthStr = dateStr.substring(4, 6);
        return parse(yearStr + "-" + monthStr + "-01", YMD);
    }


    /**
     * 获取一个月的最后第一天
     *
     * @param month YYYYMM
     * @return YYYY-MM-DD
     */
    public static Date getMonthLastDay(int month) {
        Date firstDate = getMonthFirstDay(month);
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(firstDate);
        final int lastDay = cDay.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date lastDate = cDay.getTime();
        lastDate.setDate(lastDay);
        return lastDate;
    }



    /**
     * 获取两个日期间的间隔天数
     * 比如20170910，20170911 间隔天数为1
     *
     * @param fromDate 起始日期 yyyyMMdd
     * @param toDate 结束日期 yyyyMMdd
     * @return 间隔天数
     */
    public static long getIntervalDays(Integer fromDate, Integer toDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate from = LocalDate.parse(fromDate.toString(), formatter);
        LocalDate to = LocalDate.parse(toDate.toString(), formatter);
        return ChronoUnit.DAYS.between(from, to);
    }




}
