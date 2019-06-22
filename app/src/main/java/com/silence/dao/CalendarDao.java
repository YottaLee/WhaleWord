package com.silence.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import com.silence.enums.RecordType;
import com.silence.utils.Const;
import com.silence.utils.FileUtils;
import com.silence.utils.SDUtil;
import com.silence.utils.WRUtil;
import com.silence.word.R;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: py
 * @Date: 2019-06-22 19:04
 * @Version 1.0
 */
public class CalendarDao {
    private final static String CALENDAR_NAME = "calendar.txt";



    public String listDay(String year, String month, Context context) {
        SDUtil sdUtil = new SDUtil(context);
        StringBuilder res = new StringBuilder();

        try {
            String dayListStr = sdUtil.readFromSD(CALENDAR_NAME);

            String[] dayArr = dayListStr.split(",");
            if ( dayArr.length == 0 ) {
                return "";
            }
            for (int i = 0; i < dayArr.length - 1; i++) {
                String dayStr = dayArr[i];
                String[] speDayArr = dayStr.split("-");
                if (speDayArr[0].equals(year) && speDayArr[1].equals(month)) {
                    res.append(dayStr).append(",");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res.substring(0, res.length() - 1);
    }


    public void punch(Context context) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        WRUtil wrUtil = new WRUtil();

        String currentDate = dateFormat.format(new Date());
        System.out.println(currentDate);

        try {
            wrUtil.writeFile(context, currentDate, RecordType.CALENDAR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
