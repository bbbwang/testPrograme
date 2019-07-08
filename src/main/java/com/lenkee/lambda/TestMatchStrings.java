package com.lenkee.lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by amettursun on 2019/6/12.
 */
public class TestMatchStrings {
    public static void main(String[] args) {
        String xml = "/Users/amettursun/Desktop/diff-ios.xml";
        String dev = "/Users/amettursun/Desktop/remot.xml";
        List<String> commands = readLine(xml);
        List<String> remot = readLine(dev);
        StringBuffer combine = combine(commands);
        String diffIos = combine.toString();

        remot.stream().forEach(remo->{
//            System.out.println(remo);
            if (remo != null)
            if (!diffIos.contains(remo)){
                System.out.println("不存在："+remo);
            }
        });
    }

    public static StringBuffer combine(List<String> strings){
        StringBuffer sb = new StringBuffer();
        strings.stream().forEach(s -> {
            sb.append(s);
        });
        return sb;
    }

    public static List<String> readLine(String filePath) {
        List<String> strings = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(filePath));
            String str;
            while ((str = in.readLine()) != null) {
                strings.add(str);
            }
            strings.add(str);
        } catch (IOException e) {
        }
        return strings;
    }

    public static String filter(String pre){
        try{
            String newString = pre;
            if (newString != null){
                String reg = "<string name=.*\">";
                newString = regx(pre, reg);
                newString = newString.split("\"")[1];
            }
            return newString;
        }catch (Exception e){
            return  null;
        }
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
