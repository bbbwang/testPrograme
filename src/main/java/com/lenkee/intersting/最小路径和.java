package com.lenkee.intersting;

/**
 * Created by amettursun on 2019/8/23.
 */
public class 最小路径和 {
    /**
     * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     说明：每次只能向下或者向右移动一步。

     示例:
     输入:
     [
       [1,3,1],
     [1,5,1],
     [4,2,1]
     ]
     输出: 7
     解释: 因为路径 1→3→1→1→1 的总和最小。

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/minimum-path-sum
     */
    public void main(String[] args) {
        int[][] grid = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        System.out.println(minPathSum(grid));
    }

    public int minPathSum(int[][] grid) {
        // 出口，不能像右，不能向下
        return goAhead(grid,0,0);
    }

    public int goAhead(int[][] grid, int x, int y){
        if (!hasNext(grid, 0, x, y) && !hasNext(grid, 1, x, y)){
            return grid[x][y];
        }
        if (!hasNext(grid, 0, x, y)){
            return goAhead(grid, x, y+1) + grid[x][y];
        }
        if (!hasNext(grid, 1, x, y)){
            return goAhead(grid, x + 1, y) + grid[x][y];
        }
        return goAhead(grid, x, y+1)<goAhead(grid, x + 1, y)?goAhead(grid, x, y+1)+grid[x][y]:goAhead(grid, x + 1, y)+grid[x][y];
    }

    /**
     * 判断是否可以下一步
     * @param director 0是向下，1是向右
     */
    public boolean hasNext(int[][] grid, int director,int x, int y){
        try{
            if (director == 0){
              return grid.length>(x+1);
            }
            else
                return grid[0].length>(y+1);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
