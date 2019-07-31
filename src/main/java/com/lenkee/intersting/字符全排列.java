package com.lenkee.intersting;

public class 字符全排列 {

	public static void main(String[] args) {
		int[] arr = new int[]{1,2,3,4};
		perm(arr,0,arr.length-1);
		
	}
	
	//数组全排列,从第k个开始，到第m个结束
	public static void perm(int[] arr,int k,int m){
		//定义出口
		if(k>=m){
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i]);
			}
			System.out.println();
		}else{	//递归调用
			for (int i = k; i <=m ; i++) {
				int tem=arr[i];
				arr[i]=arr[k];
				arr[k]=tem;
				perm(arr,k+1,m);
				tem=arr[i];
				arr[i]=arr[k];
				arr[k]=tem;
			}
		}
	}

}
