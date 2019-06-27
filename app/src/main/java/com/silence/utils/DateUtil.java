package com.silence.utils;

/**
 * @Author: py
 * @Date: 2019-06-25 19:16
 * @Version 1.0
 */
import android.annotation.SuppressLint;
import com.silence.enums.RecordType;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static int getDaysBetweenRange(String beginDate, String endDate) {
        long betweenDays = 0;
        try {

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //跨年不会出现问题
            //如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 0
            Date fDate = sdf.parse(beginDate);
            Date oDate = sdf.parse(endDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(fDate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(oDate);
            long time2 = cal.getTimeInMillis();
            betweenDays = (time2-time1)/(1000*3600*24);

//            System.out.println("f: " + fDate);
//            System.out.println(oDate);
//            days = (int) (oDate.getTime() - fDate.getTime()) / (1000 * 3600 * 24);

            System.out.print("DAYS" + betweenDays);

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return (int)betweenDays;
    }

    public static int getMinutes() {
        int min = 0;
        SDUtil sdUtil = new SDUtil();

        try {
            String minStr = sdUtil.readFromSD(RecordType.TODAY.getPath());
            min = Integer.parseInt(minStr) * 2;

        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

        return min;
    }




}
