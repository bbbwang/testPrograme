package com.lenkee.intersting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 字符全排列 {

	public static void main(String[] args) {
		int[] arr = new int[]{1,2,3};
		List<List<Integer>> perm = permute(arr);
		perm.stream().forEach(arrs->{
			arrs.stream().forEach(eme->{
				System.out.print(eme);
			});
			System.out.println();
		});

	}

	public static List<List<Integer>> permute(int[] nums) {
		Integer[] integers = new Integer[nums.length];
		for (int i = 0; i < nums.length; i++) {
			integers[i] = nums[i];
		}
		return perm(integers, 0, integers.length - 1);
	}
	
	//数组全排列,从第k个开始，到第m个结束
	public static List<List<Integer>> perm(Integer[] arr, int k, int m){
		//定义出口
		List<List<Integer>> list = new ArrayList<>();
		if(k>=m){
//			for (int i = 0; i < arr.length; i++) {
//				System.out.print(arr[i]);
//			}
//			System.out.println();
			list.add(new ArrayList<>(Arrays.asList(arr)));
		}else{	//递归调用
			for (int i = k; i <=m ; i++) {
				int tem=arr[i];
				arr[i]=arr[k];
				arr[k]=tem;
				List<List<Integer>> perm = perm(arr, k + 1, m);
				list.addAll(perm);
				tem=arr[i];
				arr[i]=arr[k];
				arr[k]=tem;
			}
		}
		return list;
	}

}
