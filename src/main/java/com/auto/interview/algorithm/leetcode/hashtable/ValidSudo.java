package com.auto.interview.algorithm.leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : jihai
 * @date : 2020/9/15
 * @description :
 *
 * 36. 有效的数独
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 *
 * 上图是一个部分填充的有效的数独。
 *
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   ["5","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: true
 * 示例 2:
 *
 * 输入:
 * [
 *   ["8","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: false
 * 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
 *      但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 * 说明:
 *
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 给定数独序列只包含数字 1-9 和字符 '.' 。
 * 给定数独永远是 9x9 形式的。
 */
public class ValidSudo {

    /**
     * 自己写的
     */
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Map<Character, Boolean>> rowMap = new HashMap<>();
        Map<Integer, Map<Character, Boolean>> listMap = new HashMap<>();
        Map<Integer, Map<Character, Boolean>> boxMap = new HashMap<>();
        for (int i = 0;i < board.length;i ++) {
            for (int j = 0;j < board[0].length;j ++) {
                char c = board[i][j];
                if (board[i][j] == '.') continue;
                Map<Character, Boolean> rMap = rowMap.getOrDefault(i, new HashMap<>());
                if (rMap.getOrDefault(c, false)) return false;
                rMap.put(c, true);
                rowMap.put(i, rMap);

                Map<Character, Boolean> lMap = listMap.getOrDefault(j, new HashMap<>());
                if (lMap.getOrDefault(c, false)) return false;
                lMap.put(c, true);
                listMap.put(j, lMap);

                int boxIndex = (i / 3) * 3 + j / 3;
                Map<Character, Boolean> bMap = boxMap.getOrDefault(boxIndex, new HashMap<>());
                if (bMap.getOrDefault(c, false)) return false;
                bMap.put(c, true);
                boxMap.put(boxIndex, bMap);
            }
        }
        return true;
    }


    /**
     * 官方很好的思路
     */
    public boolean isValidSudoku2(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] list = new boolean[9][9];
        boolean[][] box = new boolean[9][9];
        for (int i = 0;i < board.length;i ++) {
            for (int j = 0;j < board[0].length;j ++) {
                if (board[i][j] == '.') continue;
                int boxIndex = i / 3 * 3 + j / 3;
                int num = board[i][j] - '1';
                if (row[i][num] || list[j][num] || box[boxIndex][num]) return false;
                row[i][num] = true;
                list[j][num] = true;
                box[boxIndex][num] = true;
            }
        }
        return true;
    }
}
