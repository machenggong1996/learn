package suanfa2024;

import java.util.Arrays;

/**
 * 三角形最大周长
 * https://leetcode.cn/problems/largest-perimeter-triangle/
 *
 *
 * @author machenggong
 * @since 2024/10/17
 */
public class Triangle {

    // 贪心算法
    public static int largestPerimeter(int[] nums) {
        // 从小到大排序
        Arrays.sort(nums);
        // 从后往前遍历
        for (int i = nums.length - 1; i >= 2; --i) {
            // 如果能组成三角形，则返回
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i] + nums[i - 1] + nums[i - 2];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(largestPerimeter(new int[]{2, 1, 1}));
    }


}
