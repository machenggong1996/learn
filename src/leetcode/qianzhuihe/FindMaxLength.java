package leetcode.qianzhuihe;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/contiguous-array/
 * 525. 连续数组
 * @author machenggong
 * @since 2025/2/13
 */
public class FindMaxLength {

    /**
     *
     * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
     * 求最长的连续子数组，其元素和为 0
     *
     * @param nums
     * @return
     */
    public static int findMaxLength(int[] nums) {
        int maxLength = 0;
        // key数量 value索引
        // key是前缀和 value就是i
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int counter = 0;
        map.put(counter, -1);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num == 1) {
                counter++;
            } else {
                counter--;
            }
            if (map.containsKey(counter)) {
                // 相同的前缀和说明元素个数相同
                // 如果 counter 的值在哈希表中已经存在，则取出 counter 在哈希表中对应的下标 prevIndex
                // nums 从下标 prevIndex+1 到下标 i 的子数组中有相同数量的 0 和 1，该子数组的长度为 i−prevIndex，
                // 使用该子数组的长度更新最长连续子数组的长度；
                int prevIndex = map.get(counter);
                maxLength = Math.max(maxLength, i - prevIndex);
            } else {
                // 如果 counter 的值在哈希表中不存在，则将当前余数和当前下标 i 的键值对存入哈希表中
                map.put(counter, i);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 1, 1, 0};
        System.out.println(findMaxLength(nums));
    }

}
