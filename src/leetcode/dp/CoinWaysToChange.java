package leetcode.dp;

/**
 * @author machenggong
 * @date 2020/04/23
 */
public class CoinWaysToChange {

    /**
     * 硬币面值问题
     *
     * @param n
     * @return
     */
    public static int waysToChange(int n) {
        final int MOD = 1000000007;
        int[] coins = new int[] { 1, 5, 10, 25 };
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= n; j++) {
                dp[j] += dp[j - coins[i]];
                dp[j] %= MOD;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(waysToChange(11));
    }

}
