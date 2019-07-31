package com.lenkee.intersting;

/**

 标题：纸牌三角形

 A,2,3,4,5,6,7,8,9 共9张纸牌排成一个正三角形（A按1计算）。要求每个边的和相等。
 下图就是一种排法（如有对齐问题，参看p1.png）。


 9 6
 4   8
 3 7 5 2

 这样的排法可能会有很多。

 如果考虑旋转、镜像后相同的算同一种，一共有多少种不同的排法呢？

 请你计算并提交该数字。
 */
public class 等边三角形 {
    public static void main(String[] args) {
        String[] arr = {"1","2","3","4"};
        String[] fun = fun(arr);
        for (int i = 0; i < fun.length; i++) {
            System.out.println(fun[i]);
        }
        }

   /* public static int[][] step(int[][] arr){
        int arr2[][] = new int[arr.length*(arr[0].length+1)][arr[0].length+1];  // 新产生的排序
        if (arr.length == 1){
            arr2 = {arr}
        }
        for (int i = 0; i < arr.length; i++) {

        }

        return arr2;
    }*/

    public static String[] fun(String arr[]){
        if (arr.length ==1)
            return  arr;
        String[] arr2 = new String[arr[0].length()+1*arr.length];
        int num = 0;
        for (int i = 0; i < arr.length; i++) { // 插入位置
            // 子数组排序
            String[] sub = fun(cutArray(arr, 1, arr.length));
            for (int j = 0; j < sub.length; j++) {
                arr2[num++] = sub[j].substring(0,j)+arr[i]+sub[j].substring(j);
            }
        }
        return arr2;
    }

    // 左闭右开
    public static String[] cutArray(String[] array, int start, int end){
        int len = end-start;
        String list[] = new String[len];
        int num = 0;
        for (int i = start; i < end; i++) {
            list[num++] = array[i];
        }
        return list;

    }


}
