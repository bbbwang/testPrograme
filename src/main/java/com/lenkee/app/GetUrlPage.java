package com.lenkee.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by amettursun on 2019/8/14.
 */
public class GetUrlPage {


    public static void main(String[] args) {
        try {
            //建立连接
            URL url = new URL("https://blog.csdn.net/HLK_1135/article/details/53968002");
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //获取输入流
            InputStream input = httpUrlConn.getInputStream();
            //将字节输入流转换为字符输入流
            InputStreamReader read = new InputStreamReader(input, "utf-8");
            //为字符输入流添加缓冲
            BufferedReader br = new BufferedReader(read);
            // 读取返回结果
            String data = br.readLine();
            while (data != null) {
                System.out.println(data);
                data = br.readLine();
            }
            // 释放资源
            br.close();
            read.close();
            input.close();
            httpUrlConn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
