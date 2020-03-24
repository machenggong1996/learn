package leetcode.dp;

/**
 * Created by machenggong on 2020/3/24.
 */
public class Massage {

    /**
     * 按摩师 动态规划
     * dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])
     *
     * @param nums
     * @return
     */

    public static int massage1(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }

    public static int massage2(int[] nums) {
        int a = 0, b = 0;
        for (int i = 0; i < nums.length; i++) {
            int c = Math.max(b, a + nums[i]);
            a = b;
            b = c;
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(massage1(new int[]{2, 1, 4, 5, 3, 1, 1, 3}));
    }

}
