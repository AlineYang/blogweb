package com.blogsweb.web.blogsweb.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @类名: StringUtils
 * @包名: com.blogsweb.web.blogsweb.utils
 * @IDE的名称: IntelliJ IDEA
 * @当前项目的名称: blogsweb
 * @作者: 杨冕
 * @时间: 2020/5/13 17:18
 * @版本: 1.0.0
 * <p>说明: </p>
 */
public class StringUtils {

    /**
     * 描述：是否是邮箱.
     *
     * @param str 指定的字符串
     * @return 是否是邮箱:是为true，否则false
     */
    public static Boolean isEmail(String str) {
        Boolean isEmail = false;
        String expr = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$";

        if (str.matches(expr)) {
            isEmail = true;
        }
        return isEmail;
    }

    /**
     * 判断是否是手机号
     *
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone) {
        Pattern pattern = Pattern
                .compile("^(13[0-9]|15[0-9]|153|15[6-9]|180|18[23]|18[5-9])\\d{8}$");
        Matcher matcher = pattern.matcher(phone);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
