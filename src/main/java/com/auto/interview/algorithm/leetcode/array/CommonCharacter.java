package com.auto.interview.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : jihai
 * @date : 2020/10/14
 * @description :
 * 1002. 查找常用字符
 *
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 *
 * 你可以按任意顺序返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 * 示例 2：
 *
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] 是小写字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-common-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CommonCharacter {

    public List<String> commonChars(String[] A) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : A[0].toCharArray()) {
            if (map.get(c) != null) continue;
            for (int i = 0;i < A.length;i ++) {
                int count = 0;
                for (int j = 0;j < A[i].length();j ++) {
                    if (A[i].charAt(j) == c) count ++;
                }
                if (count > 0) {
                    if (i > 1 && map.get(c) == null) continue;
                    if (map.get(c) == null) {
                        map.put(c, count);
                    } else if (map.get(c) > count){
                        map.put(c, count);
                    }
                } else {
                    map.remove(c);
                }
            }
        }

        List<String> retList = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            for (int i = 0;i < entry.getValue();i ++) {
                retList.add(String.valueOf(entry.getKey()));
            }
        }
        return retList;
    }
}
