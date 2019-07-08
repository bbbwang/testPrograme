package com.lenkee.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amettursun on 2019/5/22.
 */
public class TeachTest {
    public static void main(String[] args) {
        System.out.println("total has:"+ fun(2014));
    }

    public static int fun(int total){
        final int[] num = {0};
        int flag = 1;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        // 顺着数
        System.out.println("顺着数：===");
        for (int i = 1; i <= total; i++) {
            if (flag>3)
                flag=1;
            if (flag==3){
                list1.add(i);
                System.out.print(i+"   ");
            }
            flag++;

        }
        System.out.println();
        System.out.println("倒着数=------");
        flag = 1;
        for (int i = total; i >0; i--) {
            if (flag>4)
                flag=1;
            if (flag==3){
                list2.add(i);
                System.out.print(i+"  ");
            }
            flag++;

        }
        list1.stream().forEach(l1->{
            list2.stream().forEach(l2->{
                if(l1.equals(l2)){
                    num[0]++;
                }
            });
        });
        return num[0];
    }

}


