package com.lenkee.intersting;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amettursun on 2019/11/6.
 */
public class 统计单词个数 {
    public static void main(String[] args) {
        String str="aa hello world aa hello eh";
        countWord(str);
    }

    public static void countWord(String str){
        Map<String, Integer> map = new HashMap<>();
        String[] data = str.split(" ");
        for (String word : data) {
            map.put(word, map.containsKey(word) ? map.get(word) + 1 : 1);
        }
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
    }
}
