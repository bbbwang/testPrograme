package com.lenkee.app.testBug;

import java.io.*;

/**
 * Created by amettursun on 2019/9/27.
 */
public class ReSerializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        UnsafeClass Unsafe = new UnsafeClass();
        Unsafe.name = "Mi1k7ea";

        System.out.println("[*]序列化对象");
        FileOutputStream fos = new FileOutputStream("object.ser");
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(Unsafe);
        os.close();
        fos.close();

        System.out.println("[*]反序列化文件中保存的序列化对象");
        FileInputStream fis = new FileInputStream("object.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        UnsafeClass objectFromDisk = (UnsafeClass)ois.readObject();
        System.out.println(objectFromDisk.name);
        ois.close();
        fis.close();
    }
    }

class UnsafeClass implements Serializable {
    public String name;
    //重写readObject()方法
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
        //执行默认的readObject()方法
        in.defaultReadObject();
        //执行命令
        Runtime.getRuntime().exec("open /Users/amettursun/Documents/server_setting/服务器连接信息.md");
    }
}