package com.silence.dao;

import com.silence.enums.RecordType;
import com.silence.utils.DateUtil;
import com.silence.utils.SDUtil;

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
            dateArr = dateStr.split(",");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return dateArr;
    }

}
