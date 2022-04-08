package leetcode.offer;

import java.util.Arrays;

/**
 * @author machenggong
 * @date 2021/3/13
 * @description
 */
public class TwoSum {

    /**
     * 剑指 Offer 57. 和为s的两个数字
     *
     * @param nums   排序数组
     * @param target 目标结果
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int s = nums[i] + nums[j];
            if (s < target) {
                i++;
            } else if (s > target) {
                j--;
            } else {
                return new int[]{nums[i], nums[j]};
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSum(nums, 9)));
    }

}
