package com.yadong.springbootproject_1.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {
    public static boolean notNullString(String string){
        return string != null && !"".equals(string.trim());
    }

    public static boolean noNullStringInList(String... string) {
        if(string.length == 0){
            return false;
        }
        for(String str:string){
            if(str == null || "".equals(str.trim()))
                return false;
        }
        return true;
    }

    public static String getTime(String pattern) {
        if(!StringUtils.notNullString(pattern)){
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdfTime = new SimpleDateFormat(pattern);
        return sdfTime.format(new Date());
    }

    public static String getTimeForRandom(String pattern) {
        if(!StringUtils.notNullString(pattern)){
            pattern = "yyyyMMdd";
        }
        SimpleDateFormat sdfTime = new SimpleDateFormat(pattern);
        return sdfTime.format(new Date());
    }

    public static String getRandomNum(Integer n){
        double random = Math.random();
        for(int i = 0;i<n;i++){
            random *= 10;
        }
        Double r = (Double)random;
        Integer i = r.intValue();
        return i.toString();
    }
}
