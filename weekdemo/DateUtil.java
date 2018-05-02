package com.michael.weekdemo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    public static final long SEC_MILL_SECONDS = 1000;
    public static final long MINUTE_MILL_SECONDS = 60 * SEC_MILL_SECONDS;
    public static final long HOUR_MILL_SECONDS = 60 * MINUTE_MILL_SECONDS;
    public static final long DAY_MILL_SECONDS = 24 * HOUR_MILL_SECONDS;

    // ��ȡĳ��ĵڼ��ܵĿ�ʼ����
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar c = getFirstDayOfWeek(year);
        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    // ��ȡĳ��ĵڼ��ܵĽ�������
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar c = getFirstDayOfWeek(year);
        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return getLastDayOfWeek(cal.getTime());
    }

    /** ��ȡһ�꿪ʼ�ĵ�һ�ܵ���һ
     *  ��һ���������������Ͼ����Ǹ���ĵ�һ��,
     *  ���������һ������һ��(���ڿ����жϵ�һ�ܿ�ʼʱ��)
     * @param year
     * @return
     */
    public static Calendar getFirstDayOfWeek(int year){
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.JANUARY, 1, 0, 0, 0);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        // ���1��1�������壬�����������գ���ô��һ������һ�����һ�ܣ����¼������ĵ�һ�ܿ�ʼ����һ
        if(dayOfWeek == Calendar.FRIDAY || dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY){
            while(c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY){
                c.add(Calendar.DAY_OF_YEAR, 1);
            }
        }
        return c;
    }

    // ��ȡ��ǰʱ�������ܵĿ�ʼ����
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }

    // ��ȡ��ǰʱ�������ܵĽ�������
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }

    /**
     * �жϸ�������һ��(xxxx��12��31��)���ڵ����ǲ��ǵ�������һ��,
     * ���������һ��ĵ�һ��
     */
    public static boolean isLastDayInYearLastWeek(Date lastDate){
        boolean result = false;
        Calendar c = new GregorianCalendar();
        c.setTime(lastDate);
        int lastDay = c.get(Calendar.DAY_OF_WEEK);
        // ��lastDay�����ġ��塢�����գ� �������ܾ����Ǹ�������һ��
        if(lastDay == Calendar.THURSDAY || lastDay == Calendar.FRIDAY || lastDay == Calendar.SATURDAY || lastDay == Calendar.SUNDAY){
            result = true;
        }
        return result;
    }

    // ��ȡһ������һ��
    public static Date getLastDayOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 0, 0, 0);
        return c.getTime();
    }

    /**
     * �жϵ�ǰʱ���Ƿ���[startTime, endTime]���䣬ע��ʱ���ʽҪһ��
     *
     * @param nowTime ��ǰʱ��
     * @param startTime ��ʼʱ��
     * @param endTime ����ʱ��
     * @return
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if(Math.abs(nowTime.getTime() - startTime.getTime()) < 1000 ||
                Math.abs(nowTime.getTime() - endTime.getTime()) < 1000){
            return true;
        }

        Calendar date = new GregorianCalendar();
        date.setTime(nowTime);

        Calendar begin = new GregorianCalendar();
        begin.setTime(startTime);

        Calendar end = new GregorianCalendar();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
}