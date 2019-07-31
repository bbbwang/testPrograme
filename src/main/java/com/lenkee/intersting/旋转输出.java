package com.lenkee.intersting;

public class 旋转输出 {

	public static void main(String[] args) {

		int[][] arr = new int[6][8];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j]=i*arr[0].length+j+1;
			}
		}
		fun(arr);
		
		
		
	}
	
	
	//旋转输出数组值，从arr[0][0]开始 
	public static void fun(int[][] arr){
		//定义走向方向0,1,2,3带表：右，下，左，上
		int direc=0,x=0,y=0;
		//判断是否已经走过
		int[][] check = new int[arr.length][arr[0].length];
		for (int i = 0; i < arr.length*arr[0].length; i++) {
			//走下一步,判断方向，标记走过的路
			System.out.print(arr[x][y]+"  ");
			check[x][y]=1;
			//判断接下来忘哪儿走,首先确定你走的哪个方向
			if(direc==0){	//如果右方向
				if((y+1)==arr[0].length || check[x][y+1]==1)	//如果碰到墙就转方向
					direc++;
			}else if(direc==1){
				if((x+1)==arr.length || check[x+1][y]==1)	//如果碰到墙就转方向
					direc++;
			}else if(direc==2){
				if((y-1)<0 || check[x][y-1]==1)	//如果碰到墙就转方向
					direc++;
			}else if(direc==3){
				if((x-1)<0 || check[x-1][y]==1)	//如果碰到墙就转方向
					direc=0;
			}
			switch(direc){		//跟着方向，前进一步
			case 0: y++;break;
			case 1: x++;break;
			case 2: y--;break;
			case 3:x--;break;
			default:break;
			}
			
		}
	}

}
