package com.lenkee.intersting;

import java.util.Scanner;

public class 判断扑克是否为顺子 {

	public static void main(String[] args) {
		/*
		 * 从扑克牌中随机抽出5张牌，判断是否为顺子，即这5张牌是否为连续的
		 * 2~10为本身，J为11,以此类推，而大小王为任意数字
		 */

		//我们不妨设大小王为G
		Scanner input = new Scanner(System.in);
		char[] pocker = new char[5];
		System.out.println("请输入任意5张扑克，用空格隔开");
		for (int i = 0; i < pocker.length; i++) {
			pocker[i] = input.next().charAt(0);
		}
		int[] arr = change(pocker);
		if(fun(arr)){
			System.out.println("是顺子");
		}else{
			System.out.println("不是顺子");
		}
			
		
	}
	
	
	//判断是否是顺子
	public static boolean fun(int[] arr){
		//首先扫描有多少个王
		int ghost=0;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]==-1)
				ghost++;
		}
		//排序
		arr = sort(arr);
		//判断是否连续
		for (int i = ghost; i < arr.length-1; i++) {
			if(arr[i+1]==arr[i]+1)
				continue;
			else{
				if(ghost>0)
				{
					ghost--;
					continue;
				}
				return false;
			}
		}
		
		return true;
	}
	
	//将字符串转换成整型数组
	public static int[] change(char[] arr){
		int[] aa= new int[arr.length];
		for (int i = 0; i < aa.length; i++) {
			switch(arr[i]){
			case '2': aa[i]=arr[i]-'0';break;
			case '3': aa[i]=arr[i]-'0';break;
			case '4': aa[i]=arr[i]-'0';break;
			case '5': aa[i]=arr[i]-'0';break;
			case '6': aa[i]=arr[i]-'0';break;
			case '7': aa[i]=arr[i]-'0';break;
			case '8': aa[i]=arr[i]-'0';break;
			case '9': aa[i]=arr[i]-'0';break;
			case '1': aa[i]= 10;break;
			case 'j': aa[i]=11;break;
			case 'q': aa[i]=12;break;
			case 'k': aa[i]=13;break;
			case 'a': aa[i]=14;break;
			case 'g': aa[i]=-1;break;
			}
		}
		return aa;
		
	}
	
	//冒泡排序
	public static int[] sort(int[] arr){
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length-i-1; j++) {
				if(arr[j]>arr[j+1]){
					int tem=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=tem;
				}
			}
		}
		return arr;
	}

}
