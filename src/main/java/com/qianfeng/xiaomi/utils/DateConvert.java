package com.qianfeng.xiaomi.utils;


import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvert implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        System.out.println(s);
        if(TextUtils.empty(s)) {
            return null;
        }
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
