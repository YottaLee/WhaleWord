package com.silence.utils;

/**
 * Created by Autumn on 2019/6/11 0011.
 */
public interface Const {
    String META_KEY = "com.silence.table.meta";
    String UNIT_KEY = "com.silence.unit.key";
    String WORD_KEY = "com.silence.word.key";
    String DIC_KEY = "com.silence.dic.key";
    String WORDS_NMET = "TABLE_NMET";
    String WORDS_CET4 = "TABLE_CET4";
    String WORDS_CET6 = "TABLE_CET6";
    String WORDS_IETSL = "TABLE_IETSL";
    String WORDS_GRE = "TABLE_GRE";
    String DIC_ALL = "DIC_ALL";
    String DIC_STUDIED = "DIC_STUDIED";
    String DIC_UNFAMILIAR = "DIC_UNFAMILIAR";
    String DIC_HANDLED = "DIC_HANDLED";
    String SP_KEY = "exist";
    String DB_NAME = "words.db";
    String DB_DIR = "databases";
    long DELAY_TIME = 1000;
    String PLAY_SPEED = "play_speed";
    String AUTO_SPEAK = "auto_speak";
    String[] META_KEYS = {WORDS_NMET, WORDS_CET4, WORDS_CET6, WORDS_IETSL, WORDS_GRE};
}
