package com.auto.interview.algorithm.leetcode.array;

import com.auto.interview.algorithm.leetcode.utils.Assert;

import java.util.*;

/**
 * @author : jihai
 * @date : 2020/8/23
 * @description :
 */
public class MiniArray {

    public static void main(String[] args) {
        MiniArray miniArray = new MiniArray();

        Assert.assertTrue(miniArray.minPathSum(new int[][]{
                {1,3,1},
                {1,5,1},
                {4,2,1}
        }) == 7);

        Assert.assertTrue(miniArray.minPathSum(new int[][]{
                {0}
        }) == 0);

    }

    /**
     * 分治法递归+备忘录
     */
    private Map<String, Integer> map;
    public int minPathSum(int[][] grid) {
        map = new HashMap<>();
        return getMinPath(grid, 0, 0);
    }

    public int getMinPath(int[][] grid, int x, int y) {
        if (map.get(x + "_" + y) != null) {
            return map.get(x + "_" + y);
        }
        int ret = 0;
        if (x == grid.length - 1 && y == grid[0].length - 1) {
            ret = grid[x][y];
            map.put(x + "_" + y, ret);
            return ret;
        }
        if (x < grid.length - 1 && y < grid[0].length - 1) {
            ret = Math.min(getMinPath(grid, x + 1, y) + grid[x][y], getMinPath(grid, x, y + 1) + grid[x][y]);
            map.put(x + "_" + y, ret);
            return ret;
        }
        if (x == grid.length - 1) {
            ret = getMinPath(grid, x, y + 1) + grid[x][y];
            map.put(x + "_" + y, ret);
            return ret;
        }
        if (y == grid[0].length - 1) {
            ret = getMinPath(grid, x + 1, y) + grid[x][y];
            map.put(x + "_" + y, ret);
            return ret;
        }
        return ret;
    }

    /**
     * dp 算法
     * dp[i][j]表示第i,j的位置最小值
     dp[i][j] = min(dp[i - 1][j] + grid[i][j], dp[i][j - 1] + grid[i][j])
     用例
     */
    /**
     * 动态规划
     dp[i][j] 代表 i j最短路径
     dp[i][j] = min(dp[i - 1][j] + dp[i][j], dp[i][j - 1] + dp[i][j])
     */
    public int minPathSum2(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int[][] dp = new int[grid.length][grid[0].length];
        int sum1 = 0;
        for (int i = 0;i < grid.length;i ++) {
            sum1 += grid[i][0];
            dp[i][0] = sum1;
        }
        sum1 = 0;
        for (int j = 0;j < grid[0].length;j ++) {
            sum1 += grid[0][j];
            dp[0][j] = sum1;
        }

        for (int i = 1;i < grid.length;i ++) {
            for (int j = 1;j < grid[0].length;j ++) {
                dp[i][j] = Math.min(dp[i - 1][j] + grid[i][j], dp[i][j - 1] + grid[i][j]);
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }
}
