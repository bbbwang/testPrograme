package com.lenkee.app;

import org.testng.annotations.Test;

/**
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。

 示例 1:

 输入: "III"
 输出: 3
 示例 2:

 输入: "IV"
 输出: 4
 示例 3:

 输入: "IX"
 输出: 9
 示例 4:

 输入: "LVIII"
 输出: 58
 解释: L = 50, V= 5, III = 3.
 示例 5:

 输入: "MCMXCIV"
 输出: 1994
 解释: M = 1000, CM = 900, XC = 90, IV = 4.

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/roman-to-integer
 */
public class 罗马数字转整数 {
    public  final String values[] = {"I","V","X","L","C","D","M"};
    public  final int num[] = {1,5,10,50,100,500,1000};

    @Test
    public  void main() {

        String roman = "IV";
        int total = 0;
        for (int i = 0; i < roman.length(); i++) {
            total += judgeSymbol(roman,i);
        }
        if (total>3999 || total<1)
            throw  new RuntimeException("符号不符合规范...");
        System.out.println(total);
    }

    //判断数字符号
    public  int judgeSymbol( String str,int index){
        // 判断这个位置符号
        String v = str.charAt(index)+"";
        // 判断后面是否有比它大的符号
        for (int i = index; i < str.length(); i++) {
            if (compare(str.charAt(i)+"",v) == 1)  // 如果后面的比前面大，返回负数
                return -1*getNum(v);
        }
        return getNum(v);
    }
    public  int getNum(String value){
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(value))
                return num[i];
        }
        throw  new RuntimeException("符号不符合规范...");
    }

    // 判断两个符号大小
    public  int compare(String s1, String s2){
        if (s1.equals(s2))
            return 0;
        for (int i = 0; i < values.length; i++) {
            String value = values[i];
            if (value.equals(s1))
                return -1;
            if (value.equals(s2))
                return 1;
        }
        throw  new RuntimeException("符号不符合规范...");
    }
}

