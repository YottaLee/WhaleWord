package com.silence.utils;

import com.google.gson.Gson;
/**
 * @Author: py
 * @Date: 2019-06-25 13:21
 * @Version 1.0
 */
public class GsonUtil {

    public static <T> T getBean(String jsonStr, Class<T> tClass){
        Gson gson = new Gson();
        T result = gson.fromJson(jsonStr, tClass);
        return result;
    }

    public static String toJson(Object o){
        Gson gson = new Gson();
        return gson.toJson(o);
    }

}
