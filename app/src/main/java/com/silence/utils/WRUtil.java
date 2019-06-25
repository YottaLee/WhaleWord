package com.silence.utils;

import android.content.Context;
import com.silence.enums.RecordType;

import java.util.ArrayList;
import java.util.List;

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

//    public List<String> listWord() {
//        String[] wordArr = Const.WORD_LIST_STR_HALF0.split("\n");
//        List<String> wordList = new ArrayList<>();
//        for (String str: wordArr) {
//            System.out.println(str);
//            wordList.add(str);
//        }
//
//        return wordList;
//    }


}
