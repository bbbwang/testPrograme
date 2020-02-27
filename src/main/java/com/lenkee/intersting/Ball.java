package com.lenkee.intersting;

import java.util.List;

/**
 * Created by amettursun on 2020/2/9.
 */
public class Ball {
    public static int balls[][] = new int[4][3];
    public static int step = 1;
    public static void main(String[] args) {

        Integer num[] = {1,2,3
                          ,4,
                         5,6,7
                          ,8,
                        9,10,11};
        List<Integer[]> arrange = 全排列.arrange(num);
        arrange.stream().forEach(num2->{
            boolean b1 = num2[0] + num2[1] + num2[2] == 18;
            boolean b2 = num2[4] + num2[5] + num2[6] == 18;
            boolean b3 = num2[8] + num2[9] + num2[10] == 18;
            boolean b4 = num2[1] + num2[3] + num2[5] + num2[7] + num2[9] == 18;

            if (b1 && b2 && b3 && b4){
                for (int i = 0; i < num2.length; i++) {
                    System.out.print(num2[i]+" ");
                }
                System.out.println();
            } 
        });

        

    }

    public static boolean duplicate(int balls[][]){
        int temp = -1;
        for (int i = 0; i < balls.length; i++) {
            for (int j = 0; j < balls[0].length; j++) {
                temp = balls[i][j];
                for (int k = 0; k < balls.length; k++) {
                    for (int l = 0; l < balls[0].length; l++) {
                        if (i==k && j==l)
                            continue;
                        if (temp == balls[k][l])
                            return false;
                    }
                }
            }
        }
        return true;
    }


}
