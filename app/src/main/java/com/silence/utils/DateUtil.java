package com.silence.utils;

/**
 * @Author: py
 * @Date: 2019-06-25 19:16
 * @Version 1.0
 */
import android.annotation.SuppressLint;
import com.silence.enums.RecordType;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static int getDaysBetweenRange(String beginDate, String endDate) {
        int days = 0;
        try {

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //跨年不会出现问题
            //如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 0
            Date fDate = sdf.parse(beginDate);
            Date oDate = sdf.parse(endDate);
            days = (int) (oDate.getTime() - fDate.getTime()) / (1000 * 3600 * 24);

            //  System.out.print(days);

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return days;
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
