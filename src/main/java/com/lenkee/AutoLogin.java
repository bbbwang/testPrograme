package com.lenkee;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by amettursun on 2019/12/6.
 */
public class AutoLogin {
    public static void main(String args[]) throws Exception {
        String username="testtest";
        String password="12345678";
        String random="";

        // 调用验证码接口获取验证码

        // 调用聚合数据识别验证码

        //模拟登录

        //创建 CloseableHttpClient 对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        //创建 the RequestBuilder 对象
        RequestBuilder reqbuilder = RequestBuilder.post();

        //设置 URI 参数
        RequestBuilder reqbuilder1 = reqbuilder.setUri("http://sso.wyschina.com/SSOAuth");
        RequestBuilder reqbuilder2 = reqbuilder1.addParameter("username",
                username).addParameter("password", password)
                .addParameter("random",random).addParameter("url","http://w.wyschina.com/custom/login.action?action=http%3A%2F%2Fw.wyschina.com%2Fhome%2Fhome.action");

        //Building 对象
        HttpUriRequest httppost = reqbuilder2.build();

        //执行excute
        HttpResponse httpresponse = httpclient.execute(httppost);

        //输出返回内容
        System.out.println(EntityUtils.toString(httpresponse.getEntity()));
        System.out.println(httpresponse.getStatusLine());

        HttpEntity entity = httpresponse.getEntity();
        System.out.println(EntityUtils.toByteArray(entity).toString());
    }

    public void getRandom() throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(new HttpGet("http://sso.wyschina.com/login.jsp?url=http%3A%2F%2Fw.wyschina.com%2Fcustom%2Flogin.action%3Faction%3Dhttp%253A%252F%252Fw.wyschina.com%252Fhome%252Fhome.action"));
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        System.out.println(responseString);
    }



}
