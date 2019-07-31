package com.lenkee.intersting;

import java.util.ArrayList;

public class 一个数求和匹配 {
	/*
	 * 给出一个排序好的数组和一个数，
	 * 求数组中连续元素的和等于所给数的子数组
	 * 
	 */
	public static void main(String[] args) {
		int[] num = {1,2,2,3,4,5,6,7,8,9}; 
        int sum = 11; 
        ArrayList<ArrayList<Integer>> list = fun(num,sum);
        if(list==null)
        	System.out.println("没有找到");
        else
        	 for (ArrayList<Integer> arrayList : list) {
				for (Integer integer : arrayList) {
					System.out.print(integer+"   ");
				}
				System.out.println();
			}
       
		
	}
	public static ArrayList<ArrayList<Integer>> fun(int[] arr,int index){
		ArrayList<ArrayList<Integer>> values = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			int total=0;
			ArrayList<Integer> list = new ArrayList<>();
			for (int j = i; j < arr.length && total<index; j++) {
				total+=arr[j];
				list.add(arr[j]);
				if(total==index)
					break;
			}
			if(total==index)
				values.add(list);
		}
		return values;
		
	}

}
