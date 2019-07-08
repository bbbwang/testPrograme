package com.lenkee.app;

import java.io.UnsupportedEncodingException;

/**
 * Created by amettursun on 2019/7/4.
 */
public class StringTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        function("人ABC",4);
    }

    public static void function(String str, int i) throws UnsupportedEncodingException {
        int num = 0;
        byte[] b = str.getBytes("gbk");//默认UTF-8 中文一般三个字节表示，gbk两个字节 变化就是%3
        if(b[i-1] > 0){
            System.out.println(new String(b,0,i,"gbk"));
        }
        else{
            for(int j = 0; j < i; j++){
                if(b[j] < 0){
                    num++;
                    num = num % 2;
                }
            }
            System.out.println(new String(b,0,i-num,"gbk"));
        }
    }
}
