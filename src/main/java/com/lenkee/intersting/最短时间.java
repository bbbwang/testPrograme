package com.lenkee.intersting;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * url:https://www.toutiao.com/i6321295272966095361/
 * 每年双十一/双十二都有很多同学需要去值班，我们现在知道，有n个时间一定需要有人在值班。
 * 现在有k个同学，马老板也知道大家很辛苦，于是强制要求每个人能且仅能值班一段时间，
 * 现在，他想知道，大家最少需要工作多少个小时。

 样例

 n = 7, k = 2

 1 , 2, 7, 10, 11,13,14

 结果为 10， 这两个人分别在[1,2][7,14]的时候进行工作。
 当 k = 3，结果为8 [1,2],[7],[10,14]
 */
public class 最短时间 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Integer> times = new ArrayList();
        System.out.println("please input time, end with 'q'");
        while(true){
            String str = input.next();
            if (str.equals("q"))
                break;
            times.add(Integer.parseInt(str));
        }
        Integer[] cells = times.toArray(new Integer[times.size()]);
        System.out.println("plase input how may people:");
        int nums = input.nextInt();
        System.out.println("最短时间是："+ shortTime(cells, nums));
    }

    public static int shortTime(Integer[] cells, int nums){
        if (nums <= 1)
            return countTimes(cells);
        int mshotTime = countTimes(cells);
        int pos = 0;
        for (int i = 1; i < cells.length - 1; i++) {
            int head =  countTimes( cutArray(cells,0,i) );
            int tail = shortTime(cutArray(cells,i,cells.length), nums - 1);
            if (mshotTime > (head+tail)){
                mshotTime = head+tail;
                pos = i;
            }
        }
        return  mshotTime;
    }

    public static int countTimes(Integer[] cells){
        if (cells.length <= 1 )
            return 1;
        return cells[cells.length-1] - cells[0] + 1;
    }

    // 左闭右开
    public static Integer[] cutArray(Integer[] array, int start, int end){
        int len = end-start;
        List<Integer> list = new ArrayList();
        for (int i = start; i < end; i++) {
            list.add(array[i]);
        }
        return list.toArray(new Integer[list.size()]);

    }

}
