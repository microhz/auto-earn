package com.auto.interview.algorithm.leetcode.string;

/**
 * @author : jihai
 * @date : 2020/9/13
 * @description :
 *
 * 79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 *
 * 提示：
 *
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 */
public class WordSearch {


    /**
     * [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
     * "ABCB"
     * @param args
     */
    public static void main(String[] args) {
        WordSearch wordSearch = new WordSearch();
//        wordSearch.exist(new char[][]{
//                {'A','B','C','E'},
//                {'S','F','C','S'},
//                {'A','D','E','E'}
//        }, "ABCCED");
        System.out.println("asv".startsWith(""));
    }


    public boolean exist(char[][] board, String word) {
        for (int i = 0;i < board.length;i ++) {
            for (int j = 0;j < board[0].length;j ++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                if (dfs(i, j, board, word, "", visited)) {
                    System.out.println("ret true");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(int i, int j, char[][] board, String word, String str, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;
        if (visited[i][j]) return false;
        visited[i][j] = true;
        if (! word.startsWith(str)) {
            visited[i][j] = false;
            return false;
        }
        str += board[i][j];
        if (str.equals(word)) {
            System.out.println("true");
            return true;
        }
        int[][] nextStep = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        for (int[] step : nextStep) {
            if (dfs(i + step[0], j + step[1], board, word, str, visited)) {
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }
}
