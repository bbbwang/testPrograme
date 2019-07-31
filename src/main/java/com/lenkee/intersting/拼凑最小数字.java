package com.lenkee.intersting;

public class 拼凑最小数字 {

	public static long value=-1;
	public static void main(String[] args) {
		
		//调用全排列
		 long[] arr={1,5,9,13,442,44,6,21,211}; 
		perm(arr,0,arr.length-1);
		System.out.println(value);
		
	}
	
	//两个数字 拼凑a,b->ab
	public static long fun(long a,long b){
		long bb=b;
		int num=0;//记录b有多少位
		int[] value = new int[20];
		while(bb>0){
			value[num]=(int) (bb%10);
			bb/=10;
			num++;
		}
		for (int i = num-1; i>=0; i--) {
			a = a*10+value[i];
		}
		
		return a;
	}
	

	//全排列
	public static void perm(long[] arr,int k,int m){
		if(k>=m){	//定义出口
			long total=arr[0];
			for (int i = 1; i < arr.length; i++) {
				total=fun(total,arr[i]);
			}
			if(total<value || value==-1)
				value=total;
		}else{
			for (int i = k; i <=m; i++) {
				long tem=arr[i];
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

