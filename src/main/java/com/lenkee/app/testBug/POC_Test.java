package com.lenkee.app.testBug;

/**
 * Created by amettursun on 2019/9/27.
 */
public class POC_Test {
    public static void main(String[] args) {
        InvokerTransformer it = new InvokerTransformer(
                "exec",
                new Class[]{String.class},
                new Object[]{"open /Users/amettursun/Documents/server_setting/服务器连接信息.md"});
        it.transform(java.lang.Runtime.getRuntime());
    }
}
