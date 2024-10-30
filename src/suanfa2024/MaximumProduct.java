package suanfa2024;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/maximum-product-of-three-numbers/
 * 数组中三个数的最大乘积
 *
 * @author machenggong
 * @since 2024/10/11
 */
public class MaximumProduct {

    // 线性扫描
    public static int maximumProduct(int[] nums) {
        // 找出两个最小的 三个最大的
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int num : nums){
            if (num < min1){
                min2 = min1;
                min1 = num;
            }else if (num < min2){
                min2 = num;
            }
            if (num > max1){
                max3 = max2;
                max2 = max1;
                max1 = num;
            }else if (num > max2){
                max3 = max2;
                max2 = num;
            }else if (num > max3){
                max3 = num;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }

    // 排序算法解决
    public static int maximumProduct1(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        // 去前两个负数 或者 最后三个正数
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 1] * nums[n - 2] * nums[n - 3]);
    }

    public static void main(String[] args) {
        System.out.println(maximumProduct(new int[]{1, 2, 3}));
    }

}
