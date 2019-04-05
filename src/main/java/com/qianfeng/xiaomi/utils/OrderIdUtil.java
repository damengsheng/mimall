package com.qianfeng.xiaomi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderIdUtil {

    public static String getOrderId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        String orderId = sdf.format(new Date());
        return orderId;
    }
}
