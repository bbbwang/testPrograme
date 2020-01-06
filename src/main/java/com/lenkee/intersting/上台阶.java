package com.lenkee.intersting;

public class 上台阶 {

	/*
	 * 有一段楼梯台阶有15级台阶，以小明的脚力一步最多只能跨3级，
	 * 请问小明登上这段楼梯有多少种不同的走法?()
	 */
	public static void main(String[] args) {
		fun(15);
		System.out.println(steps);

	}
	public static int steps=0;//计入次数
	public static void fun(int num){
		if(num<=0)
			{
				steps++;
				return;
			}
		//遍历三种上台阶情况
		for (int i = 1; i <= 3; i++) {
			if(num-i>=0){
				fun(num-i);
			}
		}
	}
}
