package com.lenkee.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amettursun on 2019/7/5.
 */
public class 逃离大迷宫 {
    /**
     * 在一个 10^6 x 10^6 的网格中，每个网格块的坐标为 (x, y)，其中 0 <= x, y < 10^6。
     我们从源方格 source 开始出发，意图赶往目标方格 target。每次移动，我们都可以走到网格中在四个方向上相邻的方格，只要该方格不在给出的封锁列表 blocked 上。
     只有在可以通过一系列的移动到达目标方格时才返回 true。否则，返回 false。

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/escape-a-large-maze
     */
    public static void main(String[] args) {
        int[][] blocked = {{1,0},{1,3},{2,1},{2,2}};
        int[][] graph = dealData(blocked);
        int[] source = new int[]{0,0};
        int[] target = new int[]{6,2};
        
        System.out.println(isEscapePossible(graph, source, target));
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length; j++) {
                System.out.print(list.get(i)[j]+" ");
            }
            System.out.print("->");
        }
    }
    public static List<int[]> list = new ArrayList<>();

    public static boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        int x = source[0];
        int y = source[1];
        // 出口
        if (x == target[0] && y==target[1]){
            list.add(new int[]{x,y});
            return true;
        }

        // 上下左右移动
        // 上
        if (outOfScope(x-1, y,blocked)){
            blocked[x][y] = 1;  // 把当前的状态block
            list.add(new int[]{x,y}); // 记录路径
            // 跳到下一步
            source[0] = x-1;
            source[1] = y;
            if (!isEscapePossible(blocked, source, target)){
                // 跳回来
                list.remove(list.size()-1); //删除路径
                source[0] = x;
                source[1] = y;
            }
        }
        // 下
        if (outOfScope(x+1, y,blocked)){
            blocked[x][y] = 1;  // 把当前的状态block
            list.add(new int[]{x,y}); // 记录路径
            // 跳到下一步
            source[0] = x+1;
            source[1] = y;
            if (!isEscapePossible(blocked, source, target)){
                // 跳回来
                list.remove(list.size()-1); //删除路径
                source[0] = x;
                source[1] = y;
            }else
                return true;
        }
        // 左边

        if (outOfScope(x, y-1,blocked)){
            blocked[x][y] = 1;  // 把当前的状态block
            list.add(new int[]{x,y}); // 记录路径
            // 跳到下一步
            source[0] = x;
            source[1] = y-1;
            if (!isEscapePossible(blocked, source, target)){
                list.remove(list.size()-1); //删除路径
                // 跳回来
                source[0] = x;
                source[1] = y;
            }else
                return true;
        }
        // 右边

        if (outOfScope(x, y+1,blocked)){
            blocked[x][y] = 1;  // 把当前的状态block
            list.add(new int[]{x,y}); // 记录路径
            // 跳到下一步
            source[0] = x;
            source[1] = y+1;
            if (!isEscapePossible(blocked, source, target)){
                list.remove(list.size()-1); //删除路径
                // 跳回来
                source[0] = x;
                source[1] = y;
            }else
                return true;
        }


        return false;
    }
    
    public static int[][] dealData(int[][]blocked){
        int[][] graph = new int[10][10];
        for (int i = 0; i < blocked.length; i++) {
            int x = blocked[i][0];
            int y = blocked[i][1];
            graph[x][y] = 1;
        }
        return graph;
    }

    public static boolean outOfScope(int x, int y, int[][] blocked){
        return x>=0 && y>=0 && x<blocked.length && y<blocked[0].length && blocked[x][y]==0;
    }

}
