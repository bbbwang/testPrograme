package com.lenkee.intersting;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amettursun on 2019/8/28.
 */
public class 文件行读取 {

    public static void main(String[] args) throws IOException
    {
        String filePath  = "/Users/amettursun/Desktop/remote.json";
        List<String> lines = new ArrayList<>();
        List<String> list = readLines(filePath);
        System.out.println(list.toArray());


    }

    private static List<String> readLines(String filePath ) throws IOException {
        List<String> lines = new ArrayList<>();
        //BufferedReader是可以按行读取文件
        FileInputStream inputStream = new FileInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String str = null;
        while((str = bufferedReader.readLine()) != null)
        {
            lines.add(str);
        }

        //close
        inputStream.close();
        bufferedReader.close();
        return lines;
    }
}
