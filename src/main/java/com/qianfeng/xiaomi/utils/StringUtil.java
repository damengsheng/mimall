package com.qianfeng.xiaomi.utils;

public class StringUtil {

    /**
     * 判断字符串是否为空
     * @param resource
     * @return
     */
    public static boolean isEmpty(String resource) {
        return resource == null || resource.equalsIgnoreCase("");
    }


}
