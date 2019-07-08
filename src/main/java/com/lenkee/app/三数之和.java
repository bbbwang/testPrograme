package com.lenkee.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amettursun on 2019/7/2.
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

 满足要求的三元组集合为：
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/3sum
 */
public class 三数之和 {
    public static void main(String[] args) {
       int nums[] = {0,3,0,1,1,-1,-5,-5,3,-3,-3,0};
        System.out.println("---------------");
        List<List<Integer>> function = function(nums);
        function.stream().forEach(data->{
            System.out.println(data);
        });
    }

    public static List<List<Integer>> function(int nums[]){
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                for (int k = 0; k < nums.length; k++) {
                    if (i==j || i==k || j==k)
                        continue;
                    if (nums[i] + (nums[j]+nums[k]) ==0){
                        List<Integer> data = new ArrayList<>();
                        data.add(nums[i]);
                        data.add(nums[j]);
                        data.add(nums[k]);
                        result.add(data);
                    }
                }
            }
        }
        // 去重复xx
        List<Integer> sames = new ArrayList<>();
        List<List<Integer>> result2 = new ArrayList<>();
        for (int i = 0; i < result.size()-1; i++) {
            for (int j = i+1; j < result.size(); j++) {
                List<Integer> pre = result.get(i);
                List<Integer> nex = result.get(j);
                if (contain(sames,i) || contain(sames,j))
                    continue;
                if (isSame(pre,nex)){
                    sames.add(j);
                }
            }
        }
        for (int i = 0; i < result.size(); i++) {
            if (contain(sames,i))
                continue;
            result2.add(result.get(i));
        }
        return result;

    }

    public static boolean contain(List<Integer> list, int num){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == num)
                return true;
        }
        return false;
    }

    public static boolean isSame(List<Integer> pre, List<Integer> nex){
        Integer[] array1 =  pre.toArray(new Integer[pre.size()]);
        Integer[] array2 =  nex.toArray(new Integer[nex.size()]);
        int index = 0;
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                if (array1[i] == array2[j]){
                    index++;
                    break;
                }
            }
        }
        return (index == 3);
    }


}

