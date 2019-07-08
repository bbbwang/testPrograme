package com.lenkee.lambda;

import java.util.Scanner;

/**
 * Created by amettursun on 2019/6/11.
 */
public class StringsTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str[] = new String[3];
        while (true){
           try{
               str[0] = input.nextInt()+"";
               str[1] = input.next();

               System.out.println(str[1]);
           }catch (Exception e){

           }

        }
    }

}
