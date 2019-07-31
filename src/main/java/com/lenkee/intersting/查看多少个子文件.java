package com.lenkee.intersting;

import java.io.File;

public class 查看多少个子文件 {
	public static void main(String[] ages)
{
    File file=new File("J:\\百度云盘\\高数强化班汤家凤");
    tree(file);
    System.out.println("总共有"+Counter+"个文件");
}
static int Counter=0;//用于计数文件夹中所有文件数量
static void tree(File f){
    File[] fa=f.listFiles();
    for(int i=0;i<fa.length;i++){
          Counter++;
        if(fa[i].isDirectory()){
            tree(fa[i]);
        }
     
    }
}

}
