package com.lenkee.app;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by amettursun on 2019/7/22.
 */
public class 数组去重复总结 {
    public static void main(String[] args) {
        String str = "11,23,44,55,22,22,11,33,44,,";
        String[] strings = fun1(str.split(","));
        System.out.println(Arrays.toString(strings));
    }
    // 加入set排序，但是是无序排列
    public static String[] fun1(String array[]){
        Set<String> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }
        String[] arrayResult = (String[]) set.toArray(new String[set.size()]);
        return arrayResult;
    }
}
