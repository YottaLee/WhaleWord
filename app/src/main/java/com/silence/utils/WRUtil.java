package com.silence.utils;

import android.content.Context;
import com.silence.enums.RecordType;

/**
 * @Author: py
 * @Date: 2019-06-22 20:44
 * @Version 1.0
 */
public class WRUtil {

    private final static String DAY_NAME = "day.txt";
    private final static String TODAY_NAME = "today.txt";
    private final static String WORD_NAME = "word.txt";
    private final static String CALENDAR_NAME = "calendar.txt";
    private final static String PLAN_NAME = "plan.txt";


    public void writeFile(Context context, String content, RecordType type) {
        SDUtil sdUtil = new SDUtil(context);
        String fileName = "";
        try {
            switch (type) {
                case DAY:
                    fileName = DAY_NAME;
                    break;
                case WORD:
                    fileName = WORD_NAME;
                    break;
                case TODAY:
                    fileName = TODAY_NAME;
                    break;
                case CALENDAR:
                    fileName = CALENDAR_NAME;
                    break;
                case PLAN:
                    fileName = PLAN_NAME;
                    break;
            }
            if (type.equals(RecordType.CALENDAR)) {
                sdUtil.appendFileToSD(fileName, content);
                //   sdUtil.saveFileToSD(fileName, content);
            } else {
                sdUtil.saveFileToSD(fileName, content);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
