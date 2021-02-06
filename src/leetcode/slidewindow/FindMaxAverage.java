package leetcode.slidewindow;

/**
 * @author machenggong
 * @date 2021/2/4
 * @description
 */
public class FindMaxAverage {

    /**
     * 643. 子数组最大平均数 I
     *
     * @param nums
     * @param k
     * @return
     */
    public static double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i = k; i < n; i++) {
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return 1.0 * maxSum / k;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 12, -5, -6, 50, 3};
        System.out.println(findMaxAverage(nums, 4));
    }

}
