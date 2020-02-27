package com.lenkee.intersting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amettursun on 2020/1/5.
 * 力扣团队买了一个可编程机器人，机器人初始位置在原点(0, 0)。小伙伴事先给机器人输入一串指令command，机器人就会无限循环这条指令的步骤进行移动。指令有两种：
 * U: 向y轴正方向移动一格
 * R: 向x轴正方向移动一格。
 * 不幸的是，在 xy 平面上还有一些障碍物，他们的坐标用obstacles表示。机器人一旦碰到障碍物就会被损毁。
 * 给定终点坐标(x, y)，返回机器人能否完好地到达终点。如果能，返回true；否则返回false。
 * <p>
 * 输入：command = "URR", obstacles = [], x = 3, y = 2
 * 输出：true
 * 解释：U(0, 1) -> R(1, 1) -> R(2, 1) -> U(2, 2) -> R(3, 2)。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/programmable-robot
 */
public class 机器人大冒险 {
    public  void main(String[] args) {
        String command = "URR";
        int[][]obstacles = {};
        int x = 3,y=2;
//        List<int[]> neiberNodes = getNeiberNodes(command, 3, 2);
        System.out.println(robot(command,obstacles,x,y));
    }

    public  boolean robot(String command, int[][] obstacles, int x, int y) {
        // 判断有无障碍物
        for (int i = 0; i < obstacles.length; i++) {
            int ox = obstacles[i][0];
            int oy = obstacles[i][1];
            if (getRouteNodes(command,ox,oy) && ox<=x && oy<=y)
                return false;
        }
        // 判断能否落到终点
        int xs = getCommandXsOrYs(command, 0);
        int ys = getCommandXsOrYs(command, 1);
        int resultx = x/xs;
        int resulty = y/ys;

        return contain(getNeiberNodes(command,xs*resultx, ys*resultx),x,y) || contain(getNeiberNodes(command,xs*resulty, ys*resulty),x,y);
    }

    public  boolean getRouteNodes(String command, int tx, int ty) {
        int xs = getCommandXsOrYs(command, 0);  // 获取x个数
        int ys = getCommandXsOrYs(command, 1);  // 获取y个数
        List<int[]> list = new ArrayList<>();
        // 从x轴判断附近的点
        int resultx = tx/xs;  // 计算目标点与一个x周期的倍数
        // 减一附近
        List<int[]> neiberNodes = getNeiberNodes(command, xs * (resultx ), ys * (resultx ));
        // 加一附近
        List<int[]> neiberNodes2 = getNeiberNodes(command, xs * (resultx + 1), ys * (resultx + 1));

        // 从y轴判断附近d点
        // 减一附近
        int resulty = ty/ys;
        List<int[]> neiberNodesy = getNeiberNodes(command, xs * (resulty ), ys * (resulty ));
        // 加一附近
        List<int[]> neiberNodesy2 = getNeiberNodes(command, xs * (resulty + 1), ys * (resulty + 1));
        list.addAll(neiberNodes);list.addAll(neiberNodes2);list.addAll(neiberNodesy);list.addAll(neiberNodesy2);
        return contain(list,tx,ty);
    }

    public  int getCommandXsOrYs(String command, int type) {
        int nums = 0;
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'R')
                nums++;
        }
        if (type == 0)
            return nums;
        return command.length() - nums;
    }

    public  List<int[]> getNeiberNodes(String command, int tx,int ty){
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{tx,ty});
        for (int i = 0; i < command.length(); i++) {
         if (command.charAt(i)=='R'){
             tx = tx+1;
         }else {
             ty = ty+1;
         }
            list.add(new int[]{tx,ty});
        }
        return list;
    }
    public  boolean contain(List<int[]> list, int x, int y){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)[0]==x && list.get(i)[1]==y)
                return true;
        }
        return false;
    }
}
