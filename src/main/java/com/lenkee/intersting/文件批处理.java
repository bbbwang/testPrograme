package com.lenkee.intersting;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 删掉文件夹内所有自定义内容
 * @author BOB
 *
 */
public class 文件批处理 {
	static int Counter=0;//用于计数文件夹中所有文件数量
	static int made=0,premade=0;
	public static void main(String arg[]) throws InterruptedException{
		Scanner input = new Scanner(System.in);
		System.out.print("请输入修改文件夹/文件路径：");
		String url = input.next();
		String url2 = url.replaceAll("\\\\","/");
		File file = new File(url2);
		if(!file.isDirectory()||file.isFile()){
			System.out.println("未找到文件或文件夹！");
			return;
		}
		System.out.print("您要修改的内容:");
		String str = input.next();
		System.out.println("输入修改后内容：");
		String str2= input.next();
		System.out.println(url2);
		tree(file);
		System.out.println("请稍后..");
		updateFiles(file, str,str2);
		System.out.println(">");
		System.out.println("修改成功！");
	}
	
	
	static void tree(File f){
	    File[] fa=f.listFiles();
	    for(int i=0;i<fa.length;i++){
	          Counter++;
	        if(fa[i].isDirectory()){
	            tree(fa[i]);
	        }
	     
	    }
	}
	
	
	/**
	 * 递归删除文件夹内指定字符串
	 * @param url 指定目录
	 * @param str 删除的内容
	 * @throws InterruptedException 
	 */
	public  static  void updateFiles(File file,String str,String str2) throws InterruptedException{
		//先修改文件名称，再递归子文件
		String name = file.getName();
		file = rName(file, cutName(name, str,str2));
		made++;
		if(made==(premade+Counter/10)){
			System.out.print("-");
			Thread.sleep(20);
			premade=made;
		}
		File[] childs = getChilds(file);
		if(childs!=null){	//如果是目录，继续递归
			for (File file2 : childs) {
				updateFiles(file2, str,str2);
			}
		}
		return;
	}
	/**
	 * 获取子文件
	 * @param url
	 * @return
	 */
	public static File[] getChilds(File file){
		//获取文件夹的所有子文件
		File[] files = null;
		if(file.isDirectory()){
//			System.out.println("-------  "+file.getName()+"  是一个文件夹------");
			files = file.listFiles();
//				int i=1;
//			for (File file2 : files) {
//				System.out.print("他的子文件有：  "+i++);
//				System.out.print(file2.getName()+"  --  ");
//			}
//			i=1;
//			System.out.println();
		}
		return files;
	}
	
	/**
	 * 名字串截取
	 * @param name 原文件名
	 * @param str  要修改的文件名
	 * param str2      修改后的文件名
	 * @return
	 */
	public static String cutName(String name,String str,String str2){
		if(name.contains(str)){
			int start = name.indexOf(str);	//截取开始位置
			int end = start+str.length();	//截取结束位置
			StringBuffer head = new StringBuffer(name.substring(0, start));	//前半部分
			StringBuffer tail = new StringBuffer(name.substring(end, name.length()));	//后 半部分
			StringBuffer newName = head.append(str2).append(tail);
			return newName.toString();
		}
		return name;
	}
	
	//将文件、文件夹重命名
	public static File rName(File file ,String name){
		File fileNew = new File(file.getParent()+"/"+name);
		file.renameTo(fileNew);
//		System.out.println("重命名后路径:"+fileNew.getPath());
		return fileNew;
	}
	
}
