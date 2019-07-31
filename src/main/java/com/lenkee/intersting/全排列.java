package com.lenkee.intersting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amettursun on 2019/7/3.
 */
public class 全排列 {
    public static void main(String[] args) {
        Integer[] element = {1,2,3,4,5};
        List<Integer[]> insert = arrange(element);
        System.out.println("size:"+insert.size());
        for (int i = 0; i < insert.size(); i++) {
            Integer[] integers = insert.get(i);
            for (int j = 0; j < integers.length; j++) {
                System.out.print(integers[j]);
            }
            System.out.println();
        }
    }

    public static List<Integer[]> arrange(Integer[] array){
        if (array.length <= 1){
            List<Integer[]> list = new ArrayList<>();
            list.add(array);
            return list;
        }
        List<Integer[]> result = new ArrayList<>();
        Integer[] pop = pop(array, array.length-1);
        // 下一级所有排列
        List<Integer[]> arrange = arrange(pop);
        for (int i = 0; i < arrange.size(); i++) {
            // 列出所有插入中间情况
            Integer[] integers = arrange.get(i);
            List<Integer[]> subList = insert(integers, array[array.length - 1]);
            result.addAll(subList);
        }

        return result;
    }

    public static List<Integer[]> insert(Integer[] nums, Integer num){
        List<Integer[]> result = new ArrayList<>();
            for (int k = 0; k <= nums.length; k++) {
                Integer[] temp = new Integer[nums.length+1];
                int index = 0;
                for (int j = 0; j < nums.length ; j++) {
                    if (index == k){
                        temp[index++] = num;
                        j--;
                        continue;
                    }
                    temp[index++] = nums[j];
                }
                if (index == nums.length)
                    temp[temp.length-1] = num;
                result.add(temp);
            }

        return result;
    }
    public static Integer[] pop(Integer[] nums, int index){
        Integer[] result = new Integer[nums.length-1];
        int conut = 0 ;
        for (int i = 0; i < nums.length ; i++) {
            if (i == index)
                continue;
            result[conut++] = nums[i];
        }
        return result;
    }
}
