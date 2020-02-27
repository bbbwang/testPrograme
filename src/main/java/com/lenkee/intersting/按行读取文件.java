package com.lenkee.intersting;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by amettursun on 2020/1/6.
 */
public class 按行读取文件 {
    public static void main(String[] args) throws IOException {

        //BufferedReader是可以按行读取文件
        FileInputStream inputStream = new FileInputStream("/Users/amettursun/eBay/andr_core/eBayMobile/src/main/res/values/strings.xml");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }

        //close
        inputStream.close();
        bufferedReader.close();

    }

}
