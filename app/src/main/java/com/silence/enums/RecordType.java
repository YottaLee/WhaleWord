package com.silence.enums;

/**
 * @Author: py
 * @Date: 2019-06-22 20:55
 * @Version 1.0
 */
public enum RecordType {
    DAY("day.txt"), TODAY("today.txt"), CALENDAR("calendar.txt"),
    WORD("word.txt"), PLAN("plan.txt"), WORD_LIST("wordlist.txt");

    private String path;

    private RecordType(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
