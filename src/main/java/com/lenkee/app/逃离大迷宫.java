package com.lenkee.app;

import java.util.NoSuchElementException;
import java.util.Stack;

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
    private static final int scope = 100000; // 地图长宽
    public static Stack<int[]> trace = new Stack<>();
    public static void main(String[] args) {
        int[][] blocked = {{0,2},{1,0},{1,3},{2,1},{2,3},{1,0},{1,0},{3,0}};
        int[][] graph = dealData(blocked);
        int[] source = new int[]{0,0};
        int[] target = new int[]{2,4};

        System.out.println(isEscapePossible2(graph, source, target));

    }


    public static int[][] dealData(int[][]blocked){
        int[][] graph = new int[scope][scope];
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

    public static boolean isEscapePossible2(int[][] graph, int[] source, int[] target){
        // 上下左右轮询，一直到taget和source是同一个点为止
        trace.add(source.clone());
        while (true){
            int x = source[0];
            int y = source[1];
            System.out.print(x+" "+y+"-->");
            // 出口
            if (x == target[0] && y==target[1]){
                return true;
            }
            // 上
            if (goAhead(graph,source, 1)){
                // 移动成功，将路径加入到stack里面, 移动source
                trace.add(source.clone());
                continue;
            }else if (goAhead(graph,source, 2)){
                // 下
                trace.add(source.clone());
                continue;
            }else if (goAhead(graph,source, 3)){
                // 左
                trace.add(source.clone());
                continue;
            }else if (goAhead(graph,source, 4)){
                // 右
                trace.add(source.clone());
                continue;
            }else {
                // 失败，回撤
                int[] ints = rollBack(trace, graph);
                if (ints != null)
                    source = ints.clone();
                else
                    source = null;
            }
            // 退回到起点, 走投无路
            if (trace.size() ==0 && source == null){
                return false;
            }

        }

    }

    // 如果能走，则走到下一步，返回true，否则返回false
    public static boolean goAhead(int[][]graph,int[]source, int director){
        // 1.上 2.下 3.左 4.右
        int x = source[0];
        int y = source[1];
        switch (director){
            case 1:{
                if (outOfScope(x-1, y,graph)){
                    graph[x][y] = 1;
                    source[0] = x-1;
                    source[1] = y;
                    return true;
                }else
                    return false;
            }
            case 2:{
                if (outOfScope(x+1, y,graph)){
                    graph[x][y] = 1;
                    source[0] = x+1;
                    source[1] = y;
                    return true;
                }else
                    return false;
            }
            case 3:{
                if (outOfScope(x, y-1,graph)){
                    graph[x][y] = 1;
                    source[0] = x;
                    source[1] = y-1;
                    return true;
                }else
                    return false;
            }
            case 4:{
                if (outOfScope(x, y+1,graph)){
                    graph[x][y] = 1;
                    source[0] = x;
                    source[1] = y+1;
                    return true;
                }else
                    return false;
            }
            default:return false;
        }
    }

    // 返回source位置
    public static int[] rollBack(Stack<int[]> stack, int[][]graph){
        try {
            int[] pop = stack.pop();
            int x = pop[0];
            int y = pop[1];
            graph[x][y] = 1;
            return stack.lastElement();
        }catch (NoSuchElementException e){
            return null;
        }
    }
}
