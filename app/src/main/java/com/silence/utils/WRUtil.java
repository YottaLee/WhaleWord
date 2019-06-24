package com.silence.utils;

import android.content.Context;
import com.silence.enums.RecordType;

/**
 * @Author: py
 * @Date: 2019-06-22 20:44
 * @Version 1.0
 */
public class WRUtil {

    public void writeFile(Context context, String content, RecordType type) {
        SDUtil sdUtil = new SDUtil(context);
        String fileName = type.getPath();
        try {
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
