package com.yadong.springbootproject_1.util;


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
}
