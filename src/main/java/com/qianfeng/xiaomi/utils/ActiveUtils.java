package com.qianfeng.xiaomi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ActiveUtils {

    public static String createActiveCode() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        String format = sdf.format(date);
        String s2 = Integer.toHexString((int)(Math.random()*900) + 100); 
        return format + s2;
    } 
}
