package com.lenkee.app;

/**
 * Created by amettursun on 2019/7/5.
 */
public class 字符串转换整数 {

    public static void main(String[] args) {
        String description = "  ";
        String regx = "[\\+|-]?\\d+";
        System.out.println(function(description));
    }

    public static int function(String str){
        String toNums = null;
        try {
            str = str.trim();
            toNums = regx(str);
            int integer = Integer.parseInt(toNums);
            return integer;
        }catch (Exception e){
            try {
                // 报错信息有两种，1.数字提取失败;2.数字转换失败
                if (toNums == null || "".equals(toNums))
                    return 0;
                if (toNums.equals("+") || toNums.equals("-"))
                    return 0;
                if ((toNums.charAt(0)+"").equals("-"))
                    return Integer.MIN_VALUE;
                else
                    return Integer.MAX_VALUE;
            }catch (Exception e1){
                return 0;
            }
        }

    }

    // 数字提取
    public static String regx(String description){
        try{
            String s = description.charAt(0) + "";
            StringBuilder sb = new StringBuilder();
            if (s.equals("-")||s.equals("+")){
                description = description.substring(1);
                sb.append(s);
            }
            for (int i = 0; i < description.length(); i++) {
                char c = description.charAt(i);
                if (!Character.isDigit(c)){
                    break;
                }
                sb.append(c);
            }
            return sb.toString();
        }catch (Exception e){
            return null;
        }

    }
}
