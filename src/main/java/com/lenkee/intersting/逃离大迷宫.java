package com.lenkee.intersting;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by amettursun on 2019/7/5.
 */
public class 逃离大迷宫 {
    /**
     * 在一个 10^6 x 10^6 的网格中，每个网格块的坐标为 (x, y)，其中 0 <= x, y < 10^6。
     我们从源方格 source 开始出发，意图赶往目标方格 target。每次移动，我们都可以走到网格中在四个方向上相邻的方格，只要该方格不在给出的封锁列表 blocked 上。
     只有在可以通过一系列的移动到达目标方格时才返回 true。否则，返回 false。
     */
    // 记录行走路径
    public static Stack<int[]> trace = new Stack<>();
    // 设置棋盘大小6x6
    public  static int scope = 6;
    public static void main(String[] args) {
        // 设置棋盘阵型
        int[][] blocked = {{3,2},{4,2},{5,3},{4,4},{2,5}};
        // 起点
        int[] source = new int[]{0,0};
        //终点
        int[] target = new int[]{5,5};
        System.out.println("^ v ^");
        // 判断是否走得通
        boolean escapePossible = isEscapePossible(blocked, source, target);
        System.out.println( escapePossible);
        if (escapePossible){
            trace.stream().forEach(t->{
                System.out.print("{"+t[0]+","+t[1]+"}-->");
            });
        }

    }


    public static Map<String, Integer> dealData(int[][]blocked){
        Map<String, Integer> graph= new HashMap<>();
        for (int i = 0; i < blocked.length; i++) {
            int x = blocked[i][0];
            int y = blocked[i][1];
            String key = x+""+y;
            graph.put(key,1);
        }
        return graph;
    }
    // 判断是否超出范围
    public static boolean outOfScope(int x, int y,Map<String, Integer> graph){
        return x>=0 && y>=0 && x<scope && y<scope && getGraph(graph,x+""+y)==0;
    }

    public static boolean isEscapePossible(int[][] blocked, int[] source, int[] target){
//        Stack<int[]> trace = new Stack<>();
        Stack<int[]> trace2 = new Stack<>();
        // 正向和逆向，都走一遍
        return isEscapePossible2(blocked, source, target, trace) && isEscapePossible2(blocked, target, source, trace2);
    }

    public static boolean isEscapePossible2(int[][] blocked, int[] source, int[] target, Stack<int[]> trace){
        Map<String, Integer> graph = dealData(blocked);
        // 上下左右轮询，一直到taget和source是同一个点为止
        trace.add(source.clone());
        while (true){
            int x = source[0];
            int y = source[1];
            // 出口
            if (x == target[0] && y==target[1]){
                return true;
            }
            if (trace != null && trace.size() > 19900)
                return true;
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
    public static boolean goAhead(Map<String, Integer> graph,int[]source, int director){
        // 1.上 2.下 3.左 4.右
        int x = source[0];
        int y = source[1];
        switch (director){
            case 1:{
                if (outOfScope(x-1, y,graph)){
                    blockGraph(graph,source,1);
                    source[0] = x-1;
                    source[1] = y;
                    return true;
                }else
                    return false;
            }
            case 2:{
                if (outOfScope(x+1, y,graph)){
                    blockGraph(graph,source,1);
                    source[0] = x+1;
                    source[1] = y;
                    return true;
                }else
                    return false;
            }
            case 3:{
                if (outOfScope(x, y-1,graph)){
                    blockGraph(graph,source,1);
                    source[0] = x;
                    source[1] = y-1;
                    return true;
                }else
                    return false;
            }
            case 4:{
                if (outOfScope(x, y+1,graph)){
                    blockGraph(graph,source,1);
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
    public static int[] rollBack(Stack<int[]> stack, Map<String, Integer> graph){
        try {
            int[] pop = stack.pop();
            int x = pop[0];
            int y = pop[1];
            blockGraph(graph,pop,1);
            return stack.lastElement();
        }catch (Exception e){
            return null;
        }
    }
    // 打开该图格子0，或block该格子1
    public static void blockGraph(Map<String, Integer> graph, int[] position, int action){
        String key = position[0] +""+ position[1];
        if (action == 0){
            graph.put(key,0);
        }
        else {
            graph.put(key,1);
        }
    }
    public static int getGraph(Map<String, Integer> graph, String key){
        Integer integer = graph.get(key);
        if (integer != null)
            return integer;
        else return 0;
    }
}