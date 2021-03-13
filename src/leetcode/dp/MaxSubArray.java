package leetcode.dp;

/**
 * Created by machenggong on 2020/3/15.
 */
public class MaxSubArray {

    /**
     * 最大连续子数组的和
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = nums[0];
        int max = nums[0];

        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static int maxSubArray1(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0)
                sum += num;
            else
                sum = num;
            res = Math.max(res, sum);
        }
        return res;
    }

    public static int maxSubArray2(int[] nums) {
        int res = nums[0];
        for(int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            res = Math.max(res, nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] arr2 = {-1,2};
        System.out.println(maxSubArray(arr1));
    }
}
