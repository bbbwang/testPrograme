package com.lenkee.intersting;

import java.util.ArrayList;
import java.util.List;

public class 倒水问题 {

	/*
	 * 有三个容积分别为3,5,8升的水桶，其中容积为8升的水桶中装了水，容积为3,5的水桶为空。
	 * 水桶没有刻度尺，现在需要将水桶中的8升水等分成2分，每份都是4升水，该怎么分。
	 * 总共有多少种分方法
	 */
	
	public static void main(String[] args) {
		Action a = new Action();
		a.bucket[0]=8;
		a.bucket[1]=0;
		a.bucket[2]=0;
		fun(a);
		System.out.println("共有:"+num+"种");

	}
	//水桶规则
	public static final int[]rule = {8,5,3};
	//记录有多少种分法
	public static int num=0;
	//分水执行的动作，包括水桶状态，分水的情况
	//----------------------------------------------------------------
	//倒水情况
	public static void fun(Action action){
		//出口,当平分，或者回路时退出
		for (int[] r : action.rout) {
			if(isequal(r, action.bucket))	//如果两个水桶水相同，即走到了回路，停止
					return;
		}
		//添加新路径
		{
			int []t = new int[3];
			for (int i = 0; i < action.bucket.length; i++) {
				t[i]=action.bucket[i];
			}
			action.rout.add(t);
		}
		//判断平分
		if(action.bucket[0]==4 && action.bucket[1]==4){
			for (int[] r : action.rout) {
				System.out.print(r[0]+":"+r[1]+":"+r[2]+":"+"--->");
			}
			System.out.println();
			num++;
			return;
		}
		//---------
		//6种倒法
		if(canPour(0, 1, action))	//1倒2
		{
			Action tem = new Action();	//临时存储，一会儿还要变回去
			copy(tem,action);
			//倒水
			pour(0, 1, action);	
			fun(action);
			action=tem;
		}
		if(canPour(0, 2, action))	//1倒3
		{
			Action tem = new Action();	//临时存储，一会儿还要变回去
			copy(tem,action);
			//倒水
			pour(0, 2, action);
			fun(action);
			action=tem;
		}
		if(canPour(1, 0, action))	//2倒1
		{
			Action tem = new Action();	//临时存储，一会儿还要变回去
			copy(tem,action);
			//倒水
			pour(1, 0, action);
			fun(action);
			action=tem;
		}
		if(canPour(1, 2, action))	//2倒3
		{
			Action tem = new Action();	//临时存储，一会儿还要变回去
			copy(tem,action);
			//倒水
			pour(1, 2, action);
			fun(action);
			action=tem;
		}
		if(canPour(2, 0, action))	//3倒1
		{
			Action tem = new Action();	//临时存储，一会儿还要变回去
			copy(tem,action);
			//倒水
			pour(2, 0, action);
			fun(action);
			action=tem;
		}
		if(canPour(2, 1, action))	//1倒2
		{
			Action tem = new Action();	//临时存储，一会儿还要变回去
			copy(tem,action);
			//倒水
			pour(2, 1, action);
			fun(action);
			action=tem;
		}
		
		
	}
	
	//----------------------------------------------------------------
	
	
	
	
	//判断两数组值是否相等
	public static boolean isequal(int[] a1 ,int[] a2){
		if(a1.length!=a2.length)
			return false;
		for (int i = 0; i < a2.length; i++) {
			if(a1[i]!=a2[i])
				return false;
		}
		return true;
	}

	
	//判断是否能倒水,不考虑是否存在以前倒过的情况
	public static boolean canPour(int from, int to,Action action){
		/*
		 * to水桶满：false
		 * from水桶空：false
		 * from水桶水+to水桶水>to水桶量：false
		 * from=to不能倒
		 */
		if(from==to)
			return false;
		if(action.bucket[from]==0)
			return false;
		if(action.bucket[to]==rule[to])
			return false;
//		if(action.bucket[from]+action.bucket[to]>rule[to])
//			return false;
		return true;
	}
	
	//倒水
	public static void pour(int from,int to,Action action){
		//分两种，多倒少，少倒多
		int remain = rule[to]-action.bucket[to];
		if(action.bucket[from]>=remain){		//是否多倒少
			action.bucket[from]-=remain;
			action.bucket[to]=rule[to];
		}else{	//少倒多情况
			action.bucket[to]+=action.bucket[from];
			action.bucket[from]=0;
		}
	}
	
	//把action给tem
	public static void copy(Action tem,Action action){
		for (int i = 0; i < action.bucket.length; i++) {
			tem.bucket[i]=action.bucket[i];
		}
		for (int[] r : action.rout) {
			int[] t = new int[3];
			for (int i = 0; i < r.length; i++) {
				t[i]=r[i];
			}
			tem.rout.add(t);
		}
	}
	
	
	
}


class Action{
	//定义属性，并初始化
	int[] bucket = {8,0,0};
	List<int[]> rout = new ArrayList<>();
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+bucket[0]+bucket[1]+bucket[2]+"]";
	}
}
