package com.lenkee.intersting;

/**
 * Created by amettursun on 2019/7/15.
 */
public class 矩阵中的幻方 {
    /**
     * 3 x 3 的幻方是一个填充有从 1 到 9 的不同数字的 3 x 3 矩阵，其中每行，每列以及两条对角线上的各数之和都相等。

     给定一个由整数组成的 grid，其中有多少个 3 × 3 的 “幻方” 子矩阵？（每个子矩阵都是连续的

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/magic-squares-in-grid
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int grid[][] = {{3,2,9,2,7},{6,1,8,4,2},{7,5,3,2,7},{2,9,4,9,6},{4,3,8,2,5}};
        System.out.println(numMagicSquaresInside(grid));
    }
    public static int numMagicSquaresInside(int[][] grid) {

        try {
            // 判断数字是否唯一
            int temp[] = new int[9];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[grid[i][j]-1] = 1;
                }
            }
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] != 1)
                    return 0;
            }
            int sum = grid[0][0] + grid[1][1] + grid[2][2];
            if ((grid[2][0]+grid[1][1]+grid[0][2] != sum))
                return 0;
            // 列
            for (int i = 0; i < 3; i++) {
                if ((grid[i][0]+grid[i][1]+grid[i][2]) != sum)
                    return 0;
            }
            // 横
            for (int i = 0; i < 3; i++) {
                if ((grid[0][i]+grid[1][i]+grid[2][i]) != sum)
                    return 0;
            }
            return 1;
        }catch (Exception e){
            return 0;
        }
    }
}
