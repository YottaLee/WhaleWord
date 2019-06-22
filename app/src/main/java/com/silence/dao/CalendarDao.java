package com.silence.dao;

import android.content.Context;
import com.silence.utils.FileUtils;
import com.silence.word.R;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: py
 * @Date: 2019-06-22 19:04
 * @Version 1.0
 */
public class CalendarDao {
    public List<String> listCalendar(Context context) {
        List<String> dateList = new ArrayList<>();
        try {

            InputStream inputStream = context.getResources().openRawResource(R.raw.calendar);
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(inputStream, "gbk");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = "";
            try {
                while ((line = reader.readLine()) != null) {
                    dateList.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dateList;
    }


    public String listDay(String year, String month, Context context) {
        StringBuilder res = new StringBuilder();
        List<String> dayList;
        dayList = listCalendar(context);
        for(String day: dayList) {
            String[] dayArr = day.split("-");
            String curYear = dayArr[0];
            String curMon = dayArr[1];
            if (curYear.equals(year) && curMon.equals(month)) {
                res.append(day).append(",");
            }
        }
        return res.substring(0, res.length() - 1);
    }
}
