package com.michael.weekdemo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeekHelper {

    private static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * ��ȡĳһ��������
     * @param currentYear
     * @return
     */
    public static List<Week> getWeeksByYear(int currentYear) {
        ArrayList<Week> weekList = new ArrayList<>();
        Date lastDay = DateUtil.getLastDayOfYear(currentYear);
        boolean result = DateUtil.isLastDayInYearLastWeek(lastDay);
        // һ�������53������
        for(int i = 0;i < 53 ; i++){
            Date firstDayOfWeek = DateUtil.getFirstDayOfWeek(currentYear, i);
            Date lastDayOfWeek = DateUtil.getLastDayOfWeek(currentYear, i);

            Calendar startCal = Calendar.getInstance();
            startCal.setTime(firstDayOfWeek);
            Calendar endCal = Calendar.getInstance();
            endCal.setTime(lastDayOfWeek);
            String WeekNumStr = String.valueOf(i + 1);
            if (i < 9) {
                WeekNumStr = "0" + (i + 1);
            }
            Week everyWeek = new Week();
            everyWeek.setBeginYear(startCal.get(Calendar.YEAR));
            everyWeek.setEndYear(endCal.get(Calendar.YEAR));
            everyWeek.setWeekBegin(sdf.format(startCal.getTime()));
            everyWeek.setWeekEnd(sdf.format(endCal.getTime()));
            everyWeek.setWeekNum(WeekNumStr);
            if(i <= 50){
                weekList.add(everyWeek);
            }
            if(i > 50){
                // �ж����һ���Ƿ�����������
                if(DateUtil.isEffectiveDate(lastDay,firstDayOfWeek,lastDayOfWeek)){
                    // �жϸ�������һ��(xxxx��12��31��)���ڵ����ǲ��ǵ�������һ��
                    if(result){
                        weekList.add(everyWeek);
                    }
                    break;
                }else{
                    weekList.add(everyWeek);
                }
            }

        }
        return weekList;
    }
}