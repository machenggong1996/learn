package leetcode.dp;

/**
 * @author machenggong
 * @date 2020/12/21
 * @description
 */
public class MinCostClimbingStairs {

    /**
     * 使用最小花费爬楼梯
     * 动态规划
     * 假设数组 cost 的长度为 n，则 n 个阶梯分别对应下标 0 到 n-1，楼层顶部对应下标 n，问题等价于计算达到下标 n 的最小花费。可以通过动态规划求解。
     * <p>
     * 创建长度为 n+1 的数组 dp，其中 dp[i] 表示达到下标 i 的最小花费。
     *
     * @param cost
     * @return
     */
    public static int minCostClimbingStairs1(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }

    public static int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int prev = 0, curr = 0;
        for (int i = 2; i <= n; i++) {
            int next = Math.min(curr + cost[i - 1], prev + cost[i - 2]);
            prev = curr;
            curr = next;
        }
        return curr;
    }

    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs1(new int[]{10, 15, 20}));
    }

}
