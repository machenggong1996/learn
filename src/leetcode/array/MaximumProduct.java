package leetcode.array;

import java.util.Arrays;

/**
 * @author machenggong
 * @date 2021/1/20
 * @description
 */
public class MaximumProduct {

    /**
     * 628. 三个数的最大乘积
     *
     * @param nums
     * @return
     */
    public static int maximumProduct(int[] nums) {
        // 最小的和第二小的
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        // 最大的、第二大的和第三大的
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int x : nums) {
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min2 = x;
            }

            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
            } else if (x > max3) {
                max3 = x;
            }
        }

        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }

    public static int maximumProduct1(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 3] * nums[n - 2] * nums[n - 1]);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-100, -98, -1, 1, 2, 5};
        System.out.println(maximumProduct(nums));
        System.out.println(maximumProduct1(nums));
    }


}
