package com.silence.dao;

import com.silence.enums.RecordType;
import com.silence.utils.DateUtil;
import com.silence.utils.SDUtil;

import java.io.IOException;

/**
 * @Author: py
 * @Date: 2019-06-25 19:38
 * @Version 1.0
 */
public class PlanDao {

    private SDUtil sdUtil = new SDUtil();

    public int getDays() {
        return DateUtil.getDaysBetweenRange(getPlanDate()[0], getPlanDate()[1]);
    }

    private String[] getPlanDate() {
        String[] dateArr = new String[2];
        try {
            String dateStr = sdUtil.readFromSD(RecordType.PLAN.getPath());
            System.out.println("DATESTR: " + dateStr);
            dateArr = dateStr.split(",");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return dateArr;
    }


    public int getToDoWords() {
        SDUtil sdUtil = new SDUtil();
        int studiedWordsCnt = 0;

        try {
            studiedWordsCnt = Integer.parseInt(sdUtil.readFromSD(RecordType.TODAY.getPath()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        System.out.println("STUDIEDCNT" + studiedWordsCnt);
//        System.out.println("LPEND");
        int cnt = getSigWords() - studiedWordsCnt;
        System.out.println("CNT : " + cnt);
        if (cnt < 0) {
            return 0;
        } else {
            return cnt;
        }

    }

    public int getSigWords() {
        System.out.println("SIGWORDS: " + 3233/getDays());
        return 3233/getDays();
    }


}
