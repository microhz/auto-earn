package com.auto.interview.algorithm.leetcode.array;

import com.auto.interview.algorithm.leetcode.base.Learn;
import com.auto.interview.algorithm.leetcode.base.Self;
import com.auto.interview.algorithm.leetcode.utils.Assert;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author : jihai
 * @date : 2020/8/11
 * @description :
 *
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 *
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 *
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 */
public class FindDuplicateNumber {

    public static void main(String[] args) {
        FindDuplicateNumber findDuplicateNumber = new FindDuplicateNumber();

        Assert.assertTrue(findDuplicateNumber.findDuplicate5(new int[]{2,5,9,6,9,3,8,9,7,1}) == 9);
        Assert.assertTrue(findDuplicateNumber.findDuplicate5(new int[]{1,3,4,2,2}) == 2);

        Assert.assertTrue(findDuplicateNumber.findDuplicate5(new int[]{3, 1, 3, 4, 2}) == 3);


        Assert.assertTrue(findDuplicateNumber.findDuplicate5(new int[]{5, 0, 0, 0, 0, 5}) == 5);

        Assert.assertTrue(findDuplicateNumber.findDuplicate5(new int[]{1, 2, 3, 4, 4, 5}) == 4);

        Assert.assertTrue(findDuplicateNumber.findDuplicate5(new int[]{3,1,3,4,2}) == 3);

        Assert.assertTrue(findDuplicateNumber.findDuplicate6(new int[]{1,3,4,2,2}) == 2);



    }



    // 1,3,4,2,2
    public int findDuplicate6(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;

    }


    // 1 3 4 2 2
    // 0 1 2 3 4
    public int findDuplicate5(int[] nums) {
        int slow = nums[0], fast = nums[nums[0]];
        while (nums[slow] != nums[fast]) {
            System.out.println("--fast:" + fast + ", ---slow:" + slow);
            slow = nums[slow];
            fast = nums[nums[fast]];

            System.out.println("fast:" + fast + ", slow:" + slow);
        }
        // 相遇
        System.out.println("相遇:" + fast);
        fast = 0;
        while (nums[slow] != nums[fast]) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return nums[slow];
    }

    //
    public int findDuplicate4(int[] nums) {
        int fast = nums[nums[0]], slow = nums[0];
        while(fast != slow) {
            fast = nums[nums[fast]];
            slow = nums[slow];
        }

        fast = 0;
        while(fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }

        return slow;
    }

    /**
     *
     * 一个非常巧妙的算法
     * 遍历一次，给每个值取模n得到的位置的值+n
     * 第二次遍历大于2n的位置就是该数字
     */
    @Learn
    public int findDuplicate3(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[nums[i] % n] += n;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 2 * n) return i;
        }
        return -1;
    }

    /**
     * 用一个哈希表存储已经遍历过的
     *
     * 时间复杂度 n
     * 空间复杂度 n
     */
    @Self
    public int findDuplicate2(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return -1;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) {
                return i;
            }
            set.add(i);
        }
        return -1;
    }

    /**
     * 定义两个指针，一个走快，一个走慢
     */
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        Random random = new Random();
        while (true) {
            if (slow >= nums.length) {
                slow = 0;
                continue;
            }
            if (fast >= nums.length) {
                fast = 0;
                continue;
            }
            if (nums[slow] == nums[fast] && slow != fast) {
                return nums[slow];
            }
            slow ++;
            fast += random.nextInt(nums.length);
        }
    }

}
