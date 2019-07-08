package com.lenkee.lambda;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by amettursun on 2019/5/22.
 */
public class RegexTest {
    public static void main(String[] args) {
        String description = "我的电话是：13888888888，有事联系";
        String reg = "(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{7,14}"; // 手机电话的正则表达式
        System.out.println(regx(description, reg));
    }
    public static String regx(String description, String regx){
        try {
            Pattern p=Pattern.compile(regx);
            Matcher matcher = p.matcher(description);
            if (matcher.find()) {
                String group = matcher.group(0);  // 找到第一个匹配到项
                return  group;
            }else
                return null;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

}


